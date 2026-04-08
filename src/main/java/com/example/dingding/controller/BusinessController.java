package com.example.dingding.controller;

import com.example.dingding.dto.CreateSceneGroupReqDTO;
import com.example.dingding.dto.CreateSceneGroupResult;
import com.example.dingding.dto.ServiceRecordExportResult;
import com.example.dingding.service.GroupService;
import com.example.dingding.service.ServiceRecordService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 钉钉群聊相关接口。
 */
@RestController
@RequestMapping("/api/dingtalk/group")
public class BusinessController {

    @Resource
    private GroupService groupService;

    @Resource
    private ServiceRecordService serviceRecordService;

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

    /**
     * 获取近 7 天服务记录转写文本并写入本地文件。
     *
     * @return 导出结果
     */
    @GetMapping("/service-record/transcripts/recent-7-days")
    public ServiceRecordExportResult exportRecentSevenDayTranscripts() {
        return serviceRecordService.exportRecentSevenDayTranscripts();
    }
}
