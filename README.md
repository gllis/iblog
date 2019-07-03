# iBlog-Api
iBlog相关接口，本项目采用springboot webflux技术实现，数据库存储采用mongodb，支持异步请求。

# 环境规范：

JDK 1.8及以上  
MongoDB >= v3.6.3

# 使用技术

* springboot 2.1.x webflux  
* jwt鉴权  
* mongodb  

# 使用前准备
暂时不支持后台修改用户名，后续会增加。所以需要准备工作如下：  
>进入iblog库  
$ use iblog  

>插入登录用户名及密码 
$ db.users.insert({name:'demo',password:'14e1b600b1fd579f47433b88e8d85291'})

>用户名及密码登录  
用户名：demo  
密码： 123456  
 
>springboot 配置  
jwt.key  # jwt密钥  

>阿里云OSS配置  
aliyun.oss.endpoint  
aliyun.oss.accessKeyId  
aliyun.oss.accessKeySecret  
aliyun.oss.default.url  
aliyun.oss.bucketName  
