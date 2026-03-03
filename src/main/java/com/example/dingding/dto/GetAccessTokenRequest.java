package com.example.dingding.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GetAccessTokenRequest {

    @NotBlank
    private String appKey;

    @NotBlank
    private String appSecret;
}
