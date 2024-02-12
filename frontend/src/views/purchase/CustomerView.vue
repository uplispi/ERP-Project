<template>
  <Layout>
    <Title title="客户管理"></Title>
    <el-button type="primary" size="medium" @click="dialogVisible = true">新增客户</el-button>
<!--  添加客户的表单    -->
    <el-dialog
      title="新增客户"
      :visible.sync="dialogVisible"
      width="40%"
      :before-close="handleClose">
      <div style="width: 90%; margin: 0 auto">
        <el-form :model="customerForm" label-width="80px" ref="customerForm" :rules="rules">
          <el-form-item label="姓名" prop="name">
            <el-input type="text" v-model="customerForm.name"></el-input>
          </el-form-item>

          <el-form-item label="类型" prop="type" style="width: 50%; margin-right: 5%">
            <el-select v-model="customerForm.type" placeholder="请选择类型">
              <el-option value="供应商"></el-option>
              <el-option value="销售商"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="级别" prop="level" style="width: 50%">
            <el-select v-model="customerForm.level" placeholder="请选择级别">
              <el-option value="1"></el-option>
              <el-option value="2"></el-option>
              <el-option value="3"></el-option>
              <el-option value="4"></el-option>
              <el-option value="5"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="电话" prop="phone">
            <el-input type="text" v-model="customerForm.phone"></el-input>
          </el-form-item>

          <el-form-item label="地址" prop="address">
            <el-input type="text" v-model="customerForm.address"></el-input>
          </el-form-item>

          <el-form-item label="邮编">
            <el-input type="text" v-model="customerForm.zipcode"></el-input>
          </el-form-item>

          <el-form-item label="邮箱">
            <el-input type="text" v-model="customerForm.email"></el-input>
          </el-form-item>

          <el-form-item label="应收额度">
            <el-input type="text" v-model="customerForm.lineOfCredit"></el-input>
          </el-form-item>

          <el-form-item label="应收">
            <el-input type="text" v-model="customerForm.receivable"></el-input>
          </el-form-item>

          <el-form-item label="应付">
            <el-input type="text" v-model="customerForm.payable"></el-input>
          </el-form-item>


        </el-form>
      </div>
      <!-- 提交按钮-->
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm('customerForm')">立即创建</el-button>
      </span>
    </el-dialog>

    <div>
      <el-input
          style="margin-top: 10px"
          v-model="search"
          autosize
          placeholder="输入关键词搜索客户">
      </el-input>
    </div>
<!--    显示所有客户-->
    <div style="margin-top: 10px">
      <el-table
        :data="customerList.filter(data => !search || data.name.includes(search) || data.id === Number(search))"
        stripe
        style="width: 100%"
        :header-cell-style="{'text-align':'center'}"
        :cell-style="{'text-align':'center'}">
        <el-table-column
          prop="id"
          label="id"
          width="50">
        </el-table-column>
        <el-table-column
          prop="name"
          label="姓名"
          width="70">
        </el-table-column>
        <el-table-column
          prop="type"
          label="类型"
          width="70"
          :filters="[{ text: '供应商', value: '供应商' }, { text: '销售商', value: '销售商' }]"
          :filter-method="filterTag"
          filter-placement="bottom-end">
          <template slot-scope="scope">
            <el-tag
              :type="scope.row.type === '供应商' ? 'primary' : 'success'"
              disable-transitions>{{scope.row.type}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column
          prop="level"
          label="级别"
          width="70">
        </el-table-column>
        <el-table-column
          prop="phone"
          label="电话"
          width="100">
        </el-table-column>
        <el-table-column
          prop="address"
          label="地址"
          width="100">
        </el-table-column>
        <el-table-column
          prop="zipcode"
          label="邮编"
          width="100">
        </el-table-column>
        <el-table-column
          prop="email"
          label="邮箱"
          width="100">
        </el-table-column>
        <el-table-column
          prop="lineOfCredit"
          label="应收额度(元)"
          width="90">
        </el-table-column>
        <el-table-column
          prop="receivable"
          label="应收(元)"
          width="90">
        </el-table-column>
        <el-table-column
          prop="payable"
          label="应付(元)"
          width="90">
        </el-table-column>
        <el-table-column
          prop="operator"
          label="操作员"
          width="100">
        </el-table-column>
        <el-table-column
          label="操作">
          <template slot-scope="scope">
            <div>
              <el-button
                @click.native.prevent="editInfo(scope.row.id)"
                type="text"
                size="small">
                编辑
              </el-button>
            </div>
            <div>
              <el-button
                @click.native.prevent="handleDelete(scope.row.id)"
                type="text"
                size="small"
                style="margin: auto 0;">
                删除
              </el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </div>
<!-- 编辑客户的表单   -->
    <el-dialog
      title="修改客户信息"
      :visible.sync="editDialogVisible"
      width="30%"
      @close="close()">
      <el-form :model="editForm" :label-width="'100px'" size="mini">
        <el-form-item label="姓名" prop="name">
          <el-input type="text" v-model="editForm.name"></el-input>
        </el-form-item>

        <el-form-item label="类型" prop="type" style="width: 50%; margin-right: 5%">
          <el-select v-model="editForm.type" placeholder="请选择类型">
            <el-option value="供应商"></el-option>
            <el-option value="销售商"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="级别" prop="level" style="width: 50%">
          <el-select v-model="editForm.level" placeholder="请选择级别">
            <el-option value="1"></el-option>
            <el-option value="2"></el-option>
            <el-option value="3"></el-option>
            <el-option value="4"></el-option>
            <el-option value="5"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="电话" prop="phone">
          <el-input type="text" v-model="editForm.phone"></el-input>
        </el-form-item>

        <el-form-item label="地址" prop="address">
          <el-input type="text" v-model="editForm.address"></el-input>
        </el-form-item>

        <el-form-item label="邮编">
          <el-input type="text" v-model="editForm.zipcode"></el-input>
        </el-form-item>

        <el-form-item label="邮箱">
          <el-input type="text" v-model="editForm.email"></el-input>
        </el-form-item>

        <el-form-item label="应收额度">
          <el-input type="text" v-model="editForm.lineOfCredit"></el-input>
        </el-form-item>

        <el-form-item label="应收">
          <el-input type="text" v-model="editForm.receivable"></el-input>
        </el-form-item>

        <el-form-item label="应付">
          <el-input type="text" v-model="editForm.payable"></el-input>
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
import {deleteCustomer,createCustomer, getAllCustomer, getCustomerByType, updateCustomer} from "../../network/purchase";

export default {
  name: 'CustomerView',
  components: {
    Layout,
    Title
  },
  data() {
    return {
      dialogVisible: false,  // 对话框是否显示
      editDialogVisible: false, // 编辑对话框是否显示
      customerList: [],
      customerForm: {      // 表单信息
        name: '',
        type: '',
        level: '',
        phone: '',
        address: '',
        zipcode: '',
        email: '',
        lineOfCredit: '',
        receivable: '',
        payable: '',
      },
      editForm: {      // 修改信息表单
      },
      search: '',
      rules: {
        // 规则
      }
    }
  },
  async mounted() {
    // 选择供应商
    await getCustomerByType({ params : { type: 'SUPPLIER' } }).then(_res => {
      this.customerList = this.customerList.concat(_res.result)
    })
    // 选择销售商
    await getCustomerByType({ params : { type: 'SELLER' } }).then(_res => {
      this.customerList = this.customerList.concat(_res.result)
    })
  },
  methods: {
    filterTag(value, row) {
      return row.type === value
    },

    // 获取所有客户
    getCustomer(){
        getAllCustomer({}).then(_res => {
          this.customerList = _res.result;
        })
    },

    submitForm(formName) {
      // TODO: 新增客户
      createCustomer(this.customerForm).then(_res => {
        if (_res.code === "B0001" || _res.code === "B0002") {
          this.$message({
            type: 'error',
            message: _res.msg
          });
        } else {
          this.$message({
            type: 'success',
            message: '新增成功!'
          })
          // 清空表格 关闭表单
          this.resetForm()
          this.dialogVisible = false;
          // 重新刷新客户页面
          this.getCustomer()
        }
      })
    },

    // 关闭表单
    handleClose(done){
      this.$confirm('确认关闭？').then(_ => {
        // 清空表格
        this.resetForm();
        done();
      }).catch(_ => {})
    },

    // 清空表格
    resetForm(){
      this.customerForm = {
        name: '',
        type: '',
        level: '',
        phone: '',
        address: '',
        zipcode: '',
        email: '',
        lineOfCredit: '',
        receivable: '',
        payable: '',
      }
    },

    editInfo(id) {
      // TODO: 修改客户信息
      // 填写editForm 把customerList该客户的值传递给editForm
      // ！！！要值传递不能引用传递
      this.editForm = JSON.parse(JSON.stringify(this.customerList.filter(x => x.id === id)[0]));
      // 打开对话框
      this.editDialogVisible = true
    },
    handleEdit(type){
      if (type === false) {
        // 取消了
        this.editDialogVisible = false;
        this.editForm = {};
      } else if (type === true) {
        // 确定 => 发送请求
        updateCustomer(this.editForm).then(_res => {
          if (_res.code === 'B0003') {
            this.$message({
              type: 'error',
              message: _res.msg
            })
          } else {
            this.$message({
              type: 'success',
              message: '修改成功！'
            })
            // 隐藏修改框
            this.editDialogVisible = false;
            this.editForm = {};
            // 刷新客户列表
            this.getCustomer();
          }
        })
      }
    },

    // TODO 删除顾客
    handleDelete(id) {
      let config = {
        params: {
          id: id
        }
      }
      this.$confirm('是否要删除该客户？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteCustomer(config).then(_res => {
          if (_res.msg === 'Success') {
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
            this.getCustomer();
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    close() {
      this.addForm = {};
      this.editForm = {};
    }
  }
}
</script>

<style>

</style>