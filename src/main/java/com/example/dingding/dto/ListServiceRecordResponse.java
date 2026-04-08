package com.example.dingding.dto;

import lombok.Data;

import java.util.List;

/**
 * 服务记录分页结果。
 */
@Data
public class ListServiceRecordResponse {

    private String nextToken;

    private Integer totalCount;

    private List<ServiceRecord> result;

    @Data
    public static class ServiceRecord {

        private String recordId;

        private User user;

        private String deviceSn;

        private Long startTimestamp;

        private Long endTimestamp;

        private String duration;

        private String customerId;

        private Team team;
    }

    @Data
    public static class User {

        private String name;

        private String userId;
    }

    @Data
    public static class Team {

        private String name;

        private String code;
    }
}
