package com.example.dingding.controller;

import com.example.dingding.dto.GetAccessTokenResult;
import com.example.dingding.service.AccessTokenService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 钉钉 OAuth2 相关接口。
 */
@RestController
@RequestMapping("/api/dingtalk/oauth2")
public class AccessTokenController {

    @Resource
    private AccessTokenService accessTokenService;

    /**
     * 获取应用访问令牌。
     *
     * @return 钉钉 access token 结果
     */
    @GetMapping("/access-token")
    public GetAccessTokenResult getAccessToken() {
        return accessTokenService.getAccessToken();
    }
}
