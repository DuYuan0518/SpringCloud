package org.example.feign;

import org.example.entity.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//指定调用的食品微服务名
//@FeignClient注解的fallback属性指定熔断降级方法
@FeignClient(name="food-server")
public interface FoodFeignService {
    @GetMapping("/food/listFoodByBusinessId/{businessId}")
    public CommonResult listFoodByBusinessId(@PathVariable("businessId") Integer businessId);
}
