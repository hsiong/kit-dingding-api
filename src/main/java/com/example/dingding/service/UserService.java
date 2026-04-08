package com.example.dingding.service;

import com.example.dingding.dto.GetUserDetailResult;

/**
 * 用户信息服务。
 */
public interface UserService {

    /**
     * 通过手机号获取钉钉用户ID。
     *
     * @param mobile 手机号
     * @return 用户ID
     */
    String getUserByMobile(String mobile);

    /**
     * 查询用户详情。
     *
     * @param userId 用户ID
     * @return 用户详情
     */
    GetUserDetailResult getUserDetail(String userId);
}
