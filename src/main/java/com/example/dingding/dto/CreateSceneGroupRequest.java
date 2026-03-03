package com.example.dingding.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class CreateSceneGroupRequest {

    @Schema(description = "群标题")
    @NotBlank
    private String title;

    @Schema(description = "群模板ID")
    @JsonProperty("template_id")
    @NotBlank
    private String templateId;

    @Schema(description = "群主用户ID")
    @JsonProperty("owner_user_id")
    @NotBlank
    private String ownerUserId;
    
    @Schema(description = "群成员用户ID列表，以逗号分隔", example = "xxx,xxx2...")
    @JsonProperty("user_ids")
    @NotBlank
    private String userIds;
    
    @Schema(description = "群管理员用户ID列表")
    @JsonProperty("subadmin_ids")
    private String subadminIds;
    
    @Schema(description = "业务方唯一标识，幂等控制")
    private String uuid;
    
    @Schema(description = "群头像URL")
    private String icon;

    @Schema(description = "是否允许@所有人, 0(默认)-允许，1-不允许")
    @JsonProperty("mention_all_authority")
    private Integer mentionAllAuthority;

    @Schema(description = "新成员是否可查看历史消息，0(默认)-不允许，1-允许")
    @JsonProperty("show_history_type")
    private Integer showHistoryType;
    
    @Schema(description = "入群验证方式, 0(默认)-无需验证，1-需要验证")
    @JsonProperty("validation_type")
    private Integer validationType = 1;

    @Schema(description = "是否可被搜索, 0(默认)-不可搜索，1-可搜索")
    private Integer searchable = 1;
    
    @Schema(description = "禁言配置")
    @JsonProperty("chat_banned_type")
    @Valid
    private ChatBannedType chatBannedType;


    @Schema(description = "群管理配置")
    @JsonProperty("management_options")
    @Valid
    private ManagementOptions managementOptions;

    @Schema(description = "是否仅管理员可发DING")
    @JsonProperty("only_admin_can_ding")
    private Integer onlyAdminCanDing;

    @Schema(description = "是否允许所有成员发起视频会议")
    @JsonProperty("all_members_can_create_mcs_conf")
    private Integer allMembersCanCreateMcsConf;

    @Schema(description = "是否允许所有成员创建日程")
    @JsonProperty("all_members_can_create_calendar")
    private Integer allMembersCanCreateCalendar;

    @Schema(description = "是否禁用群邮箱")
    @JsonProperty("group_email_disabled")
    private Integer groupEmailDisabled;

    @Schema(description = "群分享链接")
    @JsonProperty("share_url")
    private String shareUrl;

    @Schema(description = "机器人管理列表")
    @JsonProperty("chatbot_manage_list")
    @Valid
    private List<ChatbotManage> chatbotManageList;

    @Schema(description = "入群关键词配置")
    @Valid
    private Keyword keyword;

    @Schema(description = "企业组织ID")
    @JsonProperty("org_id")
    private Integer orgId;

    @Data
    public static class ManagementOptions {

        @Schema(description = "管理权限设置")
        @JsonProperty("manage_permission")
        private Integer managePermission;

        @Schema(description = "是否展示员工ID")
        @JsonProperty("show_staff_id")
        private Integer showStaffId;
    }

    @Data
    public static class ChatbotManage {

        @Schema(description = "机器人模板ID")
        @JsonProperty("chatbot_template_id")
        private String chatbotTemplateId;

        @Schema(description = "机器人管理角色")
        @JsonProperty("manage_role")
        private Integer manageRole;
    }

    @Data
    public static class ChatBannedType {

        @Schema(description = "禁言类型")
        @JsonProperty("chat_banned_type")
        private Integer chatBannedType;

        @Schema(description = "禁言词列表")
        @JsonProperty("banned_words")
        private List<String> bannedWords;

        @Schema(description = "黑名单词列表")
        @JsonProperty("blacklist_words")
        private List<String> blacklistWords;

        @Schema(description = "白名单用户ID列表")
        @JsonProperty("white_list_users")
        private List<String> whiteListUsers;

        @Schema(description = "黑名单用户ID列表")
        @JsonProperty("blacklist_users")
        private List<String> blacklistUsers;

        @Schema(description = "是否将新增禁言词加入黑名单")
        @JsonProperty("add_banned_words_blacklist")
        private Integer addBannedWordsBlacklist;
    }

    @Data
    public static class Keyword {

        @Schema(description = "关键词")
        private String keyword;

        @Schema(description = "关键词正则")
        @JsonProperty("keyword_regex")
        private String keywordRegex;

        @Schema(description = "命中后是否@所有人")
        @JsonProperty("mention_all")
        private Integer mentionAll;
    }
}
