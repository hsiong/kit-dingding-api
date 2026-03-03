package com.example.dingding.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateSceneGroupRequest {

    @Schema(description = "群名称。说明：最长不超过30字符，建议长度在10字符以内。", example = "测试群")
    @NotBlank
    private String title;

    @Schema(
            description = "群模板ID，登录开发者后台 > 开放能力 > 场景群 > 群模板查看id。",
            example = "c354***-***-***-b4ea-6f1ab***65"
    )
    @JsonProperty("template_id")
    @NotBlank
    private String templateId;

    @Schema(description = "群主的userid。", example = "022*****")
    @JsonProperty("owner_user_id")
    @NotBlank
    private String ownerUserId;

    @Schema(description = "群成员userid列表。说明：最多传999个。", example = "072*****,013*****")
    @JsonProperty("user_ids")
    private String userIds;

    @Schema(description = "群管理员userid列表。", example = "072*****,013*****")
    @JsonProperty("subadmin_ids")
    private String subadminIds;

    @Schema(description = "建群去重的业务ID，由接口调用方指定。说明：建议长度在64字符以内。", example = "axcf*-*****-*****-23da*")
    private String uuid;

    @Schema(description = "群头像，格式为mediaId。需要先调用上传媒体文件接口获取mediaId。", example = "@lADOADma*****QKA")
    private String icon;

    @Schema(description = "@all 权限：0（默认）：所有人都可以@all；1：仅群主可@all", example = "0")
    @JsonProperty("mention_all_authority")
    private Integer mentionAllAuthority;

    @Schema(description = "新成员是否可查看聊天历史消息：0（默认）：不可以查看历史记录；1：可以查看历史记录", example = "0")
    @JsonProperty("show_history_type")
    private Integer showHistoryType;

    @Schema(description = "入群是否需要验证：0（默认）：不验证入群；1：入群验证", example = "0")
    @JsonProperty("validation_type")
    private Integer validationType = 1;

    @Schema(description = "群是否可搜索：0（默认）：不可搜索；1：可搜索", example = "0")
    private Integer searchable = 1;

    @Schema(description = "是否开启群禁言：0（默认）：不禁言；1：全员禁言", example = "0")
    @JsonProperty("chat_banned_type")
    private Integer chatBannedType;

    @Schema(description = "管理类型：0（默认）：所有人可管理；1：仅群主可管理", example = "0")
    @JsonProperty("management_type")
    private Integer managementType = 1;

    @Schema(description = "群内发DING权限：0（默认）：所有人可发DING；1：仅群主和管理员可发DING", example = "0")
    @JsonProperty("only_admin_can_ding")
    private Integer onlyAdminCanDing;

    @Schema(description = "群会议权限：0：仅群主和管理员可发起视频和语音会议；1（默认）：所有人可发起视频和语音会议", example = "1")
    @JsonProperty("all_members_can_create_mcs_conf")
    private Integer allMembersCanCreateMcsConf;

    @Schema(description = "群日历设置项，群内非好友/同事的成员是否可相互发起钉钉日程：0（默认）：不可以；1：可以", example = "0")
    @JsonProperty("all_members_can_create_calendar")
    private Integer allMembersCanCreateCalendar;

    @Schema(description = "是否禁止发送群邮件：0（默认）：群内成员可以对本群发送群邮件；1：群内成员不可对本群发送群邮件", example = "0")
    @JsonProperty("group_email_disabled")
    private Integer groupEmailDisabled;

    @Schema(description = "置顶群消息权限：0（默认）：所有人可置顶群消息；1：仅群主和管理员可置顶群消息", example = "0")
    @JsonProperty("only_admin_can_set_msg_top")
    private Integer onlyAdminCanSetMsgTop;

    @Schema(description = "群成员私聊权限：0（默认）：所有人可私聊；1：普通群成员之间不能加好友、单聊，且部分功能受限", example = "0")
    @JsonProperty("add_friend_forbidden")
    private Integer addFriendForbidden;

    @Schema(description = "群直播权限：0：仅群主与管理员可发起直播；1（默认）：群内任意成员可发起群直播", example = "1")
    @JsonProperty("group_live_switch")
    private Integer groupLiveSwitch;

    @Schema(description = "是否禁止非管理员向管理员发起单聊：0（默认）：非管理员可以向管理员发起单聊；1：禁止非管理员向管理员发起单聊", example = "0")
    @JsonProperty("members_to_admin_chat")
    private Integer membersToAdminChat;
}
