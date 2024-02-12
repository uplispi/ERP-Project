//TEST
const TEST_GET = "/api/test/get";
const TEST_POST = "/api/test/post";

// 用户
const AUTH = "/api/user/auth"
const LOGIN = "/api/user/login"
const REGISTER = '/api/user/register'
const GET_USER = 'api/user/getUser'


// 商品分类管理
const COMMODITY_CLASSIFICATION_ALL = '/api/category/queryAll'
const COMMODITY_CLASSIFICATION_CREATE = '/api/category/create'
const COMMODITY_CLASSIFICATION_UPDATE = '/api/category/update'
const COMMODITY_CLASSIFICATION_DELETE = '/api/category/delete'

// 商品管理
const COMMODITY_ALL = '/api/product/queryAll';
const COMMODITY_CREATE = '/api/product/create';
const COMMODITY_UPDATE = '/api/product/update';
const COMMODITY_DELETE = '/api/product/delete';

// 库存管理
const WAREHOUSE_INPUT = '/api/warehouse/input';
const WAREHOUSE_OUTPUT_PRE = '/api/warehouse/product/count';
const WAREHOUSE_OUTPUT = '/api/warehouse/output';
const WAREHOUSE_GET_INPUTSHEET = '/api/warehouse/inputSheet/state';
const WAREHOUSE_GET_OUTPUTSHEET = '/api/warehouse/outputSheet/state';
const WAREHOUSE_IO_DEATIL_BY_TIME = '/api/warehouse/sheetContent/time';
const WAREHOUSE_IPQ_BY_TIME = '/api/warehouse/inputSheet/time/quantity';
const WAREHOUSE_OPQ_BY_TIME = '/api/warehouse/outputSheet/time/quantity';
const WAREHOUSE_OUTPUTSHEET_APPROVE = '/api/warehouse/outputSheet/approve';
const WAREHOUSE_INPUTSHEET_APPROVE = '/api/warehouse/inputSheet/approve';
const WAREHOUSE_DAILY_COUNT = '/api/warehouse/warehouse/counting';
const WAERHOUSE_DAILY_EXCEL ='/api/warehouse/warehouse/excel';//库存盘点下载excel
// 库存入库单
const WAREHOUSE_GIFT_SHOW = 'api/warehouseGift/getSheetsByState'
const WAREHOUSE_GIFT_APPROVAL = 'api/warehouseGift/approval'

// 销售管理
// 进货管理
const PURCHASE_ALL = '/api/purchase/sheet-show';
const PURCHASE_FIND_SHEET_BY_ID = '/api/purchase/find-sheet';
const PURCHASE_CREATE = '/api/purchase/sheet-make';
const PURCHASE_FIRST_APPROVAL = '/api/purchase/first-approval';
const PURCHASE_SECOND_APPROVAL = '/api/purchase/second-approval';
// 进货退货管理
const PURCHASE_RETURN_ALL = '/api/purchase-returns/sheet-show';
const PURCHASE_RETURN_CREATE = '/api/purchase-returns/sheet-make';
const PURCHASE_RETURN_FIRST_APPROVAL = '/api/purchase-returns/first-approval';
const PURCHASE_RETURN_SECOND_APPROVAL = '/api/purchase-returns/second-approval';
// 销售管理
const SALE_ALL = '/api/sale/sheet-show';
const SALE_CREATE = '/api/sale/sheet-make';
const SALE_FIRST_APPROVAL = '/api/sale/first-approval';
const SALE_SECOND_APPROVAL = '/api/sale/second-approval';
const SALE_FIND_SHEET_BY_ID = '/api/sale/find-sheet';
// 销售退货管理
const SALE_RETURN_ALL = '/api/sale-return/sheet-show';
const SALE_RETURN_CREATE = '/api/sale-return/sheet-make';
const SALE_RETURN_FIRST_APPROVAL = '/api/sale-return/first-approval'
const SALE_RETURN_SECOND_APPROVAL = '/api/sale-return/second-approval'

//销售明细navez
const SALE_DETAIL_QUERY = 'api/salesDetail/query'//查询销售明细表
const SALE_DETAIL_EXCEL = 'api/salesDetail/excel'
const SALE_DETAIL_OPERATOR = 'api/salesDetail/operators'

//制定工资单
const PAYROLL_QUERY_ALL = "/api/payroll/queryAll"//查询所有表单
const PAYROLL_ADD = "/api/payroll/add"//添加表单
const PAYROLL_GET_DETAIL = "api/payroll/getDetail"//获取工资细节
const PAYROLL_UPDATE = "api/payroll/update"//更新表单
const PAYROLL_DELETE = "api/payroll/delete"//删除

// 客户管理
const CUSTOMER_QUERY_BY_TYPE = '/api/customer/findByType';
const CUSTOMER_CREATE = '/api/customer/create'
const SALE_CUSTOMER_QUERY = '/api/customer/findByType';
const CUSTOMER_UPDATE = 'api/customer/update'
const CUSTOMER_QUERY = 'api/customer/queryAll'
const CUSTOMER_DELETE = 'api/customer/delete'

const SALE_PURCHASE_ALL = '/api/purchase/sheet-show';
const SALE_PURCHASE_CREATE = '/api/purchase/sheet-make';
const SALE_CUSTOMER_MAX = '/api/sale/maxAmountCustomer';

// 账户管理
const ACCOUNT_CREATE = 'api/account/create';
const ACCOUNT_UPDATE = 'api/account/update';
const ACCOUNT_DELETE = 'api/account/delete';
const ACCOUNT_SHOW = 'api/account/show';


// 收款单管理
const COLLECTION_CREATE = 'api/collection/create'
const COLLECTION_APPROVAL = 'api/collection/approval'
const COLLECTION_SHOW = 'api/collection/sheet-show'
const COLLECTION_SHOW_BY_ID = 'api/collection/find-sheet'

//促销管理
const PROMOTION_CREATE = 'api/promotion/create'
const PROMOTION_SHOW = 'api/promotion/queryAll'
const PROMOTION_DELETE = 'api/promotion/delete'
const PROMOTION_STRATEGY = 'api/promotion/getStrategy'
const PROMOTION_SHOW_VALID = 'api/promotion/queryValid'

// 付款单管理
const PAYMENT_CREATE = 'api/payment/create'
const PAYMENT_APPROVAL = 'api/payment/approval'
const PAYMENT_SHOW = 'api/payment/sheet-show'
const PAYMENT_SHOW_BY_ID = 'api/payment/find-sheet'

// 员工管理
const EMPLOYEE_CREATE = 'api/employee/create'
const EMPLOYEE_UPDATE = 'api/employee/update'
const EMPLOYEE_DELETE = 'api/employee/delete'
const EMPLOYEE_QUERY_ALL = 'api/employee/queryAll'
const EMPLOYEE_SHOW =  'api/employee/show'

//查看经营历程表
const BUSINESS_HISTORY_QUERY = 'api/businessHistory/querySheet'
const BUSINESS_HISTORY_DOWNLOAD = 'api/businessHistory/downloadSheet'

//查看经营情况表
const OPERATION_GET = 'api/operation/get'
const OPERATION_EXCEL = 'api/operation/excel'
// 员工打卡
const ATTENDANCE_RECORD_CREATE = 'api/attendanceRecord/createRecord'
const ATTENDANCE_RECORD_SHOW_BY_ID = 'api/attendanceRecord/queryById'
const ATTENDANCE_RECORD_SHOW_BY_DATE = 'api/attendanceRecord/queryByDay'
const ATTENDANCE_RECORD_SHOW_BY_TIME = 'api/attendanceRecord/queryByTime'

//HR.PostManagement
const POST_QUERY_ALL = 'api/post/queryAll'
const POST_UPDATE = 'api/post/update'
const POST_CREATE = 'api/post/create'
const POST_DELETE = 'api/post/delete'

//提成管理
const PUSH_MONEY_QUERY_ALL = 'api/pushMoney/queryAll'
const PUSH_MONEY_UPDATE = 'api/pushMoney/update'
const PUSH_MONEY_CREATE = 'api/pushMoney/create'
const PUSH_MONEY_DELETE = 'api/pushMoney/delete'


module.exports = {
  TEST_GET,
  TEST_POST,

  AUTH,
  LOGIN,
  REGISTER,
  GET_USER,

  COMMODITY_CLASSIFICATION_ALL,
  COMMODITY_CLASSIFICATION_CREATE,
  COMMODITY_CLASSIFICATION_UPDATE,
  COMMODITY_CLASSIFICATION_DELETE,

  COMMODITY_ALL,
  COMMODITY_CREATE,
  COMMODITY_UPDATE,
  COMMODITY_DELETE,

  WAREHOUSE_INPUT,
  WAREHOUSE_OUTPUT_PRE,
  WAREHOUSE_OUTPUT,
  WAREHOUSE_GET_INPUTSHEET,
  WAREHOUSE_GET_OUTPUTSHEET,
  WAREHOUSE_IO_DEATIL_BY_TIME,
  WAREHOUSE_IPQ_BY_TIME,
  WAREHOUSE_OPQ_BY_TIME,
  WAREHOUSE_OUTPUTSHEET_APPROVE,
  WAREHOUSE_INPUTSHEET_APPROVE,
  WAREHOUSE_DAILY_COUNT,
  WAERHOUSE_DAILY_EXCEL,//下载库存盘点EXCEL
  WAREHOUSE_GIFT_SHOW, // 库存赠品单获取
  WAREHOUSE_GIFT_APPROVAL, // 库存赠品单审批

  PURCHASE_ALL,
  PURCHASE_CREATE,
  PURCHASE_FIRST_APPROVAL,
  PURCHASE_SECOND_APPROVAL,
  PURCHASE_RETURN_ALL,
  PURCHASE_RETURN_CREATE,
  PURCHASE_RETURN_FIRST_APPROVAL,
  PURCHASE_RETURN_SECOND_APPROVAL,
  PURCHASE_FIND_SHEET_BY_ID,

  SALE_ALL,
  SALE_CREATE,
  SALE_FIRST_APPROVAL,
  SALE_SECOND_APPROVAL,
  SALE_CUSTOMER_QUERY,
  SALE_CUSTOMER_MAX,
  SALE_FIND_SHEET_BY_ID,

  SALE_RETURN_ALL,
  SALE_RETURN_CREATE,
  SALE_RETURN_FIRST_APPROVAL,
  SALE_RETURN_SECOND_APPROVAL,

  SALE_DETAIL_QUERY,
  SALE_DETAIL_EXCEL,
  SALE_DETAIL_OPERATOR,

  PAYROLL_QUERY_ALL,
  PAYROLL_ADD,
  PAYROLL_GET_DETAIL,
  PAYROLL_UPDATE,
  PAYROLL_DELETE,

  CUSTOMER_QUERY_BY_TYPE,
  CUSTOMER_CREATE,
  CUSTOMER_UPDATE,
  CUSTOMER_QUERY,
  CUSTOMER_DELETE,

  ACCOUNT_CREATE,
  ACCOUNT_UPDATE,
  ACCOUNT_DELETE,
  ACCOUNT_SHOW,

  COLLECTION_CREATE,
  COLLECTION_APPROVAL,
  COLLECTION_SHOW,
  COLLECTION_SHOW_BY_ID,

  PROMOTION_CREATE,
  PROMOTION_SHOW,
  PROMOTION_DELETE,
  PROMOTION_STRATEGY,
  PROMOTION_SHOW_VALID,

  PAYMENT_CREATE,
  PAYMENT_APPROVAL,
  PAYMENT_SHOW,
  PAYMENT_SHOW_BY_ID,

  EMPLOYEE_CREATE,
  EMPLOYEE_UPDATE,
  EMPLOYEE_DELETE,
  EMPLOYEE_QUERY_ALL,
  EMPLOYEE_SHOW,

  POST_QUERY_ALL,
  POST_CREATE,
  POST_DELETE,
  POST_UPDATE,

  PUSH_MONEY_CREATE,
  PUSH_MONEY_DELETE,
  PUSH_MONEY_QUERY_ALL,
  PUSH_MONEY_UPDATE,

  ATTENDANCE_RECORD_CREATE,
  ATTENDANCE_RECORD_SHOW_BY_ID,
  ATTENDANCE_RECORD_SHOW_BY_DATE,
  ATTENDANCE_RECORD_SHOW_BY_TIME,

  //查看经营历程
  BUSINESS_HISTORY_QUERY,
  BUSINESS_HISTORY_DOWNLOAD,

  //查看经营情况表
  OPERATION_GET,
  OPERATION_EXCEL
};
