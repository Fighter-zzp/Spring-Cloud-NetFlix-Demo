package com.zzp.spring.cloud.netflix.business.controller;

import com.netflix.config.DynamicPropertyFactory;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.zzp.spring.cloud.netflix.business.api.AdminFeign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class BusinessAdminController {

    @Resource
    private AdminFeign adminFeign;

    @GetMapping("/hi")
    @HystrixCommand(commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")})
    public String sayHi(){
        return adminFeign.sayHi();
    }

    @GetMapping(value = "hello")
    public String sayHello() {
        var property = DynamicPropertyFactory.getInstance().getStringProperty("demo.message", "Hello World");
        return property.getValue();
    }
}
