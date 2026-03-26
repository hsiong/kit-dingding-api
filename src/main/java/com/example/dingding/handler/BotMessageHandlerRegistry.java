package com.example.dingding.handler;

import com.example.dingding.constant.BotMsgType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * 〈〉
 *
 * @author Hsiong
 * @version 1.0.0
 * @since 3/26/26
 */
@Slf4j
@Component
public class BotMessageHandlerRegistry {
	
	private final Map<BotMsgType, BotMsgHandler> handlerMap = new EnumMap<>(BotMsgType.class);
	
	public BotMessageHandlerRegistry(List<BotMsgHandler> handlers) {
		for (BotMsgHandler handler : handlers) {
			BotMsgType type = handler.supportMsgType();
			if (handlerMap.put(type, handler) != null) {
				throw new IllegalStateException("Duplicate handler for type: " + type);
			}
		}
	}
	
	/**
	 * 获取指定类型的处理器
	 * 
	 * @param type 消息类型
	 * @return 对应类型的处理器
	 * @throws IllegalArgumentException 如果类型不支持
	 */
	public BotMsgHandler getHandler(BotMsgType type) {
		BotMsgHandler handler = handlerMap.get(type);
		if (handler == null) {
			// 默认返回 text
			log.warn("no handler for type: {}, fallback to TextMessageHandler", type);
			handler = handlerMap.get(BotMsgType.TEXT);
		}
		return handler;
	}
	
}
