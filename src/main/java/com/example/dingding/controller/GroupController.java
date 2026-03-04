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

/**
 * 钉钉群聊相关接口。
 */
@RestController
@RequestMapping("/api/dingtalk/group")
public class GroupController {

    @Resource
    private GroupService groupService;

    /**
     * 直接使用群参数创建场景群。
     *
     * @param request 建群请求
     * @return 建群结果
     */
    @PostMapping("/scene/create")
    public CreateSceneGroupResult createSceneGroup(@Valid @RequestBody CreateSceneGroupRequest request) {
        return groupService.createSceneGroup(request);
    }

    /**
     * 通过手机号集合创建场景群。
     *
     * @param request 建群请求（手机号版本）
     * @return 建群结果
     */
    @PostMapping("/scene/createByPhone")
    public CreateSceneGroupResult createSceneGroupByPhone(@Valid @RequestBody CreateSceneGroupReqDTO request) {
        return groupService.createSceneGroupByPhone(request);
    }
}
