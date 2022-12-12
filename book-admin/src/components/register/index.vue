<template>
  <div class="login-container">
    <el-form
      ref="loginForm"
      :model="refisterForm"
      :rules="loginRules"
      class="login-form"
      auto-complete="on"
      label-position="left"
    >
      <div class="title-container">
        <h3 class="title">图书管理系统</h3>
      </div>

      <el-form-item prop="user.username">
        <span class="svg-container">
          <i class="el-icon-user-solid" />
        </span>
        <el-input
          ref="username"
          v-model="refisterForm.user.username"
          placeholder="请输入用户名"
          name="username"
          type="text"
          tabindex="1"
          auto-complete="on"
        />
      </el-form-item>

      <el-form-item prop="user.password">
        <span class="svg-container">
          <i class="el-icon-lock" />
        </span>
        <el-input
          :key="passwordType"
          ref="password"
          v-model="refisterForm.user.password"
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
      <el-form-item prop="user.newpassword">
        <span class="svg-container">
          <i class="el-icon-lock" />
        </span>
        <el-input
          :key="passwordType"
          ref="password"
          v-model="refisterForm.user.newpassword"
          :type="passwordType"
          placeholder="请确认密码"
          name="newpassword"
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
      <el-form-item prop="reader.name">
        <span class="svg-container">
          <i class="el-icon-user-solid" />
        </span>
        <el-input
          :key="newpasswordType"
          ref="newpassword"
          v-model="refisterForm.reader.name"
          type="text"
          placeholder="请输入姓名"
          name="newpassword"
          tabindex="2"
          auto-complete="on"
          @keyup.enter.native="handleLogin"
        />
      </el-form-item>
      <el-form-item prop="reader.sex">
        <span class="svg-container">
          <i class="el-icon-female" />
        </span>
        <el-radio v-model="refisterForm.reader.sex" label="男">男</el-radio>
        <el-radio v-model="refisterForm.reader.sex" label="女">女</el-radio>
      </el-form-item>
      <el-form-item prop="reader.birth">
        <span class="svg-container">
          <i class="el-icon-time" />
        </span>

        <el-date-picker
          v-model="refisterForm.reader.birth"
          placeholder="请选择出生日期"
          velue-format="yyyy-MM-dd"
          type="date"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item prop="reader.address">
        <span class="svg-container">
          <i class="el-icon-location-information" />
        </span>
        <el-input
          ref="username"
          v-model="refisterForm.reader.address"
          placeholder="请选择地址"
          name="username"
          type="text"
          tabindex="1"
          auto-complete="on"
        />
      </el-form-item>
      <el-form-item prop="reader.telcode">
        <span class="svg-container">
          <i class="el-icon-mobile" />
        </span>
        <el-input
          ref="username"
          v-model="refisterForm.reader.telcode"
          placeholder="请输入手机号"
          name="username"
          type="text"
          tabindex="1"
          auto-complete="on"
        />
      </el-form-item>

      <el-button
        :loading="loading"
        type="primary"
        style="width: 100%; margin-bottom: 30px"
        @click.native.prevent="handleLogin"
        >注 册</el-button
      >
    </el-form>
  </div>
</template>

<script>
import userApi from "@/api/user";
export default {
  name: "Login",

  data() {
    return {
      loginForm: {
        username: "1706010221",
        password: "123456",
        newpassword: "123456",
      },
      loginRules: {
        user: {
          username: [
            { required: true, message: "请输入用户名", trigger: "blur" },
          ],
          password: [
            { required: true, message: "请输入密码", trigger: "blur" },
          ],
          newpassword: [{ validator: this.validatePass, trigger: "blur" }],
        },
        reader: {
          name: [{ required: true, message: "请输入姓名", trigger: "blur" }],
          sex: [{ required: true, message: "请选择性别", trigger: "blur" }],
          birth: [
            { required: true, message: "请选择出生日期", trigger: "blur" },
          ],
          address: [{ required: true, message: "请选择地址", trigger: "blur" }],
          telcode: [
            { required: true, message: "请输入手机号", trigger: "blur" },
          ],
        },
      },
      loading: false,
      passwordType: "password",
      newpasswordType: "password",
      redirect: undefined,
      refisterForm: {
        user: {
          username: "",
          password: "",
          newpassword: "",
        },
        reader: {
          name: "",
          sex: "",
          birth: "",
          address: "",
          telcode:""
        },
      },
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
    validatePass(rule, value, callback) {
      if (value === "") {
        callback(new Error("请输入确认密码"));
      } else {
        let { password, newpassword } = this.refisterForm.user;
        if (password != newpassword) {
          callback(new Error("两次输入不一致"));
        } else {
          callback();
        }
      }
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
          this.loading = true;
          delete this.refisterForm.newpassword;
          userApi
            .register(this.refisterForm)
            .then((r) => {
               
              this.loading = false;
              this.$router.replace({
                path: "/login",
              });
            })
            .catch(() => {
              this.loading = false;
            });
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
// ::deep.el-date-editor--date {
//   .el-input__icon {
//     display: none;
//   }
// }

.login-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 80px 35px 0;
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
<style lang="scss">
.el-date-editor{
  .el-input__icon {
    display: none !important;
  }
}
</style>