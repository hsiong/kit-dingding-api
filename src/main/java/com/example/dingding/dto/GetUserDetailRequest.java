package com.example.dingding.dto;

import lombok.Data;

/**
 * 查询用户详情请求。
 * 对应接口：POST /topapi/v2/user/get
 */
@Data
public class GetUserDetailRequest {

    /**
     * 用户的 userId。
     */
    private String userid;

    /**
     * 通讯录语言，默认可使用 zh_CN。
     */
    private String language;
}
