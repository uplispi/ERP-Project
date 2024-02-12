import request from "@/network/request"
const testAPI = require("@/apis")

export const getAllPurchase = config => request._get(testAPI.PURCHASE_ALL, config)
export const createPurchase = config => request._post(testAPI.PURCHASE_CREATE, config)
export const firstApproval = config => request._get(testAPI.PURCHASE_FIRST_APPROVAL, config)
export const secondApproval = config => request._get(testAPI.PURCHASE_SECOND_APPROVAL, config)
export const getAllPurchaseReturn = config => request._get(testAPI.PURCHASE_RETURN_ALL, config)
export const createPurchaseReturn = config => request._post(testAPI.PURCHASE_RETURN_CREATE, config)
export const returnFirstApproval = config => request._get(testAPI.PURCHASE_RETURN_FIRST_APPROVAL, config)
export const returnSecondApproval = config => request._get(testAPI.PURCHASE_RETURN_SECOND_APPROVAL, config)
export const getCustomerByType = config => request._get(testAPI.CUSTOMER_QUERY_BY_TYPE, config)
export const getPurchaseBySheetId = config => request._get(testAPI.PURCHASE_FIND_SHEET_BY_ID, config)

// 获取所有顾客的API
export const getAllCustomer = config => request._get(testAPI.CUSTOMER_QUERY, config)
// 添加客户的API
export const createCustomer = config => request._post(testAPI.CUSTOMER_CREATE, config)
// 修改客户信息的API
export const updateCustomer = config => request._post(testAPI.CUSTOMER_UPDATE, config)
// 删除客户的API
export const deleteCustomer = config => request._delete(testAPI.CUSTOMER_DELETE, config)