<template>
	<el-row>
		<el-col :span="12">
			<el-table :data="tableList">
				<el-table-column type="index" width="50"></el-table-column>
				<el-table-column label="标签" property="name" width="150">
					<template #default="scope">
						<el-tag effect="dark" :color="scope.row.color">{{ scope.row.name }}</el-tag>
					</template>
				</el-table-column>
				<el-table-column label="操作">
					<template #default="scope">
						<el-button @click="handleEdit(scope.row)">编辑</el-button>
						<el-button type="danger" @click="remove(scope.row.id)">删除</el-button>
					</template>
				</el-table-column>
			</el-table>
		</el-col>
		<el-col :span="6">
			<el-form :model="formData" :rules="rules" ref="formRef">
				<el-form-item prop="name">
					<el-input placeholder="标签名" v-model="formData.name"></el-input>
				</el-form-item>
				<el-form-item prop="color">
					<el-row>
						<el-col :span="12">
							<el-input readonly placeholder="颜色" v-model="formData.color"></el-input>
						</el-col>
						<el-col :span="12">
							&nbsp;
							<el-color-picker v-model="formData.color"></el-color-picker>
						</el-col>
					</el-row>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="submitForm(formRef, 1)">创建</el-button>
					<el-button type="success" @click="submitForm(formRef, 2)">更新</el-button>
				</el-form-item>
			</el-form>
		</el-col>
	</el-row>
</template>

<script lang="ts" setup>
import { reactive, ref, onMounted } from 'vue'
import type { ElForm } from 'element-plus'
import tagApi from '~/api/tag'

type FormInstance = InstanceType<typeof ElForm>
const formRef = ref<FormInstance>()
const tableList = reactive([]);
const formData = reactive({
	id: '',
	name: '',
	color: ''
})
const rules = reactive({
	name: [
		{ required: true, message: '请输入标签名称', trigger: 'blur' },
	],
	color: [
		{ required: true, message: '请选择颜色', trigger: 'blur' },
	]
})

const getTagList = () => {
	tagApi.get().then(({ data: { data } }) => {
		tableList.splice(0, tableList.length)
		Object.assign(tableList, data)
	})
}

onMounted(() => {
	getTagList()
})

// 编辑
const handleEdit = (param: any) => {
	Object.assign(formData, param);
}
// 删除 
const remove = (id: string) => {
	tagApi.remove({ id: id }).then(() => {
		getTagList()
	})
}
const submitForm = (formEl: FormInstance | undefined, type: number = 1) => {
	if (!formEl) return
	formEl.validate((valid) => {
		if (valid) {
			if (type == 1) {
				formData.id = ''
			}
			tagApi.save(formData).then(() => {
				getTagList()
			});
		} else {
			console.log('error submit!')
			return false
		}
	})
}
</script>