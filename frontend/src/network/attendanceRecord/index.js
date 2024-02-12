import request from "@/network/request";
const testAPI = require("@/apis"); //此处务必使用require导入（因为是module.exports导出的...）

export const createAttendanceRecord = config => request._post(testAPI.ATTENDANCE_RECORD_CREATE, config);
export const showAttendanceRecordById = config => request._get(testAPI.ATTENDANCE_RECORD_SHOW_BY_ID, config);
export const showAttendanceRecordByDate = config => request._get(testAPI.ATTENDANCE_RECORD_SHOW_BY_DATE, config)
export const showAttendanceRecordByTime = config => request._get(testAPI.ATTENDANCE_RECORD_SHOW_BY_TIME, config)