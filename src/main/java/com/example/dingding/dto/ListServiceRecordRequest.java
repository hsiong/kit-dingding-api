package com.example.dingding.dto;

import lombok.Data;

/**
 * 服务记录分页查询参数。
 */
@Data
public class ListServiceRecordRequest {

    private Integer maxResults;

    private String nextToken;

    private Long endTime;

    private Long startTime;

    private String userId;

    private String teamCode;
}
