package com.atguigu.boot.springbootprofilegroup.controller;

import com.atguigu.boot.springbootprofilegroup.dto.Human;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloHumanController {

    @Autowired
    private Human human;

    @Value("${human.name:李四}")
    private String name;

    @Value("${maven_home}")
    private String msg;

    @Value("${os.name}")
    private String osName;

    @GetMapping("/hello")
    public String getHello(){
        return human.getClass().toString();
    }

    @GetMapping("/human")
    @ResponseBody
    public Human getHuman(){
        return human;
    }

    @GetMapping("/msg")
    public String getMsg(){
        return msg+"+"+osName;
    }
}
