package com.example.dingding.controller;

import com.example.dingding.dto.*;
import com.example.dingding.service.AccessTokenService;
import com.example.dingding.service.GroupService;
import com.example.dingding.service.MessageService;
import com.example.dingding.service.ServiceRecordService;
import com.example.dingding.service.UserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * 钉钉用户相关接口。
 */
@RestController
@RequestMapping("/api/dingtalk/user")
public class ApiTestController {

    @Resource
    private UserService userService;
    
    @Resource
    private GroupService groupService;
    
    @Resource
    private MessageService messageService;
    
    @Resource
    private AccessTokenService accessTokenService;

    @Resource
    private ServiceRecordService serviceRecordService;
    
    /**
     * 获取应用访问令牌。
     *
     * @return 钉钉 access token 结果
     */
    @GetMapping("/access-token")
    public GetAccessTokenResult getAccessToken() {
        return accessTokenService.getAccessToken();
    }

    /**
     * 通过手机号查询钉钉用户 ID。
     *
     * @param request 查询参数
     * @return 用户 ID
     */
    @PostMapping("/get-by-mobile")
    public String getByMobile(@Valid @RequestBody GetUserByMobileRequest request) {
        return userService.getUserByMobile(request.getMobile());
    }
    
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
     * 发送群助手消息（旧版 SDK 对应接口）。
     *
     * @param request 发送请求
     * @return 发送结果
     */
    @PostMapping("/scene/message/send")
    public SendSceneGroupAssistantMessageResult sendSceneGroupAssistantMessage(
        @Valid @RequestBody SendSceneGroupAssistantMessageDTO request
    ) {
        return messageService.sendSceneGroupAssistantMessage(request);
    }
    
    /**
     * 获取近 7 天服务记录。
     *
     * @return 导出结果
     */
    @GetMapping("/service-record/listServiceRecords")
    public List<ListServiceRecordResponse.ServiceRecord> listServiceRecords(@RequestParam(name = "amount") Integer amount) {
        long endTime = Instant.now().toEpochMilli();
        long startTime = Instant.now().minus(amount, ChronoUnit.DAYS).toEpochMilli();
        return serviceRecordService.listServiceRecords(startTime, endTime);
    }

    /**
     * 获取单条服务记录的完整转写文本。
     *
     * @param recordId 服务记录 ID
     * @return 完整转写文本
     */
    @GetMapping("/service-record/transcript-text")
    public String getServiceRecordTranscriptText(@RequestParam String recordId) {
        return serviceRecordService.getServiceRecordTranscriptText(recordId);
    }
}
