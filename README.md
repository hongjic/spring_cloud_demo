# Spring Cloud Demo
本demo模拟一个展示产品详情的场景，构建一个基于Spring Cloud的微服务应用。

### 架构





### config-server
对于分布式系统的一个集中式配置管理服务。微服务的配置文件通过http从config server读取，一般只在服务启动的时候读取一次，运维可以通过在对应微服务上的refresh请求刷新配置。配置文件系统可以使用git

### eureka-service
Eureka负责服务注册和发现。

### service-gateway
使用zuul，作为应用的唯一入口。通过eureka-service发现服务，并转发请求。

