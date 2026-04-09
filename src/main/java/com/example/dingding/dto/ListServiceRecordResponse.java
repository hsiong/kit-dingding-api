package com.example.dingding.dto;

import lombok.Data;

import java.util.List;

/**
 * 分页获取企业下服务记录的返回结果。
 * 对应接口：GET /v1.0/dvi/service-records
 */
@Data
public class ListServiceRecordResponse {

    /**
     * 下一页查询 token；存在下页数据时返回。
     */
    private String nextToken;

    /**
     * 服务记录总量。
     */
    private Integer totalCount;

    /**
     * 当前页服务记录列表。
     */
    private List<ServiceRecord> result;

    /**
     * 单条服务记录。
     */
    @Data
    public static class ServiceRecord {

        /**
         * 服务记录 ID。
         */
        private String recordId;

        /**
         * 服务用户信息；员工离职后可能为空。
         */
        private User user;

        /**
         * 服务设备 SN。
         */
        private String deviceSn;

        /**
         * 服务开始时间戳，单位毫秒。
         */
        private Long startTimestamp;

        /**
         * 服务结束时间戳，单位毫秒。
         */
        private Long endTimestamp;

        /**
         * 服务持续时长，单位毫秒。
         */
        private String duration;

        /**
         * 客户 ID；绑定过客户时返回。
         */
        private String customerId;

        /**
         * 服务所属团队、门店信息。
         */
        private Team team;
    }

    /**
     * 服务用户信息。
     */
    @Data
    public static class User {

        /**
         * 员工姓名。
         */
        private String name;

        /**
         * 员工 userId。
         */
        private String userId;
    }

    /**
     * 服务所属团队、门店信息。
     */
    @Data
    public static class Team {

        /**
         * 团队或门店名称。
         */
        private String name;

        /**
         * 团队编码。
         */
        private String code;
    }
}
