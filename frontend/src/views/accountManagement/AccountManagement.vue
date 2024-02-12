<template>
  <Layout>
    <Title title="账户管理"></Title>
    <el-button type="primary" size="medium" @click="dialogVisible = true"
      >添加账户</el-button
    >
    <!-- 搜索框   -->
    <div>
      <el-input
        style="margin-top: 10px"
        v-model="search"
        autosize
        placeholder="输入关键词搜索账户"
      >
      </el-input>
    </div>

    <!--  添加账户的表单    -->
    <el-dialog
      title="添加账户"
      :visible.sync="dialogVisible"
      width="40%"
      :before-close="handleClose"
    >
      <div style="width: 90%; margin: 0 auto">
        <el-form
          :model="accountForm"
          label-width="80px"
          ref="customerForm"
          :rules="rules"
        >
          <el-form-item label="姓名" prop="name">
            <el-input type="text" v-model.trim="accountForm.name"></el-input>
          </el-form-item>
          <el-form-item label="余额" prop="balance">
            <el-input
              type="number"
              v-model.trim="accountForm.balance"
            ></el-input>
          </el-form-item>
        </el-form>
      </div>
      <!-- 提交按钮-->
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm('accountForm')"
          >立即创建</el-button
        >
      </span>
    </el-dialog>

    <!--  显示所有账户  -->
    <div style="margin-top: 10px">
      <el-table
        :data="
          accountList.filter(
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
        <el-table-column prop="id" label="id" width="200"> </el-table-column>
        <el-table-column prop="name" label="姓名" width="200">
        </el-table-column>
        <el-table-column prop="balance" label="余额(元)" width="200">
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              @click.native.prevent="editInfo(scope.row.id)"
              type="text"
              size="small"
            >
              编辑
            </el-button>
            <el-button
              @click.native.prevent="handleDelete(scope.row.id)"
              type="text"
              size="small"
            >
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 编辑账户信息的表单(只能修改名称) -->
    <el-dialog
      title="修改客户信息"
      :visible.sync="editDialogVisible"
      width="30%"
      @close="close()"
    >
      <el-form :model="editForm" :label-width="'100px'" size="mini">
        <el-form-item label="姓名" prop="name">
          <el-input type="text" v-model="editForm.name"></el-input>
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
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
import {
  createAccount,
  deleteAccount,
  showAccount,
  updateAccount,
} from "@/network/account";

export default {
  name: "AccountManagement",
  components: {
    Layout,
    Title,
  },
  data() {
    return {
      dialogVisible: false,
      accountForm: {},
      accountList: [],
      editDialogVisible: false,
      editForm: {},
      search: "", // 搜索的关键词
      rules: {
        name: [{ required: true, message: "请输入姓名", trigger: "change" }],
        balance: [{ required: true, message: "请输入余额", trigger: "change" }],
      },
    };
  },
  async mounted() {
    await showAccount({}).then((_res) => {
      this.accountList = _res.result;
    });
  },
  methods: {
    getAllAccount() {
      showAccount({}).then((_res) => {
        this.accountList = _res.result;
      });
    },
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then((_) => {
          // 清空表格
          this.resetForm();
          done();
        })
        .catch((_) => {});
    },
    resetForm() {
      this.accountForm = {};
    },
    submitForm(formName) {
      this.$refs.customerForm.validate((valid) => {
        if (valid) {
          // 防御式编程
          // 余额不能为负数
          if (this.accountForm.balance < 0) {
            this.$message({
              type: "error",
              message: "余额不能小于0",
            });
            return;
          }

          createAccount(this.accountForm).then((_res) => {
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
              // 重新刷新客户页面
              this.getAllAccount();
            }
          });
        }
      });
    },
    close() {
      this.editForm = {};
    },
    editInfo(id) {
      this.editForm = JSON.parse(
        JSON.stringify(this.accountList.filter((x) => x.id === id)[0])
      );
      this.editDialogVisible = true;
    },
    handleEdit(flag) {
      if (flag === false) {
        // 取消修改
        this.editDialogVisible = false;
        this.editForm = {};
      } else {
        updateAccount(this.editForm).then((_res) => {
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
            // 隐藏修改框
            this.editDialogVisible = false;
            this.editForm = {};
            // 刷新客户列表
            this.getAllAccount();
          }
        });
      }
    },
    handleDelete(id) {
      let config = {
        params: {
          id: id,
        },
      };
      this.$confirm("是否要删除该账户？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          deleteAccount(config).then((_res) => {
            if (_res.msg === "Success") {
              this.$message({
                type: "success",
                message: "删除成功!",
              });
              this.getAllAccount();
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
  },
};
</script>

<style>
</style>