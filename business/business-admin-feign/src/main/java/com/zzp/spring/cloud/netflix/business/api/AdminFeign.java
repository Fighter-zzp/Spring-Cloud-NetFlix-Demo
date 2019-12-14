package com.zzp.spring.cloud.netflix.business.api;

import com.zzp.spring.cloud.netflix.business.fallback.BusinessAdminFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "provider-admin",fallback = BusinessAdminFallback.class)
public interface AdminFeign {

    @GetMapping("/hi")
    String sayHi();
}
