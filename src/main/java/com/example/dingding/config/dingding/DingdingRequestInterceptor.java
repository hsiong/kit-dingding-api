package com.example.dingding.config.dingding;

import com.example.dingding.service.AccessTokenService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class DingdingRequestInterceptor implements RequestInterceptor {
    
    @Resource
    private AccessTokenService accessTokenService;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.query("access_token", accessTokenService.getAccessToken().getAccessToken());
    }
}
