package com.example.dingding.service;

import com.example.dingding.dto.*;

/**
 * 群聊服务。
 */
public interface MessageService {
    
    /**
     * 发送群助手消息（群模板机器人）。
     *
     * @param request 发送请求
     * @return 发送结果
     */
    SendSceneGroupAssistantMessageResult sendSceneGroupAssistantMessage(SendSceneGroupAssistantMessageDTO request);
}
