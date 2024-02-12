import request from "@/network/request";
const testAPI = require("@/apis")

export const deleteCommodityClassification = config => request._get(testAPI.COMMODITY_CLASSIFICATION_DELETE, config);
export const querySheet = config => request._get(testAPI.BUSINESS_HISTORY_QUERY, config)
export const downloadSheet = config => request._download(testAPI.BUSINESS_HISTORY_DOWNLOAD, config)