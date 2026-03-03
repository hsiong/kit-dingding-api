package com.example.dingding.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GetUserByMobileRequest {

    @Schema(description = "用户手机号", example = "13812345678")
    @NotBlank
    private String mobile;
}
