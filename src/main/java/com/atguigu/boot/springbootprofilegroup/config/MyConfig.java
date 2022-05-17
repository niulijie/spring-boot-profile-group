package com.atguigu.boot.springbootprofilegroup.config;

import com.atguigu.boot.springbootprofilegroup.dto.Color;
import com.niulijie.boot.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class MyConfig {

    @Profile("sec")
    @Bean
    public Color red(){
        return new Color();
    }

    @Profile("dev")
    @Bean
    public Color green(){
        return new Color();
    }

    @Bean
    public HelloService helloService(){
        HelloService helloService = new HelloService();
        return helloService;
    }
}
