package com.example.dingding.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.List;

/**
 * 服务记录转写结果。
 */
@Data
public class GetServiceRecordTranscriptResponse {

    private Result result;

    @Data
    public static class Result {

        @JsonAlias("audionText")
        private TranscriptSection audioText;

        private SpeakerSection speaker;
    }

    @Data
    public static class TranscriptSection {

        private String status;

        private List<TranscriptSegment> dataList;
    }

    @Data
    public static class TranscriptSegment {

        private String channel;

        private String startTime;

        private String endTime;

        private String text;
    }

    @Data
    public static class SpeakerSection {

        private String status;

        private List<SpeakerRole> dataList;
    }

    @Data
    public static class SpeakerRole {

        private String channel;

        private String role;
    }
}
