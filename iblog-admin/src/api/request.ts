import axios from 'axios';
import Storages from '../utils/storages'
import { ElMessage } from 'element-plus'


const getRequestHeader = (type: string = 'json') => {
	const getToken = () => {
		return Storages.get('token')
	}
	const headersObj: (Record<string, string>) = {
		json: 'application/json',
		form: 'application/x-www-form-urlencoded',
	}
	return {
		'Content-Type': headersObj[type],
		'Authorization': getToken(),
	}
}
const request = axios.create({
	timeout: 5 * 60 * 1000, // 请求超时时间
	baseURL: 'http://localhost:8080',

})

// 请求拦截
request.interceptors.request.use(
	config => {
		return config
	},
	error => {

		// return Promise.reject(error.message)
		throw error
	}
)

// 响应拦截
request.interceptors.response.use(
	response => {
		const isLogoutCode = (code: number) => {
			let result = false
			if (code == 40013) {
				result = true
			}
			return result
		}
		const {
			data,
			status,
			statusText,
			config: { url },
			headers: { authorization },
		} = response
		if (response.headers['content-type'] === 'application/xml') {
			return data
		}
		console.info('logout1', data.errcode)
		if (isLogoutCode(data.errcode)) {
			console.info('logout')
			Storages.remove('token')
			Storages.remove('userInfo')
			window.location.href = '/login'
			throw new ReferenceError('token err' + data.errcode)
		}
		if (data.errcode !== 0) {
			ElMessage.closeAll()
			ElMessage({
				showClose: true,
				type: "error",
				message: data.errmsg
			})
			throw new Error(data.errmsg)
		}
		if (authorization) {
			Storages.set("token", authorization)
		}
		return response
	},
	error => {
		ElMessage.closeAll()
		ElMessage({
			showClose: true,
			type: "error",
			message: error.toString()
		})
		throw error
	}
)

function getFormData(obj: any) {
	let form = "";
	for (let key in obj) {
		form += key + '=' + obj[key] + '&';
	}
	if (form.length > 0) {
		form = form.substring(0, form.length - 1)
	}
	return form
}
class Request {
	static formGet(url: string, params: any) {
		return request({ url, method: 'get', params, headers: getRequestHeader('form') })
	}
	static formPost(url: string, params: any) {
		return request({ url, method: 'post', data: getFormData(params), headers: getRequestHeader('form') })
	}
	static post(url: string, params: any) {
		return request({ url, method: 'post', data: params, headers: getRequestHeader() })
	}
}
export default Request