<template>
	<el-row>
		<el-col :span="12">
			<el-form ref="formRef" :model="formData" :rules="rules" label-width="120px" class="ui-setting">
				<el-form-item prop="name" :label="'网站名称'">
					<el-input v-model="formData.name"></el-input>
				</el-form-item>
				<el-form-item prop="description" :label="'网站描述'">
					<el-input v-model="formData.description"></el-input>
				</el-form-item>
				<el-form-item prop="keywords" :label="'搜索关键词'">
					<el-input v-model="formData.keywords" type="textarea"></el-input>
				</el-form-item>
				<el-form-item prop="author" :label="'作者'">
					<el-input v-model="formData.author"></el-input>
				</el-form-item>
				<el-form-item prop="job" :label="'职业'">
					<el-input v-model="formData.job"></el-input>
				</el-form-item>
				<el-form-item prop="mail" :label="'邮箱'">
					<el-input v-model="formData.mail"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="submitForm(formRef)">保存</el-button>
					<el-button @click="resetForm(formRef)">重置</el-button>
				</el-form-item>
			</el-form>
		</el-col>
	</el-row>
</template>

<script lang="ts" setup>
import { reactive, ref, onMounted } from 'vue'
import type { ElForm } from 'element-plus'
import settingApis from '~/api/setting'

type FormInstance = InstanceType<typeof ElForm>
const formRef = ref<FormInstance>()

const formData = reactive({
	name: '',
	description: '',
	keywords: '',
	author: '',
	job: '',
	mail: ''

})

const rules = reactive({
	name: [
		{ required: true, message: '请输入网站名称', trigger: 'blur' },
	],
	description: [
		{ required: true, message: '请输入网站描述', trigger: 'blur' },
	],
	keywords: [
		{ required: true, message: '请输入搜索关键词', trigger: 'blur' },
	],
	author: [
		{ required: true, message: '请输入作者', trigger: 'blur' },
	],
	job: [
		{ required: true, message: '请输入职业', trigger: 'blur' },
	]
})

const getSetting = () => {
	settingApis.get().then(({ data: { data } }) => {

		if (!data) return
		formData.name = data.name
		formData.description = data.description
		formData.keywords = data.keywords
		formData.author = data.author
		formData.job = data.job
		formData.mail = data.mail
	});
}
onMounted(() => {
	getSetting();
})





const submitForm = (formEl: FormInstance | undefined) => {
	if (!formEl) return
	formEl.validate((valid) => {
		if (valid) {
			console.log('submit!')
			let params = { key: "blog-setting", value: formData }
			settingApis.save(params).then(({ data: { errcode } }) => {
				getSetting()
			})
		} else {
			console.log('error submit!')
			return false
		}
	})
}

const resetForm = (formEl: FormInstance | undefined) => {
	if (!formEl) return
	formEl.resetFields()
}
</script>
