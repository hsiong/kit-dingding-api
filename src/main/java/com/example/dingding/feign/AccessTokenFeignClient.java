package com.example.dingding.feign;

import com.example.dingding.config.FeignConfiguration;
import com.example.dingding.dto.GetAccessTokenRequest;
import com.example.dingding.dto.GetAccessTokenResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 钉钉 OAuth2 令牌接口客户端。
 */
@FeignClient(
    name = "AccessTokenFeignClient",
    url = "${dingding.token-url}",
    configuration = {
        FeignConfiguration.class
        }
)
public interface AccessTokenFeignClient {

    /**
     * 调用钉钉接口获取 access token。
     *
     * @param request 应用凭证
     * @return 令牌结果
     */
    @PostMapping("/v1.0/oauth2/accessToken")
    GetAccessTokenResult getAccessToken(@RequestBody GetAccessTokenRequest request);
}
