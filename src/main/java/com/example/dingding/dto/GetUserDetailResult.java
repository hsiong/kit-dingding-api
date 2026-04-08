package com.example.dingding.dto;

import lombok.Data;

/**
 * 用户详情结果。
 */
@Data
public class GetUserDetailResult {

    private String userid;

    private String unionid;

    private String name;

    private String mobile;

    private String title;

    private String email;

    private Boolean active;
}
