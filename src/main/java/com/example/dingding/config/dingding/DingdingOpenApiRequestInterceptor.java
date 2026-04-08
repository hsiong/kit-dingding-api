package com.example.dingding.config.dingding;

import com.example.dingding.config.redis.RedisUtil;
import com.example.dingding.dto.GetAccessTokenResult;
import com.example.dingding.service.AccessTokenService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 钉钉 OpenAPI 请求拦截器：为新版接口自动追加访问令牌请求头。
 */
@Component
public class DingdingOpenApiRequestInterceptor implements RequestInterceptor {

    private static final String ACCESS_TOKEN_CACHE_KEY = "dingding_access_token";

    @Resource
    private AccessTokenService accessTokenService;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        Object accessTokenObj = redisUtil.get(ACCESS_TOKEN_CACHE_KEY);
        String accessToken;

        if (accessTokenObj != null) {
            accessToken = accessTokenObj.toString();
        } else {
            GetAccessTokenResult tokenResult = accessTokenService.getAccessToken();
            accessToken = tokenResult.getAccessToken();
            redisUtil.set(ACCESS_TOKEN_CACHE_KEY, accessToken, tokenResult.getExpireIn());
        }

        requestTemplate.header("x-acs-dingtalk-access-token", accessToken);
    }
}
