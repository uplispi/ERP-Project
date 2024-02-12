import request from "@/network/request";
const testAPI = require("@/apis")

export const queryAllPushMoney = config => request._get(testAPI.PUSH_MONEY_QUERY_ALL, config);
export const createPushMoney = config => request._post(testAPI.PUSH_MONEY_CREATE, config);
export const updatePushMoney = config => request._post(testAPI.PUSH_MONEY_UPDATE, config);
export const deletePushMoney = config => request._delete(testAPI.PUSH_MONEY_DELETE, config);