package com.example.dingding.controller;

import com.example.dingding.dto.CreateSceneGroupRequest;
import com.example.dingding.dto.CreateSceneGroupResult;
import com.example.dingding.dto.ApiResponse;
import com.example.dingding.service.GroupService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dingtalk/group")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/scene/create")
    public ApiResponse<CreateSceneGroupResult> createSceneGroup(@Valid @RequestBody CreateSceneGroupRequest request) {
        return groupService.createSceneGroup(request);
    }
}
