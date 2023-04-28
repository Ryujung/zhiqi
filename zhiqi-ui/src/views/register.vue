<template>
  <div class="register">
    <el-form :model="registerForm" :rules="registerRules" ref="registerForm" label-width="100px" class="register-form">
      <h3 class="title">知其后台管理系统</h3>

      <el-form-item prop="username">
        <el-input type="text" v-model="registerForm.username" autocomplete="off" placeholder="账号"></el-input>
      </el-form-item>

      <el-form-item prop="password">
        <el-input type="password" v-model="registerForm.password" autocomplete="off" placeholder="密码"
          @keyup.enter.native="handleRegister">
          <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon" />
        </el-input>
      </el-form-item>

      <el-form-item prop="confirmPassword">
        <el-input type="password" v-model="registerForm.confirmPassword" autocomplete="off" placeholder="确认密码"
          @keyup.enter.native="handleRegister">
          <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon" />
        </el-input>
      </el-form-item>

      <el-form-item prop="code">
        <el-input v-model="registerForm.code" auto-complete="false" placeholder="验证码" @keyup.enter.native="handleRegister" style="width: 63%">
          <svg-icon slot="prefix" icon-class="validCode" class="el-input__icon input-icon" />
        </el-input>

        <div class="valid-code">
          <img :src="codeUrl" @click="getCode" class="valid-code-img">
        </div>
      </el-form-item>

      <el-form-item style="width:100%">
        <el-button :loading=loading size="medium" type="primary" style="width:100%" @click="handleRegister">
          <span v-if="!loading">注 册</span>
          <span v-else>注 册 中...</span>
        </el-button>

        <!-- TODO refirect to login as exist users -->
      </el-form-item>

    </el-form>

    <!-- 底部 -->
    <div class="el-register-footer">
      <span>Copyright © 2023-2023 ZhiQi All Rights Reserved.</span>
    </div>

  </div>
</template>

<script>
import { getCodeImg, register } from '@/api/login'

export default ({
  name: 'register',
  data() {
    const equalsToPassword = (rule, value, callback) => {
      if (value !== this.registerForm.password) {
        callback(new Error('两次输入密码不一致'));
      } else {
        callback();
      }
    };

    return {
      registerForm: {
        username: '',
        password: '',
        confirmPassword: '',
        code: '',
        uuid: ''
      },
      registerRules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 2, max: 20, message: '用户名长度在 2 到 20 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 5, max: 20, message: '密码长度在 5 到 20 个字符', trigger: 'blur' }
        ],
        confirmPassword: [
          { required: true, message: '请确认密码', trigger: 'blur' },
          { validator: equalsToPassword, trigger: 'blur' }
        ],
        code: [{ required: true, message: '请输入验证码', trigger: 'change' }]
      },
      loading: false,
      codeUrl: '',
      captchaOnOff: true
    }
  },
  created() {
    this.getCode()
  },
  methods: {
    getCode() {
      getCodeImg().then((res) => {
        this.captchaOnOff = res.captchaOnOff === undefined ? true : res.captchaOnOff
        if (this.captchaOnOff) {
          this.codeUrl = `data:image/gif;base64, ${res.img}`
          this.registerForm.uuid = res.uuid
        }
      })
    },
    handleRegister() {
      this.$ref.registerForm.validate(valid => {
        if (valid) {
          this.loading = true
          register(this.registerForm).then(res => {
            const username = this.registerForm.username
            this.$alert(`<font color='red'>恭喜您，您的账号：${username} 注册成功！</font>`, '系统提示', {
              // dangerouslyUseHTMLString: true
            }).then(() => {
              this.$router.push('/login')
            }).catch(() => { })
          }).catch(() => {
            // 注册失败
            this.loading = false
            if (this.captchaOnOff) {
              this.getCode()
            }
          })
        }
      })
      register()
    }
  }
})
</script>

<style rel="stylesheet/scss" lang="scss">
.register {
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

.register-form {
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


.el-register-footer {
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
