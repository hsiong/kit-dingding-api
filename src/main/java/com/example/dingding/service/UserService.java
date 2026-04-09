package com.example.dingding.service;

import com.example.dingding.dto.GetUserDetailRequest;
import com.example.dingding.dto.GetUserDetailResult;
import com.example.dingding.dto.GetUserByMobileRequest;
import com.example.dingding.dto.GetUserByMobileResult;
import com.example.dingding.feign.RestDingClient;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 用户信息服务。
 */
@Service
public class UserService {

    @Resource
    private RestDingClient restDingClient;

    /**
     * 调用钉钉通讯录接口，通过手机号查询用户 ID。
     */
    public String getUserByMobile(String mobile) {
        GetUserByMobileRequest request = new GetUserByMobileRequest();
        request.setMobile(mobile);

        // 统一解码器会先校验 errcode，再返回 result。
        GetUserByMobileResult userByMobile = null;
        try {
            userByMobile = restDingClient.getUserByMobile(request);
        } catch (Exception e) { // 找不到用户，在这里处理
            throw new RuntimeException(e);
        }
        return userByMobile.getUserId();
    }

    public GetUserDetailResult getUserDetail(String userId) {
        GetUserDetailRequest request = new GetUserDetailRequest();
        request.setUserid(userId);
        request.setLanguage("zh_CN");
        return restDingClient.getUserDetail(request);
    }
}
