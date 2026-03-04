package com.example.dingding.service.impl;

import com.example.dingding.dto.GetUserByMobileRequest;
import com.example.dingding.dto.GetUserByMobileResult;
import com.example.dingding.feign.RestDingClient;
import com.example.dingding.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private RestDingClient restDingClient;

    @Override
    public String getUserByMobile(String mobile) {
        GetUserByMobileRequest request = new GetUserByMobileRequest();
        request.setMobile(mobile);
        return restDingClient.getUserByMobile(request).getUserId();
    }
}
