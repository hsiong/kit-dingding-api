package com.example.dingding.handler.impl;

import com.dingtalk.open.app.api.models.bot.ChatbotMessage;
import com.example.dingding.constant.BotMsgType;
import com.example.dingding.handler.BotMsgHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 〈〉
 *
 * @author Hsiong
 * @version 1.0.0
 * @since 3/26/26
 */
@Slf4j
@Component
public class MarkdownMessageHandler implements BotMsgHandler {
	
	@Override
	public BotMsgType supportMsgType() {
		return BotMsgType.MARKDOWN;
	}
	
	@Override
	public String getContent(ChatbotMessage message) {
		if (message.getText() == null) {
			log.warn("text message content is null, msgId={}", message.getMsgId());
			return null;
		}
		
		String content = message.getText().getContent();
		log.info("handle text message, msgId={}, content={}", message.getMsgId(), content);
		
		// 这里写 text 的业务逻辑
		return content; 
	}
}
