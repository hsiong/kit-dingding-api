package com.example.dingding.service.impl;

import com.example.dingding.config.dingding.DingdingConfig;
import com.example.dingding.dto.GetAccessTokenRequest;
import com.example.dingding.dto.GetAccessTokenResult;
import com.example.dingding.feign.AccessTokenFeignClient;
import com.example.dingding.service.AccessTokenService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 访问令牌服务实现。
 */
@Service
public class AccessTokenServiceImpl implements AccessTokenService {

    @Resource
    private AccessTokenFeignClient accessTokenFeignClient;

    @Resource
    private DingdingConfig dingdingConfig;

    /**
     * 使用应用凭证换取 access token。
     */
    @Override
    public GetAccessTokenResult getAccessToken() {
        // 组装请求参数（appKey/appSecret）。
        GetAccessTokenRequest request = new GetAccessTokenRequest();
        request.setAppKey(dingdingConfig.getAppKey());
        request.setAppSecret(dingdingConfig.getAppSecret());

        // 调用钉钉 OAuth2 接口获取 token。
        return accessTokenFeignClient.getAccessToken(request);
    }
}
