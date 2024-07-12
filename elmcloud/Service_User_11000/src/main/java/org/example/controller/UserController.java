package org.example.controller;

import org.example.entity.CommonResult;
import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RefreshScope
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUserByIdByPass/{userId}/{password}")
    public CommonResult<User> getUserByIdByPass(
            @PathVariable("userId") String userId,
            @PathVariable("password") String password) throws Exception{
        User param = new User();
        param.setUserId(userId);
        param.setPassword(password);
        User user = userService.getUserByIdByPass(param);
        return new CommonResult(200,"success",user);
    }

    @GetMapping("/getUserById/{userId}")
    public CommonResult<Integer> getUserById(@PathVariable("userId") String userId) throws Exception{
        int result = userService.getUserById(userId);
        return new CommonResult(200,"success",result);
    }

    @PostMapping("/saveUser/{userId}/{password}/{userName}/{userSex}")
    public CommonResult<Integer> saveUser(
            @PathVariable("userId") String userId,
            @PathVariable("password") String password,
            @PathVariable("userName") String userName,
            @PathVariable("userSex") Integer userSex) throws Exception{
        User param = new User();
        param.setUserId(userId);
        param.setPassword(password);
        param.setUserName(userName);
        param.setUserSex(userSex);
        int result = userService.saveUser(param);
        return new CommonResult(200,"success",result);
    }
}

