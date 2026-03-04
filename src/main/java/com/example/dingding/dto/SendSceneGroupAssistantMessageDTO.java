package com.example.dingding.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class SendSceneGroupAssistantMessageDTO {

    @Schema(description = "群ID（open_conversation_id）", example = "cid9FTRQSLo+sK*****==")
    @JsonProperty("target_open_conversation_id")
    @NotBlank
    private String targetOpenConversationId;

    @Schema(description = "文本参数映射，JSON 字符串", example = "{\"text1\":\"hello\",\"text2\":\"world\"}")
    private String message;

    @Schema(description = "@人 userId 列表（最多50）")
    @JsonProperty("at_users")
    private List<String> atUsers;
}
