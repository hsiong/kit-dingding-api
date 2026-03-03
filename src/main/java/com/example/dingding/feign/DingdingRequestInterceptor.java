package com.example.dingding.feign;

import com.example.dingding.config.DingdingConfig;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class DingdingRequestInterceptor implements RequestInterceptor {

    @Resource
    private DingdingConfig dingdingConfig;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.query("access_token", dingdingConfig.getAccessToken());
    }
}
