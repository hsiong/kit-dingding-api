package com.example.dingding.service;

import com.example.dingding.dto.GetUserByMobileResult;

public interface UserService {
    GetUserByMobileResult getUserByMobile(String mobile);
}
