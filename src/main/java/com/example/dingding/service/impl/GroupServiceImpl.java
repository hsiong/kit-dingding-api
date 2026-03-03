package com.example.dingding.service.impl;

import com.example.dingding.config.DingdingConfig;
import com.example.dingding.dto.ApiResponse;
import com.example.dingding.dto.CreateSceneGroupRequest;
import com.example.dingding.dto.CreateSceneGroupResult;
import com.example.dingding.service.GroupService;
import com.example.dingding.service.RestGroupFeignClient;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {

    @Resource
    private RestGroupFeignClient restGroupFeignClient;
    
    @Resource
    private DingdingConfig dingdingConfig;

    @Override
    public ApiResponse<CreateSceneGroupResult> createSceneGroup(CreateSceneGroupRequest request) {
        request.setTemplateId(dingdingConfig.getTemplateId()); // 模板ID
        return restGroupFeignClient.createSceneGroup(request);
    }
}
