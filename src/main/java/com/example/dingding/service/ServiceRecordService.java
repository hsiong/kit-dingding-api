package com.example.dingding.service;

import com.example.dingding.dto.ListServiceRecordResponse;
import com.example.dingding.dto.ServiceRecordExportResult;

import java.util.List;

/**
 * 服务记录服务。
 */
public interface ServiceRecordService {

    /**
     * 查询指定时间范围内的全部服务记录。
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 服务记录列表
     */
    List<ListServiceRecordResponse.ServiceRecord> listServiceRecords(Long startTime, Long endTime);

    /**
     * 获取服务记录完整转写文本。
     *
     * @param recordId 服务记录ID
     * @return 按角色拼接后的完整文本
     */
    String getServiceRecordTranscriptText(String recordId);

    /**
     * 导出近 7 天服务记录转写文本。
     *
     * @return 导出结果
     */
    ServiceRecordExportResult exportRecentSevenDayTranscripts();
}
