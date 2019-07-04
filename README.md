# iblog个人博客（基于Nuxt.js服务器渲染SSR构建）

> 基于Nuxt.js服务器渲染构建的个人博客，该项目采用前后端分离实现，前端采用Nuxt.js作为主框架, axios作为网络请求插件，支持异步获取接口数据。后端使用springboot webflux实现。

### 线上地址: [GL's Blog](https://gllis.com)
### 接口源码: [iblog-api](https://github.com/gllis/iblog-api)

## 修改接口域名地址
> 进入plugins/axios.js 将config.baseURL = "http://localhost:8080";修改为自己的后端接口地址

## 构建
``` bash
# install dependencies
$ npm install

# serve with hot reload at localhost:3000
$ npm run dev

# build for production and launch server
$ npm run build
$ npm start

# generate static project
$ npm run generate
```
