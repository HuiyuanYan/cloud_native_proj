FROM openjdk:8-jre-alpine
#作者
MAINTAINER Group1 1084683108@qq.com
#复制文件并命名，ADD支持远程获取URL资源
ADD cloud_native_proj-0.0.1-SNAPSHOT.jar /cloud_native_proj.jar
#VOLUME ，VOLUME 指向了一个/tmp的目录，由于 Spring Boot 使用内置的Tomcat容器，Tomcat 默认使用/tmp作为工作目录。这个命令的效果是：
#在宿主机的/var/lib/docker目录下创建一个临时文件并把它链接到容器中的/tmp目录
#VOLUME /tmp
#声明端口
EXPOSE 8080
ENTRYPOINT ["java","-jar","/cloud_native_proj.jar"]
#命令：docker build -t cloud_native_proj .
#docker run --name cloud_native_proj -p 8080:8080 -d cloud_native_proj