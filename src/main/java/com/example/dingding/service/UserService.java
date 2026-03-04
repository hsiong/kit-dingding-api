package com.example.dingding.service;

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
}
