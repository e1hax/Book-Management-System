import request from "@/utils/request"

export default {
  // 请求所有图书
  apiUrl: "admin/book/",
  page(ops) {
    return request({
      url: this.apiUrl + "pageSearch",
      method: "get",
      params: ops,
    });
  },
};