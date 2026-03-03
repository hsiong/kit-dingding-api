package com.example.dingding.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class GetAccessTokenResult {

    @Schema(description = "生成的accessToken")
    private String accessToken;

    @Schema(description = "accessToken过期时间，单位秒")
    private Long expireIn;
}
