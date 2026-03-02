package com.example.dingding.service;

import com.example.dingding.dto.CreateSceneGroupRequest;
import com.example.dingding.dto.CreateSceneGroupResult;
import com.example.dingding.dto.ApiResponse;

public interface GroupService {
    ApiResponse<CreateSceneGroupResult> createSceneGroup(CreateSceneGroupRequest request);
}
