import Vue from "vue";
import Vuex from "vuex";
import { constantRoutes, adminRoutes, userRoutes } from "@/router/routes";
Vue.use(Vuex);

const state = {
  usertype:{},
  menuRoutes:constantRoutes
};
const mutations = {
  SETUSERTYPE(state,usertype){
    state.usertype=usertype
  },
  SETMENUROUTES(state,menuRoutes){
    state.menuRoutes=menuRoutes
  }
};
const actions = {
  setUserType({commit}){
    let usertype= JSON.parse(localStorage.getItem("token"))||{}
    commit('SETUSERTYPE',usertype)
  },
  setMenuRoutes({commit},menuRoutes){
    commit('SETMENUROUTES',menuRoutes)
  }
};
const getters = {};
export default new Vuex.Store({
  state,
  mutations,
  actions,
  getters,
  modules: {
  },
});
