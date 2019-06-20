import qs from 'qs';

export default function ({ $axios, app, redirect }) {
    $axios.onRequest(config => {
      let token = app.$cookies.get('token');
      if (!token) {
        redirect('/admin/login')
      }
      // 如果是formData有值，修改为表单提交
      if (config.data && config.data.formData) {
        config.data = qs.stringify(config.data.formData);
      }
      config.baseURL = "http://localhost:8080";
      if (token) {
        config.headers.Authorization = token;
      }
      console.log('Making request to ' + config.url)
    })
  
    $axios.onResponse(response => {
      
      // 返回消息头中有auth信息时，缓存到cookie中
      if (response.headers.authorization) {
        console.info(response.headers.authorization);
        app.$cookies.set('token', response.headers.authorization, {
          path: '/',
          maxAge: 60 * 60 * 24 * 7
        });
      }

      // 判断token失效时，移除token
      var data = response.data;
      if (data) {
        if (data.errcode == 40013) {
          app.$cookies.remove('token');
          redirect('/admin/login')
        }
      }
    })

    $axios.onError(error => {
      const code = parseInt(error.response && error.response.status)
      if (code === 400) {
        redirect('/400')
      }
    })
}