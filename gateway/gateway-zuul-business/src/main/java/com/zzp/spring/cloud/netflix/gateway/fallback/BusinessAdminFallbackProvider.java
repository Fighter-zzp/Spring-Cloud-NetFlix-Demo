package com.zzp.spring.cloud.netflix.gateway.fallback;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * @Author 佐斯特勒
 * @Description 在服务器无法请求时也出发熔断
 * @Date 2019/12/14
 * @Time 14:13
 * @ClassName BusinessAdminFallbackProvider
 **/
@Component
public class BusinessAdminFallbackProvider implements FallbackProvider {

    /**
     * 获取路由，转发的到business-admin
     * 如果要为所有路由提供默认回退，可以创建FallbackProvider类型的bean并使getRoute方法返回*或null
     * @return 服务名
     */
    @Override
    public String getRoute() {
        return "business-admin";
    }

    /**
     * 如果请求服务失败，则返回指定的信息给调用者
     * @param route
     * @param cause
     * @return
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;//转发成功
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.OK.value();//转发成功状态值
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.OK.getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                var objectMapper = new ObjectMapper();
                var map = new HashMap<String, Object>();
                map.put("status", 200);
                map.put("message", "无法连接，请检查您的网络");
                return new ByteArrayInputStream(objectMapper.writeValueAsString(map).getBytes(StandardCharsets.UTF_8));
            }

            @Override
            public HttpHeaders getHeaders() {
                var httpHeaders = new HttpHeaders();
                // 和 getBody 中的内容编码一致
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                return httpHeaders;
            }
        };
    }
}
