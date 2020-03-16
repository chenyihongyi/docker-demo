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

四.Hystrix熔断机制与服务降级

1.熔断机制

熔断机制是服务雪崩的一种有效解决方案。当指定时间窗内的请求失败率达到设定阈值时，系统将通过断路器直接将此请求链路断开。常见的熔断有两种：

预熔断

即时熔断

2.服务降级

服务降级是请求发生问题时的一种增强用户体验的方式。

现代系统中，当发生服务熔断时，一定会发生服务降级，但发生服务降级，并不一定是发生了服务熔断。

3.Spring Cloud是通过Hystrix来实现服务熔断与降级的。

4.Hystrix简介

4.1 fallbackMethod服务降级

4.2 fallbackFactory服务降级

![](https://i.imgur.com/8kROcx2.png)

![](https://i.imgur.com/Qwhyggy.png)

![](https://i.imgur.com/vfrujgn.png)

![](https://i.imgur.com/Uu7Z90Y.png)

![](https://i.imgur.com/2nLD9o9.png)

![](https://i.imgur.com/5jjcRQo.png)

![](https://i.imgur.com/mclJ3wW.png)

5.Hystrix高级属性配置

执行隔离策略：

防止提供者被熔断，防止大量客户端请求被阻塞。

![](https://i.imgur.com/QA0CLF3.png)

6.Hystrix高级属性配置2

7.服务降级报警机制

双重检测锁(有问题发送短信)

![](https://i.imgur.com/GnyHBaI.png)

![](https://i.imgur.com/GVmcP1U.png)

8.Dashboard监控仪表盘

![](https://i.imgur.com/YTl8ZEN.png)

![](https://i.imgur.com/YTl8ZEN.png)

![](https://i.imgur.com/LiFCjnH.png)

![](https://i.imgur.com/qmgg7X4.png)

![](https://i.imgur.com/pPC3mSF.png)

![](https://i.imgur.com/cDNAYqD.png)

![](https://i.imgur.com/Kb4Mx37.png)

![](https://i.imgur.com/UJ90ED5.png)

五.微服务网关Zuul

https://github.com/Netflix/zuul/wiki

Zuul主要提供了对请求的路由与过滤功能。路由功能主要指，将外部请求转发到具体的微服务实例上，是外部访问微服务的统一入口。过滤功能主要指，对请求的处理过程进行干预，对请求进行校验、服务聚合等处理。
将Zuul放到整个系统架构中，其作用是这样的，服务提供者是消费者通过EurekaServer进行访问的。

![](https://i.imgur.com/5rRq2w0.png)

![](https://i.imgur.com/w1aca7X.png)

![](https://i.imgur.com/5k6EN58.png)

![](https://i.imgur.com/3IuXwEj.png)

![](https://i.imgur.com/fkwZaeH.png)

![](https://i.imgur.com/DU6cuk8.png)

![](https://i.imgur.com/BInLMG2.png)

![](https://i.imgur.com/RBJTRA1.png)

![](https://i.imgur.com/H2wjbBP.png)

![](https://i.imgur.com/wEPjR4J.png)

![](https://i.imgur.com/ySVx4dB.png)

![](https://i.imgur.com/kXy7MOo.png)

![](https://i.imgur.com/cbjL4qz.png)

![](https://i.imgur.com/P60zeE0.png)

![](https://i.imgur.com/7O92Ji4.png)

六.SpringCloud Config

![](https://i.imgur.com/N24cZfC.png)

![](https://i.imgur.com/L8pP0Nv.png)

![](https://i.imgur.com/41GAGjJ.png)

![](https://i.imgur.com/Sp13KLU.png)

![](https://i.imgur.com/FmMScWE.png)

![](https://i.imgur.com/2OYW1BB.png)

![](https://i.imgur.com/1N7RREc.png)

![](https://i.imgur.com/dC5fbsq.png)

![](https://i.imgur.com/yFc99jH.png)

![](https://i.imgur.com/qt3DDvf.png)

![](https://i.imgur.com/gWVknNl.png)

![](https://i.imgur.com/PARfBwT.png)

![](https://i.imgur.com/3b4kmCI.png)

![](https://i.imgur.com/1v9tTAH.png)

七.调用链跟踪Spring Cloud Sleuth + zipkin

![](https://i.imgur.com/odSWyVo.png)

![](https://i.imgur.com/zQr2NCx.png)

![](https://i.imgur.com/QYbY5SV.png)

![](https://i.imgur.com/5jJRkbp.png)

![](https://i.imgur.com/HqXr0Jj.png)

![](https://i.imgur.com/gv4yIuy.png)

![](https://i.imgur.com/TDjd0sZ.png)

![](https://i.imgur.com/pPGeowK.png)

![](https://i.imgur.com/oqpvbxw.png)

![](https://i.imgur.com/JO58raW.png)

![](https://i.imgur.com/XzEsl68.png)

![](https://i.imgur.com/oqxJKJ6.png)

![](https://i.imgur.com/dCcE2zg.png)

![](https://i.imgur.com/GvmDBjx.png)

![](https://i.imgur.com/ygo4vnI.png)

![](https://i.imgur.com/bxXljeK.png)

八.Spring Cloud Stream

![](https://i.imgur.com/42gn7D9.png)

![](https://i.imgur.com/8ckbekK.png)

![](https://i.imgur.com/vDXD4gX.png)

![](https://i.imgur.com/BM5JPUk.png)

![](https://i.imgur.com/WjgNJ3D.png)










































