package com.example.dingding.dto;

import lombok.Data;

/**
 * 查询用户详情请求。
 */
@Data
public class GetUserDetailRequest {

    private String userid;

    private String language;
}
