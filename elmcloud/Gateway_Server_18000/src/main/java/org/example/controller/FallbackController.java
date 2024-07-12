package org.example.controller;

import org.example.entity.CommonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {
    // 这里的fallback对应application.properties中配置的fallback
    @RequestMapping(value = "/fallback", method = RequestMethod.GET)
    public CommonResult fallback() {
        return new CommonResult(403,"Gateway触发了熔断降级",null);
    }
}
