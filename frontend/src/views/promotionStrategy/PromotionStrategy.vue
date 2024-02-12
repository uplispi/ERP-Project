<template>
  <Layout>
    <Title title="促销策略"></Title>
    <el-button type="primary" size="medium" @click="dialogVisible = true">制定促销方案</el-button>

<!--    显示所有的促销策略-->
    <div style="margin-top: 10px">
      <el-table
        :data="promotionList"
        stripe
        :header-cell-style="{'text-align':'center'}"
        :cell-style="{'text-align':'center'}">
        <el-table-column
          prop="id"
          label="id">
        </el-table-column>
        <el-table-column
          prop="name"
          label="name">
        </el-table-column>
        <el-table-column
          prop="strategy"
          label="策略">
        </el-table-column>
        <el-table-column
          prop="discount"
          label="折扣">
        </el-table-column>
        <el-table-column
          prop="reduce"
          label="代金券">
        </el-table-column>
        <el-table-column
          prop="giftName"
          label="赠品">
        </el-table-column>
        <el-table-column
          prop="giftNum"
          label="赠品数量">
        </el-table-column>
        <el-table-column
          prop="startStr"
          label="开始日期"
          :formatter="myFormateDate">
        </el-table-column>
        <el-table-column
          prop="endStr"
          label="结束时间"
          :formatter="myFormateDate">
        </el-table-column>
        <el-table-column
          label="操作">
          <template slot-scope="scope">
            <el-button
              @click.native.prevent="handleDelete(scope.row.id)"
              type="text"
              size="small">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!--  添加方案的表单    -->
    <el-dialog
      title="新增方案"
      :visible.sync="dialogVisible"
      width="40%"
      :before-close="handleClose">
      <div style="width: 90%; margin: 0 auto">
        <el-form :model="promotionForm" label-width="80px" ref="promotionForm" :rules="rules">
          <el-form-item label="名称" prop="name">
            <el-input type="text" v-model="promotionForm.name"></el-input>
          </el-form-item>
          <el-form-item label="客户级别" prop="level">
            <el-input type="text" v-model="promotionForm.level"></el-input>
          </el-form-item>
          <el-form-item label="时间" prop="date">
            <el-date-picker
              v-model="date"
              type="datetimerange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期">
            </el-date-picker>
          </el-form-item>

          <el-form-item label="策略: " prop="strategy">
            <el-select v-model="promotionForm.strategy" placeholder="请选择方案">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="折扣" prop="discount" v-if="promotionForm.strategy === 'discount'">
            <el-input type="text" v-model="promotionForm.discount"></el-input>
          </el-form-item>

          <el-form-item label="额度" prop="reduce" v-if="promotionForm.strategy === 'reduce'">
            <el-input type="text" v-model="promotionForm.reduce"></el-input>
          </el-form-item>

          <el-form-item label="赠品" prop="strategy" v-if="promotionForm.strategy === 'gift'">
            <el-select v-model="promotionForm.gift" placeholder="请选择赠品">
              <el-option
                v-for="item in commodityList"
                :key="item.id"
                :label="item.name"
                :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="赠品数量" prop="discount" v-if="promotionForm.strategy === 'gift'">
            <el-input type="text" v-model="promotionForm.giftNum"></el-input>
          </el-form-item>

        </el-form>
      </div>
      <!-- 提交按钮-->
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm('promotionForm')">立即创建</el-button>
      </span>
    </el-dialog>


  </Layout>
</template>
<script>
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
import { createPromotion, deletePromotion, getAllPromotion} from "@/network/promotion";
import { formatDate, formatDateWithoutTime } from "@/common/utils";
import { getAllCommodity } from "@/network/commodity";
export default {
  components: { Title, Layout },
  data(){
    return{
      dialogVisible :false,
      promotionList :[], //促销策略列表
      date: {}, //开始、截止日期
      promotionForm : { //单个促销策略的属性
        name: '',
        discount: 10,
        reduce: 0,
        gift: '',
        strategy: '',
        startStr: '',
        endStr: '',
        giftNum: '',
        giftName: '',
        level: '',
      },
      options: [{
        value: 'discount',
        label: '折扣',
      },{
        value: 'reduce',
        label: '代金券',
      },{
        value: 'gift',
        label: '赠品'
      }],

      commodityList:[], //商品列表
      editForm: {      // 修改信息表单
      },
      rules: {
        // 规则
      }

    }
  },
  async mounted() {
    //获取所有的商品
    await getAllCommodity({}).then(_res => {
      this.commodityList = _res.result;
      this.commodityList.forEach((item) => {
        if (item.recentPp == null) {
          item.recentPp = '暂无';
        }
        if (item.recentRp == null) {
          item.recentRp = '暂无';
        }
      })
    }).catch(err => {
      this.$message({
        type: 'error',
        message: '获取商品列表失败!' + err
      })
    })

    //加载所有的促销策略
    await getAllPromotion().then(_res => {
      this.promotionList = _res.result;
      this.promotionList.forEach((item) => {
        item.giftName = '';
        if (item.strategy === 'gift'){
          this.commodityList.forEach((item2) => {
            if (item.gift === item2.id){
              item.giftName = item2.name;
            }
          })
        }
      })
    })
  },
  computed: {
    //获取开始时间
    beginDate: function(){
      return this.date === '' ? '' : formatDate(this.date[0])
    },
    //获取截止时间
    endDate: function() {
      return this.date === '' ? '' : formatDate(this.date[1])
    },
  },
  methods:{
    myFormateDate(row, column){
      console.log('row=>', row)
      console.log('column=>', column)
       let data = row[column.property]
       return formatDateWithoutTime(data)
    },
    // 获取所有方案
    getPromotion(){
      getAllPromotion({}).then(_res => {
        this.promotionList = _res.result;
        this.promotionList.forEach((item) => {
          if (item.strategy === 'gift'){
            this.commodityList.forEach((item2) => {
              if (item.gift === item2.id){
                item.giftName = item2.name;
              }
            })
          }
        })
      })

    },
    //提交方案
    submitForm(formName) {
      if(this.promotionForm.name===''){
        this.$message({
            type: 'error',
            message: '未输入促销名'
          }
        )
        return;
      }
      if(this.promotionForm.name.length > 30){
        this.$message({
            type: 'error',
            message: '促销名过长'
          }
        )
        return;
      }
      if(this.promotionForm.level===''){
        this.$message({
            type: 'error',
            message: '未输入等级'
          }
        )
        return;
      }
      if(this.promotionForm.strategy===''){
        this.$message({
            type: 'error',
            message: '未选择策略'
          }
        )
        return;
      }
      this.promotionForm.startStr = this.beginDate;
      this.promotionForm.endStr = this.endDate;
      this.promotionForm.full = Number(this.promotionForm.full);
      this.promotionForm.reduce = Number(this.promotionForm.reduce);
      this.promotionForm.discount = Number(this.promotionForm.discount);
      createPromotion(this.promotionForm).then(_res => {
          this.$message({
            type: 'success',
            message: '新增成功!'
          })
          // 清空表格 关闭表单
          this.resetForm()
          this.dialogVisible = false;
          // 重新刷新客户页面
          this.getPromotion()

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
      this.promotionForm = {
        id: '',
        name: '',
        discount: '',
        full: '',
        reduce: '',
      }
    },

    //删除方案
    handleDelete(id) {
      let config = {
        params: {
          id: id
        }
      }
      this.$confirm('是否要删除该方案？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deletePromotion(config).then(_res => {
          if (_res.msg === 'Success') {
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
            this.getPromotion();
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },

  },
}
</script>
