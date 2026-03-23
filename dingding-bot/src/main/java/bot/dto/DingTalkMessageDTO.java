package bot.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * 钉钉机器人消息回调DTO
 */
@Data
public class DingTalkMessageDTO {
	
	/**
	 * 会话ID
	 */
	@JsonProperty("conversationId")
	private String conversationId;
	
	/**
	 * 被@的用户列表
	 */
	@JsonProperty("atUsers")
	private List<AtUserDTO> atUsers;
	
	/**
	 * 企业ID
	 */
	@JsonProperty("chatbotCorpId")
	private String chatbotCorpId;
	
	/**
	 * 机器人用户ID
	 */
	@JsonProperty("chatbotUserId")
	private String chatbotUserId;
	
	/**
	 * 消息ID
	 */
	@JsonProperty("msgId")
	private String msgId;
	
	/**
	 * 发送人昵称
	 */
	@JsonProperty("senderNick")
	private String senderNick;
	
	/**
	 * 是否管理员
	 */
	@JsonProperty("isAdmin")
	private Boolean isAdmin;
	
	/**
	 * 发送人员工ID
	 * 使用 senderStaffId，为发送者 userId 值。
	 */
	@JsonProperty("senderStaffId")
	private String senderStaffId;
	
	/**
	 * Webhook过期时间（毫秒时间戳）
	 */
	@JsonProperty("sessionWebhookExpiredTime")
	private Long sessionWebhookExpiredTime;
	
	/**
	 * 消息创建时间（毫秒时间戳）
	 */
	@JsonProperty("createAt")
	private Long createAt;
	
	/**
	 * 发送人企业ID
	 */
	@JsonProperty("senderCorpId")
	private String senderCorpId;
	
	/**
	 * 会话类型（1：单聊，2：群聊）
	 */
	@JsonProperty("conversationType")
	private String conversationType;
	
	/**
	 * 加密的发送者 ID。
	 */
	@JsonProperty("senderId")
	private String senderId;
	
	/**
	 * 会话标题（群名）
	 */
	@JsonProperty("conversationTitle")
	private String conversationTitle;
	
	/**
	 * 当前机器人是否被@到
	 */
	@JsonProperty("isInAtList")
	private Boolean isInAtList;
	
	/**
	 * 临时Webhook地址（用于回复消息）
	 */
	@JsonProperty("sessionWebhook")
	private String sessionWebhook;
	
	/**
	 * 消息内容（文本内容）
	 */
	@JsonProperty("content")
	private TextDTO text;
	
	@Data
	public static class TextDTO {
		private String content;
	}
	
	/**
	 * 消息类型（text / image / 等）
	 */
	@JsonProperty("msgtype")
	private String msgtype;
	
	// ================= 时间转换方法 =================
	
	/**
	 * 将 createAt（毫秒）转为 LocalDateTime
	 */
	public LocalDateTime getCreateAtTime() {
		if (createAt == null) {
			return null;
		}
		return LocalDateTime.ofInstant(
			Instant.ofEpochMilli(createAt),
			ZoneId.systemDefault()
		);
	}
	
	/**
	 * 将 sessionWebhookExpiredTime（毫秒）转为 LocalDateTime
	 */
	public LocalDateTime getWebhookExpiredTime() {
		if (sessionWebhookExpiredTime == null) {
			return null;
		}
		return LocalDateTime.ofInstant(
			Instant.ofEpochMilli(sessionWebhookExpiredTime),
			ZoneId.systemDefault()
		);
	}
	
	// ================= 内部类 =================
	
	/**
	 * @用户信息
	 */
	@Data
	public static class AtUserDTO {
		
		/**
		 * 钉钉用户ID
		 */
		@JsonProperty("dingtalkId")
		private String dingtalkId;
		
		/**
		 * 员工ID
		 */
		@JsonProperty("staffId")
		private String staffId;
	}
}