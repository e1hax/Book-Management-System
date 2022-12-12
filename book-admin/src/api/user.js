import request from "@/utils/request"

export default {
  apiUrl:'user/reader/',
  
  login(ops) {
    return request({
      url: 'login',
      method: "post",
      data: ops,
    });
  },
  register(ops) {
    return request({
      url: 'register',
      method: "post",
      data: ops,
    });
  },
  readerInfo(userid){
    return request({
      url:this.apiUrl+'readerInfo/'+userid
    })
  },
  updateInfo(ops){
    return request({
      url:this.apiUrl+'updateInfo/',
      method:"put",
      data:ops
    })
  }
};