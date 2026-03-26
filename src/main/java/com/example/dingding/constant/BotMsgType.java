package com.example.dingding.constant;

import java.util.Arrays;

/**
 * 自定义机器人发送消息的消息类型
 * https://open.dingtalk.com/document/dingstart/custom-bot-send-message-type?spm=ding_open_doc.document.0.0.79704c27H8DLqM
 */
public enum BotMsgType {
	
	TEXT("text"),
	LINK("link"),
	MARKDOWN("markdown"),
	ACTION_CARD("actionCard"),
	FEED_CARD("feedCard"),
	UNKNOWN("unknown");
	
	private final String code;
	
	BotMsgType(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	public static BotMsgType fromCode(String code) {
		if (code == null || code.isBlank()) {
			return UNKNOWN;
		}
		return Arrays.stream(values())
					 .filter(e -> e.code.equalsIgnoreCase(code))
					 .findFirst()
					 .orElse(UNKNOWN);
	}
}
