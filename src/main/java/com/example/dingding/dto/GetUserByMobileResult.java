package com.example.dingding.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class GetUserByMobileResult {

    @Schema(description = "用户在企业内的userid")
    @JsonProperty("userid")
    private String userId;
}
