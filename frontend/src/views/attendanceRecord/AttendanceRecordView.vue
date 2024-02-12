<template>
  <Layout >
    <Title title="员工打卡"></Title>

    <!-- 打卡按钮 -->
    <el-button type="primary" size="medium" @click="createRecord">点击打卡</el-button>

    <div class="year">
      <el-button type="primary" size="mini" @click="disCurrentYear">&lt;上一年</el-button>
      <div>当前年份{{currentYear}}</div>
      <el-button type="primary" size="mini" @click="incCurrentYear">下一年&gt;</el-button>
    </div>

    <div class="month">
      <el-button type="primary" size="mini" @click="disCurrentMonth">&lt;上个月</el-button>
      <div>当前月份{{currentMonth}}</div>
      <el-button type="primary" size="mini" @click="incCurrentMonth">下个月&gt;</el-button>
    </div>
    <!-- 显示所有打卡记录 -->
    <div>
      <div>本月打卡次数: {{totalOfCurrentMonth}}天/{{totalDaysOfCurrentMonth}}天</div>
    </div>
    <div style="margin-top: 10px">
      <el-table
        :data = "recordListOfCurrentMonth"
        stripe
        :header-cell-style="{'text-align':'center'}"
        :cell-style="{'text-align':'center'}">
        <el-table-column
          prop="date"
          label="打卡时间">
        </el-table-column>
      </el-table>
    </div>
  </Layout>
</template>

<script>
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
import {formatDate} from "@/common/utils";
import {createAttendanceRecord, showAttendanceRecordById} from "@/network/attendanceRecord";
import {getUser} from "@/network/auth";
export default {
  name: "AttendanceRecordView",
  components: {Layout, Title},
   mounted() {
    getUser({ params : { name :  sessionStorage.getItem("name") } }).then(_res => {
      this.userId = _res.result.id
      showAttendanceRecordById({params: {id: Number(this.userId)}}).then(_res => {
        console.log(_res)
        this.recordList = _res.result
        this.refreshRecordList()
      })
    })
  },
  computed: {
    totalOfCurrentMonth: function (){
      return this.recordListOfCurrentMonth.length;
    },
    totalDaysOfCurrentMonth: function (){
      var date = new Date(this.currentYear, this.currentMonth, 0)
      return date.getDate()
    }
  },
  data(){
    return {
      date: new Date(),
      userId: '',
      recordList: [],  // 该用户的打卡记录
      currentMonth: new Date().getMonth()+1,  // 当前月份
      currentYear: new Date().getFullYear(),  // 当前年
      recordListOfCurrentMonth: [],
    }
  },
  methods: {
    createRecord() {
      // 防止同一天多次打卡
      const tmp = this.recordList.filter(item => {
        let tmp_date = new Date(item.date)
        return this.date.getYear() === tmp_date.getYear()  && tmp_date.getMonth() === this.date.getMonth() && tmp_date.getDay() === this.date.getDay()
      })
      if (tmp.length === 1) {
        this.$message.error("今天已打卡")
      } else {
        // 首次打卡
        let config = {
          empId: Number(this.userId),
          date: ''
        }
        console.log(config)
        createAttendanceRecord(config).then(_res => {
          if (_res.msg === 'Success') {
            this.$message.success('打卡成功!')
            showAttendanceRecordById({params: {id: Number(this.userId)}}).then(_res => {
              this.recordList = _res.result
              this.refreshRecordList()
            })
          } else {
            this.$message.error('今天已打过卡')
          }
        })
      }
    },
    disCurrentMonth(){
      if(this.currentMonth===0){
        this.currentMonth = 12;
        this.currentYear -= 1;
      }else {
        this.currentMonth = this.currentMonth - 1;
      }
      this.refreshRecordList();
    },
    incCurrentMonth(){
      if(this.currentMonth === 12){
        this.currentMonth=0;
        this.currentYear+=1;
      }else {
        this.currentMonth = this.currentMonth + 1;
      }
      this.refreshRecordList()
    },
    disCurrentYear(){
      if(this.currentYear === 0){
        return;
      }
      this.currentYear = this.currentYear - 1 ;
      this.refreshRecordList();
    },
    incCurrentYear(){
      this.currentYear = this.currentYear + 1 ;
      this.refreshRecordList()
    },
    refreshRecordList(){
      this.recordListOfCurrentMonth = this.recordList.filter(item => {
        let tmp_date = new Date(item.date)
        return tmp_date.getMonth()+1 === this.currentMonth && tmp_date.getFullYear() === this.currentYear;
      })
    }
  },


}
</script>

<style scoped>
.year{
  margin-top: 10px;
  margin-bottom: 5px;
  display: flex;
  justify-content:space-evenly;
}
.month{
  margin-top: 10px;
  margin-bottom: 5px;
  display: flex;
  justify-content:space-evenly;
}
</style>