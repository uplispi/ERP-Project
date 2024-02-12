<template>
  <Layout>
    <Title title="员工管理"> </Title>
    <el-button type="primary" size="medium" @click="dialogVisible = true">
      <el-dialog
        title="添加员工"
        :visible.sync="dialogVisible"
        width="40%"
        :before-close="handleClose"
      >
        <div style="width: 90%; margin: 0 auto">
          <el-form :model="employeeForm" label-width="80px">
            <el-form-item label="姓名" prop="name">
              <el-input type="text" v-model="employeeForm.name"></el-input>
            </el-form-item>
            <el-form-item label="联系电话" prop="phoneNum">
              <el-input type="text" v-model="employeeForm.phoneNum"></el-input>
            </el-form-item>
            <el-form-item label="住址" prop="address">
              <el-input type="text" v-model="employeeForm.address"></el-input>
            </el-form-item>
            <el-form-item label="岗位id" prop="postId">
              <el-input type="text" v-model="employeeForm.postId"></el-input>
            </el-form-item>
            <el-form-item label="银行卡号" prop="bankCard">
              <el-input type="text" v-model="employeeForm.bankCard"></el-input>
            </el-form-item>
            <el-form-item label="入职日期" prop="hiredate">
              <el-input type="date" v-model="employeeForm.hiredate"></el-input>
            </el-form-item>
          </el-form>
        </div>
        <!-- 提交按钮-->
        <span slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm('employeeForm')"
            >立即创建</el-button
          >
        </span> </el-dialog
      >添加员工</el-button
    >

    <!--  显示所有员工-->
    <div class="employee-body" style="margin-top: 10px">
      <el-table
        :data="
          employeeList.filter(
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
        <el-table-column prop="id" label="员工id" width="80"> </el-table-column>
        <el-table-column prop="name" label="姓名" width="80"> </el-table-column>
        <el-table-column prop="phoneNum" label="联系电话" width="120">
        </el-table-column>
        <el-table-column prop="address" label="住址" width="100">
        </el-table-column>
        <el-table-column prop="postId" label="岗位id" width="80">
        </el-table-column>
        <el-table-column prop="postName" label="岗位名称" width="80">
        </el-table-column>
        <el-table-column prop="bankCard" label="银行卡号" width="140">
        </el-table-column>
        <el-table-column prop="hiredate" label="入职日期" width="100">
        </el-table-column>
        <el-table-column>
          <template slot="header">
            <div class="top">
              <div class="left">
                <el-tooltip
                  content="请注意区分大小写，提供员工姓名和id的搜索"
                  effect="dark"
                >
                  <el-input
                    v-model="search"
                    size="mini"
                    placeholder="输入关键字搜索"
                  />
                </el-tooltip>
              </div>
            </div>
          </template>
          <template slot-scope="scope">
            <el-button
              @click="editEmployee(scope.row.id)"
              type="text"
              size="small"
            >
              编辑
            </el-button>
            <el-button
              @click="dropEmployee(scope.row.id)"
              type="text"
              size="small"
            >
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <el-dialog
      title="修改员工信息"
      :visible.sync="editDialogVisible"
      width="30%"
      @close="close()"
    >
      <el-form :model="editForm" :label-width="'100px'" size="mini">
        <el-form-item label="姓 名">
          <el-input
            v-model="editForm.name"
            placeholder="请输入员工姓名"
          ></el-input>
        </el-form-item>
        <el-form-item label="联系电话">
          <el-input
            v-model="editForm.phoneNum"
            type="text"
            placeholder="请输入员工电话号码"
          ></el-input>
        </el-form-item>
        <el-form-item label="住址">
          <el-input
            v-model="editForm.address"
            type="text"
            placeholder="请输入员工住址"
          ></el-input>
        </el-form-item>
        <el-form-item label="岗位">
          <el-input
            v-model="editForm.postId"
            type="text"
            placeholder="请输入员工岗位编号"
          ></el-input>
        </el-form-item>
        <el-form-item label="银行卡号">
          <el-input
            v-model="editForm.bankCard"
            type="text"
            placeholder="请输入员工银行卡号"
          ></el-input>
        </el-form-item>
        <el-form-item label="入职日期">
          <el-input v-model="editForm.hiredate" type="date"></el-input>
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
  createEmployee,
  queryAllEmployee,
  updateEmployee,
  deleteEmployee,
} from "../../network/employee";

export default {
  name: "EmployeeManagement",
  components: { Title, Layout },
  data() {
    return {
      employeeList: [],
      search: "",
      employeeForm: {},
      dialogVisible: false,
      rules: {},
      editForm: {},
      editDialogVisible: false,
    };
  },
  mounted() {
    this.getAllEmployee();
  },
  methods: {
    getAllEmployee() {
      queryAllEmployee({})
        .then((_res) => {
          this.employeeList = _res.result;
        })
        .catch((err) => {
          this.$message({
            type: "error",
            message: "获取员工信息失败！" + err,
          });
        });
      console.log(this.employeeList);
      console.log(this.employeeList.keys());
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
      this.employeeForm = {};
    },

    submitForm() {
      createEmployee(this.employeeForm).then((_res) => {
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
          this.getAllEmployee();
        }
      });
    },

    dropEmployee(id) {
      console.log(id);
      let config = {
        params: {
          id: id,
        },
      };
      this.$confirm("是否要删除该员工？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          deleteEmployee(config).then((_res) => {
            if (_res.msg === "Success") {
              this.$message({
                type: "success",
                message: "删除成功!",
              });
              this.getAllEmployee();
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

    editEmployee(id) {
      this.editForm = JSON.parse(
        JSON.stringify(this.employeeList.filter((x) => x.id === id)[0])
      );
      this.editDialogVisible = true;
    },
    handleEdit(type) {
      if (type === false) {
        this.editDialogVisible = false;
        this.editForm = {};
      } else if (type === true) {
        updateEmployee(this.editForm).then((_res) => {
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
            this.getAllEmployee();
          }
        });
      }
    },
  },
};
</script>

<style scoped>
</style>