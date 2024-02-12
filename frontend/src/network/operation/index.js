import request from "@/network/request";
const testAPI = require("@/apis")

export const getOperatons = config => request._get(testAPI.OPERATION_GET, config);
export const downloadExcel = config => request._download(testAPI.OPERATION_EXCEL, config);

