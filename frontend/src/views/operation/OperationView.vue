<template>
    <Layout>
        <Title title="经营情况表"></Title>
        <el-button type="primary" size="medium" @click="handleDownload">导出excel</el-button>
        <el-date-picker v-model="date" type="datetimerange" range-separator="至" start-placeholder="开始日期"
            end-placeholder="结束日期" @change="handleChange">
        </el-date-picker>
        <el-card>折前总额：{{result.totalBeforeDiscount}}</el-card>
        <el-card>折后总额：{{result.totalAfterDiscount}}</el-card>
        <el-card>折让：{{result.purchaseExpenditure}}</el-card>
        <el-card>销售退货总额：{{result.totalReturns}}</el-card>
        <el-card>进货支出：{{result.purchaseExpenditure}}</el-card>
        <el-card>退货收入：{{result.purchaseReturnIncome}}</el-card>
        <el-card>人力支出：{{result.labourCosts}}</el-card>
        <el-card>总支出：{{result.totalExpenditure}}</el-card>
        <el-card>总收入：{{result.totalRevenue}}</el-card>
        <el-card>总利润：{{result.totalAccount}}</el-card>
    </Layout>

</template>

<script>
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
import { downloadExcel, getOperatons } from '../../network/operation'
import { formatDate } from "@/common/utils";
export default {
    name: 'OperationView',
    components: {
        Layout,
        Title,
    },
    data(){
        return{
          date:null,
          result:{}
        }
    },
    mounted() {
       this.setPage()
    },
    computed: {
        beginDate: function () {
            return this.date === null ? null : formatDate(this.date[0])
        },
        endDate: function () {
            return this.date === null ? null : formatDate(this.date[1])
        },
    },
    methods: {
        handleChange(){
            let params = {}
            this.beginDate && (params.beginDate = this.beginDate)
            this.endDate && (params.endDate = this.endDate)
            console.log(this.beginDate)
            let config = {
               params
            }
            getOperatons(config).then(res=>{
                this.result = res.result
            })
            
        },
        setPage(){
            // console.log(this.beginDate)
            getOperatons().then(res=>{
                console.log(res)
                this.result = res.result
                
            })
        },
        handleDownload(){
            let params = {}
            this.beginDate && (params.beginDate = this.beginDate)
            this.endDate && (params.endDate = this.endDate)
            downloadExcel(params)
            this.$message({
                type: 'success',
                message: '下载成功!'
            })
        },
        handleTest(){
            console.log("this\n",this)
        }
    }
}
</script>

<style lang="scss" scoped>
.body {
    margin: 0 auto;
    margin-top: 10px;
    height: 80vh;
    overflow-y: auto;
    width: 100%;
    background: rgba($color: #fff, $alpha: 0.5);
}
</style>