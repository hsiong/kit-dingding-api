package com.example.dingding.dto;

import lombok.Data;

/**
 * 用户详情结果。
 * 对应接口：POST /topapi/v2/user/get
 * 当前仅映射本项目实际使用的字段。
 */
@Data
public class GetUserDetailResult {

    /**
     * 员工 userId。
     */
    private String userid;

    /**
     * 员工在当前开发者企业账号范围内的唯一标识。
     */
    private String unionid;

    /**
     * 员工姓名。
     */
    private String name;

    /**
     * 手机号码。
     */
    private String mobile;

    /**
     * 职位。
     */
    private String title;

    /**
     * 员工邮箱。
     */
    private String email;

    /**
     * 是否已激活钉钉。
     */
    private Boolean active;
}
