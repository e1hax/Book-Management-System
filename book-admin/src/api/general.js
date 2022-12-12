import request from "@/utils/request";

export default {
  // 请求所有图书
  apiUrl: "user/record/",
  recordPage(ops) {
    return request({
      url: this.apiUrl + "recordPage",
      method: "get",
      params: ops,
    });
  },
};
