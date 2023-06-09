<template>
  <div class="login">
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" label-width="100px" class="login-form">

      <h3 class="title">知其后台管理系统</h3>

      <el-form-item prop="validateUsername">
        <el-input type="text" v-model="loginForm.username" autocomplete="off" placeholder="用户名">
          <svg-icon slot="prefix" icon-class="user" class="el-input__icon input-icon" />
        </el-input>
      </el-form-item>

      <el-form-item prop="validatePassword">
        <el-input type="password" v-model="loginForm.password" autocomplete="off" placeholder="密码"
          @keyup.enter.native="handleLogin">
          <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon" />
        </el-input>
      </el-form-item>

      <el-form-item prop="code" v-if="captchaOnOff">
        <el-input v-model="loginForm.code" auto-complete="off" placeholder="验证码" style="width:63%"
          @keyup.enter.native="handleLogin">
          <svg-icon slot="prefix" icon-class="validCode" class="el-input__icon input-icon" />
        </el-input>
        <div class="valid-code">
          <img :src="codeUrl" @click="getKaptchaCode" class="valid-code-image" />
        </div>
      </el-form-item>

      <el-form-item>
        <el-checkbox class="remember-me-checkbox" v-model="loginForm.rememberMe">记住密码</el-checkbox>
      </el-form-item>

      <el-form-item style="width: 100%">
        <el-button :loading="loading" type="primary" size="medium" style="width: 100%"
          @click.native.prevent="handlerLogin">
          <span v-if="!loading">登录</span>
          <span v-else>登 录 中...</span>
        </el-button>

        <div style="float: right;" v-if="register">
          <router-link class="link-type" :to="'/register'">前往注册</router-link>
        </div>
      </el-form-item>
    </el-form>

    <!-- 底部 -->
    <div class="el-login-footer">
      <span>Copyright © 2023-2023 ZhiQi All Rights Reserved.</span>
    </div>
  </div>
</template>

<script>
import { getCodeImg } from '@/api/login'
import Cookies from 'js-cookie'
import { encrypt, decrypt } from "@/utils/jsencrypt";

export default {
  name: 'Login',
  data() {
    return {
      codeUrl: '',
      cookiePassword: '',
      loading: false,
      // 验证码开关
      captchaOnOff: true,
      // 注册开关
      register: true,
      redirect: undefined,

      loginForm: {
        username: 'admin',
        password: 'admin123',
        rememberMe: false,
        code: '',
        uuid: ''
      },
      loginRules: {
        validateUsername: [
          { require: true, trigger: 'blur', message: '请输入您的账号' }
        ],
        validatePassword: [
          { require: true, trigger: 'blur', message: '请输入您的密码' }
        ],
        validateCode: [
          { require: true, trigger: 'change', message: '请输入验证码' }
        ]
      }
    }
  },
  watch: {
    $router: {
      handler(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  created() {
    this.getKaptchaCode()
    this.getUserInfoFromCookie()
  },
  methods: {
    getKaptchaCode() {
      getCodeImg().then(res => {
        // captcha request and show
        this.captchaOnOff = res.captchaOnOff === undefined ? true : res.captchaOnOff;
        if (this.captchaOnOff) {
          this.codeUrl = "data:image/gif;base64," + res.img
          this.loginForm.uuid = res.uuid
        }
      })

    },
    getUserInfoFromCookie() {
      const username = Cookies.get('username')
      const password = Cookies.get('password')
      const rememberMe = Cookies.get('remeberMe')
      this.loginForm = {
        username: username === undefined ? this.loginForm.username : username,
        password: password === undefined ? this.loginForm.password : decrypt(password),
        rememberMe: rememberMe === undefined ? false : Boolean(rememberMe)
      }
    },
    handleLogin() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          this.loading = true
          if (this.loginForm.rememberMe) {
            Cookies.set('username', this.loginForm.username, { expire: 30 })
            Cookies.set('password', encrypt(this.loginForm.password), {
              expire: 30
            })
            Cookies.set('rememberMe', this.loginForm.rememberMe, { expire: 30 })
          } else {
            Cookies.remove('username')
            Cookies.remove('password')
            Cookies.remove('rememberMe')
          }
          this.$store.dispatch('Login', this.loginForm).then(() => {
            this.$router.push({ path: this.redirect || '/' }).catch(() => { })
          }).catch(() => {
            this.loading = false
            // 登录失败，刷新验证码
            if (this.captchaOnOff) {
              this.getKaptchaCode()
            }
          })
        }
      })
    }
  },
}
</script>

<style rel="stylesheet/scss" lang="scss">
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  background-image: url("../assets/images/login-background.jpg");
  background-size: cover;
}

.title {
  margin: 0px auto 30px auto;
  text-align: center;
  color: #707070;
}

.login-form {
  border-radius: 6px;
  background: #ffffff;
  width: 400px;
  padding: 25px 25px 5px 25px;


  .el-input {
    height: 38px;

    input {
      height: 38px;
    }
  }

  .input-icon {
    height: 39px;
    width: 14px;
    margin-left: 2px;
  }
}

.valid-code {
  width: 33%;
  height: 38px;
  float: right;

  img {
    height: 38px;
    cursor: pointer;
    vertical-align: middle;
  }
}

.el-login-footer {
  height: 40px;
  line-height: 40px;
  position: fixed;
  bottom: 0;
  width: 100%;
  text-align: center;
  color: #fff;
  font-family: Arial, Helvetica, sans-serif;
  font-size: 12px;
  letter-spacing: 1px;
}

.remember-me-checkbox {
  margin: 0 0 25xp 0;
}
</style>
