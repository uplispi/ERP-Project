
export const ROLE = {
  INVENTORY_MANAGER: "INVENTORY_MANAGER", //库存管理人员
  SALE_STAFF: "SALE_STAFF", // 进货销售人员
  SALE_MANAGER: "SALE_MANAGER", //销售经理
  FINANCIAL_STAFF: "FINANCIAL_STAFF", // 财务人员
  HR: "HR", // 人力资源人员
  GM: "GM", // 总经理
  ADMIN: "ADMIN" // 最高权限
};


export const PATH = {
  // INVENTORY_MANAGER
  COMMODITY_CLASSIFICATION: {
    path: '/commodityClassification',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },
  COMMODITY_MANAGEMENT: {
    path: '/commodityManagement',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },
  INVENTORY_MANAGEMENT: {
    path: '/inventoryManagement',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },
  INVENTORY_OPERATION: {
    path: '/inventoryOperation',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },
  INVENTORY_CHECK: {
    path: '/inventoryCheck',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },
  INVENTORY_IN: {
    path: '/inventoryIn',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },
  INVENTORY_OUT: {
    path: '/inventoryOut',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },
  INVENTORY_LOSS: {
    path: '/inventoryLoss',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },
  INVENTORY_WARNING: {
    path: '/inventoryWarning',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },
  INVENTORY_OVERFLOW: {
    path: '/inventoryOverflow',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },
  INVENTORY_PRESENT: {
    path: '/inventoryPresent',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },
  INVENTORY_VIEW: {
    path: '/inventoryView',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },
  // 库存赠品单
  INVENTORY_GIFT_VIEW: {
    path: '/inventoryGift',
    requiresAuth: [ROLE.ADMIN, ROLE.GM]
  },

  // SALE_STAFF & SALE_MANAGER
  PURCHASE_VIEW: {
    path: '/purchaseView',
    requiresAuth: [ROLE.SALE_STAFF,ROLE.GM,ROLE.SALE_MANAGER,ROLE.ADMIN]
  },
  PURCHASE_RETURN_VIEW: {
    path: '/purchaseReturnView',
    requiresAuth: [ROLE.SALE_STAFF,ROLE.SALE_MANAGER,ROLE.GM,ROLE.ADMIN]
  },
  SALE_VIEW: {
    path: '/saleView',
    requiresAuth: [ROLE.SALE_STAFF,ROLE.SALE_MANAGER,ROLE.GM,ROLE.ADMIN]
  },
  SALE_RETURN_VIEW: {
    path: '/saleReturnView',
    requiresAuth: [ROLE.SALE_STAFF,ROLE.SALE_MANAGER,ROLE.GM,ROLE.ADMIN]
  },
  CUSTOMER_VIEW: {
    path: '/customerView',
    requiresAuth: [ROLE.SALE_STAFF,ROLE.SALE_MANAGER,ROLE.ADMIN]
  },

  SALE_PROMOTION: {
    path: '/promotion',
    requiresAuth: [ROLE.GM,ROLE.ADMIN]
  },

  // GM
  GM_APPROVAL: {
    path: '/approval',
    requiresAuth: [ROLE.GM,ROLE.ADMIN]
  },


  // FINANCIAL_STAFF 财务人员
  FINANCE: {
    path: '/finance',
    requiresAuth: [ROLE.FINANCIAL_STAFF, ROLE.ADMIN]
  },
    // 账户管理
  ACCOUNT_MANAGEMENT_VIEW: {
    path: '/accountManagement',
    requiresAuth: [ROLE.FINANCIAL_STAFF,  ROLE.ADMIN]
  },

  SALES_DETAIL_VIEW:{
    path:'/salesDetail',
    requiresAuth: [ROLE.FINANCIAL_STAFF, ROLE.ADMIN, ROLE.GM]
  },

  PAYROLL_VIEW:{
    path:'/payroll',
    requiresAuth: [ROLE.FINANCIAL_STAFF, ROLE.ADMIN, ROLE.GM, ROLE.HR]
  },

    // 收款单
  COLLECTION_SHEET_VIEW: {
    path: '/collectionSheetView',
    requiresAuth: [ROLE.FINANCIAL_STAFF, ROLE.GM, ROLE.ADMIN]
  },

    // 付款单
  PAYMENT_SHEET_VIEW: {
    path: '/paymentView',
    requiresAuth: [ROLE.FINANCIAL_STAFF, ROLE.GM, ROLE.ADMIN]
  },


  //  员工管理
  HR_VIEW: {
    path:"/EmployeeManagement",
    requiresAuth: [ROLE.HR, ROLE.ADMIN]
  },

  //销售历程
  BUSINESS_HISTORY:{
    path:"/BusinessHistory",
    requiresAuth: [ROLE.FINANCIAL_STAFF, ROLE.ADMIN, ROLE.GM ]
  },

  //销售情况
  OPERATION_VIEW:{
    path:"/operation",
    requiresAuth:[ROLE.FINANCIAL_STAFF, ROLE.ADMIN, ROLE.GM]
  },
  // 岗位
  POST_VIEW: {
    path: '/PostManagement',
    requiresAuth: [ROLE.HR, ROLE.ADMIN]
  },

  //提成管理
  PUSH_MONEY_VIEW: {
    path: '/pushMoneyManagement',
    requiresAuth: [ROLE.ADMIN, ROLE.HR]
  },

  // 打卡管理
  ATTENDANCE_RECORD_VIEW: {
    path: "/attendanceRecord",
    requiresAuth: [ROLE.ADMIN,ROLE.HR, ROLE.SALE_MANAGER, ROLE.INVENTORY_MANAGER,ROLE.FINANCIAL_STAFF, ROLE.SALE_STAFF]
  },


}
