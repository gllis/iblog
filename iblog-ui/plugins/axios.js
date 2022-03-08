import qs from 'qs';
// process.env.NODE_TLS_REJECT_UNAUTHORIZED='0';

export default function ({ $axios, redirect }) {
    $axios.onRequest(config => {

      console.log('Making request to ' + config.url)
      // 如果是formData有值，修改为表单提交
      if (config.data && config.data.formData) {
        config.data = qs.stringify(config.data.formData);
      }
      config.baseURL = "https://api.gllis.com";
    })
  

    $axios.onError(error => {
      const code = parseInt(error.response && error.response.status)
      if (code === 400) {
        redirect('/400')
      }
    })
}