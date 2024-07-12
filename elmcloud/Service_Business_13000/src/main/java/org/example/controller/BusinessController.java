package org.example.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.example.entity.Business;
import org.example.entity.CommonResult;
import org.example.feign.FoodFeignService;
import org.example.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/business")
@RefreshScope
@LoadBalancerClient(name = "food-server")  // 负载均衡注解，默认是轮询策略；也可以自己定义策略
public class BusinessController {

    @Autowired
    private BusinessService businessService;
    @Autowired
    private FoodFeignService foodFeignService;

    @GetMapping("/listBusinessByOrderTypeId/{orderTypeId}")
    public CommonResult<List> listBusinessByOrderTypeId(
            @PathVariable("orderTypeId") Integer orderTypeId) throws Exception{
        List<Business> list = businessService.listBusinessByOrderTypeId(orderTypeId);
        return new CommonResult(200,"success",list);
    }

    @GetMapping("/getBusinessById/{businessId}")
    @CircuitBreaker(name = "backendA", fallbackMethod = "fallback")
//    @CircuitBreaker(name = "backendB", fallbackMethod = "fallback")  // 熔断
//    @Bulkhead(name = "bulkheadA", fallbackMethod = "fallback", type = Bulkhead.Type.SEMAPHORE)  // 信号量隔离
//    @RateLimiter(name = "rate-limiterA", fallbackMethod = "fallback")  // 限流
    public CommonResult<Business> getBusinessById(
            @PathVariable("businessId") Integer businessId) throws Exception{
        Business business = businessService.getBusinessById(businessId);
        //在商家微服务中调用食品微服务
        CommonResult<List> result = foodFeignService.listFoodByBusinessId(businessId);
        if(result.getCode()==200) {
            business.setFoodList(result.getResult());
        }else {
            business.setFoodList(new ArrayList());
        }
        return new CommonResult(200,"success",business);
    }

    public CommonResult fallback(Integer userId, Throwable e) {
        e.printStackTrace();
        System.out.println("fallback已经调用!");
        return new CommonResult(403,"fegin触发了熔断降级",null);
    }
}
