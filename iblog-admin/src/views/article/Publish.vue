<template>
	<el-form :model="formData" :rules="rules" ref="formRef" label-width="120px">
		<el-form-item :label="'标题'" prop="title">
			<el-input v-model="formData.title" placeholder="标题"></el-input>
		</el-form-item>
		<el-form-item :label="'简介'" prop="summary">
			<el-input type="textarea" v-model="formData.summary" placeholder="简介"></el-input>
		</el-form-item>
		<el-form-item :label="'标签'" prop="tag">
			<el-select v-model="formData.tag.id" placeholder="请选择标签">
				<el-option v-for="(item, idx) of tags" :key="idx" :label="item['name']" :value="item['id']"></el-option>
			</el-select>
		</el-form-item>
		<el-form-item :label="'内容'">
			<md-editor
				v-model="formData.content"
				:toolbars="[
					'bold',
					'underline',
					'italic',
					'strikeThrough',
					'-',
					'title',
					'sub',
					'sup',
					'quote',
					'unorderedList',
					'orderedList',
					'-',
					'codeRow',
					'code',
					'link',
					'image',
					'table',
					0,
					1,
					'-',
					'revoke',
					'next',
					'=',
					'prettier',
					'pageFullscreen',
					'fullscreen',
					'preview',
					'htmlPreview',
					'catalog',
				]"
				@upload-img="imgAdd"
				@html-changed="getData"
			/>
		</el-form-item>
		<el-form-item>
			<el-button type="primary" @click="submitForm(formRef)">发布</el-button>
		</el-form-item>
	</el-form>
</template>

<script lang="ts" setup>
import { reactive, ref, onMounted, onUnmounted } from 'vue'
import { useStore } from "vuex"
import { ElMessage } from 'element-plus'
import type { ElForm } from 'element-plus'
import MdEditor from 'md-editor-v3';
import 'md-editor-v3/lib/style.css';

import tagApis from '~/api/tag'
import articleApis from '~/api/article'
import { error } from 'console';

const { state, dispatch } = useStore()


type FormInstance = InstanceType<typeof ElForm>
const formRef = ref<FormInstance>()
const tags = reactive([])
const formData = reactive({
	title: '',
	summary: '',
	tag: { id: '' },
	content: '',
	content_html: ''
})

const rules = reactive({
	title: [
		{ required: true, message: '请输入标题', trigger: 'blur' },
	],
	summary: [
		{ required: true, message: '请输入简介', trigger: 'blur' },
	]
})
const getTags = () => {
	tagApis.get().then(({ data }) => {
		Object.assign(tags, data);
	})
}
const getData = (render: string) => {
	console.info(render)
	formData.content_html = render
}
const imgAdd = (files: FileList, callback: (urls: string[]) => void) => {

	let formdata = new FormData();
	formdata.append("file", files[0]);

	articleApis.upload(formdata).then(({ data: { data } }) => {
		console.info(data)
		let urls = [data]
		callback(urls.map((url: any) => url))
	})
}

onMounted(() => {
	getTags()
	console.info(state.article)
	Object.assign(formData, state.article.info)
})
onUnmounted(() => {
	dispatch("article/set", {});
})


const submitForm = (formEl: FormInstance | undefined) => {
	if (!formEl) return
	formEl.validate((valid) => {
		if (valid) {
			console.log('submit!')

			articleApis.save(formData).then((resp) => {
				ElMessage({
					showClose: true,
					type: 'success',
					message: '发布文章成功'
				})
			});
		} else {
			console.log('error submit!')
			return false
		}
	})
}
</script>

<style lang="scss" scoped>
.v-note-wrapper {
	min-width: auto;
	width: 100%;
}
</style>