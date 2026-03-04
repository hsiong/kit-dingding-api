package com.example.dingding.service;

import com.example.dingding.dto.GetAccessTokenResult;

/**
 * 访问令牌服务。
 */
public interface AccessTokenService {

    /**
     * 获取钉钉 access token。
     *
     * @return 令牌结果
     */
    GetAccessTokenResult getAccessToken();
}
