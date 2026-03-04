package com.example.dingding.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SendSceneGroupAssistantMessageResult {

    @Schema(description = "消息ID，可用于查询消息已读状态")
    @JsonProperty("open_msg_id")
    private String openMsgId;
}
