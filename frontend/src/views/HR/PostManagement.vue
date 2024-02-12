<template>
  <Layout>
    <Title title="岗位信息管理"></Title>

    <el-button type="primary" size="medium" @click="dialogVisible = true">
      <el-dialog
        title="新增岗位信息"
        :visible.sync="dialogVisible"
        width="30%"
        :before-close="handleClose"
      >
        <div style="width: 90%; margin: 0 auto">
          <el-form :model="postForm" label-width="80px">
            <el-form-item
              label="岗位名称"
              prop="name"
              style="width: 90%; margin-right: 5%"
            >
              <el-input
                type="text"
                v-model="postForm.name"
                placeholder="请输入新增岗位名称"
              ></el-input>
            </el-form-item>
            <el-form-item
              label="基本工资"
              prop="basicWage"
              style="width: 90%; margin-right: 5%"
            >
              <el-input
                type="number"
                v-model.number="postForm.basicWage"
                placeholder="如：3500.00"
                @keydown.native="handleInput"
              ></el-input>
            </el-form-item>
            <el-form-item
              label="岗位工资"
              prop="postWage"
              style="width: 90%; margin-right: 5%"
            >
              <el-input
                type="number"
                v-model.number="postForm.postWage"
                placeholder="如：3500.00"
                @keydown.native="handleInput"
              ></el-input>
            </el-form-item>
            <el-form-item
              label="薪资类型"
              prop="type"
              style="width: 90%; margin-right: 5%"
            >
              <el-select v-model="postForm.type">
                <el-option value="月薪提成制"></el-option>
                <el-option value="月薪固定薪资制"></el-option>
                <el-option value="年薪提成制"></el-option>
                <el-option value="年薪固定薪资制"></el-option>
              </el-select>
            </el-form-item>
          </el-form>
        </div>
        <!-- 提交按钮-->
        <span slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm('postForm')"
            >立即创建</el-button
          >
        </span> </el-dialog
      >新增岗位</el-button
    >

    <!--    显示所有岗位信息-->
    <div class="post-body" style="margin-top: 10px">
      <el-table
        :data="
          postList.filter(
            (data) =>
              !search ||
              data.name.includes(search) ||
              data.id === Number(search)
          )
        "
        stripe
        style="width: 100%"
        :header-cell-style="{ 'text-align': 'center' }"
        :cell-style="{ 'text-align': 'center' }"
      >
        <el-table-column prop="id" label="岗位id" width="150">
        </el-table-column>
        <el-table-column prop="name" label="岗位名称" width="150">
        </el-table-column>
        <el-table-column prop="basicWage" label="基本工资" width="150">
        </el-table-column>
        <el-table-column prop="postWage" label="岗位工资" width="150">
        </el-table-column>
        <el-table-column prop="type" label="岗位薪资类型" width="150">
        </el-table-column>
        <el-table-column>
          <template slot="header">
            <div class="top">
              <div class="left">
                <el-input
                  v-model="search"
                  size="mini"
                  placeholder="输入关键字搜索"
                />
              </div>
            </div>
          </template>
          <template slot-scope="scope">
            <el-button @click="editPost(scope.row.id)" type="text" size="small">
              编辑
            </el-button>
            <el-button @click="dropPost(scope.row.id)" type="text" size="small">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <el-dialog
      title="修改岗位信息"
      :visible.sync="editDialogVisible"
      width="30%"
      @close="close()"
    >
      <el-form :model="editForm" :label-width="'100px'" size="mini">
        <el-form-item
          label="岗位名称"
          prop="name"
          style="width: 90%; margin-right: 5%"
        >
          <el-input
            v-model="editForm.name"
            placeholder="请输入岗位名称"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="基本工资"
          prop="basicWage"
          style="width: 90%; margin-right: 5%"
        >
          <el-input
            v-model.number="editForm.basicWage"
            type="number"
            placeholder="如：3500.00"
            @keydown.native="handleInput"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="岗位工资"
          prop="postWage"
          style="width: 90%; margin-right: 5%"
        >
          <el-input
            v-model.number="editForm.postWage"
            type="number"
            placeholder="如：3500.00"
            @keydown.native="handleInput"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="类型"
          prop="type"
          style="width: 95%; margin-right: 5%"
        >
          <el-select v-model="editForm.type" placeholder="请选择类型">
            <el-option value="月薪提成制"></el-option>
            <el-option value="月薪固定薪资制"></el-option>
            <el-option value="年薪提成制"></el-option>
            <el-option value="年薪固定薪资制"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleEdit(false)">取 消</el-button>
        <el-button type="primary" @click="handleEdit(true)">确 定</el-button>
      </div>
    </el-dialog>
  </Layout>
</template>

<script>
import Layout from "../../components/content/Layout";
import Title from "../../components/content/Title";
import {
  createPost,
  queryAllPost,
  updatePost,
  deletePost,
} from "../../network/post";
import { updateEmployee } from "../../network/employee";

export default {
  name: "PostManagement",
  components: { Title, Layout },
  data() {
    return {
      postList: [],
      classificationList: [],
      postForm: {},
      rules: {},
      search: "",
      dialogVisible: false,
      editForm: {},
      addForm: {
        name: "",
        basicWage: "",
        postWage: "",
        type: "",
      },
      editDialogVisible: false,
    };
  },
  mounted() {
    this.getAllPost();
  },
  methods: {
    handleInput(e) {
      let key = e.key;
      if (
        key === "e" ||
        key === "E" ||
        key === "+" ||
        key === "-" ||
        key === "."
      ) {
        this.$message({
          type: "error",
          message: "请输入数字",
        });
        e.returnValue = false;
        return false;
      }
      return true;
    },
    getAllPost() {
      queryAllPost({})
        .then((_res) => {
          this.postList = _res.result;
        })
        .catch((err) => {
          this.$message({
            type: "error",
            message: "获取岗位信息失败！" + err,
          });
        });
    },

    submitForm() {
      createPost(this.postForm).then((_res) => {
        if (_res.code === "B0001" || _res.code === "B0002") {
          this.$message({
            type: "error",
            message: _res.msg,
          });
        } else {
          this.$message({
            type: "success",
            message: "新增成功!",
          });
          // 清空表格 关闭表单
          this.resetForm();
          this.dialogVisible = false;
          // 重新刷新员工页面
          this.getAllPost();
        }
      });
    },

    handleClose(done) {
      this.$confirm("确认关闭？")
        .then((_) => {
          //清空表格
          this.resetForm();
          done();
        })
        .catch((_) => {});
    },

    resetForm() {
      this.postForm = {};
    },

    editPost(id) {
      this.editForm = JSON.parse(
        JSON.stringify(this.postList.filter((x) => x.id === id)[0])
      );
      this.editDialogVisible = true;
    },

    handleEdit(type) {
      if (type === false) {
        this.editDialogVisible = false;
        this.editForm = {};
      } else if (type === true) {
        updatePost(this.editForm).then((_res) => {
          if (_res.code === "B0003") {
            this.$message({
              type: "error",
              message: _res.msg,
            });
          } else {
            this.$message({
              type: "success",
              message: "修改成功！",
            });
            this.editForm = {};
            this.editDialogVisible = false;
            this.getAllPost();
          }
        });
      }
    },

    dropPost(id) {
      let config = {
        params: {
          id: id,
        },
      };
      this.$confirm("是否删除该岗位信息？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          deletePost(config).then((_res) => {
            if (_res.msg === "Success") {
              this.$message({
                type: "success",
                message: "删除成功！",
              });
              this.getAllPost();
            } else if (_res.code === "B0004" || _res.code === "B0003") {
              this.$message({
                type: "error",
                message: _res.msg,
              });
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    close() {
      this.addForm = {};
      this.editForm = {};
    },
  },
};
</script>

<style scoped>
</style>