<template>
    <div class="card">
        <el-card v-for="item in list" :index="item.index" :key="item.id" shadow="hover">
            <template #header>
                <el-row>
                    <el-col :span="18">
                        <span><strong>id: </strong>{{item.id}}</span>
                        <el-button v-if="authorization() === 1" style="margin-left: 10px" type="success"
                            icon="el-icon-check" circle size="mini" @click="approval(item.id)"></el-button>
                        <el-button v-if="authorization() === 1" type="danger" icon="el-icon-close" circle size="mini"
                            @click="deny(item.id)"></el-button>
                        <el-button v-if="authorization() === 2" style="margin-left: 10px" type="primary"
                            icon="el-icon-check" circle size="mini" @click="approval(item.id)"></el-button>
                        <el-button v-if="authorization() === 2" type="danger" icon="el-icon-close" circle size="mini"
                            @click="deny(item.id)"></el-button>
                        <span style="margin-left: 10px">
                            <el-tag v-if="type === 3" effect="dark" type='success'>审核通过</el-tag>
                            <el-tag v-if="type === 4" effect="dark" type='danger'>审核未通过</el-tag>
                        </span>
                    </el-col>
                </el-row>
            </template>
            <div>
                <el-row>
                    <el-col :span="6">
                        <span><strong>日期：</strong>{{ item.createTime }}</span>
                    </el-col>
                    <el-col :span="6">
                        <span><strong>实发工资：</strong>{{ item.actualAmountIssued }}（元）</span>
                    </el-col>
                    <el-col :span="6">
                        <span><strong>银行卡: </strong>{{ item.bankCard }}</span>
                    </el-col>
                    <el-col :span="6">
                        <span><strong>员工编号: </strong>{{ item.employeeId }}</span>
                    </el-col>
                </el-row>
                <el-row style="margin-top: 15px">
                    <el-col :span="6">
                        <span><strong>单据编号: </strong>{{ item.id }}</span>
                    </el-col>
                    <el-col :span="6">
                        <span><strong>员工姓名: </strong>{{ item.name }}</span>
                    </el-col>
                    <el-col :span="6">
                        <span><strong>操作员: </strong>{{ item.operator }}</span>
                    </el-col>
                </el-row>
                <el-row style="margin-top: 15px">
                    <el-col :span="6">
                        <span><strong>应付工资: </strong>{{ item.payableWages }}（元）</span>
                    </el-col>
                    <el-col :span="6">
                        <span><strong>个人所得税: </strong>{{ item.personalIncomeTax }}（元）</span>
                    </el-col>
                </el-row>
                <el-row style="margin-top: 15px">
                    <el-col :span="6">
                        <span><strong>失业保险: </strong>{{ item.unemploymentInsurance }}（元）</span>
                    </el-col>
                    <el-col :span="6" v-if="item.yearEndBonus != null">
                        <span><strong>年终奖: </strong>{{ item.yearEndBonus }}（元）</span>
                    </el-col>
                </el-row>
                <el-row style="margin-top: 15px">
                    <el-col :span="24">
                        <span><strong>备注: </strong>{{item.remark}}</span>
                    </el-col>
                </el-row>
            </div>
        </el-card>
    </div>
</template>

<script>
import { update  } from '../../../network/Payroll'
export default {
    name: 'PayrollList',
    props: {
        list: Array,
        type: Number
    },
    data() {
        return {
        }
    },
    mounted() {
        
    },
    methods: {
        authorization() {
            if (this.type === 1 && (sessionStorage.getItem('role') === 'HR' || sessionStorage.getItem('role') === 'ADMIN')) {
                return 1
            } else if (this.type === 2 && (sessionStorage.getItem('role') === 'GM' || sessionStorage.getItem('role') === 'ADMIN')) {
                return 2
            }
        },
        approval(id) {
            let config = {
                id: id,
                state: this.type === 1 ? '待二级审批' : '审批完成'
            }
            if (this.type === 1) {
                update(config).then(res=>{
                    this.$emit("refresh")
                    this.$message({
                        message: '操作成功!',
                        type: 'success'
                    })
                })
            } else {
                update(config).then(res=>{
                    this.$emit("refresh")
                    this.$message({
                        message: '操作成功!',
                        type: 'success'
                    })
                })
            }
        },
        deny(id) {
            let config = {
                id: id,
                state: '审批失败'
            }
            if (this.type === 1) {
                update(config).then(res=>{
                    this.$emit("refresh")
                    this.$message({
                        message: '操作成功!',
                        type: 'success'
                    })
                })
            } else {
                update(config).then(res=>{
                    this.$emit("refresh")
                    this.$message({
                        message: '操作成功!',
                        type: 'success'
                    })
                })
            }
        }
    }
}
</script>

<style lang="scss" scoped>
.card {
    width: 80%;
    margin: 0 auto;

    .button {
        float: right;
        padding: 3px 0
    }
}

.el-card {
    margin-bottom: 20px;
    background: #EEF7F2;
}
</style>