package com.example.dingding.config.dingding;

import com.example.dingding.config.redis.RedisUtil;
import com.example.dingding.dto.GetAccessTokenResult;
import com.example.dingding.service.AccessTokenService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 钉钉接口请求拦截器：为每次请求自动追加 access_token。
 */
@Component
public class DingdingRequestInterceptor implements RequestInterceptor {

    @Resource
    private AccessTokenService accessTokenService;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String redisKey = "dingding_access_token";
        Object accessTokenObj = redisUtil.get(redisKey);
        String accessToken;

        // 优先读取缓存，避免每次请求都调用 OAuth2 接口。
        if (accessTokenObj != null) {
            accessToken = accessTokenObj.toString();
        } else {
            // 缓存未命中时调用钉钉接口，并按过期时间写回缓存。
            GetAccessTokenResult tokenResult = accessTokenService.getAccessToken();
            accessToken = tokenResult.getAccessToken();
            redisUtil.set(redisKey, accessToken, tokenResult.getExpireIn());
        }

        // 钉钉开放接口使用 query 参数传递 access_token。
        requestTemplate.query("access_token", accessToken);
    }
}
