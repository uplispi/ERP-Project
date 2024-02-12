<template>
  <Layout>
    <Title title="付款单管理"></Title>
    <el-button v-if="permitted()"
               type="primary"
               size="medium"
               @click="dialogVisible = true">
      制定收款单
    </el-button>

    <!-- 创建付款单的对话框 -->
    <el-dialog
        title="创建付款单"
        :visible.sync="dialogVisible"
        width="40%"
        :before-close="handleClose">
      <div style="width: 90%; margin: 0 auto">
        <el-form :model="paymentSheetForm" label-width="80px" ref="paymentSheetForm">
          <el-form-item label="客户 " prop="customer">
            <el-select v-model="paymentSheetForm.customer" placeholder="请选择客户">
              <el-option
                  v-for="item in customerList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <el-form>
          <el-form-item
              v-for="(item, index) in paymentSheetForm.paymentSheetContentList"
              :key="index"
              :label="'转账条目' + index">
            <div>
              <el-select v-model="item.accountId" placeholder="请选择账户id" style="width: 40%; margin-right: 5%">
                <el-option
                    v-for="item1 in accountList"
                    :key="item1.id"
                    :label="item1.name"
                    :value="item1.id">
                </el-option>
              </el-select>
              <el-input type="number" v-model="item.total" style="width: 25%; margin-right: 5%" placeholder="请输入转账金额"></el-input>
            </div>
            <div style="margin-top: 10px">
              <el-input v-model="item.remark" style="width: 70%; margin-right: 10%" placeholder="请填写备注"></el-input>
              <el-button type="text" size="small" @click="addContent" v-if="index === paymentSheetForm.paymentSheetContentList.length - 1">添加</el-button>
              <el-button type="text" size="small" @click.prevent="removeContent(item)" v-if="index !== 0">删除</el-button>
            </div>
          </el-form-item>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm('paymentSheetForm,m')">立即创建</el-button>
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
            <payment-list :list="pendingLevel1List" :type="1" @refresh="getPaymentSheet()"/>
          </div>
        </el-tab-pane>

        <el-tab-pane label="审批完成" name="SUCCESS">
          <div v-if="successList.length === 0">
            <el-empty description="暂无数据"></el-empty>
          </div>
          <div v-else>
            <payment-list :list="successList" :type="3"/>
          </div>
        </el-tab-pane>

        <el-tab-pane label="审批失败" name="FAILURE">
          <div v-if="failureList.length === 0">
            <el-empty description="暂无数据"></el-empty>
          </div>
          <div v-else>
            <payment-list :list="failureList" :type="4"/>
          </div>
        </el-tab-pane>

      </el-tabs>
    </div>

  </Layout>
</template>

<script>
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
import {createPaymentSheet, getAllPaymentSheet} from "@/network/finance";
import PaymentList from "@/views/finance/components/PaymentList";
import {getAllCustomer} from "@/network/purchase";
import {showAccount} from "@/network/account";

export default {
  name: "PaymentView",
  components: {PaymentList, Title, Layout},
  data(){
    return {
      dialogVisible: false,
      activeName: 'PENDING_LEVEL_1',  // 导航栏
      paymentSheetList: [], // 所有的收款单
      pendingLevel1List: [],   // 待审批的收款单
      successList: [],          // 成功的收款单
      failureList: [],          // 失败的收款单
      paymentSheetForm: {
        paymentSheetContentList: [
          {
            total: '',
            remark: '',
          }
        ]
      },
      customerList: [] ,   // 客户列表
      accountList: [],    // 账户列表
    }
  },
  async mounted() {
       await getAllCustomer({}).then(_res => {
          this.customerList = _res.result
        }),
        await showAccount({}).then(_res => {
          this.accountList = _res.result
        }),
        await getAllPaymentSheet({}).then(_res => {
          this.paymentSheetList = _res.result
          this.pendingLevel1List = this.paymentSheetList.filter(item => item.state === '待审批')
          this.successList = this.paymentSheetList.filter(item => item.state === '审批完成')
          this.failureList = this.paymentSheetList.filter(item => item.state === '审批失败')
        })
  },
  methods: {
    getPaymentSheet(){
      // 获取所有付款单
      getAllPaymentSheet({}).then(_res => {
        this.paymentSheetList = _res.result
        this.pendingLevel1List = this.paymentSheetList.filter(item => item.state === '待审批')
        this.successList = this.paymentSheetList.filter(item => item.state === '审批完成')
        this.failureList = this.paymentSheetList.filter(item => item.state === '审批失败')
      })
    },
    permitted(){
      // 只有财务人员才可以制定付款单
      return sessionStorage.getItem('role') === 'FINANCIAL_STAFF' || sessionStorage.getItem('role') === 'ADMIN'
    },
    handleClose(done){
      this.$confirm('确认关闭？')
          .then(_ => {
            this.resetForm()
            done();
          })
          .catch(_ => {});
    },
    addContent(){
      // 添加条目
      this.paymentSheetForm.paymentSheetContentList.push({})
    },
    removeContent(item){
      // 删除条目
      var index = this.paymentSheetForm.paymentSheetContentList.indexOf(item)
      if (index !== -1) {
        this.paymentSheetForm.paymentSheetContentList.splice(index, 1)
      }
    },
    submitForm(formName){
      // 防御式编程
        // 没有选择客户
      if(!this.paymentSheetForm.customer){
        this.$message.error("请选择客户")
        return;
      }
        // 没有选择账户
        // 转账条目的金额不能小于0
      for(var item of this.paymentSheetForm.paymentSheetContentList){
        if(!item.accountId){
          this.$message.error("请选择账户")
          return;
        }
        if(item.total < 0){
          this.$message.error("转账金额不能小于0")
          return;
        }
      }

      createPaymentSheet(this.paymentSheetForm).then(_res => {
        if (_res.msg === 'Success') {
          this.$message.success('创建成功!')
          this.dialogVisible = false
          this.resetForm()
          this.getPaymentSheet()
        }
      })
    },
    resetForm(){
      this.paymentSheetForm = {}
      this.paymentSheetForm.paymentSheetContentList = [{total: '', remark: ''}]
    },
  }
}
</script>

<style scoped>

</style>