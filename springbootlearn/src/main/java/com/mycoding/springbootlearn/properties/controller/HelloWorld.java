package com.mycoding.springbootlearn.properties.controller;

import com.mycoding.springbootlearn.properties.service.PropertiesEnv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    @Autowired
    private PropertiesEnv env;

    @GetMapping("/hello")
    public String sayHello(){
        return "world";
    }

    @GetMapping("properties")
    public String getPropertiesEnv(){
        return env.getPropertiesEnv();
    }
}
