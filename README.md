springboot什么作用？官网介绍
1.为所有Spring开发提供从根本上更快且可广泛访问的入门体验。
2.开箱即用，但随着需求开始偏离默认值而迅速摆脱困境。
3.提供大型项目（例如嵌入式服务器，安全性，度量标准，运行状况检查和外部化配置）通用的一系列非功能性功能。
4.绝对没有代码生成，也不需要XML配置。

从spring快速构建一个springboot项目开始入手
1.引入springboot pom 
    <!--第一步引入springboot依赖父类-->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.2.RELEASE</version>
    </parent>
     <!--第二步引入springboot启动包-->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
            </dependency>
2.创建启动类
/*第三步创建启动类*/
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
这个springboot项目就构建好了