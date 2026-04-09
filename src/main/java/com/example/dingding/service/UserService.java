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
 * 提供钉钉通讯录用户信息查询功能，支持按手机号和用户 ID 查询。
 */
@Service
public class UserService {

    @Resource
    private RestDingClient restDingClient;

    /**
     * 通过手机号查询用户 ID。
     * 调用钉钉通讯录接口，根据手机号获取对应的用户 ID。
     * 
     * @param mobile 用户手机号
     * @return 对应的钉钉 userId
     * @throws RuntimeException 如果查询失败或未找到用户
     */
    public String getUserByMobile(String mobile) {
        GetUserByMobileRequest request = new GetUserByMobileRequest();
        request.setMobile(mobile);

        // 统一解码器会先校验 errcode，再返回 result
        GetUserByMobileResult userByMobile = null;
        try {
            userByMobile = restDingClient.getUserByMobile(request);
        } catch (Exception e) {
            throw new RuntimeException("通过手机号查询用户失败：" + mobile, e);
        }
        return userByMobile.getUserId();
    }

    /**
     * 根据 userId 获取用户详细信息。
     * 
     * @param userId 钉钉用户 ID
     * @return 包含用户详细信息的返回结果
     */
    public GetUserDetailResult getUserDetail(String userId) {
        GetUserDetailRequest request = new GetUserDetailRequest();
        request.setUserid(userId);
        request.setLanguage("zh_CN");
        return restDingClient.getUserDetail(request);
    }
}
