<template>
  <el-container>
    <el-form :model="loginForm" :rules="loginRules" ref="loginForm" label-position="left" label-width="0px" class="login-container">
      <h3 class="title">iBlog-admin</h3>
      <el-form-item prop="account" class="form-item">
        <el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="账号"></el-input>
      </el-form-item>
      <el-form-item prop="checkPass" class="form-item">
        <el-input
          type="password"
          v-model="loginForm.password"
          auto-complete="off"
          placeholder="密码"
          @keyup.enter.native="handleSubmit">
        </el-input>
      </el-form-item>
      <el-checkbox v-model="checked" checked class="remember">记住密码</el-checkbox>
      <el-form-item style="width:100%;">
        <el-button type="primary" style="width:100%;" @click.native.prevent="handleSubmit" :loading="logining">登录</el-button>
      </el-form-item>
    </el-form>
    <el-footer>
      Copyright © 2019. All rights reserved.
    </el-footer>
  </el-container>
</template>

<script>
export default {
    data() {
      return {
        logining: false,
        loginForm: {
          username: 'admin',
          password: 'e10adc3949ba59abbe56e057f20f883e'
        },
        loginRules: {
          username: [
            { required: true, message: '请输入账号', trigger: 'blur' },
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' },
          ]
        },
        checked: true
      };
  },
  methods: {
    async handleSubmit() {
      const res = await this.$axios.$post("/login", {formData: this.loginForm});
      console.info(res);
      if (res.errcode == 0) {
        sessionStorage.setItem('user', JSON.stringify(res.data));
        this.$router.push('/admin/article/publish');
      }
    }
  }
}
</script>

<style lang="scss" scoped>
  .login-container {
    margin: 180px auto;
    width: 350px;
    -webkit-border-radius: 5px;
      border-radius: 5px;
      -moz-border-radius: 5px;
      background-clip: padding-box;
      padding: 35px 35px 15px 35px;
      background: #fff;
      border: 1px solid #eaeaea;
      box-shadow: 0 0 25px #cac6c6;
   
    .title {
      margin: 0px auto 40px auto;
      text-align: center;
      color: #505458;
    }
    .remember {
      margin: 0px 0px 15px 0px;
    }
    .form-item {
      margin-bottom: 20px;
    }
  }
  
  .el-header, .el-footer {
    color: #333;
    text-align: center;
  }
</style>