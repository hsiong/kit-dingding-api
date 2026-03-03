package com.example.dingding.service;

import com.example.dingding.dto.CreateSceneGroupRequest;
import com.example.dingding.dto.CreateSceneGroupResult;
import com.example.dingding.feign.FeignConfiguration;
import com.example.dingding.feign.FeignResponseDecoder;
import feign.Headers;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
    name = "ai",
    url = "${ai.baseUrl}",                    // 配置文件里定义 baseUrl
    configuration = {
        FeignConfiguration.class,
        FeignResponseDecoder.class
    }
)
public interface RestDingClient {
    
    /**
     * 创建群聊
     * https://open.dingtalk.com/document/development/create-a-scene-group-v2
     * @param request
     * @return
     */
    @RequestLine("POST /topapi/im/chat/scenegroup/create?access_token={accessToken}")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    CreateSceneGroupResult createSceneGroup(CreateSceneGroupRequest request);
}
