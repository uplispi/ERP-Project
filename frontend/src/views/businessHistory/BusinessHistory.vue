<template>
    <Layout>
        <Title title="查看经营历程"></Title>
        <el-button @click="handleDownload" type="primary" size="medium">导出excel</el-button>
        <el-button class="excel" type="primary" size="medium" @click="clear">清空选择</el-button>
        <el-select v-model="type" placeholder="请选择单据类型" @change="handleChange">
            <el-option v-for="item of sheetTypes" :key="item.value" :label="item.name" :value="item.value">
            </el-option>
        </el-select>
        <el-date-picker v-model="date" type="datetimerange" range-separator="至" start-placeholder="开始日期"
            end-placeholder="结束日期" @change="handleChange">
        </el-date-picker>
        <el-select v-model="customer" placeholder="请选择客户" @change="handleChange">
            <el-option v-for="item of customers" :key="item" :label="item" :value="item">
            </el-option>
        </el-select>
        <el-select v-model="salesman" placeholder="请选择业务员" @change="handleChange">
            <el-option v-for="item of salesmans" :key="item" :label="item" :value="item">
            </el-option>
        </el-select>
        <div v-for="item in showList" :key="item.id">
            <sale-list v-if="item.id.startsWith('XSD')" :list="[item]" :type="3"></sale-list>
            <sale-return-list v-else-if="item.id.startsWith('XSTHD')" :list="[item]" :type="3"></sale-return-list>
            <purchase-list v-else-if="item.id.startsWith('JHD')" :list="[item]" :type="3"></purchase-list>
            <purchase-return-list v-else-if="item.id.startsWith('JHTHD')" :list="[item]" :type="3">
            </purchase-return-list>
            <payment-list v-else-if="item.id.startsWith('FKD')" :list="[item]" :type="3"></payment-list>
            <collection-list v-else-if="item.id.startsWith('SKD')" :list="[item]" :type="3"></collection-list>
            <payroll-list v-else-if="item.id.startsWith('GZD')" :list="[item]" :type='3'></payroll-list>
        </div>


    </Layout>

</template>

<script>
import { formatDate } from "@/common/utils";

import { downloadSheet } from '../../network/businessHistory'
import { getAllSale, getAllSaleReturn } from "@/network/sale";
import {getAllPurchaseReturn, getAllPurchase} from '../../network/purchase'
import { getAllPaymentSheet, getAllCollectionSheet } from "@/network/finance";
import { queryAll } from '../../network/Payroll'

import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
import SaleList from '../sale/components/SaleList'
import SaleReturnList from '../sale/components/SaleReturnList.vue';
import PurchaseReturnList from '../purchase/components/PurchaseReturnList.vue';
import PurchaseList from '../purchase/components/PurchaseList.vue';
import PaymentList from '../finance/components/PaymentList.vue';
import PayrollList from '../payroll/components/PayrollList.vue';
import CollectionList from '../finance/components/CollectionList.vue';
export default {
    name: 'BusinessHistory',
    components: {
    Layout,
    Title,
    SaleList,
    SaleReturnList,
    PurchaseList,
    PurchaseReturnList,
    PaymentList,
    PayrollList,
    CollectionList,
},
    data() {
        return {
            showList:[],
            list:[],
            date: null,
            sheetTypes: [{ name: "销售单", value: "XSD" }, { name: "销售退货单", value: 'XSTHD' }, { name: "进货单", value: 'JHD' }, { name: "进货退货单", value: 'JHD' }, { name: "付款单", value: 'FKD' }, { name: "收款单", value: 'SKD' }, { name: "工资单", value: 'GZD' }],
            type:undefined,
            customer:undefined,
            customers:[],
            salesman:undefined,
            salesmans:[],

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
        }
    },
    methods: {
        setPage(){
            getAllSale().then(res=>{
                this.list = [...this.list, ...res.result]
                getAllSaleReturn().then(res=>{
                    this.list = [...this.list, ...res.result]
                    getAllPurchase().then(res => {
                        this.list = [...this.list, ...res.result]
                        getAllPurchaseReturn().then(res=>{
                            this.list = [...this.list, ...res.result]
                            getAllPaymentSheet().then(res=>{
                                this.list = [...this.list, ...res.result]
                                getAllCollectionSheet().then(res=>{
                                    this.list = [...this.list, ...res.result]
                                    queryAll().then(res=>{
                                        this.list = [...this.list, ...res.result]
                                        this.list = this.list.filter(item=>item.state === '审批完成')
                                        this.list.sort((first, second) => {
                                            let fisrtDate = new Date(first.createTime)
                                            let secondDate = new Date(second.createTime)
                                            return secondDate - fisrtDate
                                        })
                                        console.log(this.list)
                                        this.showList = this.list
                                        this.customers = [...new Set(this.showList.map(item=>{
                                            return item.customer || item.supplier
                                        }))].filter(item=>item !== undefined)
                                        this.salesmans =[...new Set(this.showList.map(item=>{
                                            return item.salesman
                                        }))].filter(item=>item !== undefined)
                                    })
                                })
                            })
                        })
                    })
                })
            })
        },
        handleChange(){
            this.showList = this.list.filter(item=>{
                let customer = item.customer || item.supplier
                
                return !(  
                    this.type && !(item.id.startsWith(this.type))
                    || this.customer && (customer != this.customer)
                    || this.salesman && item.salesman && (item.salesman != this.salesman)
                    || this.beginDate  && (this.date[0] > new Date(item.createTime))
                    || this.endDate  && (this.date[1] < new Date(item.createTime))
                )
            })
            console.log(this.showList)
        },
        handleDownload(){
            if(!this.type){
                this.$message.error("请选择单据类型")
                return
            }
            let sheetType = this.sheetTypes.filter(item=>item.value === this.type)[0]['name']
            const config = { sheetType }
            this.beginDate && (config.beginDate = this.beginDate)
            this.endDate && (config.endDate = this.endDate)
            this.customer && (config.customer = this.customer)
            this.salesman && (config.salesman = this.salesman)
            downloadSheet(config)
            this.$message({
                type: 'success',
                message: '下载成功!'
            })

        },
        clear(){
            this.date = null
            this.type = null
            this.customer = null
            this.salesman = null
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