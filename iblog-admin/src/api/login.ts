import Request from "./request"
const Apis = {
	login: (params: any) => Request.formPost('/login', params),
	loginOut: () => Request.formPost('/logout', null)
}

export default Apis
