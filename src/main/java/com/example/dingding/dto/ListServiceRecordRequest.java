package com.example.dingding.dto;

import lombok.Data;

/**
 * 分页获取企业下服务记录的查询参数。
 * 对应接口：GET /v1.0/dvi/service-records
 */
@Data
public class ListServiceRecordRequest {

    /**
     * 每页返回的数据量，最多 20 条。
     */
    private Integer maxResults;

    /**
     * 下一页查询 token，首次查询时为空。
     */
    private String nextToken;

    /**
     * 服务结束时间，Unix 时间戳，单位毫秒。
     */
    private Long endTime;

    /**
     * 服务开始时间，Unix 时间戳，单位毫秒。
     */
    private Long startTime;

    /**
     * 员工 userId。
     */
    private String userId;

    /**
     * 团队编码。
     */
    private String teamCode;
}
