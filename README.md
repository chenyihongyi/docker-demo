1、微服务下的Docker介绍和使用场景
		简介：Docker介绍和使用场景

		1、什么是Dokcer
			百科:一个开源的应用容器引擎，让开发者可以打包他们的应用以及依赖包到一个可移植的容器中，然后发布到任何流行的 Linux 机器上，也可以实现虚拟化。容器是完全使用沙箱机制，相互之间不会有任何接口；

			使用go语言编写，在LCX（linux容器）基础上进行的封装

			简单来说：
				1）就是可以快速部署启动应用
				2）实现虚拟化，完整资源隔离
				3）一次编写，四处运行（有一定的限制，比如Docker是基于Linux 64bit的，无法在32bit的linux/Windows/unix环境下使用）

		2、为什么要用
			1、提供一次性的环境，假如需要安装Mysql，则需要安装很多依赖库、版本等，如果使用Docker则通过镜像就可以直接启动运行   

			2、快速动态扩容，使用docker部署了一个应用，可以制作成镜像，然后通过Dokcer快速启动

			3、组建微服务架构，可以在一个机器上模拟出多个微服务，启动多个应用

			4、更好的资源隔离和共享

			一句话：开箱即用，快速部署，可移植性强，环境隔离







	2、Linux云服务器Centos7安装Docker实战
		简介：讲解阿里云ECS服务安装Docker实战
		Linux Standard Base的缩写，lsb_release命令用来显示LSB和特定版本的相关信息
		命令： lsb_release -a 

		阿里云安装手册：
		https://help.aliyun.com/document_detail/51853.html?spm=a2c4g.11186623.6.820.RaToNY
		
		常见问题：
		https://blog.csdn.net/daluguishou/article/details/52080250






	3、Docker仓库、镜像、容器核心知识讲解
		简介：快速掌握Dokcer基础知识，

		1、概念：
			Docker 镜像 - Docker images：
					 容器运行时的只读模板，操作系统+软件运行环境+用户程序
					 
					 class User{
						 private String userName;
						 private int age;
					 }


			Docker 容器 - Docker containers：
					容器包含了某个应用运行所需要的全部环境
					
					 User user = new User()


			Docker 仓库 - Docker registeries： 
					用来保存镜像，有公有和私有仓库，好比Maven的中央仓库和本地私服
					镜像仓库：	
					
					（参考）配置国内镜像仓库：https://blog.csdn.net/zzy1078689276/article/details/77371782

			对比面向对象的方式
			Dokcer 里面的镜像 : Java里面的类 Class
			Docker 里面的容器 : Java里面的对象 Object
			通过类创建对象，通过镜像创建容器


	4、Docker容器常见命令实战
		简介：讲解Docker在云服务上的实际应用
		1、 常用命令（安装部署好Dokcer后，执行的命令是docker开头）,xxx是镜像名称

			搜索镜像：docker search xxx
			
			列出当前系统存在的镜像：docker images
			
			拉取镜像：docker pull xxx
				xxx是具体某个镜像名称(格式 REPOSITORY:TAG)
				REPOSITORY：表示镜像的仓库源,TAG：镜像的标签

			运行一个容器：docker run -d --name "xdclass_mq" -p 5672:5672 -p 15672:15672 rabbitmq:management
				docker run - 运行一个容器
				-d 后台运行
				-p 端口映射
				rabbitmq:management  (格式 REPOSITORY:TAG)，如果不指定tag，默认使用最新的
				--name "xxx"
			
			列举当前运行的容器：docker ps

			检查容器内部信息：docker inspect 容器名称

			删除镜像：docker rmi IMAGE_NAME
				 强制移除镜像不管是否有容器使用该镜像 增加 -f 参数，

			停止某个容器：docker stop 容器名称

			启动某个容器：docker start 容器名称

			移除某个容器： docker rm 容器名称 （容器必须是停止状态）


		文档：
			https://blog.csdn.net/permike/article/details/51879578







	5、实战应用之使用Docker部署Nginx服务器
		简介：讲解使用Docker部署Nginx服务器实战
		1、获取镜像 
			docker run (首先会从本地找镜像，如果有则直接启动，没有的话，从镜像仓库拉起，再启动)

			docker search nignx
		2、列举
			docker images
		3、拉取
			docker pull nignx
		3、启动
			docker run -d --name "xdclass_nginx" -p 8088:80 nginx
			docker run -d --name "xdclass_nginx2" -p 8089:80 nginx
			docker run -d --name "xdclass_nginx3" -p 8090:80 nginx
		4、访问
			如果是阿里云服务，记得配置安全组，腾讯云也需要配置，这个就是一个防火墙







	6、公司中Docker镜像仓库使用讲解
		简介：讲解一般公司中镜像仓库在的使用
		
		1、为啥要用镜像仓库


		2、官方公共镜像仓库和私有镜像仓库
			公共镜像仓库：
					官方：https://hub.docker.com/，基于各个软件开发或者有软件提供商开发的
					非官方：其他组织或者公司开发的镜像，供大家免费试用

			私有镜像仓库：
					用于存放公司内部的镜像，不提供给外部试用； 

					SpringCloud 开发了一个支付系统 -》做成一个镜像 （操作系统+软件运行环境+用户程序）






	7、高级篇幅之构建自己的镜像仓库
		简介：使用阿里云搭建自己的镜像仓库
		
		1、阿里云镜像仓库：https://dev.aliyun.com/search.html

		点击管理控制台-》初次使用会提示开通，然后设置密码
		xdclass.net123


		2、使用阿里云私有镜像仓库
			1)登录： docker login --username=794666918@qq.com registry.cn-shenzhen.aliyuncs.com
			
			2) 推送本地镜像：
			docker tag [ImageId] registry.cn-shenzhen.aliyuncs.com/xdclass/xdclass_images:[镜像版本号]
			例子：
			docker tag 2f415b0e9a6e registry.cn-shenzhen.aliyuncs.com/xdclass/xdclass_images:xd_rabbitmq-v1.0.2

			docker push registry.cn-shenzhen.aliyuncs.com/xdclass/xdclass_images:xd_rabbitmq-v1.0.2

			3)拉取镜像
				线上服务器拉取镜像：
					docker login --username=794666918@qq.com registry.cn-shenzhen.aliyuncs.com

					docker pull registry.cn-shenzhen.aliyuncs.com/xdclass/xdclass_images:xd_rabbitmq-v1.0.2

					启动容器：
					docker run -d --name "xdclass_mq" -p 5672:5672 -p 15672:15672 2f415b0e9a6e