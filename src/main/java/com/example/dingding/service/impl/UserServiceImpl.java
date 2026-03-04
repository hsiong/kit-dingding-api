package com.example.dingding.service.impl;

import com.example.dingding.dto.GetUserByMobileRequest;
import com.example.dingding.dto.GetUserByMobileResult;
import com.example.dingding.feign.RestDingClient;
import com.example.dingding.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现。
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private RestDingClient restDingClient;

    /**
     * 调用钉钉通讯录接口，通过手机号查询用户ID。
     */
    @Override
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
}
