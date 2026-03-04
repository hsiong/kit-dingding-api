package com.example.dingding.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SendSceneGroupAssistantMessageRequest {

    @Schema(description = "群ID（open_conversation_id）", example = "cid9FTRQSLo+sK*****==")
    @JsonProperty("target_open_conversation_id")
    @NotBlank
    private String targetOpenConversationId;

    @Schema(description = "消息模板ID", example = "offical_template_test_action_card")
    @JsonProperty("msg_template_id")
    @NotBlank
    private String msgTemplateId;

    @Schema(description = "文本参数映射，JSON 字符串", example = "{\"text1\":\"hello\",\"text2\":\"world\"}")
    @JsonProperty("msg_param_map")
    private String msgParamMap;

    @Schema(description = "多媒体参数映射，JSON 字符串", example = "{\"pic1\":\"@123\",\"pic2\":\"@456\"}")
    @JsonProperty("msg_media_id_param_map")
    private String msgMediaIdParamMap;

    @Schema(description = "接收人 userId 列表")
    @JsonProperty("receiver_user_ids")
    private String receiverUserIds;

    @Schema(description = "接收人 unionId 列表")
    @JsonProperty("receiver_union_ids")
    private String receiverUnionIds;

    @Schema(description = "接收人手机号列表")
    @JsonProperty("receiver_mobiles")
    private String receiverMobiles;

    @Schema(description = "@人手机号列表（最多50）")
    @JsonProperty("at_mobiles")
    private String atMobiles;

    @Schema(description = "@人 userId 列表（最多50）")
    @JsonProperty("at_users")
    private String atUsers;

    @Schema(description = "是否 @所有人", example = "false")
    @JsonProperty("is_at_all")
    private Boolean isAtAll;

    @Schema(description = "机器人编码", example = "fTv5O*****")
    @JsonProperty("robot_code")
    @NotBlank
    private String robotCode;
}
