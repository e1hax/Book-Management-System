<template>
  <div class="header">
    <div class="header-left">
      <div class="logo" @click="toBook">
        <i class="el-icon-notebook-1 icon"></i>
        <span class="title">图书管理系统</span>
      </div>
      <ul v-if="role=='user'" class="menu-list">
        <li
         v-if="item.title"
          @click="selectNav(item)"
          :class="{ active: $route.path.includes(item.path) }"
          v-for="(item, index) in userRoutes"
          :key="index"
          :id="item.path"
        >
          {{ item.title }}
        </li>
      </ul>
      <ul v-else class="menu-list">
        <li
         v-if="item.title"
          @click="selectNav(item)"
          :class="{ active: $route.path.includes(item.path) }"
          v-for="(item, index) in adminRoutes"
          :key="index"
          :id="item.path"
        >
          {{ item.title }}
        </li>
      </ul>
    </div>
    <div class="header-right">
      <span class="user">{{(info?.name||usertype.username)}},您好</span>
      <el-popover
        width="150"
        popper-class="userinfo"
        placement="bottom"
        trigger="click"
      >
        <div class="info">
          <ul v-if="userInfo.length">
            <li v-for="(item, index) in userInfo" :key="index">
              <span>{{ titles[index] }}:</span>
              <div v-if="item.isEdit" @click="setEdit(item, index)">
                {{ item.val }}
              </div>
              <el-input
                :ref="index"
                @blur="blurHandler(item)"
                size="mini"
                v-else
                v-model="item.val"
              ></el-input>
            </li>
          </ul>
          <div v-else>{{usertype.username}}</div>

          <div class="logout" @click="logout">退出登录</div>
        </div>
        <i slot="reference" class="el-icon-caret-bottom"></i>
      </el-popover>
    </div>
  </div>
</template>
<script>
import { constantRoutes, adminRoutes, userRoutes } from "@/router/routes";
import userApi from "@/api/user";
import { mapState } from "vuex";
export default {
  name: "Header",
  data() {
    return {
      navList: this.$store.state.menuRoutes,
      userInfo: [],
      titles: ["姓名", "性别", "地址", "电话"],
      info:{},
      role:this.$store.state.usertype.role,
      adminRoutes:adminRoutes, userRoutes:userRoutes
    };
  },
  computed: {
    ...mapState({
      usertype: (state) => state.usertype,
    }),
  },
  mounted() {
    this.$store.dispatch('setUserType')
    this.readerInfo();
    if(this.role=='user'){
      document.getElementById('/bookall').click()
      // this.$refs['/book'].click()
    }else{
      document.getElementById('/book').click()
// this.$refs['/bookAll'].click()
    }
    // console.log( document.getElementById('/book'));
    // console.log(document.getElementById('/bookall'));

  },
  methods: {
    blurHandler(item) {
      item.isEdit = true;
      this.info[item.title]=item.val
      userApi.updateInfo({...this.info}).then((r)=>{
      })
    },
    setEdit(item, index) {
      item.isEdit = false;
      this.$nextTick(() => {
        // this.$refs[item.val].focus();
        this.$refs[index][0].focus();
      });
    },
    selectNav(item) {
      console.log(item);
      this.$router.push({
        path: item.path,
      });
    },
    logout() {
      localStorage.removeItem("token")
      this.$router.replace({
        path: "/login",
      });
      this.$store.dispatch('setMenuRoutes',[])
    },
    toBook() {
      this.$router.push({
        path: "/book",
      });
    },
    readerInfo() {
      // console.log(this.usertype.username);
      if(!this.usertype.username){
        this.userInfo=[]
        return 
      }
      if(this.usertype.username=="admin"){
        this.userInfo=[]
        return
      }
      let flags = [
        // "readerId",
        "name",
        "sex",
        // "birth",
        "address",
        "telcode",
        // "gs",
        // "status",
      ];
      userApi.readerInfo(this.usertype.username).then((r) => {
        this.info=r.data
        let arr = Object.keys(r.data).map((item) => {
          if (flags.includes(item)) {
            return {
              val: r.data[item],
              isEdit: true,
              title:item
            };
          }
        });
        let newArr = [];
        arr.forEach((item, index) => {
          if (item != undefined) {
            newArr.push(item);
          }
        });
        this.userInfo = newArr;
        
      });
    },
  },
};
</script>

<style lang="less" scoped>
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 50px;
  padding: 20px;
  background: linear-gradient(70deg, #29c9fd, #ae2afe);
  color: #fff;
  .header-left {
    display: flex;
    align-items: center;
    .logo {
      font-size: 30px;
      display: flex;
      align-items: center;
      cursor: pointer;
      .icon {
        font-size: 33px;
      }
      .title {
        margin-left: 10px;
      }
    }
    .menu-list {
      display: flex;
      font-size: 20px;
      li {
        margin: 40px;
        cursor: pointer;
        color: rgb(240, 228, 228);
      }
      .active {
        color: #fff;
        font-weight: bolder;
      }
    }
  }
  .header-right {
    font-size: 16px;
    .user {
      margin-right: 5px;
    }
  }
}
</style>
<style lang="less" scoped>
.userinfo {
  .info {
    line-height: 30px;
    ul {
      li {
        display: flex;
        span {
          width: 50px;
        }
        /deep/.el-input {
          font-size: 14px !important;
        }
      }
    }
    .logout {
      border-top: 1px solid #ccc;
      cursor: pointer;
    }
  }
}
</style>