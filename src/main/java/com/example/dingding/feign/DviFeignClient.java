package com.example.dingding.feign;

import com.example.dingding.config.FeignConfiguration;
import com.example.dingding.config.dingding.DingdingOpenApiRequestInterceptor;
import com.example.dingding.dto.GetServiceRecordTranscriptResponse;
import com.example.dingding.dto.ListServiceRecordRequest;
import com.example.dingding.dto.ListServiceRecordResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 钉钉 AI 销售管理接口客户端。
 * 使用开放平台域名 https://api.dingtalk.com。
 */
@FeignClient(
    name = "DviFeignClient",
    url = "https://api.dingtalk.com",
    configuration = {
        FeignConfiguration.class,
        DingdingOpenApiRequestInterceptor.class
    }
)
public interface DviFeignClient {

    /**
     * 分页获取企业下的服务记录。
     *
     * @param request 查询条件，支持 maxResults、nextToken、时间范围、userId、teamCode
     * @return 当前页服务记录及下一页 token
     */
    @GetMapping("/v1.0/dvi/service-records")
    ListServiceRecordResponse listServiceRecords(@SpringQueryMap ListServiceRecordRequest request);

    /**
     * 获取服务记录转写文本。
     *
     * @param id 文件 ID
     * @return 转写文本与说话人角色识别结果
     */
    @GetMapping("/v1.0/dvi/service/transcript")
    GetServiceRecordTranscriptResponse getServiceRecordTranscript(@RequestParam("id") String id);
}
