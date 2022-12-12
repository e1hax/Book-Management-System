import request from "@/utils/request"

export default {
  // 请求所有图书
  apiUrl: "admin/record/",
  page(ops) {
    return request({
      url: this.apiUrl + "page",
      method: "get",
      params: ops,
    });
  },
  userpage(ops) {
    return request({
      url: this.apiUrl + "userpage",
      method: "get",
      params: ops,
    });
  },
};