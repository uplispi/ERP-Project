<template>
    <Layout>
        <Title title="制定工资单"></Title>
        <template v-if="permit()">
            <el-select v-model="addId" placeholder="待发工资的员工">
                <el-option v-for="item in detail_list" :key="item.employeeId" :label="item.name"
                    :value="item.employeeId">
                </el-option>
            </el-select>
            <el-button type="primary" size="medium" @click="handleAdd() ">新建工资单</el-button>
        </template>

        <div class="body">
            <el-tabs v-model="activeName" :stretch="true">
                <el-tab-pane label="待一级审批" name="PENDING_LEVEL_1">
                    <div v-if="pendingLevel1List.length === 0">
                        <el-empty description="暂无数据"></el-empty>
                    </div>
                    <div v-else>
                        <payroll-list :list="pendingLevel1List" :type="1" @refresh="getPayroll()" />
                    </div>
                </el-tab-pane>
                <el-tab-pane label="待二级审批" name="PENDING_LEVEL_2">
                    <div v-if="pendingLevel2List.length === 0">
                        <el-empty description="暂无数据"></el-empty>
                    </div>
                    <div v-else>
                        <payroll-list :list="pendingLevel2List" :type="2" @refresh="getPayroll()" />
                    </div>
                </el-tab-pane>
                <el-tab-pane label="审批完成" name="SUCCESS">
                    <div v-if="successList.length === 0">
                        <el-empty description="暂无数据"></el-empty>
                    </div>
                    <div v-else>
                        <payroll-list :list="successList" :type="3" />
                    </div>
                </el-tab-pane>
                <el-tab-pane label="审批失败" name="FAILURE">
                    <div v-if="failureList.length === 0">
                        <el-empty description="暂无数据"></el-empty>
                    </div>
                    <div v-else>
                        <payroll-list :list="failureList" :type="4" />
                    </div>
                </el-tab-pane>
            </el-tabs>
        </div>

        <el-dialog title="新建工资单" :visible.sync="dialogVisable" width="30%">
            <el-form :label-width="'100px'" size="mini">
                <el-form-item label="员工编号" :disable="true">
                    <el-input type="Number" v-model="addForm.employeeId"></el-input>
                </el-form-item>
                <el-form-item label="员工姓名">
                    <el-input type="text" v-model="addForm.name"></el-input>
                </el-form-item>
                <el-form-item label="打卡次数">
                    <el-input type="Number" v-model="addForm.times"></el-input>
                </el-form-item>
                <el-form-item label="应发工资">
                    <el-input type="Number" v-model="addForm.payableWages"></el-input>
                </el-form-item>
                <el-form-item label="失业保险">
                    <el-input type="Number" v-model="addForm.unemploymentInsurance"></el-input>
                </el-form-item>
                <el-form-item label="住房公积金">
                    <el-input type="text" v-model="addForm.housingProvidentFund"></el-input>
                </el-form-item>
                <el-form-item label="个人所得税">
                    <el-input type="Number" v-model="addForm.personalIncomeTax"></el-input>
                </el-form-item>
                <el-form-item label="年终奖" v-if="addForm.yearEndBonus != null">
                    <el-input type="text" v-model="addForm.yearEndBonus"></el-input>
                </el-form-item>
                <el-form-item label="实际发金额">
                    <el-input type="text" v-model="addForm.actualAmountIssued"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="handleConfirm(false)">取 消</el-button>
                <el-button type="primary" @click="handleConfirm(true)">确 定</el-button>
            </div>
        </el-dialog>
    </Layout>

</template>

<script>
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
import { queryAll, add, getDetail, update } from '../../network/Payroll'
import PayrollList from "./components/PayrollList"
export default {
    name: 'PayrollView',
    components: {
        PayrollList,
        Layout,
        Title,
    },
    data(){
        return{
            // role:"",
            activeName: 'PENDING_LEVEL_1',
            addId:undefined,
            isAdd : true,
            dialogVisable:false,
            addForm:{},
            detail_list:[],
            payrollList:[],
            pendingLevel1List:[],
            pendingLevel2List:[],
            successList:[],
            failureList:[]
        }
    },
    mounted() {
        
        this.getPayroll()
        getDetail().then(res => {
            this.detail_list = res.result
            console.log(this.detail_list)
        })
    },
    computed:{
    },
    methods: {
        permit(){
            return sessionStorage.getItem('role') === 'FINANCIAL_STAFF' || sessionStorage.getItem('role') === 'ADMIN'
        },
        getPayroll() {
            queryAll().then(_res=>{
                this.payrollList = _res.result
                this.pendingLevel1List = this.payrollList.filter(item => item.state === '待一级审批')
                this.pendingLevel2List = this.payrollList.filter(item => item.state === '待二级审批')
                this.successList = this.payrollList.filter(item => item.state === '审批完成')
                this.failureList = this.payrollList.filter(item => item.state === '审批失败')

            })
        },
        handleGetDetail(){
            getDetail().then(res=>{
                console.log("getDetail\n", res)
            })
        },
        handleAdd(){
            if(!this.addId){
                this.$message.error("请选择员工")
                return
            }
            this.isAdd = true
            console.log(this.addId)
            const sh = this.detail_list.filter(item=>{
                return item.employeeId === this.addId
            })
            console.log(sh)
            this.addForm = sh[0]
            console.log(this.addForm)

            this.dialogVisable = true;
        },
        handleConfirm(confirm){
            console.log(confirm)
            if(confirm){
                if(this.isAdd){
                    add(this.addForm)
                }
                else{
                    console.log(this.isAdd)
                    console.log(this.addForm)
                    const updateForm = this.addForm
                    delete updateForm.state
                    update(updateForm)
                }
            this.getPayroll()
            }
            this.dialogVisable = false
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