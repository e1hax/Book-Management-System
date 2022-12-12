import axios from "axios"
import NProgress from "nprogress"
import "nprogress/nprogress.css"
const instance = axios.create({
    // baseURL: "http://43.143.63.236:8081/admin/",
    baseURL: "/api",
    timeout: 200000
})
//请求拦截器
instance.interceptors.request.use(
    (config) => {
        NProgress.start()
        return config
    },
    (error) => {
        return Promise.reject(error)
    }
)

// 响应拦截器
instance.interceptors.response.use(
    (response)=>{
        NProgress.done()
        return response.data
    },
    (error)=>{
        // this.$message.error("ajax请求失败:"+error.message);
        NProgress.done()
        return Promise.reject(error)
    }
)

export default instance