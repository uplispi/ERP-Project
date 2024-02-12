import request from "@/network/request"
const testAPI = require("@/apis")

//获取所有方案
export const getAllPromotion = config => request._get(testAPI.PROMOTION_SHOW, config)

//新增方案
export const createPromotion = config => request._post(testAPI.PROMOTION_CREATE, config)

//删除方案
export const deletePromotion = config => request._delete(testAPI.PROMOTION_DELETE, config)

// 获取有效的促销方案
export const getValidPromotion = config => request._get(testAPI.PROMOTION_SHOW_VALID, config)
