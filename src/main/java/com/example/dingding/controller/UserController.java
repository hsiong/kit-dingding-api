package com.example.dingding.controller;

import com.example.dingding.dto.GetUserByMobileRequest;
import com.example.dingding.service.UserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 钉钉用户相关接口。
 */
@RestController
@RequestMapping("/api/dingtalk/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 通过手机号查询钉钉用户ID。
     *
     * @param request 查询参数
     * @return 用户ID
     */
    @PostMapping("/get-by-mobile")
    public String getByMobile(@Valid @RequestBody GetUserByMobileRequest request) {
        return userService.getUserByMobile(request.getMobile());
    }
    
}
