<template>
	<el-container>
		<el-form
			:model="form"
			:rules="rules"
			ref="ruleFormRef"
			label-position="left"
			label-width="0px"
			class="login-container"
		>
			<h3 class="title">iBlog-admin</h3>
			<el-form-item prop="username" class="form-item">
				<el-input type="text" v-model="form.username" auto-complete="off" placeholder="账号"></el-input>
			</el-form-item>
			<el-form-item prop="password" class="form-item">
				<el-input type="password" v-model="form.password" auto-complete="off" placeholder="密码"></el-input>
			</el-form-item>
			<el-form-item style="width:100%;">
				<el-button type="primary" style="width:100%;" @click="onSubmit(ruleFormRef)">登录</el-button>
			</el-form-item>
		</el-form>
		<el-footer>Copyright © 2019. All rights reserved.</el-footer>
	</el-container>
</template>

<script setup lang="ts">
import { reactive, ref } from "vue"
import type { ElForm } from 'element-plus'
import { useStore } from "vuex"
import md5 from "md5"

type FormInstance = InstanceType<typeof ElForm>
const ruleFormRef = ref<FormInstance>()

const form = reactive({
	username: '',
	password: ''
})
const rules = reactive({
	username: [
		{ required: true, message: '请输入账号', trigger: 'blur' },
	],
	password: [
		{ required: true, message: '请输入密码', trigger: 'blur' },
	]
})
const { state, dispatch } = useStore();

const onSubmit = (formEl: FormInstance | undefined) => {
	if (!formEl) return
	formEl.validate((valid) => {
		if (valid) {
			let param = {
				username: form.username,
				password: md5(form.password)
			}
			dispatch("user/login", param);
		} else {
			console.log('error submit!')
			return false
		}
	})

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

.el-header,
.el-footer {
	color: #333;
	text-align: center;
}
</style>