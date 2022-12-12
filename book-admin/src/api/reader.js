import request from "@/utils/request"

export default {
  // 请求所有图书
  apiUrl: "admin/reader/",
  page(ops) {
    return request({
      url: this.apiUrl + "page",
      method: "get",
      params: ops,
    });
  },
  //挂失状态修改
  updateGs(ops) {
    return request({
      url: this.apiUrl + "updateGs",
      method: "put",
      data: ops,
    });
  },
  //停借状态修改
  updateStatus(ops) {
    return request({
      url: this.apiUrl + "updateStatus",
      method: "put",
      data: ops,
    });
  },
};