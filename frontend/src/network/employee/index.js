import request from "@/network/request";
const testAPI = require("@/apis")

export const createEmployee = config => request._post(testAPI.EMPLOYEE_CREATE, config);
export const updateEmployee = config => request._post(testAPI.EMPLOYEE_UPDATE, config);
export const deleteEmployee = config => request._delete(testAPI.EMPLOYEE_DELETE, config);
export const queryAllEmployee = config => request._get(testAPI.EMPLOYEE_QUERY_ALL, config);
export const showEmployee = config => request._get(testAPI.EMPLOYEE_SHOW, config);
