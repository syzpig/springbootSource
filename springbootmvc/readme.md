1.spring3以后官网就推荐不再使用xml配置文件了。而spring mvc主要入口就是 DispatcherServlet
实现他有三种方式 xml,注解，还有一种是java 代码注册的方式（Java配置注册并初始化DispatcherServlet，它由Servlet容器自动检测）。
因此他完完全全就可以实现springboot方式，
https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-servlet

2.spring3项目搭建是需要web.xml ContenxtLoaderListener application.xml这三个配置
因此上面介绍的方式就是来代替这些配置的内容。看类SpringInitializer

3.spring中日志是如何管理的？我们没有配置日志插件为什么还会打印日志。
由源码知道，他是内置了一个jcl日志管理插件。如果我们想控制spring日志，那么仅仅是添加个log4j
是不行的，因为我们没法控制jcl.我们先去完全控制就要剔除spring自带的jcl日志组件，而jcl是继承spring-core
架包的，这个架包继承体系中，继承spring-context架包。所以我们可以再这个pom中去剔除。
但是事实呢？
剔除之后，代码就会出错。但是日志依旧会打印
这是为什么呢？
因为日志打印的类是tomcat的类，tomcat内置的cjl架包，跟spring没有关系，依旧可以执行，只不过在此时idea编译器中没有

     @Override
     public void onStartup(ServletContext servletCxt) {
     这个方法只是tomcat程序入口，不是所有容器的入口。比如jetty,这个代码就不适用jetty
     这就是tomcat和jeety自spring中 的区别
因此spring官网推荐的这种java注册启动spring仅仅适用tomcat ，jeety等容器并不能去启动spring容器 
那怎么办？
因此我们就来模拟一个springboot 
通过main方法来内嵌不同容器。来启动我们的容器（tomcat）


spring日志：
<artifactId>spring-jcl</artifactId>  这个包是抽象编程，都是接口，都是由他的底层实现
jcl这个类阿帕奇提供的抽象类，提供统一的接口做日志，但他自己不做日志都是都第三方底层去实现的，比如：logf4j,slf4j等等
比如你没有实现，所以就是用tomcat内置的slf4j去实现。
（tomcat内置的使用是slf4j框架，而jetty却没有内置，所以就必须需要spring自带的日志框架），所以如果我们不想用他，想自己去实现，但当我们先用log4j去改变日志时发现没有用。所以spring默认
不支持logf4j的。所以我们还要引用log4j和slf4j整合的架包，那么我们的程序就可以使用log4j去记录日志了。所以这就是
spring日志时是怎么回事。

我们经常用到的commoms-logging这个架包就是提供了一套抽象编程，提供接口，由底层logf4j去实现

jetty没有内置servlet-api所以pom加的scope的原因。

4.内置tomcat
既然要将tomcat内置到项目中，并且能够成功的启动项目就要知道 tomcat  做了哪些事情 ，那么就必须先搞明白 
一个 普通的web项目是如何被我们本地配置的tomcat启动并运行的
　（1）、先告诉tomcat 要运行哪些项目 （也就是在使用eclipse、idea启动项目前对tomcat的配置工作、或linux上将编译后的war包拷贝到webapp下）
     　 从而在 后面在启动tomcat时，tomcat就会加载编译后的.class项目 
  （2）、 tomcat在加载编译后的项目时也会加载 web.xml或上篇博客的WebApplication文件 ，而在这个文件中
     　 做的事情就是 加载spring进而加载springMVC 
     　  加载SpringMVC: 将springMVC的DispatcherServlet注册到ServletContext容器  
　那么只需要在项目内部集成tomcat时，运行main方法启动tomcat之前将编译后的项目和tomcat相关联，然后tomcat就能自动加载WebApplication类了

springBoot启动tomcat源码跟踪https://blog.csdn.net/csdn_ygy/article/details/71202734

