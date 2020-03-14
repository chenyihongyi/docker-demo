一.SpringCloud介绍:

1.微服务与微服务架构
微服务架构是一种新型的系统架构，其设计思路是，将单体架构系统拆分为多个可以相互调用、配合的独立运行的小程序。这每个小程序对整体系统所提供的功能就称为一个微服务。
由于每个微服务都是独立运行的，所以每个微服务都独自占用一个进程。微服务间采用轻量级的HTTP RESTful协议通信。每个微服务程序不受编程语言的限制，整个系统关心的是微服务程序所提供的具体服务，并不关心其具体的实现。每个微服务可以有自己独立的数据库。既可以操作自己的独立数据库，也可以操作操作整体系统的数据库。
总结:Spring Cloud是微服务系统架构的一站式解决方案。

SpringCloud在线资源

SpringCloud官网:

https:projects.spring.io/spring-cloud/

SpringCloud中文网:

https://springcloud.cc/

SpringCloud中国社区:

http://springcloud.cn/


![](https://i.imgur.com/nX9bUlt.png)

2.SpringBoot与SpringCloud的关系?

SpringBoot为SpringCloud提供了代码实现环境,使用SpringBoot将其它组件有机融合到了SpringCloud的架构体系当中，Spring Cloud是基于SpringBooot的微服务系统架构的一站式解决方案

3.SpringCloud 与SpringBoot的版本

SpringCloud                   SpringBoot

Greenwich                      2.1.x

Finchley                       2.0.x

Edgware                        1.5.x

Dalston                        1.5.x

二.Eureka

1.Eureka Client是一个java客户端,内置又负载均衡器，为消费者从Eureka Server的服务注册表中选择合适的提供者,，即使所有的Eureka  Server都挂掉，客户端依然可以利用缓存中的信息为消费者提供服务发现功能。不过，此时不再接受服务注册，因为Eureka Server已经全部挂掉了。这就是Ap原则。
Eureka通过心跳检查,客户端缓存等机制，确保了系统的高可用性，灵活性和可伸缩性。
目前最稳定的版本是:1.9.3

2.Eureka yml配置

![](https://i.imgur.com/uDxotvi.png)

![](https://i.imgur.com/F4PgJFY.png)

![](https://i.imgur.com/ezoSbc5.png)

![](https://i.imgur.com/d7KgvCi.png)

![](https://i.imgur.com/GU5NYbK.png)

![](https://i.imgur.com/REMw0Ue.png)

![](https://i.imgur.com/6cwwLbP.png)

![](https://i.imgur.com/L0yqpnK.png)



三.Ribbon负载均衡、OpenFeign 与Feign 

1.OpenFeign可以使消费者将提供者提供的服务名伪装未接口进行消费，消费者只需使用"Service接口+注解"的方式即可直接调用Service接口方法，而无需再使用RestTemplate.

2.

![](https://i.imgur.com/Hnm7Et1.png)

</--openfeign依赖-->

<dependency>
<groupId>org.springframework.cloud</groupId>
<artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>

</--feign依赖-->

<dependency>
<groupId>org.springframework.cloud</groupId>
<artifactId>spring-cloud-starter-feign</artifactId>
</dependency>

2.OpenFeign配置在客户端

![](https://i.imgur.com/OUnche9.png)

![](https://i.imgur.com/6gtDJv0.png)

OpenFeign底层依旧调的restTemplate.

![](https://i.imgur.com/mLflAIw.png)

3.Ribbon负载均衡

![](https://i.imgur.com/ycLfSXx.png)

![](https://i.imgur.com/5v62pPB.png)

![](https://i.imgur.com/itsTJJS.png)

![](https://i.imgur.com/ztIIcT8.png)

![](https://i.imgur.com/06n1mmy.png)

![](https://i.imgur.com/RUOD7pr.png)

![](https://i.imgur.com/M39z1BF.png)



























