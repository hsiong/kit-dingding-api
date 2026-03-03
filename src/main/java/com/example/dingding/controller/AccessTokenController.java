package com.example.dingding.controller;

import com.example.dingding.dto.GetAccessTokenResult;
import com.example.dingding.service.AccessTokenService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dingtalk/oauth2")
public class AccessTokenController {

    @Resource
    private AccessTokenService accessTokenService;

    @GetMapping("/access-token")
    public GetAccessTokenResult getAccessToken() {
        return accessTokenService.getAccessToken();
    }
}
