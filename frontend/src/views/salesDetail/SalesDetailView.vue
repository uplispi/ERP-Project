<template>
    <Layout>
        <Title title="销售明细表查询"></Title>

        <!-- <el-button type="primary" size="medium" @click="handleQuery()">查询</el-button> -->

        <el-button class="excel" type="primary" size="medium" @click="handleDownload()">导出excel</el-button>
        <el-button class="excel" type="primary" size="medium" @click="clear">清空选择</el-button>
        <el-date-picker v-model="date" type="datetimerange" range-separator="至" start-placeholder="开始日期"
            end-placeholder="结束日期" @change="handleChange">
        </el-date-picker>
        <el-select v-model="pid" placeholder="请选择商品名" @change="handleChange">
          <el-option v-for="item in products" :key="item.id" :label="item.name" :value="item.id">
          </el-option>
        </el-select>
        <el-select v-model="salesman" placeholder="请选择业务人员" @change="handleChange">
            <el-option v-for="item in salesmans" :key="item" :label="item" :value="item">
            </el-option>
        </el-select>
        <el-select v-model="customerId" placeholder="请选择客户" @change="handleChange">
            <el-option v-for="item in customers" :key="item.id" :label="item.name" :value="item.id">
            </el-option>
        </el-select>
        <div class="table-body">
            <el-table :data="showList" style="width: 100%;" :header-cell-style="{ 'text-align': 'center' }"
                :cell-style="{ 'text-align': 'center' }">
                <el-table-column fixed type="index" label="行号" min-width="12%">
                </el-table-column>

                <el-table-column prop="createTime" label="日期" min-width="12%">
                </el-table-column>
                <el-table-column prop="name" label="商品名" min-width="12%">
                </el-table-column>
                <el-table-column prop="type" label="型号" min-width="12%">
                </el-table-column>
                <el-table-column prop="quantity" label="数量" min-width="12%">
                </el-table-column>
                <el-table-column prop="unitPrice" label="单价" min-width="12%">
                </el-table-column>
                <el-table-column prop="totalPrice" label="总价" min-width="12%">
                </el-table-column>
                <el-table-column prop="salesType" label="销售类型" min-width="12%">
                </el-table-column>
            </el-table>
        </div>
    </Layout>
</template>

<script>
import { formatDate } from "@/common/utils";
import { getSalesDetail, downloadSalesDetail } from '../../network/saleDetail'
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
export default {
    name: 'SalesDetailView',
    components: {
        Layout,
        Title
    },
    data() {
        return {
            date:null,
            customers:[],//{id, name}
            salesmans:[],//string
            products:[],//{id, name}
            cur_list: [],
            cur_row: {},
            selected:{},

            list:[],
            showList:[],
            salesman:undefined,
            pid:undefined,
            customerId:undefined,
        }
    },
    computed: {
        beginDate: function () {
            console.log(this.date)
            return this.date === null ? null : formatDate(this.date[0])
        },
        endDate: function () {
            return this.date === null ? null : formatDate(this.date[1])
        },
    },
    mounted() {
        getSalesDetail().then(_res => {
            console.log("销售明细", _res);
            this.list = _res.result
            this.showList = this.list
            this.customers = [...new Set(this.list.map(item => { return { id: item.customerId, name: item.customer } }).map(item => { return JSON.stringify(item) }))].map(item => { return JSON.parse(item) }).filter(item => item != null)
            console.log("customers",this.customers)
            this.salesmans =[...new Set(this.list.map(item => { return item.salesman }).map(item => JSON.stringify(item)))].map(item => JSON.parse(item)).filter(item=>item!=null)
            console.log("salesman", this.salesmans)
            this.products = [...new Set(this.list.map(item => { return { id: item.pid, name: item.name } }).map(item => JSON.stringify(item)))].map(item => JSON.parse(item)).filter(item => item != null)

        })
    },
    methods: {
        handleChange(){
            this.showList = this.list.filter(item=>{
                // console.log(this.date, new Date(item.createTime))
                // console.log(this.beginDate, this.endDate, item.createTime)
                return !(
                    this.beginDate && (this.date[0] > new Date(item.createTime)) 
                    || this.endDate && (this.date[1] < new Date(item.createTime)) 
                    || this.customerId && (this.customerId !== item.customerId) 
                    || this.salesman && (this.salesman !== item.salesman) 
                    || this.pid && (this.pid !== item.pid) 
                )
            })
        },

        handleDownload(){
            const config = {}
            this.beginDate && (config.beginDate = this.beginDate)
            this.endDate && (config.endDate = this.endDate)
            this.customerId && (config.customerId = this.customerId)
            this.salesman && (config.salesman = this.salesman)
            this.pid && (config.pid = this.pid)
            console.log(config)
            downloadSalesDetail(config)
            this.$message({
                type: 'success',
                message: '下载成功!'
            })
            
        },
        clear(){
            this.date = null
            this.salesman = undefined
            this.pid = undefined
            this.customerId = undefined
            this.handleChange()
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