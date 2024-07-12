package org.example.controller;

import org.example.entity.CommonResult;
import org.example.entity.Food;
import org.example.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
@RefreshScope
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping("/listFoodByBusinessId/{businessId}")
    public CommonResult<List> listFoodByBusinessId(
            @PathVariable("businessId") Integer businessId) throws Exception{
        List<Food> list = foodService.listFoodByBusinessId(businessId);
        return new CommonResult(200,"success",list);
    }
}

