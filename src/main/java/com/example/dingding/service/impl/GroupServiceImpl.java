package com.example.dingding.service.impl;

import com.example.dingding.config.DingdingConfig;
import com.example.dingding.dto.CreateSceneGroupRequest;
import com.example.dingding.dto.CreateSceneGroupResult;
import com.example.dingding.service.GroupService;
import com.example.dingding.service.RestDingClient;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {

    @Resource
    private RestDingClient restDingClient;

    @Resource
    private DingdingConfig dingdingConfig;

    @Override
    public CreateSceneGroupResult createSceneGroup(CreateSceneGroupRequest request) {
        request.setTemplateId(dingdingConfig.getTemplateId()); // 模板ID
        return restDingClient.createSceneGroup(request);
    }
}
