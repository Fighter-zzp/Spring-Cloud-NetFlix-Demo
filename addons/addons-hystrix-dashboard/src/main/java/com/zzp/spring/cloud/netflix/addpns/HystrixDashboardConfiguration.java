package com.zzp.spring.cloud.netflix.addpns;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 佐斯特勒
 * @Description 熔断器配置类
 * @Date 2019/12/14
 * @Time 11:19
 * @ClassName HystrixDashboardConfiguration
 **/
@Configuration
public class HystrixDashboardConfiguration {
    @Bean
    public ServletRegistrationBean getServlet(){
        var hystrixMetricsStreamServlet = new HystrixMetricsStreamServlet();
        var registrationBean = new ServletRegistrationBean<>(hystrixMetricsStreamServlet);
        //设置启动数
        registrationBean.setLoadOnStartup(1);
        //配置收集端点
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("hystrixMetricsStreamServlet");
        return registrationBean;
    }
}
