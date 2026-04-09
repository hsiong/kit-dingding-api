package com.example.dingding.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.List;

/**
 * 服务记录转写文本返回结果。
 * 对应接口：GET /v1.0/dvi/service/transcript
 */
@Data
public class GetServiceRecordTranscriptResponse {

    /**
     * 转写结果主体。
     */
    private Result result;

    @Data
    public static class Result {

        /**
         * 转写文本结果。
         * 接口原始字段名存在 audionText 拼写，使用 JsonAlias 兼容映射。
         */
        @JsonAlias("audionText")
        private TranscriptSection audioText;

        /**
         * 说话人角色识别结果。
         */
        private SpeakerSection speaker;
    }

    /**
     * 转写文本分段结果。
     */
    @Data
    public static class TranscriptSection {

        /**
         * 处理状态，例如 PROCESSING、COMPLETED。
         */
        private String status;

        /**
         * 转写文本分段列表。
         */
        private List<TranscriptSegment> dataList;
    }

    /**
     * 单条转写文本分段。
     */
    @Data
    public static class TranscriptSegment {

        /**
         * 声道编号。
         */
        private String channel;

        /**
         * 分段开始时间，单位毫秒。
         */
        private String startTime;

        /**
         * 分段结束时间，单位毫秒。
         */
        private String endTime;

        /**
         * 转写文本内容。
         */
        private String text;
    }

    /**
     * 说话人识别结果。
     */
    @Data
    public static class SpeakerSection {

        /**
         * 处理状态，例如 PROCESSING、COMPLETED。
         */
        private String status;

        /**
         * 声道与角色映射列表。
         */
        private List<SpeakerRole> dataList;
    }

    /**
     * 单个声道的角色识别结果。
     */
    @Data
    public static class SpeakerRole {

        /**
         * 声道编号。
         */
        private String channel;

        /**
         * 角色标识，例如 saler。
         */
        private String role;
    }
}
