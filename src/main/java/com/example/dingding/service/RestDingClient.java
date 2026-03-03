package com.example.dingding.service;

import com.example.dingding.dto.CreateSceneGroupRequest;
import com.example.dingding.dto.CreateSceneGroupResult;
import com.example.dingding.dto.GetUserByMobileRequest;
import com.example.dingding.dto.GetUserByMobileResult;
import com.example.dingding.feign.DingdingRequestInterceptor;
import com.example.dingding.feign.FeignConfiguration;
import com.example.dingding.feign.FeignResponseDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
    name = "dingding",
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
}
