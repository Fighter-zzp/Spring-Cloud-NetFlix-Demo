package com.zzp.spring.cloud.netflix.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
//使其可以扫描到addons-hystrix-dashboard里的注解
@SpringBootApplication(scanBasePackages = "com.zzp.spring.cloud.netflix")
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
public class BusinessAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusinessAdminApplication.class, args);
    }
}
