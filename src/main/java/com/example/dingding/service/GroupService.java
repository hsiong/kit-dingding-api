package com.example.dingding.service;

import com.example.dingding.dto.CreateSceneGroupReqDTO;
import com.example.dingding.dto.CreateSceneGroupRequest;
import com.example.dingding.dto.CreateSceneGroupResult;

/**
 * 群聊服务。
 */
public interface GroupService {

    /**
     * 根据完整参数创建场景群。
     *
     * @param request 建群请求
     * @return 建群结果
     */
    CreateSceneGroupResult createSceneGroup(CreateSceneGroupRequest request);

    /**
     * 根据手机号版本参数创建场景群。
     *
     * @param dto 建群请求（手机号版本）
     * @return 建群结果
     */
    CreateSceneGroupResult createSceneGroupByPhone(CreateSceneGroupReqDTO dto);
}
