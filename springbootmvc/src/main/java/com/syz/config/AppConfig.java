package com.syz.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *这种用法就在java2,5之后就推出来的javaconfig技术
 */
@Configuration
@ComponentScan("com.syz")
public class AppConfig {
}
