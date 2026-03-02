package com.example.dingding.service.impl;

import com.example.dingding.service.RestGroupFeignClient;
import com.example.dingding.dto.ApiResponse;
import com.example.dingding.dto.CreateSceneGroupRequest;
import com.example.dingding.dto.CreateSceneGroupResult;
import com.example.dingding.service.GroupService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {

    @Resource
    private RestGroupFeignClient restGroupFeignClient;

    @Override
    public ApiResponse<CreateSceneGroupResult> createSceneGroup(CreateSceneGroupRequest request) {

        return restGroupFeignClient.createSceneGroup(request);
    }
}
