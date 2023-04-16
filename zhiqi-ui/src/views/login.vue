<template>
  <div class="login">
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" label-width="100px" class="login-form">
      <h3 class="title">知其后台管理系统</h3>
      <el-form-item label="用户名" prop="validateUsername">
        <el-input type="text" v-model="loginForm.username" autocomplete="off" placeholder="用户名"></el-input>
      </el-form-item>

      <el-form-item label="密码" prop="validatePassword">
        <el-input type="password" v-model="loginForm.password" autocomplete="off" placeholder="密码"></el-input>
      </el-form-item>

      <el-form-item>
        <el-checkbox label="记住密码" v-model="loginForm.rememberMe">记住密码</el-checkbox>
      </el-form-item>

      <el-form-item>
        <el-button :loading="loading" type="primary" size="medium" style="width: 100%"
          @click.native.prevent="handlerLogin">
          <span v-if="!loading">登录</span>
          <span v-else>登 录 中...</span>
        </el-button>
        <div>
          <!-- TODO register router link -->
        </div>
      </el-form-item>
    </el-form>
    <div class="el-login-foooter">
      <span>Copyright © 2023-2023 ZhiQi All Rights Reserved.</span>
    </div>
  </div>
</template>

<script>
import { login, getCodeImg } from '@/api/login'
import Cookies from 'js-cookie'
import { encrypt, decrypt } from 'jsencrypt'

export default {
  name: 'Login',
  data() {
    return {
      codeUrl: '',
      cookiePassword: '',
      loading: false,
      captchaOnOff: true,
      register: false,
      redirect: undefined,

      loginForm: {
        username: '',
        password: '',
        rememberMe: false,
        code: '',
        uuid: ''
      },
      loginRules: {
        validateUsername: [
          { require: true, type: 'string', trigger: 'blur', message: '请输入您的账号' }
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
        // TODO figure this out !! about router
        // this.redirect =
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
        // TODO captcha request and show
        this.captchaOnOff = res.chaptchaOnOff === undefined ? true : res.chaptchaOnOff;
        if (this.chaptchaOnOff) {
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
    handlerLogin() {
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
          this.$store
            .dispatch('Login', this.loginForm)
            .then(() => {
              // TODO request login api and handle the response
            })
            .catch(() => {
              this.loading = false
              // TODO handle error login response
            })
          login(username, password, code, uuid)
        } else {
          console.log('error submit!!')
          return false
        }
      })
    }
  }
}
</script>
