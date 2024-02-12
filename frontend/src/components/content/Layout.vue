<template>
  <el-container style="height: 100%; border: 1px solid #eee">
    <el-aside width="220px" style="background-color: #545c64; overflow-x=hidden">
      <header class="title">
        <div class="title-inner">ERP</div>
      </header>
      <el-menu :default-active="getActivePath" class="el-menu-vertical-demo" background-color="#fff"
        text-color="#000000DE" @open="handleOpen" @close="handleClose" active-text-color="#7e57c2" router>
        <!-- 商品管理-->
        <el-submenu index="1" v-if="permit(PATH.COMMODITY_CLASSIFICATION.requiresAuth)">
          <template slot="title">
            <i class="el-icon-receiving"></i>
            <span slot="title">商品管理</span>
          </template>
          <el-menu-item :index="PATH.COMMODITY_CLASSIFICATION.path"
            v-if="permit(PATH.COMMODITY_CLASSIFICATION.requiresAuth)">
            <i class="el-icon-receiving"></i>
            <span slot="title">商品分类管理</span>
          </el-menu-item>
          <el-menu-item :index="PATH.COMMODITY_MANAGEMENT.path" v-if="permit(PATH.COMMODITY_MANAGEMENT.requiresAuth)">
            <i class="el-icon-receiving"></i>
            <span slot="title">商品管理</span>
          </el-menu-item>
        </el-submenu>
        <!-- 库存管理-->
        <el-submenu index="2" v-if="permit(PATH.INVENTORY_OPERATION.requiresAuth)">
          <template slot="title">
            <i class="el-icon-receiving"></i>
            <span slot="title">库存管理</span>
          </template>
          <el-menu-item :index="PATH.INVENTORY_MANAGEMENT.path" v-if="permit(PATH.INVENTORY_MANAGEMENT.requiresAuth)">
            <i class="el-icon-receiving"></i>
            <span slot="title">库存管理</span>
          </el-menu-item>
          <!--          <el-menu-item-->
          <!--            :index="PATH.INVENTORY_OPERATION.path"-->
          <!--            v-if="permit(PATH.INVENTORY_OPERATION.requiresAuth)"-->
          <!--          >-->
          <!--            <i class="el-icon-receiving"></i>-->
          <!--            <span slot="title">库存操作</span>-->
          <!--          </el-menu-item>-->
          <el-menu-item :index="PATH.INVENTORY_VIEW.path" v-if="permit(PATH.INVENTORY_VIEW.requiresAuth)">
            <i class="el-icon-receiving"></i>
            <span slot="title">库存查看</span>
          </el-menu-item>
          <el-menu-item :index="PATH.INVENTORY_CHECK.path" v-if="permit(PATH.INVENTORY_CHECK.requiresAuth)">
            <i class="el-icon-receiving"></i>
            <span slot="title">库存盘点</span>
          </el-menu-item>
        </el-submenu>
        <!-- 销售管理-->
        <el-submenu index="3" v-if="permit(PATH.CUSTOMER_VIEW.requiresAuth)">
          <template slot="title">
            <i class="el-icon-sell"></i>
            <span slot="title">销售管理</span>
          </template>
          <el-menu-item :index="PATH.PURCHASE_VIEW.path" v-if="permit(PATH.PURCHASE_VIEW.requiresAuth)">
            <i class="el-icon-shopping-cart-full"></i>
            <span slot="title">进货管理</span>
          </el-menu-item>
          <el-menu-item :index="PATH.PURCHASE_RETURN_VIEW.path" v-if="permit(PATH.PURCHASE_RETURN_VIEW.requiresAuth)">
            <i class="el-icon-shopping-cart-2"></i>
            <span slot="title">进货退货管理</span>
          </el-menu-item>
          <el-menu-item :index="PATH.SALE_VIEW.path" v-if="permit(PATH.SALE_VIEW.requiresAuth)">
            <i class="el-icon-sell"></i>
            <span slot="title">销售管理</span>
          </el-menu-item>
          <el-menu-item :index="PATH.SALE_RETURN_VIEW.path" v-if="permit(PATH.SALE_RETURN_VIEW.requiresAuth)">
            <i class="el-icon-sell"></i>
            <span slot="title">销售退货管理</span>
          </el-menu-item>
          <el-menu-item :index="PATH.CUSTOMER_VIEW.path" v-if="permit(PATH.CUSTOMER_VIEW.requiresAuth)">
            <i class="el-icon-s-custom"></i>
            <span slot="title">客户管理</span>
          </el-menu-item>
        </el-submenu>
        <!-- 财务管理-->
        <el-submenu index="4" v-if="permit(PATH.FINANCE.requiresAuth)">
          <template slot="title">
            <i class="el-icon-s-finance"></i>
            <span slot="title">财务管理</span>
          </template>
          <el-menu-item :index="PATH.ACCOUNT_MANAGEMENT_VIEW.path"
            v-if="permit(PATH.ACCOUNT_MANAGEMENT_VIEW.requiresAuth)">
            <i class="el-icon-bank-card"></i>
            <span slot="title">账户管理</span>
          </el-menu-item>

          <el-menu-item :index="PATH.COLLECTION_SHEET_VIEW.path" v-if="permit(PATH.COLLECTION_SHEET_VIEW.requiresAuth)">
            <i class="el-icon-tickets"></i>
            <span slot="title">收款单管理</span>
          </el-menu-item>

          <el-menu-item :index="PATH.PAYMENT_SHEET_VIEW.path" v-if="permit(PATH.PAYMENT_SHEET_VIEW.requiresAuth)">
            <i class="el-icon-tickets"></i>
            <span slot="title">付款单管理</span>
          </el-menu-item>
          <!-- 工资单审核 -->
          <el-menu-item :index="PATH.PAYROLL_VIEW.path" v-if="permit(PATH.PAYROLL_VIEW.requiresAuth)">
            <i class="el-icon-sell"></i>
            <span slot="title">工资单管理</span>
          </el-menu-item>

          <el-menu-item :index="PATH.SALES_DETAIL_VIEW.path" v-if="permit(PATH.SALES_DETAIL_VIEW.requiresAuth)">
            <i class="el-icon-sell"></i>
            <span slot="title">销售明细</span>
          </el-menu-item>
          <el-menu-item :index="PATH.BUSINESS_HISTORY.path" v-if="permit(PATH.BUSINESS_HISTORY.requiresAuth)">
            <i class="el-icon-sell"></i>
            <span slot="title">经营历程</span>
          </el-menu-item>
          <el-menu-item :index="PATH.OPERATION_VIEW.path" v-if="permit(PATH.OPERATION_VIEW.requiresAuth)">
            <i class="el-icon-sell"></i>
            <span slot="title">经营情况</span>
          </el-menu-item>
        </el-submenu>
        <!-- 人事管理-->
        <el-submenu index="5" v-if="permit(PATH.HR_VIEW.requiresAuth)">
          <template slot="title">
            <i class="el-icon-s-management"></i>
            <span slot="title">人事管理</span>
          </template>
          <!-- 员工管理-->
          <el-menu-item :index="PATH.HR_VIEW.path" v-if="permit(PATH.HR_VIEW.requiresAuth)">
            <i class="el-icon-user-solid"></i>
            <span slot="title">员工管理</span>
          </el-menu-item>

          <!-- 岗位信息管理-->
          <el-menu-item :index="PATH.POST_VIEW.path" v-if="permit(PATH.POST_VIEW.requiresAuth)">
            <i class="el-icon-money"></i>
            <span slot="title">岗位信息管理</span>
          </el-menu-item>
          <!--提成管理-->
          <el-menu-item :index="PATH.PUSH_MONEY_VIEW.path" v-if="permit(PATH.PUSH_MONEY_VIEW.requiresAuth)">
            <i class="el-icon-bank-card"></i>
            <span slot="title">提成管理</span>
          </el-menu-item>
        </el-submenu>


        <!-- 审批管理-->
        <el-submenu index="6" v-if="permit(PATH.GM_APPROVAL.requiresAuth)">
          <template slot="title">
            <i class="el-icon-circle-check"></i>
            <span slot="title">审核管理</span>
          </template>
          <!--          <el-menu-item :index="PATH.GM_APPROVAL.path" v-if="permit(PATH.GM_APPROVAL.requiresAuth)">-->
          <!--            <i class="el-icon-receiving"></i>-->
          <!--            <span slot="title">审批</span>-->
          <!--          </el-menu-item>-->
          <el-menu-item :index="PATH.SALES_DETAIL_VIEW.path" v-if="permit(PATH.SALES_DETAIL_VIEW.requiresAuth)">
            <i class="el-icon-sell"></i>
            <span slot="title">销售明细</span>
          </el-menu-item>
          <el-menu-item :index="PATH.BUSINESS_HISTORY.path" v-if="permit(PATH.BUSINESS_HISTORY.requiresAuth)">
            <i class="el-icon-sell"></i>
            <span slot="title">经营历程</span>
          </el-menu-item>
          <el-menu-item :index="PATH.OPERATION_VIEW.path" v-if="permit(PATH.OPERATION_VIEW.requiresAuth)">
            <i class="el-icon-sell"></i>
            <span slot="title">经营情况</span>
          </el-menu-item>
          <el-menu-item :index="PATH.COLLECTION_SHEET_VIEW.path" v-if="permit(PATH.COLLECTION_SHEET_VIEW.requiresAuth)">
            <i class="el-icon-tickets"></i>
            <span slot="title">收款单审批</span>
          </el-menu-item>
          <el-menu-item :index="PATH.PAYMENT_SHEET_VIEW.path" v-if="permit(PATH.PAYMENT_SHEET_VIEW.requiresAuth)">
            <i class="el-icon-tickets"></i>
            <span slot="title">付款单审批</span>
          </el-menu-item>
          <el-menu-item :index="PATH.PURCHASE_VIEW.path" v-if="permit(PATH.PURCHASE_VIEW.requiresAuth)">
            <i class="el-icon-shopping-cart-full"></i>
            <span slot="title">进货单审批</span>
          </el-menu-item>
          <el-menu-item :index="PATH.PURCHASE_RETURN_VIEW.path" v-if="permit(PATH.PURCHASE_RETURN_VIEW.requiresAuth)">
            <i class="el-icon-shopping-cart-2"></i>
            <span slot="title">进货退货单审批</span>
          </el-menu-item>
          <el-menu-item :index="PATH.SALE_VIEW.path" v-if="permit(PATH.SALE_VIEW.requiresAuth)">
            <i class="el-icon-sell"></i>
            <span slot="title">销售单审批</span>
          </el-menu-item>
          <el-menu-item :index="PATH.SALE_RETURN_VIEW.path" v-if="permit(PATH.SALE_RETURN_VIEW.requiresAuth)">
            <i class="el-icon-sell"></i>
            <span slot="title">销售退货单审批</span>
          </el-menu-item>
          <!-- 工资单审核 -->
          <el-menu-item :index="PATH.PAYROLL_VIEW.path" v-if="permit(PATH.PAYROLL_VIEW.requiresAuth)">
            <i class="el-icon-sell"></i>
            <span slot="title">工资单审批</span>
          </el-menu-item>
          <el-menu-item :index="PATH.INVENTORY_GIFT_VIEW.path" v-if="permit(PATH.INVENTORY_GIFT_VIEW.requiresAuth)">
            <i class="el-icon-sell"></i>
            <span slot="title">库存赠品单审批</span>
          </el-menu-item>

        </el-submenu>
        <!-- 其他-->
        <el-submenu index="7">
          <template slot="title">
            <i class="el-icon-more"></i>
            <span slot="title">其他</span>
          </template>
          <!-- 促销策略 -->
          <el-menu-item :index="PATH.SALE_PROMOTION.path" v-if="permit(PATH.SALE_PROMOTION.requiresAuth)">
            <i class="el-icon-s-promotion"></i>
            <span slot="title">促销策略管理</span>
          </el-menu-item>

          <!-- 每日打卡 -->
          <el-menu-item :index="PATH.ATTENDANCE_RECORD_VIEW.path" v-if="permit(PATH.ATTENDANCE_RECORD_VIEW.requiresAuth)">
            <i class="el-icon-date"></i>
            <span slot="title">每日打卡</span>
          </el-menu-item>
        </el-submenu>

      </el-menu>

      <footer>
        <div class="logout-fix">
          <el-tooltip class="item" effect="light" :content="getUserRole" placement="right">
            <div class="logout-name">{{ getUsername() }}</div>
          </el-tooltip>
          <div>
            <el-button :type="isRecorded? 'success' :'primary'" class="record" @click="createRecord">{{ isRecorded ? '已打卡' :  '请打卡' }}</el-button>
            <el-button type="danger" class="logout" @click="logout()" style="">退出</el-button>
          </div>
        </div>
      </footer>




    </el-aside>

    <el-main style="overflow-x: hidden">
      <slot></slot>
    </el-main>
  </el-container>
</template>

<script>
import { ROLE, PATH } from "../../common/const";
import { createAttendanceRecord, showAttendanceRecordById } from "@/network/attendanceRecord";
import { getUser } from "@/network/auth";
export default {
  name: "Layout",
  props: {
    activePath: String
  },
  data() {
    return {
      ROLE: ROLE,
      PATH: PATH,
      date: new Date(),
      userId: '',
      recordList: [],  // 该用户的打卡记录
      isRecorded:false,
    };
  },
  mounted() {
    getUser({ params: { name: sessionStorage.getItem("name") } }).then(_res => {
      this.userId = _res.result.id
      showAttendanceRecordById({ params: { id: Number(this.userId) } }).then(_res => {
        console.log(_res)
        this.recordList = _res.result
        if(this.getRecoreded()){
          this.isRecorded = true
        }
      })
    })
  },
  computed: {
    getActivePath() {
      if (this.activePath == null) {
        return this.$route.path;
      } else {
        return this.activePath;
      }
    },
    getUserRole() {
      return sessionStorage.getItem("role");
    }
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    logout() {
      sessionStorage.removeItem("name"); // name
      sessionStorage.removeItem("role"); // role
      sessionStorage.removeItem("token"); // token
      // this.$router.push('/');
      location.href = "/";
    },
    getUsername() {
      return sessionStorage.getItem("name");
    },
    permit(arr) {
      return arr.some(role => role === sessionStorage.getItem("role"));
    },
    getRecoreded() {
      const tmp = this.recordList.find(item => {
        let today = new Date(item.date)
        return this.date.getYear() === today.getYear() && today.getMonth() === this.date.getMonth() && today.getDay() === this.date.getDay()
      })
      if (tmp !== undefined) {
        return true
      }
      return false;
    },
    createRecord() {
      // 防止同一天多次打卡
      
      if(this.isRecorded){
        // 打过卡了
        this.$message.error('今天已打过卡')
      }
      else {
        // 首次打卡
        let config = {
          empId: Number(this.userId),
          date: ''
        }
        console.log(config)
        createAttendanceRecord(config).then(_res => {
          if (_res.msg === 'Success') {
            showAttendanceRecordById({ params: { id: Number(this.userId) } }).then(_res => {
              console.log(_res)
              this.recordList = _res.result
            })
            this.isRecorded = true
            this.$message.success('打卡成功!')
          } else {
            this.$message.error('今天已打过卡')
          }
        })
      }
    },
  }
};
</script>

<style>
.title {
  padding: 16px;
  margin-bottom: 10px;
}

.title-inner {
  background-color: #9fa8da !important;
  border-color: #9fa8da !important;
  border-radius: 8px !important;
  line-height: 40px;
  height: 40px;
  text-align: center;
  font-size: 21px;
  font-weight: bold;
}

.el-aside {
  background-color: #fff !important;
  margin-right: 10px;
}

.el-menu {
  border-right: solid 1px #fff;
}

.el-menu-item {
  width: 92%;
  margin: 0 auto;
  padding: 0 10px !important;
  height: 40px;
  line-height: 40px;
  font-size: 13px;
}

.el-menu-item>i {
  margin-right: 30px !important;
}

.el-menu-item:focus,
.el-menu-item:hover {
  outline: 0;
  background-color: #f6f6f6 !important;
}

.el-menu-item.is-active {
  color: #7e57c2;
  background-color: #f0ebf8 !important;
}

.logout-fix {
  position: fixed;
  left: 30px;
  bottom: 30px;
}

.el-menu-vertical-demo {
  padding-bottom: 120px;
}

.logout-fix .logout-name {
  min-width: 60px;
  background-color: #7e57c2 !important;
  border-color: #7e57c2 !important;
  color: #fff;
  border-radius: 16px;
  font-size: 14px;
  height: 32px;
  text-align: center;
  padding: 0 10px;
  /* margin-left: -10px; */
  line-height: 32px;
  margin-bottom: 14px;
  cursor: pointer;
}

.logout-fix .logout,
.logout-fix .record {
  font-size: 14px;
  cursor: pointer;
}

.el-aside::-webkit-scrollbar {display:none}

</style>
