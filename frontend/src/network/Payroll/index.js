import request from "@/network/request";
const testAPI = require("@/apis")

export const queryAll = config => request._get(testAPI.PAYROLL_QUERY_ALL, config)
export const add = config => request._post(testAPI.PAYROLL_ADD, config)
export const getDetail = config => request._get(testAPI.PAYROLL_GET_DETAIL, config)
export const update = config => request._post(testAPI.PAYROLL_UPDATE, config)
export const deletePayroll = config => request._delete(testAPI.PAYROLL_DELETE, config)
