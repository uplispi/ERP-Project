import request from "@/network/request"
const testAPI = require("@/apis")

export const getSalesDetail = config => request._get(testAPI.SALE_DETAIL_QUERY, config);
export const downloadSalesDetail = config => request._download(testAPI.SALE_DETAIL_EXCEL, config)
export const findAllOperators = config => request._get(testAPI.SALE_DETAIL_OPERATOR, config)