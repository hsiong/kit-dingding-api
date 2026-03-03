package com.example.dingding.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateSceneGroupResult {

    @Schema(description = "群聊ID")
    @JsonProperty("chat_id")
    private String chatId;

    @Schema(description = "开放会话ID")
    @JsonProperty("open_conversation_id")
    private String openConversationId;
}
