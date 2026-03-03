package com.example.dingding.service;

import com.example.dingding.dto.CreateSceneGroupRequest;
import com.example.dingding.dto.CreateSceneGroupResult;

public interface GroupService {
    CreateSceneGroupResult createSceneGroup(CreateSceneGroupRequest request);
}
