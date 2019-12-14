package com.zzp.spring.cloud.netflix.business.fallback;

import com.zzp.spring.cloud.netflix.business.api.AdminFeign;
import org.springframework.stereotype.Component;

@Component
public class BusinessAdminFallback implements AdminFeign {
    @Override
    public String sayHi() {
        return "圣上，臣妾连不上了...";
    }
}
