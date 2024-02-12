import request from "@/network/request";
const testAPI = require("@/apis")

export const queryAllPost = config => request._get(testAPI.POST_QUERY_ALL, config);
export const createPost = config => request._post(testAPI.POST_CREATE, config);
export const updatePost = config => request._post(testAPI.POST_UPDATE, config);
export const deletePost = config => request._delete(testAPI.POST_DELETE, config);