<template>
  <el-container>
    <el-form :model="entity" :rules="rules" ref="loginForm" label-position="left" label-width="0px" class="login-container">
      <h3 class="title">iBlog-admin</h3>
      <el-form-item prop="username" class="form-item">
        <el-input type="text" v-model="entity.username" auto-complete="off" placeholder="账号"></el-input>
      </el-form-item>
      <el-form-item prop="password" class="form-item">
        <el-input
          type="password"
          v-model="entity.password"
          auto-complete="off"
          placeholder="密码"
          @keyup.enter.native="handleSubmit">
        </el-input>
      </el-form-item>
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
import { MD5 } from 'crypto-js'

export default {
    data() {
      return {
        logining: false,
        entity: {
          username: '',
          password: ''
        },
        rules: {
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
      let isValid = false;
      this.$refs['loginForm'].validate((valid) => {
          isValid = valid;
      });
      if (!isValid) {
        return;
      }
      console.info(this.entity)
      this.entity.password = MD5(this.entity.password.trim()).toString();
      const res = await this.$axios.$post("/login", {formData: this.entity});
      if (res.errcode == 0) {
        sessionStorage.setItem('user', JSON.stringify(res.data));
        this.$router.push('/admin/article/publish');
      } else {
        this.$notify({
            title: '操作提示',
            message: '登录失败！',
            type: 'error'
        });
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