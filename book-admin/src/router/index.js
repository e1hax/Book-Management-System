import Vue from "vue";
import VueRouter from "vue-router";
import { constantRoutes, adminRoutes, userRoutes } from "./routes";
import store from "@/store";
Vue.use(VueRouter);

const originPush = VueRouter.prototype.push;
const originReplace = VueRouter.prototype.replace;

VueRouter.prototype.push = function (location, resolved, rejected) {
  if (resolved == undefined && rejected == undefined) {
    return originPush.call(this, location).catch(() => { });
  } else {
    return originPush.call(this, location, resolved, rejected);
  }
};
VueRouter.prototype.replace = function (location, resolved, rejected) {
  if (resolved == undefined && rejected == undefined) {
    return originReplace.call(this, location).catch(() => { });
  } else {
    return originReplace.call(this, location, resolved, rejected);
  }
};

// let routes = []
// export function setRoutes(routes) {
//   store.dispatch('setUserType')
//   const usertype = store.state.usertype
//   if (usertype.role == 'user') {
//     // userRoutes.forEach(item => {
//     //   if (item.name) {
//     //     router.addRoute(item)
//     //   }
//     // })
//     routes = [...constantRoutes, ...userRoutes]
//   } else {
//     // adminRoutes.forEach(item => {
//     //   if (item.name) {
//     //     router.addRoute(item)
//     //   }
//     // })
//     routes = [...constantRoutes, ...adminRoutes]
//   }
// }

const router = new VueRouter({
  mode: 'history',
  routes: store.state.menuRoutes,
  scrollBehavior() {
    return { y: 0 };
  },
});
export default router;
