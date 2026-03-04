package com.example.dingding.service.impl;

import com.example.dingding.config.dingding.DingdingConfig;
import com.example.dingding.dto.CreateSceneGroupReqDTO;
import com.example.dingding.dto.CreateSceneGroupRequest;
import com.example.dingding.dto.CreateSceneGroupResult;
import com.example.dingding.feign.RestDingClient;
import com.example.dingding.service.GroupService;
import com.example.dingding.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GroupServiceImpl implements GroupService {

    @Resource
    private RestDingClient restDingClient;
    
    @Resource
    private UserService userService;

    @Resource
    private DingdingConfig dingdingConfig;

    @Override
    public CreateSceneGroupResult createSceneGroup(CreateSceneGroupRequest request) {
        request.setUuid(UUID.randomUUID().toString());
        request.setTemplateId(dingdingConfig.getTemplateId()); // 模板ID
        return restDingClient.createSceneGroup(request);
    }
    
    @Override
    public CreateSceneGroupResult createSceneGroupByPhone(CreateSceneGroupReqDTO dto) {
        
        String ownerUserPhone = dto.getOwnerUserPhone();
        List<String> userPhones = dto.getUserPhones();
        String title = dto.getTitle();
        
        // 根据手机号获取成员基础信息
       String ownerUserId = userService.getUserByMobile(ownerUserPhone);
       List<String> userIds = new ArrayList<>();
       for (String userPhone : userPhones) {
           String userId = userService.getUserByMobile(userPhone);
           userIds.add(userId);
       }
        
        // 创建群聊
        CreateSceneGroupRequest request = new CreateSceneGroupRequest();
        request.setOwnerUserId(ownerUserId);
        request.setUserIds(String.join(",", userIds));
        request.setTitle(title);
        return createSceneGroup(request);
        
    }
}
