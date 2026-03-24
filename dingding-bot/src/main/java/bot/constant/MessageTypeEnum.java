package bot.constant;

import com.dingtalk.api.request.OapiRobotSendRequest;

/**
 * 自定义机器人发送消息的消息类型
 * https://open.dingtalk.com/document/dingstart/custom-bot-send-message-type?spm=ding_open_doc.document.0.0.4b7765d7vGUvkC
 */
public enum MessageTypeEnum {
	
	TEXT("text"), // 文本消息
	LINK("link"), // 链接消息
	MARKDOWN("markdown"), // Markdown 消息
	ACTION_CARD("actionCard"), // 操作卡片消息
	FEED_CARD("feedCard"), // 动态卡片消息
	
	;
	
	/**
	 * 实际值（对应钉钉 msgtype）
	 */
	private final String code;
	
	MessageTypeEnum(String code) {
		this.code = code;
	}
	
	/**
	 * 获取值
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * text 类型
	 * @param request
	 * @param content
	 * @return
	 */
	public static OapiRobotSendRequest getText(OapiRobotSendRequest request, String content) {
		OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
		text.setContent(String.format("#内容 \n > %s", content));
		request.setText(text);
		return request;
	}
	
	public static OapiRobotSendRequest getMarkdown(OapiRobotSendRequest request, String title, String content) {
		OapiRobotSendRequest.Markdown text = new OapiRobotSendRequest.Markdown();
		text.setTitle(title);
		text.setText(String.format("# 内容 \n > %s", content));
		request.setMarkdown(text);
		return request;
	}
}
