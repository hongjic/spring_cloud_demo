# Spring Cloud Demo
本demo模拟一个展示产品详情的场景，构建一个基于Spring Cloud的微服务应用。

## 运行

### before run
项目使用了config server，服务的配置文件全部在`config_repo`文件夹中。为了不影响项目的git环境，先讲`config_repo`文件夹复制到其他地方，创建git仓库，并把地址添加到config-server的配置文件中。

1. `cp -r config_repo/ {somewhere_else}/config_repo` to somewhere else. 
2. `cd {somewhere else}/config_repo`
3. `git init .`, `git add .`, `git commit -m "message"`
4. set `spring.cloud.config.server.git.uri` to the path of the git repository you create in `config-server/src/main/resources/application.properties`, 

确保端口8080,8079,8081,8082,8050,8888,8761没有被占用。

### 运行 ###
没有外部依赖，build 完成直接运行服务的启动类即可。首先启动config-server, 再启动eureka-service，然后启动service-gateway, comment-service-provider, inventory-service-provider, hystrix-dashboard, zipkin-servie.

浏览器调用

1. http://localhost:8082/comments/1  // product1的评论
2. http://localhost:8081/product/1  // product1信息
3. http://localhost:8050/productDetail/1  // product1产品详情页数据
4. http://localhost:8761/  // eureka UI
5. http://localhost:8010/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8050%2Fhystrix.stream  // Hystrix UI
6. http://localhost:8079/zipkin/  // Zipkin UI

Note:为了方便，只有id=1配置过。

## 模块


### config-server
基于git的配置服务，可以通过git提交和refresh请求主动刷新应用配置。例如修改`inventory-service.properties`中`shop.open=false`, `git commit`, 执行`curl -X POST http://{inventory-service ip:port}/refresh/` 后即可看到变化

### eureka-service
Eureka负责服务注册和发现。

### service-gateway
使用zuul转发，作为应用的唯一入口。通过eureka-service发现服务，转发请求。有一个/productDetail/{id}的GET请求接口，调用库存服务API和评论服务API获得产品详情ProductDetail。

关闭库存服务或评论服务后再调用/productDetail/{id} 会启动Hystrix，作简单返回。

### inventory-service-provider
库存服务

### comment-service-provider
评论服务

### hystrix-dashboard
Hystrix Web UI

### zipkin-service
Zipkin UI.
库存服务，评论服务和网关都配置了spring cloud sleuth来生成调用链日志。

