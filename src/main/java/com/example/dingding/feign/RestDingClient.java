package com.example.dingding.feign;

import com.example.dingding.dto.CreateSceneGroupRequest;
import com.example.dingding.dto.CreateSceneGroupResult;
import com.example.dingding.dto.GetUserDetailRequest;
import com.example.dingding.dto.GetUserDetailResult;
import com.example.dingding.dto.GetUserByMobileRequest;
import com.example.dingding.dto.GetUserByMobileResult;
import com.example.dingding.dto.SendSceneGroupAssistantMessageRequest;
import com.example.dingding.dto.SendSceneGroupAssistantMessageResult;
import com.example.dingding.config.dingding.DingdingRequestInterceptor;
import com.example.dingding.config.FeignConfiguration;
import com.example.dingding.config.FeignResponseDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 钉钉业务接口客户端（自动注入 access_token 并统一解包响应）。
 */
@FeignClient(
    name = "RestDingClient",
    url = "${dingding.base-url}",
    configuration = {
        FeignConfiguration.class,
        FeignResponseDecoder.class,
        DingdingRequestInterceptor.class
    }
)
public interface RestDingClient {

    /**
     * 创建群聊
     * https://open.dingtalk.com/document/development/create-a-scene-group-v2
     */
    @PostMapping("/topapi/im/chat/scenegroup/create")
    CreateSceneGroupResult createSceneGroup(@RequestBody CreateSceneGroupRequest request);

    /**
     * 根据手机号获取成员基础信息
     * https://open.dingtalk.com/document/development/query-users-by-phone-number
     */
    @PostMapping("/topapi/v2/user/getbymobile")
    GetUserByMobileResult getUserByMobile(@RequestBody GetUserByMobileRequest request);

    /**
     * 发送群助手消息（群机器人）。
     * https://open.dingtalk.com/document/development/chatbot-messages-sent-to-the-group-assistant
     */
    @PostMapping("/topapi/im/chat/scencegroup/message/send_v2")
    SendSceneGroupAssistantMessageResult sendSceneGroupAssistantMessage(
            @RequestBody SendSceneGroupAssistantMessageRequest request
    );

    /**
     * 查询用户详情。
     * https://open.dingtalk.com/document/development/query-user-details
     */
    @PostMapping("/topapi/v2/user/get")
    GetUserDetailResult getUserDetail(@RequestBody GetUserDetailRequest request);
}
