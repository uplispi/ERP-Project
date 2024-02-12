import Vue from "vue";
import VueRouter from "vue-router";
import { ROLE, PATH } from "../common/const";


const Error = () => import("../components/content/Error");
const Login = () => import("../views/auth/Login");
const Home = () => import("../views/Home");
const CommodityManagement = () =>
  import("../views/commodity/CommodityManagement");
const CommodityClassification = () =>
  import("../views/commodity/CommodityClassification");
const InventoryManagement = () => import("../views/inventory/InventoryManagement");
const InventoryCheck = () => import("../views/inventory/InventoryCheck");
const InventoryOperation = () =>
  import("../views/inventory/InventoryOperation");
const InventoryIn = () => import("../views/inventory/InventoryIn");
const InventoryOut = () => import("../views/inventory/InventoryOut");
const InventoryLoss = () => import("../views/inventory/InventoryLoss");
const InventoryOverflow = () => import("../views/inventory/InventoryOverflow");
const InventoryPresent = () => import("../views/inventory/InventoryPresent");
const InventoryView = () => import("../views/inventory/InventoryView");
const InventoryWarning = () => import("../views/inventory/InventoryWarning");

// 库存赠品单
const InventoryGiftSheetView = () => import("../views/inventory/InventoryGiftSheetView");

const PurchaseView = () => import("../views/purchase/PurchaseView");
const PurchaseReturnView = () => import("../views/purchase/PurchaseReturnView");
const SaleView = () => import("../views/sale/SaleView");
const SaleReturnView = () => import("../views/sale/SaleReturnView")
const CustomerView = () => import ("../views/purchase/CustomerView");
const Approval = () => import("../views/approval/Approval");


// 账户管理
const AccountManagement = () => import("../views/accountManagement/AccountManagement")
const SalesDetail = () => import("../views/salesDetail/SalesDetailView")
const PayrollView = () => import("../views/payroll/PayrollView")

// 收款单
const CollectionSheetView = () => import("../views/finance/CollectionSheetView")

//促销策略
const PromotionStrategy = () => import("../views/promotionStrategy/PromotionStrategy")

// 付款单
const PaymentSheetView = () => import("../views/finance/PaymentView")

// 员工管理
const HRView = () => import("../views/HR/EmployeeManagement")
//销售历程表
const BusinessHistory = () => import("../views/businessHistory/BusinessHistory")
//销售情况表
const Operation = () => import("../views/operation/OperationView")
//HR.PostManagement
const PostManagementView = () => import("../views/HR/PostManagement")
//提成管理
const PushMoneyManagementView = () => import("../views/HR/PushMoneyManagement")

// 员工打卡
const AttendanceRecordView = () => import("../views/attendanceRecord/AttendanceRecordView")

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    component: Home
  },
  {
    path: "/error",
    component: Error
  },
  {
    path: "/login",
    component: Login
  },
  {
    path: "/register",
    component: () => import("@/views/auth/register.vue")
  },
  // 商品管理
  {
    path: PATH.COMMODITY_CLASSIFICATION.path,
    component: CommodityClassification,
    meta: { requiresAuth: PATH.COMMODITY_CLASSIFICATION.requiresAuth }
  },
  {
    path: PATH.COMMODITY_MANAGEMENT.path,
    component: CommodityManagement,
    meta: { requiresAuth: PATH.COMMODITY_MANAGEMENT.requiresAuth }
  },
  // 库存管理
  {
    path: PATH.INVENTORY_MANAGEMENT.path,
    component: InventoryManagement,
    meta: { requiresAuth: PATH.INVENTORY_MANAGEMENT.requiresAuth }
  },
  {
    path: PATH.INVENTORY_CHECK.path,
    component: InventoryCheck,
    meta: { requiresAuth: PATH.INVENTORY_CHECK.requiresAuth }
  },
  {
    path: PATH.INVENTORY_OPERATION.path,
    component: InventoryOperation,
    name: "InventoryOperation",
    meta: { requiresAuth: PATH.INVENTORY_OPERATION.requiresAuth }
  },
  {
    path: PATH.INVENTORY_IN.path,
    component: InventoryIn,
    name: "InventoryIn",
    meta: { requiresAuth: PATH.INVENTORY_LOSS.requiresAuth }
  },
  {
    path: PATH.INVENTORY_OUT.path,
    component: InventoryOut,
    name: "InventoryOut",
    meta: { requiresAuth: PATH.INVENTORY_LOSS.requiresAuth }
  },
  {
    path: PATH.INVENTORY_LOSS.path,
    component: InventoryLoss,
    name: "InventoryLoss",
    meta: { requiresAuth: PATH.INVENTORY_LOSS.requiresAuth }
  },
  {
    path: PATH.INVENTORY_OVERFLOW.path,
    component: InventoryOverflow,
    name: "InventoryOverflow",
    meta: { requiresAuth: PATH.INVENTORY_OVERFLOW.requiresAuth }
  },
  {
    path: PATH.INVENTORY_PRESENT.path,
    component: InventoryPresent,
    name: "InventoryPresent",
    meta: { requiresAuth: PATH.INVENTORY_PRESENT.requiresAuth }
  },
  {
    path: PATH.INVENTORY_VIEW.path,
    component: InventoryView,
    meta: { requiresAuth: PATH.INVENTORY_VIEW.requiresAuth }
  },
  {
    path: PATH.INVENTORY_WARNING.path,
    component: InventoryWarning,
    name: "InventoryWarning",
    meta: { requiresAuth: PATH.INVENTORY_WARNING.requiresAuth }
  },
    // 库存赠品单
  {
    path: PATH.INVENTORY_GIFT_VIEW.path,
    component: InventoryGiftSheetView,
    name: "InventoryGift",
    meta: {requiresAuth: PATH.INVENTORY_GIFT_VIEW.requiresAuth}
  },

  // 销售管理
  {
    path: PATH.PURCHASE_VIEW.path,
    component: PurchaseView,
    name: "PurchaseView",
    meta: { requiresAuth: PATH.PURCHASE_VIEW.requiresAuth }
  },
  {
    path: PATH.PURCHASE_RETURN_VIEW.path,
    component: PurchaseReturnView,
    name: "PurchaseReturnView",
    meta: { requiresAuth: PATH.PURCHASE_RETURN_VIEW.requiresAuth }
  },
  {
    path: PATH.SALE_VIEW.path,
    component: SaleView,
    name: "SaleView",
    meta: { requiresAuth: PATH.SALE_VIEW.requiresAuth }
  },
  {
    path: PATH.SALE_RETURN_VIEW.path,
    component: SaleReturnView,
    name: "SaleReturnView",
    meta: { requiresAuth: PATH.SALE_RETURN_VIEW.requiresAuth}
  },
  {
    path: PATH.CUSTOMER_VIEW.path,
    component: CustomerView,
    name: "CustomerView",
    meta: { requiresAuth: PATH.CUSTOMER_VIEW.requiresAuth }
  },
  // 审批
  {
    path: PATH.GM_APPROVAL.path,
    component: Approval,
    meta: { requiresAuth: PATH.GM_APPROVAL.requiresAuth }
  },
    // 账户管理
  {
    path: PATH.ACCOUNT_MANAGEMENT_VIEW.path,
    component: AccountManagement,
    name: "AccountManagement",
    meta: {requiresAuth: PATH.ACCOUNT_MANAGEMENT_VIEW.requiresAuth}
  },
  // 销售明细
  {
    path: PATH.SALES_DETAIL_VIEW.path, 
    component: SalesDetail,
    name: "SalesDetail",
    meta: { requiresAuth: PATH.SALES_DETAIL_VIEW.requiresAuth }
  },
  // 工资单
  {
    path: PATH.PAYROLL_VIEW.path,
    component: PayrollView,
    name: "PayrollView",
    meta: { requiresAuth: PATH.PAYROLL_VIEW.requiresAuth }
  },
    // 收款单 CollectionSheetView
  {
    path: PATH.COLLECTION_SHEET_VIEW.path,
    component: CollectionSheetView,
    meta: {requiresAuth: PATH.COLLECTION_SHEET_VIEW.requiresAuth}
  },

  //促销策略
  {
    path: PATH.SALE_PROMOTION.path,
    component: PromotionStrategy,
    meta: {requiresAuth: PATH.SALE_PROMOTION.requiresAuth}
  },

  // 付款单
  {
    path: PATH.PAYMENT_SHEET_VIEW.path,
    component: PaymentSheetView,
    meta: {requiresAuth: PATH.PAYMENT_SHEET_VIEW.requiresAuth}
  },

    // 员工管理
  {
    path: PATH.HR_VIEW.path,
    component: HRView,
    meta: {requiresAuth: PATH.HR_VIEW.requiresAuth}
  },
    // 员工打卡
  {
    path: PATH.ATTENDANCE_RECORD_VIEW.path,
    component: AttendanceRecordView,
    meta: {requiresAuth: PATH.ATTENDANCE_RECORD_VIEW.requiresAuth}
  },
    //HR.PostManagement
  {
    path: PATH.POST_VIEW.path,
    component: PostManagementView,
    mata: {requiresAuth: PATH.POST_VIEW.requiresAuth}
  },
    //提成管理
  {
    path: PATH.PUSH_MONEY_VIEW.path,
    component: PushMoneyManagementView,
    mata: {requiresAuth: PATH.PUSH_MONEY_VIEW.requiresAuth}
  },
  //销售历程表
  {
    path:PATH.BUSINESS_HISTORY.path,
    component: BusinessHistory,
    mata:{requiresAuth: PATH.BUSINESS_HISTORY.requiresAuth}
  },
  //销售情况表
  {
    path:PATH.OPERATION_VIEW.path,
    component:Operation,
    mata:{requiresAuth:PATH.OPERATION_VIEW.requiresAuth}
  },

  // -----------------------未找到页面-----------------------------
  {
    path: "*",
    redirect: "/error"
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

router.beforeEach(async (to, from, next) => {
  // console.log(to.path);
  if (to.path === "/error" || to.path === "/login") {
    next();
  } else if (to.path === "/") {
    let token = sessionStorage.getItem("token");
    let role = sessionStorage.getItem("role");
    if (token == null || role == null) next("/login");
    else next();
  } else if (to.meta.requiresAuth) {
    if (
      to.meta.requiresAuth.some(
        role => role.toString() === sessionStorage.getItem("role")
      )
    ) {
      //有权限
      // console.log("获得访问权限");
      next()
    } else {
      // console.log("无权限访问");
      next("/"); //无权限,跳回主页
    }
  } else {
    // 非法路径, 直接next (跳转error)
    next();
  }
});

export default router;
