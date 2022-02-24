import Request from "./request"
const settingApis = {
	get: () => Request.formGet('admin/sys/get', {'key': 'blog-setting'}),
	save: (params: any) => Request.post('admin/sys/save', params)
}

export default settingApis
