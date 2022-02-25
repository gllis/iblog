import Request from "./request"
const Apis = {
	login: (params: any) => Request.formPost('/login', params),
	modifyPwd: (params: any) => Request.post('/user/modifyPwd', params)
}

export default Apis
