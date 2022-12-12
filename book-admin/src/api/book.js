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
  add(ops) {
    return request({
      url: this.apiUrl + "add",
      method: "post",
      data: ops,
    });
  },

  updateBook(ops) {
    return request({
      url: this.apiUrl + "updateBook",
      method: "put",
      data: ops,
    });
  },

  returnBook(ops) {
    return request({
      url: this.apiUrl + "returnBook",
      method: "put",
      data: ops,
    });
  },
  deleteBook(id) {
    return request({
      url: this.apiUrl + "delete/"+id,
      method: "delete",
    });
  },
  borrowBook(ops) {
    return request({
      url: this.apiUrl + "borrowBook/",
      method: "put",
      data: ops,
    });
  },
};