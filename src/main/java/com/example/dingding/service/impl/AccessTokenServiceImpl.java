package com.example.dingding.service.impl;

import com.example.dingding.config.dingding.DingdingConfig;
import com.example.dingding.dto.GetAccessTokenRequest;
import com.example.dingding.dto.GetAccessTokenResult;
import com.example.dingding.feign.AccessTokenFeignClient;
import com.example.dingding.service.AccessTokenService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class AccessTokenServiceImpl implements AccessTokenService {

    @Resource
    private AccessTokenFeignClient accessTokenFeignClient;

    @Resource
    private DingdingConfig dingdingConfig;

    @Override
    public GetAccessTokenResult getAccessToken() {
        GetAccessTokenRequest request = new GetAccessTokenRequest();
        request.setAppKey(dingdingConfig.getAppKey());
        request.setAppSecret(dingdingConfig.getAppSecret());
        return accessTokenFeignClient.getAccessToken(request);
    }
}
