1.微服务与微服务架构
微服务架构是一种新型的系统架构，其设计思路是，将单体架构系统拆分为多个可以相互调用、配合的独立运行的小程序。这每个小程序对整体系统所提供的功能就称为一个微服务。
由于每个微服务都是独立运行的，所以每个微服务都独自占用一个进程。微服务间采用轻量级的HTTP RESTful协议通信。每个微服务程序不受编程语言的限制，整个系统关心的是微服务程序所提供的具体服务，并不关心其具体的实现。每个微服务可以有自己独立的数据库。既可以操作自己的独立数据库，也可以操作操作整体系统的数据库。
总结:Spring Cloud是微服务系统架构的一站式解决方案。

![](https://i.imgur.com/nX9bUlt.png)

2.SpringBoot与SpringCloud的关系?
SpringBoot为SpringCloud提供了代码实现环境,使用SpringBoot将其它组件有机融合到了SpringCloud的架构体系当中，Spring Cloud是基于SpringBooot的微服务系统架构的一站式解决方案

3.SpringCloud 与SpringBoot的版本

SpringCloud   SpringBoot

Greenwich     2.1.x

Finchley      2.0.x

Edgware       1.5.x

Dalston       1.5.x


2.Eureka
Eureka Client是一个java客户端,内置又负载均衡器，为消费者从Eureka Server的服务注册表中选择合适的提供者,，即使所有的Eureka  Server都挂掉，客户端依然可以利用缓存中的信息为消费者提供服务发现功能。不过，此时不再接受服务注册，因为Eureka Server已经全部挂掉了。这就是Ap原则。
Eureka通过心跳检查,客户端缓存等机制，确保了系统的高可用性，灵活性和可伸缩性。
目前最稳定的版本是:1.9.3