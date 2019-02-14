package com.syz.springInitializer;

import com.syz.config.AppConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
/**
 *这个类就充当了我们原始springmnv项目中的web.xml的配置
 */
public class SpringInitializer implements WebApplicationInitializer {
    /**
     * 这个方法只是tomcat程序入口，不是所有容器的入口。比如jetty,这个代码就不适用jetty
     * 这就是tomcat和jeety自spring中 的区别
     */
    @Override
    public void onStartup(ServletContext servletCxt) {
        System.out.printf("========================");
        // Load Spring web application configuration  spring环境的初始化
        //而web.xml中配置的ContenxtLoaderListener则被下面这类替代了AnnotationConfigWebApplicationContext
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        //ContenxtLoaderListener中需要加载的application.xml则被下面这类AppConfig替代
        ac.register(AppConfig.class);
       // ac.refresh(); 加了可能会报错

        // Create and register the DispatcherServlet
        DispatcherServlet servlet = new DispatcherServlet(ac);
        ServletRegistration.Dynamic registration = servletCxt.addServlet("app", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/app/*");
    }
}
