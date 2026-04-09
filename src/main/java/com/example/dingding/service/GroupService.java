package com.example.dingding.service;

import com.example.dingding.config.dingding.DingdingConfig;
import com.example.dingding.dto.CreateSceneGroupReqDTO;
import com.example.dingding.dto.CreateSceneGroupRequest;
import com.example.dingding.dto.CreateSceneGroupResult;
import com.example.dingding.feign.RestDingClient;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 群聊服务。
 * 提供钉钉场景群的创建和管理功能。
 */
@Service
public class GroupService {

    @Resource
    private RestDingClient restDingClient;

    @Resource
    private UserService userService;

    @Resource
    private DingdingConfig dingdingConfig;

    /**
     * 创建场景群。
     * 直接透传建群参数，补齐服务端生成字段后调用钉钉接口。
     * 
     * @param request 建群请求参数
     * @return 钉钉返回的建群结果
     */
    public CreateSceneGroupResult createSceneGroup(CreateSceneGroupRequest request) {
        // UUID 用于请求幂等和链路追踪
        request.setUuid(UUID.randomUUID().toString());
        request.setTemplateId(dingdingConfig.getTemplateId()); // 场景群模板 ID（配置项）
        return restDingClient.createSceneGroup(request);
    }

    /**
     * 根据手机号列表创建场景群。
     * 将手机号版本的建群请求转换为 userId 版本后创建场景群。
     * 
     * @param dto 包含群主手机号、群成员手机号列表及群名称的 DTO
     * @return 钉钉返回的建群结果
     */
    public CreateSceneGroupResult createSceneGroupByPhone(CreateSceneGroupReqDTO dto) {
        String ownerUserPhone = dto.getOwnerUserPhone();
        List<String> userPhones = dto.getUserPhones();
        String title = dto.getTitle();

        // 先把群主、群成员手机号转换成钉钉 userId
        String ownerUserId = userService.getUserByMobile(ownerUserPhone);
        List<String> userIds = new ArrayList<>();
        for (String userPhone : userPhones) {
            String userId = userService.getUserByMobile(userPhone);
            userIds.add(userId);
        }

        // 组装标准建群请求，复用通用建群方法
        CreateSceneGroupRequest request = new CreateSceneGroupRequest();
        request.setOwnerUserId(ownerUserId);
        request.setUserIds(String.join(",", userIds));
        request.setTitle(title);
        return createSceneGroup(request);
    }
}
