package com.example.dingding.controller;

import com.example.dingding.dto.CreateSceneGroupReqDTO;
import com.example.dingding.dto.CreateSceneGroupRequest;
import com.example.dingding.dto.CreateSceneGroupResult;
import com.example.dingding.service.GroupService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dingtalk/group")
public class GroupController {

    @Resource
    private GroupService groupService;

    @PostMapping("/scene/create")
    public CreateSceneGroupResult createSceneGroup(@Valid @RequestBody CreateSceneGroupRequest request) {
        return groupService.createSceneGroup(request);
    }
    
    @PostMapping("/scene/createByPhone")
    public CreateSceneGroupResult createSceneGroupByPhone(@Valid @RequestBody CreateSceneGroupReqDTO request) {
        return groupService.createSceneGroupByPhone(request);
    }
}
