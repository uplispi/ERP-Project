import request from "@/network/request";
const testAPI = require("@/apis")

// 收款单
export const createCollectionSheet = config => request._post(testAPI.COLLECTION_CREATE, config);
export const approvalCollectionSheet = config => request._get(testAPI.COLLECTION_APPROVAL, config);
export const getAllCollectionSheet = config => request._get(testAPI.COLLECTION_SHOW, config);
export const getCollectionSheetById = config => request._get(testAPI.COLLECTION_SHOW_BY_ID, config);


// 付款单
export const createPaymentSheet = config => request._post(testAPI.PAYMENT_CREATE, config)
export const approvalPaymentSheet = config => request._get(testAPI.PAYMENT_APPROVAL, config)
export const getAllPaymentSheet = config => request._get(testAPI.PAYMENT_SHOW, config)
export const getPaymentSheetById = config => request._get(testAPI.PAYMENT_SHOW_BY_ID, config)