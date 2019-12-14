package com.zzp.spring.cloud.netflix.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderAdminController {
    @Value("${server.port}")
    private String port;

    @GetMapping("/hi")
    public String sayHi(){
        return "Hi Netflix Eureka,your port is " + port;
    }
}
