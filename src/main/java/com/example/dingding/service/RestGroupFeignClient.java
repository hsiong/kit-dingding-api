package com.example.dingding.service;

import com.example.dingding.dto.ApiResponse;
import com.example.dingding.dto.CreateSceneGroupRequest;
import com.example.dingding.dto.CreateSceneGroupResult;
import feign.Headers;
import feign.RequestLine;

public interface RestGroupFeignClient {

    @RequestLine("POST /topapi/im/chat/scenegroup/create?access_token={accessToken}")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    ApiResponse<CreateSceneGroupResult> createSceneGroup(CreateSceneGroupRequest request);
}
