import Request from "./request"
const articleApis = {
	get: (params: any) => Request.post('/admin/article/list', params),
	save: (params: any) => Request.post('/article/save', params),
	remove: (params: any) => Request.formGet('/article/remove', params)
}

export default articleApis
