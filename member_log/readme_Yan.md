# 2022 7.12

## 工作说明

实现了大作业`1.功能要求`中的第1，2点功能。

## 详细步骤

### 1.创建并初始化Spring boot项目

1. 使用idea插件`Spring boot initializer`初始化一个名为`cloud-native-proj`的springboot项目。

2. 在`src/main/resource/application.properties`文件下修改web服务端口：

   ```properties
   server.port=8080
   ```

3. 新建`src/main/java/com/example/cloud_native_proj/controller`文件夹，在该文件夹下新建`UserController.java`文件，并自定义`UserController`类，返回基本的`json`信息。（返回json信息需要导入`alibaba fastjson`依赖）

   ![image-20220712162737680](C:\Users\yhy\AppData\Roaming\Typora\typora-user-images\image-20220712162737680.png)

   <center><em>导入json依赖</em></center>

   <img src="C:\Users\yhy\AppData\Roaming\Typora\typora-user-images\image-20220712162909155.png" alt="image-20220712162909155" style="zoom: 67%;" />

   <center><em>UserController类</em></center>

### 2.实现接口限流功能

采用`current limiting`工具实现了接口限流工作，但还没有实现多pod统一限流。

1. 引入依赖

   ![image-20220712163143432](C:\Users\yhy\AppData\Roaming\Typora\typora-user-images\image-20220712163143432.png)

2. 在`src`根文件夹下新建`application.yaml`配置文件并写入流量控制的相关配置内容：

   ```yaml
   current:
     limiting:
       #开启全局限流
       enabled: false
       #开启注解限流,可使注解失效
       part-enabled: true
       #每秒并发量 这里的qps是全局限流开启的时候的值,如果使用注解在注解里设置QPS值
       qps: 100
       #开启快速失败,可切换为阻塞
       fail-fast: true
       #系统启动保护时间为0
       initial-delay: 0
   ```

3. 在`UserController`类下添加注解：

   ![image-20220712164220334](C:\Users\yhy\AppData\Roaming\Typora\typora-user-images\image-20220712164220334.png)

   为测试方便，每秒最大限流为10次（作业最终提交时可修改成100次）。

4. 新建`src/main/java/com/example/cloud_native_proj/co/FC`文件夹(Flow Control)，在该文件夹下新建` MyCurrentLimitHandler.java`文件，并自定义处理类处理超时函数。

   ![image-20220712163526837](C:\Users\yhy\AppData\Roaming\Typora\typora-user-images\image-20220712163526837.png)

   

## 功能演示

1. 编译运行

   在项目文件夹下运行命令：

   ```shell
    mvn clean install package '-Dmaven.test.skip=true' 
   ```

   ```shell
   java -jar target/cloud_native_proj-0.0.1-SNAPSHOT.jar
   ```

   构建并运行项目。

2. 打开浏览器输入网址：`http://localhost:8080/hello`（注意mapping有`/hello`）查看内容：

   ![image-20220712163835032](C:\Users\yhy\AppData\Roaming\Typora\typora-user-images\image-20220712163835032.png)

3. 用`Apache Jmeter`工具对接口进行压力测试。

   配置如下：

   <img src="C:\Users\yhy\AppData\Roaming\Typora\typora-user-images\image-20220712164044777.png" alt="image-20220712164044777" style="zoom:67%;" />

   <img src="C:\Users\yhy\AppData\Roaming\Typora\typora-user-images\image-20220712163945815.png" alt="image-20220712163945815" style="zoom:67%;" />

   会生成jmx文件。

   然后点击右上角绿色箭头运行，在结果树中查看情况。

   ![image-20220712164259802](C:\Users\yhy\AppData\Roaming\Typora\typora-user-images\image-20220712164259802.png)

   **平均**一秒内每十次左右访问就会出现访问次数过多的返回值。



