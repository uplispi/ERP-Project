<template>
  <layout>
    <Title title="员工提成管理"></Title>

    <div>
      <center>日期{{ systemTime }}</center>
    </div>

    <!--    显示所有岗位信息-->
    <div class="push-money-body" style="margin-top: 10px">
      <center>
        <el-table
          :data="
            pushMoneyList.filter(
              (data) =>
                !search ||
                data.empName.includes(search) ||
                data.empId === Number(search)
            )
          "
          stripe
          style="width: 50%"
          :header-cell-style="{ 'text-align': 'center' }"
          :cell-style="{ 'text-align': 'center' }"
        >
          <el-table-column prop="empId" label="员工id" width="150">
          </el-table-column>
          <el-table-column prop="empName" label="员工姓名" width="150">
          </el-table-column>
          <el-table-column prop="pushMoney" label="提成" width="150">
          </el-table-column>
          <el-table-column>
            <template slot="header">
              <div class="top">
                <div class="left">
                  <el-input
                    v-model="search"
                    size="mini"
                    placeholder="输入员工id搜索"
                  />
                </div>
              </div>
            </template>
            <template slot-scope="scope">
              <el-button
                @click="editPushMoney(scope.row.empId)"
                type="text"
                size="small"
              >
                修改
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </center>
    </div>

    <el-dialog
      title="修改提成"
      :visible.sync="editDialogVisible"
      width="30%"
      @close="close()"
    >
      <el-form :model="editForm" :label-width="'100px'" size="mini">
        <el-form-item
          label="提成"
          prop="pushMoney"
          style="width: 90%; margin-right: 5%"
        >
          <el-input
            v-model.number="editForm.pushMoney"
            type="number"
            placeholder="如：3500.00"
            @keydown.native="handleInput"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleEdit(false)">取 消</el-button>
        <el-button type="primary" @click="handleEdit(true)">确 定</el-button>
      </div>
    </el-dialog>
  </layout>
</template>

<script>
import Layout from "../../components/content/Layout";
import Title from "../../components/content/Title";
import { queryAllPushMoney, updatePushMoney } from "../../network/pushMoney";
import { updatePost } from "../../network/post";
import { queryAllEmployee } from "@/network/employee";

export default {
  name: "PushMoneyManagement",
  components: { Title, Layout },
  data() {
    return {
      pushMoneyList: [],
      search: "",
      currentYear: new Date().getFullYear(),
      currentMonth: new Date().getMonth() + 1,
      systemTime: "",
      editDialogVisible: false,
      editForm: {},
      addForm: {
        empId: "",
        pushMoney: "",
      },
      employeeList: [],
    };
  },

  async mounted() {
    await this.addDate();
    // 获取所有员工
    await queryAllEmployee({})
      .then((_res) => {
        this.employeeList = _res.result;
      })
      .catch((err) => {
        this.$message({
          type: "error",
          message: "获取员工信息失败！" + err,
        });
      });

    await this.getAllPushMoney();
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
    getAllPushMoney() {
      queryAllPushMoney({})
        .then((_res) => {
          this.pushMoneyList = _res.result;
          // 把员工名加到pushMoneyList中
          this.pushMoneyList.forEach((item) => {
            item.empName = this.employeeList.filter((item1) => {
              return item1.id === item.empId;
            })[0].name;
          });
        })
        .catch((err) => {
          this.$message({
            type: "error",
            message: "获取提成信息失败！" + err,
          });
        });
    },
    editPushMoney(empId) {
      this.editForm = JSON.parse(
        JSON.stringify(this.pushMoneyList.filter((x) => x.empId === empId)[0])
      );
      this.editDialogVisible = true;
    },

    addDate() {
      let nowDate = new Date();
      let date = {
        year: nowDate.getFullYear(),
        month: nowDate.getMonth() + 1,
        date: nowDate.getDate(),
      };
      this.systemTime = ":" + date.year + "年" + date.month + "月";
    },

    handleEdit(type) {
      if (type === false) {
        this.editDialogVisible = false;
        this.editForm = {};
      } else if (type === true) {
        console.log("this.editForm", this.editForm);
        updatePushMoney(this.editForm).then((_res) => {
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
            this.getAllPushMoney();
          }
        });
      }
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