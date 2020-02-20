查看所有容器，包括关闭的:
docker ps -a

列举当前运行的容器：docker ps

搜索镜像：docker search xxx

列出当前系统存在的镜像：docker images

拉取镜像：docker pull xxx
xxx是具体某个镜像名称(格式 REPOSITORY:TAG) REPOSITORY：表示镜像的仓库源,TAG：镜像的标签


删除镜像：docker rmi IMAGE_NAME 强制移除镜像不管是否有容器使用该镜像 增加 -f 参数，

停止某个容器：docker stop 容器名称

启动某个容器：docker start 容器名称

启动: docker run -d --name "elvis_nginx" -p 8088:80 nginx
注意:访问 :如果是阿里云服务，记得配置安全组，腾讯云也需要配置，这个就是一个防火墙

查看日志 docker logs -f  containerid(容器id)

退出日志: Ctrl + C


docker 项目部署参考
	https://www.cnblogs.com/huangzhimin/p/11433789.html


