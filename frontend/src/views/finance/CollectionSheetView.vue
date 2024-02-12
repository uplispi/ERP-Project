<template>
  <Layout>
    <Title title="收款单管理"></Title>
    <el-button
      v-if="permitted()"
      type="primary"
      size="medium"
      @click="dialogVisible = true"
    >
      制定收款单
    </el-button>
    <!-- 创建收款单的对话框 -->
    <el-dialog
      title="创建收款单"
      :visible.sync="dialogVisible"
      width="40%"
      :before-close="handleClose"
    >
      <div style="width: 90%; margin: 0 auto">
        <el-form
          :model="collectionSheetForm"
          label-width="80px"
          ref="collectionSheetForm"
        >
          <el-form-item label="客户 " prop="customer">
            <el-select
              v-model="collectionSheetForm.customer"
              placeholder="请选择客户"
            >
              <el-option
                v-for="item in customerList"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <el-form>
          <el-form-item
            v-for="(
              item, index
            ) in collectionSheetForm.collectionSheetContentList"
            :key="index"
            :label="'转账条目' + index"
          >
            <div>
              <el-select
                v-model="item.accountId"
                placeholder="请选择账户"
                style="width: 30%; margin-right: 5%"
              >
                <el-option
                  v-for="item1 in accountList"
                  :key="item1.id"
                  :label="item1.name"
                  :value="item1.id"
                >
                </el-option>
              </el-select>
              <el-input
                type="number"
                v-model="item.total"
                style="width: 40%; margin-right: 5%"
                placeholder="请输入转账金额"
              ></el-input>
            </div>
            <div style="margin-top: 10px">
              <el-input
                v-model="item.remark"
                style="width: 70%; margin-right: 10%"
                placeholder="请填写备注"
              ></el-input>
              <el-button
                type="text"
                size="small"
                @click="addContent"
                v-if="
                  index ===
                  collectionSheetForm.collectionSheetContentList.length - 1
                "
                >添加</el-button
              >
              <el-button
                type="text"
                size="small"
                @click.prevent="removeContent(item)"
                v-if="index !== 0"
                >删除</el-button
              >
            </div>
          </el-form-item>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm('collectionSheetFrom')"
          >立即创建</el-button
        >
      </span>
    </el-dialog>

    <!-- 审批状态  -->
    <div class="body">
      <el-tabs v-model="activeName" :stretch="true">
        <el-tab-pane label="待审批" name="PENDING_LEVEL_1">
          <div v-if="pendingLevel1List.length === 0">
            <el-empty description="暂无数据"></el-empty>
          </div>
          <div v-else>
            <collection-list
              :list="pendingLevel1List"
              :type="1"
              @refresh="getCollectionSheet()"
            />
          </div>
        </el-tab-pane>

        <el-tab-pane label="审批完成" name="SUCCESS">
          <div v-if="successList.length === 0">
            <el-empty description="暂无数据"></el-empty>
          </div>
          <div v-else>
            <collection-list :list="successList" :type="3" />
          </div>
        </el-tab-pane>

        <el-tab-pane label="审批失败" name="FAILURE">
          <div v-if="failureList.length === 0">
            <el-empty description="暂无数据"></el-empty>
          </div>
          <div v-else>
            <collection-list :list="failureList" :type="4" />
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>
  </Layout>
</template>

<script>
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
import {
  createCollectionSheet,
  getAllCollectionSheet,
} from "@/network/finance";
import { getAllCustomer } from "@/network/purchase";
import CollectionList from "@/views/finance/components/CollectionList";
import { showAccount } from "@/network/account";

export default {
  name: "CollectionSheetView",
  components: {
    Layout,
    Title,
    CollectionList,
  },
  data() {
    return {
      dialogVisible: false,
      activeName: "PENDING_LEVEL_1", // 导航栏active
      collectionSheetList: [], // 所有的收款单
      pendingLevel1List: [], // 待审批的收款单
      successList: [], // 成功的收款单
      failureList: [], // 失败的收款单
      collectionSheetForm: {
        collectionSheetContentList: [
          {
            total: "",
            remark: "",
          },
        ],
      }, // 制定的收款单
      customerList: [], // 客户列表
      accountList: [], // 账户列表
    };
  },
  async mounted() {
    await getAllCustomer({}).then((_res) => {
      this.customerList = _res.result;
    }),
      await showAccount({}).then((_res) => {
        this.accountList = _res.result;
      }),
      await getAllCollectionSheet({}).then((_res) => {
        this.collectionSheetList = _res.result;
        this.pendingLevel1List = this.collectionSheetList.filter(
          (item) => item.state === "待审批"
        );
        this.successList = this.collectionSheetList.filter(
          (item) => item.state === "审批完成"
        );
        this.failureList = this.collectionSheetList.filter(
          (item) => item.state === "审批失败"
        );
      });
  },
  methods: {
    permitted() {
      // 只有财务人员可以制定收款单
      return (
        sessionStorage.getItem("role") === "FINANCIAL_STAFF" ||
        sessionStorage.getItem("role") === "ADMIN"
      );
    },
    getCollectionSheet() {
      getAllCollectionSheet({}).then((_res) => {
        this.collectionSheetList = _res.result;
        this.pendingLevel1List = this.collectionSheetList.filter(
          (item) => item.state === "待审批"
        );
        this.successList = this.collectionSheetList.filter(
          (item) => item.state === "审批完成"
        );
        this.failureList = this.collectionSheetList.filter(
          (item) => item.state === "审批失败"
        );
      });
    },
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then((_) => {
          this.resetForm();
          done();
        })
        .catch((_) => {});
    },
    addContent() {
      this.collectionSheetForm.collectionSheetContentList.push({});
    },
    removeContent(item) {
      var index =
        this.collectionSheetForm.collectionSheetContentList.indexOf(item);
      if (index !== -1) {
        this.collectionSheetForm.collectionSheetContentList.splice(index, 1);
      }
    },
    submitForm(formName) {
      // 防御式编程
      // 没有选择客户
      if (!this.collectionSheetForm.customer) {
        this.$message.error("请选择客户");
        return;
      }
      // 没有选择账户
      // 转账金额不能小于0
      for (var item of this.collectionSheetForm.collectionSheetContentList) {
        if (!item.accountId) {
          this.$message.error("请选择账户");
          return;
        }
        if (item.total < 0) {
          this.$message.error("转账金额不能小于0");
          return;
        }
      }
      createCollectionSheet(this.collectionSheetForm)
        .then((_res) => {
          if (_res.msg === "Success") {
            this.$message.success("创建成功!");
            this.dialogVisible = false;
            this.resetForm();
            this.getCollectionSheet();
          } else {
            this.$message.error("创建失败");
          }
        })
        .catch(() => {
          this.$message.error("创建失败");
        });
    },
    resetForm() {
      // 清空表单
      this.collectionSheetForm = {};
      this.collectionSheetForm.collectionSheetContentList = [
        { accountId: "", total: "", remark: "" },
      ];
    },
  },
};
</script>

<style>
</style>