# Spring-Cloud-Netflix案例
包
* addons
    * addons-cloud-config 配置中心
    * addons-hystrix-dashboard 熔断配置器
    * addons-hystrix-turbine 流量收集器
> 配置中心使用的是gitlab，查看配置中心的方式是程序地址+配置文件名+master

* business 
    * business-admin-feign feign接口
    * business-admin-service admin消费者服务
* dependencies 统一依赖
* gateway
    * gateway-zuul-business zuul网关处理针对business
* provider
    * provider-admin-service admin提供者服务


### Zuul的熔断功能
Zuul也集成了Hystrix。假如你把springcloud-feign-client服务关掉了，它原本的熔断服务是不会生效的，因为客户端不是直接访问它，所以要在zuul上实现熔断功能

直接实现ZuulFallbackProvider接口在新版本的springcloud下是会报错的，找不到这个接口，只适用于Dalston及更低版本

#### 原因:
在Edgware.RC1版本中Spring cloud zuul针对于降级进行了升级，升级的内容主要是解决上面说到的当降级出现时，怎样在降级方法中获取具体的异常信息。
增加了一个 FallbackProvider接口，这个接口替换了ZuulFallbackProvider接口
这个接口有两个方法
```java
public interface FallbackProvider {
    String getRoute();
    ClientHttpResponse fallbackResponse(String route, Throwable cause);
}
```
- getRoute()主要是表明是为哪个微服务提供回退，返回*表示为所有微服务提供回退
- Throwable参数把异常信息也当作参数传进来了，在处理熔断时可以输出更详细的信息。
- fallbackResponse()为进入熔断器功能时执行的逻辑，返回中断信息给消费者客户端需要实现ClientHttpResponse接口中的方法
- getStatusCode()返回响应的HTTP状态代码
- getRawStatusCode()返回HTTP状态代码
- getStatusText()返回响应的HTTP状态文本
- close()关闭方法，一般不用写任何代码
- getBody()响应体,也就是熔断的返回内容
- getHeaders()设置返回体的头部
