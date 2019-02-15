package com.syz.maintomcat;

import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

/**
 * 创建Tom实例，然后让tomcat加载编译后的项
 * StartTomcat做的事情  创建线程类， springboot启动tomcat的源码也是这样的，这里只是写了必要的步骤
 *       A、 创建tomcat实例
 　　　  B、 获取项目编译后的claess 路径
 　　　  C、获取webapp 目录下的项目
 　　　  D、然后将webapp下的项目添加至tomcat的context容器
 　　　  E、启动tomcat
 　　　  F、将tomcat的Server实例await 监听请求的到来
 */
public class StartTomcat implements Runnable {
    @Override
    public void run() {
        //创建tomcat实例
        Tomcat tom = new Tomcat();
        //设置端口
        tom.setPort(8081);
        try {
            //获取项目编译后的claess 路径
            String path = StartTomcat.class.getResource("/").getPath();
            //获取webapp 文件
            String filePath = new File("src/main/webapp").getAbsolutePath();
            //然后将webapp下的项目添加至tomcat的context容器（context对应一个运行的项目）
            Context context = tom.addWebapp("/springbootSource", filePath); //参数1：一般是项目名 对应请求url中的项目
            // webResourceRoot 用于加载 项目的class文件
            WebResourceRoot webResource = new StandardRoot(context);
            webResource.addPreResources(new DirResourceSet(webResource, "/WEB-INF/classes", path, "/"));
            tom.start();
        } catch (Exception e) {
            e.printStackTrace();
        } //阻塞 ，等待前端连接 tom.getServer().await(); }
    }
}
