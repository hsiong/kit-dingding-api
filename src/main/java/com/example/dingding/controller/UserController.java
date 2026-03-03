package com.example.dingding.controller;

import com.example.dingding.dto.GetUserByMobileRequest;
import com.example.dingding.dto.GetUserByMobileResult;
import com.example.dingding.service.UserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dingtalk/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/get-by-mobile")
    public GetUserByMobileResult getByMobile(@Valid @RequestBody GetUserByMobileRequest request) {
        return userService.getUserByMobile(request.getMobile());
    }
}
