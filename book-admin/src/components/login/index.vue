<template>
  <div class="login-container">
    <el-form
      ref="loginForm"
      :model="loginForm"
      :rules="loginRules"
      class="login-form"
      auto-complete="on"
      label-position="left"
    >
      <div class="title-container">
        <h3 class="title">图书管理系统</h3>
      </div>

      <el-form-item prop="username">
        <span class="svg-container">
          <i class="el-icon-user-solid" />
        </span>
        <el-input
          size="mini"
          ref="username"
          v-model="loginForm.username"
          placeholder="请输入用户名"
          name="username"
          type="text"
          tabindex="1"
          auto-complete="on"
        />
      </el-form-item>

      <el-form-item prop="password">
        <span class="svg-container">
          <i class="el-icon-lock" />
        </span>
        <el-input
          size="mini"
          :key="passwordType"
          ref="password"
          v-model="loginForm.password"
          :type="passwordType"
          placeholder="请输入密码"
          name="password"
          tabindex="2"
          auto-complete="on"
          @keyup.enter.native="handleLogin"
        />
        <span class="show-pwd" @click="showPwd">
          <i
            :class="
              passwordType === 'password' ? 'el-icon-turn-off' : 'el-icon-open'
            "
          />
        </span>
      </el-form-item>

      <el-button
        :loading="loading"
        type="primary"
        style="width: 100%; margin-bottom: 30px"
        @click.native.prevent="handleLogin"
        >登 陆</el-button
      >
    </el-form>
    <button
      class="register"
      style="width: 100%; margin-bottom: 30px"
      @click.prevent="toRegister"
    >
      没有账号，去注册
    </button>
  </div>
</template>

<script>
import userApi from "@/api/user";
export default {
  name: "Login",

  data() {
    const validateUsername = (rule, value, callback) => {
      if (value.length < 4) {
        callback(new Error("用户名长度不能小于4位"));
      } else {
        callback();
      }
    };
    const validatePassword = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error("密码长度不能小于6位"));
      } else {
        callback();
      }
    };
    return {
      loginForm: {
        username: "",
        password: "",
        newpassword: "",
      },
      loginRules: {
        username: [{ required: true, validator: validateUsername }],
        password: [
          { required: true, trigger: "blur", validator: validatePassword },
        ],
      },
      loading: false,
      passwordType: "password",
      redirect: undefined,
    };
  },

  watch: {
    $route: {
      handler: function (route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true, // 监视回调初始就会执行一次, 而不是改变才调用
    },
  },

  methods: {
    toRegister() {
      this.$router.push({
        path: "/register",
      });
    },
    /* 
    切换密码的显示/隐藏
    */
    showPwd() {
      if (this.passwordType === "password") {
        this.passwordType = "text";
      } else {
        this.passwordType = "password";
      }
      this.$nextTick(() => {
        this.$refs.password.focus();
      });
    },
    newshowPwd() {
      if (this.newpasswordType === "password") {
        this.newpasswordType = "text";
      } else {
        this.newpasswordType = "password";
      }
      this.$nextTick(() => {
        this.$refs.newpassword.focus();
      });
    },

    /* 
    点击登陆的回调
    */
    handleLogin() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          if (this.$route.path == "/login") {
            this.loading = true;
            delete this.loginForm.newpassword;
            userApi
              .login(this.loginForm)
              .then( (r) => {
                let token = JSON.stringify(r.data);
                localStorage.setItem("token", token);
                this.loading = false;
                this.$router.replace({
                  path: "/",
                });
              })
              .catch(() => {
                this.loading = false;
              });
          }
        }
      });
    },
  },
};
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg: #283443;
$light_gray: #fff;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}
.register {
  width: 520px !important;
  margin-left: 50%;
  transform: translate(-50%);
  font-size: 14px;
  color: #ccc;
  background-color: rgba(0, 0, 0, 0);
  border: none;
  cursor: pointer;
}
/* reset element-ui css */
.login-container {
  width: 100%;
  height: 100%;
  background-image: url(~@/assets/bg.jpg);
  background-color: #2d3a4b;
  background-size: cover;
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>
$bg: #2d3a4b;
$dark_gray: #889aa4;
$light_gray: #eee;

.login-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
}
</style>
