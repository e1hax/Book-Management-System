import router from './router'
import store from './store'
import { Message } from 'element-ui'
import { constantRoutes, adminRoutes, userRoutes } from "@/router/routes";
import NProgress from 'nprogress' // 水平进度条提示: 在跳转路由时使用
import 'nprogress/nprogress.css' // 水平进度条样式
NProgress.configure({ showSpinner: false }) // 配置NProgress: 不显示右侧旋转进度条


// 不用进行token检查的白名单路径数组
const whiteList = ['/login','/register']

// 注册全局前置守卫: 在路由准备跳转前执行
router.beforeEach((to, from, next) => {

  store.dispatch('setUserType')
  // 在显示进度条
  NProgress.start()

  // 设置整个页面的标题
  // document.title = getPageTitle(to.meta.title)

  // 获取cookie中保存的token
  const token = store.state.usertype
  // 如果token存在(已经登陆或前面登陆过)
  if (token.username) {
	  // if (token.role == 'user') {
	      // userRoutes.forEach(item => {
	      //   router.addRoute(item)
	      // })
      //  let index= constantRoutes.findIndex((item)=>{
      //     item.redirect=='/bookall'
      //   })
      //   constantRoutes.splice(index,1)
        // constantRoutes.push({ path: '/',
        // redirect: '/bookall',})
	      // router.addRoutes(userRoutes)
	      // routes = [...constantRoutes, ...userRoutes]
	      // store.dispatch('setMenuRoutes',[...userRoutes,...constantRoutes])
	    // } else {
        // let index= constantRoutes.findIndex((item)=>{
        //   item.redirect=='/book'
        // })
        // constantRoutes.splice(index,1)
        // constantRoutes.push({ path: '/',
        // redirect: '/book',})
	      // adminRoutes.forEach(item => {
	      //   router.addRoute(item)
	      // router.addRoutes(adminRoutes)
	      // })
	      // store.dispatch('setMenuRoutes',[...adminRoutes,...constantRoutes])
	      // routes = [...constantRoutes, ...adminRoutes]
	    // }
    // console.log(store.state.menuRoutes,222222)
    // console.log(router,222222)
    next()
  } else { // 没有token
    // 如果目标路径在白名单中(是不需要token的路径)
    if (whiteList.indexOf(to.path) !== -1) {
      // 放行
      next()
    } else {
      // 如果没在白名单中, 跳转到登陆路由携带原目标路径
      next(`/login`)
    }
  }
})

// // // 注册全局后置守卫: 在路由跳转完成后执行
router.afterEach(() => {
  // console.log('afterEnter callback()')
  // 完成(隐藏)进度条显示
  NProgress.done()
})
