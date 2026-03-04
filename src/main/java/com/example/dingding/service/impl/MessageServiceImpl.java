package com.example.dingding.service.impl;

import com.example.dingding.config.dingding.DingdingConfig;
import com.example.dingding.dto.SendSceneGroupAssistantMessageDTO;
import com.example.dingding.dto.SendSceneGroupAssistantMessageRequest;
import com.example.dingding.dto.SendSceneGroupAssistantMessageResult;
import com.example.dingding.feign.RestDingClient;
import com.example.dingding.service.MessageService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 群聊服务实现。
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private RestDingClient restDingClient;
    
    @Resource
    private DingdingConfig dingdingConfig; 

    /**
     * 发送群助手消息。
     */
    @Override
    public SendSceneGroupAssistantMessageResult sendSceneGroupAssistantMessage(
        SendSceneGroupAssistantMessageDTO dto
    ) {
        String msgParamMap = String.format("{\"content\":\"%s\"}", dto.getMessage());
        
        SendSceneGroupAssistantMessageRequest request = new SendSceneGroupAssistantMessageRequest();
        request.setTargetOpenConversationId(dto.getTargetOpenConversationId());
        request.setAtUsers(dto.getAtUsers() == null ? null : String.join(",", dto.getAtUsers()));
        request.setMsgParamMap(msgParamMap);
        request.setRobotCode(dingdingConfig.getRobotCode());
        /**
         * https://open.dingtalk.com/document/development/group-template-robot-message
         * 文本消息模板：msg_template_id取值为inner_app_template_text。
         */
        request.setMsgTemplateId("inner_app_template_text");
        request.setIsAtAll(true);
        return restDingClient.sendSceneGroupAssistantMessage(request);
    }
}
