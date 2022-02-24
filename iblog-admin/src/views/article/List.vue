<template>
	<el-table :data="tableList">
		<el-table-column type="index" width="50"></el-table-column>
		<el-table-column label="标签" property="tag.name" width="80"></el-table-column>
		<el-table-column label="标题" property="title"></el-table-column>
		<el-table-column label="简介" property="summary"></el-table-column>
		<el-table-column label="创建时间" property="created">
			<template #default="scope">{{ new Date(scope.row.created) }}</template>
		</el-table-column>
		<el-table-column label="操作">
			<template #default="scope">
				<el-button @click="handleEdit(scope.row)">编辑</el-button>
				<el-button type="danger" @click="remove(scope.row.id)">删除</el-button>
			</template>
		</el-table-column>
	</el-table>

	<el-pagination
		background
		layout="prev, pager, next"
		:page-size="pagination.size"
		:total="pagination.total"
		@current-change="getArticleList"
		style="float:right;padding-top:20px;"
	></el-pagination>
</template>

<script lang="ts" setup>
import { reactive, ref, onMounted } from 'vue'
import { useStore } from "vuex"
import type { ElForm } from 'element-plus'
import articleApis from '~/api/article'
import router from '~/router'

type FormInstance = InstanceType<typeof ElForm>
const formRef = ref<FormInstance>()
const tableList = reactive([]);
const page = ref(0)
const limit = ref(10)
const total = ref(0)

const pagination = reactive({
	total: 0,
	size: 10
})

const { state, dispatch } = useStore();

const getArticleList = (page: number = 1) => {
	page = (page - 1) | 0;
	articleApis.get({
		page: page,
		size: pagination.size
	}).then(({ data: { data, total } }) => {
		console.info(data)
		tableList.splice(0, tableList.length)
		Object.assign(tableList, data)
		Object.assign(pagination, { total })
	})
}

onMounted(() => {
	getArticleList()
})

// 编辑
const handleEdit = (info: any) => {
	dispatch("article/set", info).then(() => {
		router.push('/article/publish')
	});

}
// 删除 
const remove = (id: string) => {
	articleApis.remove({ id: id }).then(() => {
		getArticleList()
	})
}

</script>