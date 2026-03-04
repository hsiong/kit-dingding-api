package com.example.dingding.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class CreateSceneGroupReqDTO {

    @Schema(description = "群名称。说明：最长不超过30字符，建议长度在10字符以内。", example = "测试群")
    @NotBlank
    private String title;

    @Schema(description = "群主的 手机号", example = "022*****")
    @NotBlank
    private String ownerUserPhone;

    @Schema(description = "群成员 手机号。说明：最多传999个。", example = "072*****,013*****")
    private List<String> userPhones;
}
