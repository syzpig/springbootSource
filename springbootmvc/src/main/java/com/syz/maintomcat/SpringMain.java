package com.syz.maintomcat;
/**
 *运行main启动tomcat
 * 这里也可以自定义一个注解 ，启动标注了此注解了的main方法时 ，做new StartTomcat().run(); 的事情就可以了
 *
 * 运行main方法  ，就能启动tom服务了
 */
public class SpringMain {

    public static void main(String[] args) {
        //启动
        new StartTomcat().run();
    }
}
