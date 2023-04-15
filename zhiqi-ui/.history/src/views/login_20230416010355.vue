<template></template>

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
      rules: {
        username: [
          { require: true, type: 'string', trigger: 'blur', message: '请输入您的账号' }
        ],
        checkPass: [
          { require: true, trigger: 'blur', message: '请输入您的密码' }
        ],
        code: [
          { require: true, trigger: 'blur', message: '请输入验证码' }
        ]
      }
    }
  },
  watch: {
    $router: {
      // TODO figure this out !!
      this.redirect = route.query && route.query.redirect;
    }
  },
  created() {
    getKaptchaCode();
    getUserInfoFromCookie();
  },
  methods: {
    getKaptchaCode() {
      getCodeImg().then(res => {

      });
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
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true;
          if (this.loginForm.rememberMe) {
            Cookies.set('username', this.loginForm.username, { expire: 30 })
            Cookies.set('password', encrypt(this.loginForm.password), { expire: 30 })
            Cookies.set('rememberMe', this.loginForm.rememberMe, { expire: 30 })
          } else {
            Cookies.remove('username')
            Cookies.remove('password')
            Cookies.remove('rememberMe')
          }
          this.$store.dispatch('Login', this.loginForm).then(() => {

          }).catch(() => {
            this.loading = false
            if (this.)
          })
          login(username, password, code, uuid)
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    }
  },
}
</script>
