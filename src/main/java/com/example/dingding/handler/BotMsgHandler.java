package com.example.dingding.handler;

import com.dingtalk.open.app.api.models.bot.ChatbotMessage;
import com.example.dingding.constant.BotMsgType;

/**
 * 〈〉
 *
 * @author Hsiong
 * @version 1.0.0
 * @since 3/26/26
 */
public interface BotMsgHandler {
	
	BotMsgType supportMsgType();
	
	String getContent(ChatbotMessage message);
	
	
}
