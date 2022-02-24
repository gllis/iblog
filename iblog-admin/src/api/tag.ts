import Request from "./request"
const tagApi = {
	get: () => Request.formGet('/admin/tag/list', null),
	save: (params: any) => Request.post('/tag/save', params),
	remove: (params: any) => Request.formGet('/tag/remove', params)
}

export default tagApi
