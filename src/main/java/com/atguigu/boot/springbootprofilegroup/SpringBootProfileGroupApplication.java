package com.atguigu.boot.springbootprofilegroup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

@SpringBootApplication
public class SpringBootProfileGroupApplication {

    /**
     * 1、外部配置源
     * 常用：Java属性文件、YAML文件、环境变量、命令行参数；
     *
     * 2、配置文件查找位置
     * (1) classpath 根路径
     * (2) classpath 根路径下config目录
     * (3) jar包当前目录
     * (4) jar包当前目录的config目录
     * (5) /config子目录的直接子目录
     * @param args
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringBootProfileGroupApplication.class, args);
        ConfigurableEnvironment environment = run.getEnvironment();
        Map<String, Object> systemEnvironment = environment.getSystemEnvironment();
        Map<String, Object> systemProperties = environment.getSystemProperties();
        System.out.println("环境变量为："+systemEnvironment);
        System.out.println("系统属性为："+systemProperties);
    }

}
