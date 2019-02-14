1.spring3以后官网就推荐不再使用xml配置文件了。而spring mvc主要入口就是 DispatcherServlet
实现他有三种方式 xml,注解，还有一种是java 代码注册的方式（Java配置注册并初始化DispatcherServlet，它由Servlet容器自动检测）。
因此他完完全全就可以实现springboot方式，
https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-servlet

2.spring3项目搭建是需要web.xml ContenxtLoaderListener application.xml这三个配置
因此上面介绍的方式就是来代替这些配置的内容。看类SpringInitializer

