package com.example.dingding.feign;

import com.example.dingding.config.FeignConfiguration;
import com.example.dingding.dto.GetAccessTokenRequest;
import com.example.dingding.dto.GetAccessTokenResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
    name = "AccessTokenFeignClient",
    url = "${dingding.token-url}",
    configuration = {
        FeignConfiguration.class
        }
)
public interface AccessTokenFeignClient {

    @PostMapping("/v1.0/oauth2/accessToken")
    GetAccessTokenResult getAccessToken(@RequestBody GetAccessTokenRequest request);
}
