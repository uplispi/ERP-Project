import request from "@/network/request";
const testAPI = require("@/apis"); //此处务必使用require导入（因为是module.exports导出的...）

export const createAccount = config => request._post(testAPI.ACCOUNT_CREATE, config);
export const updateAccount = config => request._post(testAPI.ACCOUNT_UPDATE, config);
export const deleteAccount = config => request._delete(testAPI.ACCOUNT_DELETE, config);
export const showAccount = config => request._get(testAPI.ACCOUNT_SHOW, config);
