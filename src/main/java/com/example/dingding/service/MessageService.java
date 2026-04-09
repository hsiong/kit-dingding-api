package com.example.dingding.service;

import com.example.dingding.config.dingding.DingdingConfig;
import com.example.dingding.dto.SendSceneGroupAssistantMessageDTO;
import com.example.dingding.dto.SendSceneGroupAssistantMessageRequest;
import com.example.dingding.dto.SendSceneGroupAssistantMessageResult;
import com.example.dingding.feign.RestDingClient;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 消息服务。
 * 提供钉钉群聊消息发送功能，支持通过群模板机器人发送助手消息。
 * 参考文档：https://open.dingtalk.com/document/development/group-template-robot-message
 */
@Service
public class MessageService {

    @Resource
    private RestDingClient restDingClient;

    @Resource
    private DingdingConfig dingdingConfig;

    /**
     * 发送群助手消息。
     * 通过群模板机器人向指定群聊发送文本消息。
     * 
     * @param dto 包含消息内容、目标群聊 ID 及@用户列表的 DTO
     * @return 钉钉返回的消息发送结果
     */
    public SendSceneGroupAssistantMessageResult sendSceneGroupAssistantMessage(SendSceneGroupAssistantMessageDTO dto) {
        // 组装消息参数字符串（文本内容）
        String msgParamMap = String.format("{\"content\":\"%s\"}", dto.getMessage());

        SendSceneGroupAssistantMessageRequest request = new SendSceneGroupAssistantMessageRequest();
        request.setTargetOpenConversationId(dto.getTargetOpenConversationId());
        request.setAtUsers(dto.getAtUsers() == null ? null : String.join(",", dto.getAtUsers()));
        request.setMsgParamMap(msgParamMap);
        request.setRobotCode(dingdingConfig.getRobotCode());
        // 文本消息模板：msg_template_id 取值为 inner_app_template_text
        request.setMsgTemplateId("inner_app_template_text");
        request.setIsAtAll(true);
        return restDingClient.sendSceneGroupAssistantMessage(request);
    }
}
