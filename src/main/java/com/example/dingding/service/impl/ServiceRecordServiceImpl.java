package com.example.dingding.service.impl;

import com.example.dingding.dto.GetServiceRecordTranscriptResponse;
import com.example.dingding.dto.GetUserDetailResult;
import com.example.dingding.dto.ListServiceRecordResponse;
import com.example.dingding.dto.ServiceRecordExportResult;
import com.example.dingding.feign.DviFeignClient;
import com.example.dingding.service.ServiceRecordService;
import com.example.dingding.service.UserService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 服务记录服务实现。
 */
@Service
public class ServiceRecordServiceImpl implements ServiceRecordService {

    private static final Logger log = LogManager.getLogger(ServiceRecordServiceImpl.class);

    private static final int MAX_RESULTS = 20;

    @Resource
    private DviFeignClient dviFeignClient;

    @Resource
    private UserService userService;

    @Override
    public List<ListServiceRecordResponse.ServiceRecord> listServiceRecords(Long startTime, Long endTime) {
        List<ListServiceRecordResponse.ServiceRecord> records = new ArrayList<>();
        String nextToken = null;

        do {
            ListServiceRecordResponse response = dviFeignClient.listServiceRecords(
                    MAX_RESULTS,
                    nextToken,
                    endTime,
                    startTime,
                    null,
                    null
            );

            if (response != null && response.getResult() != null) {
                records.addAll(response.getResult());
            }

            nextToken = response == null ? null : response.getNextToken();
        } while (StringUtils.isNotBlank(nextToken));

        return records;
    }

    @Override
    public String getServiceRecordTranscriptText(String recordId) {
        GetServiceRecordTranscriptResponse response = dviFeignClient.getServiceRecordTranscript(recordId);
        if (response == null || response.getResult() == null) {
            return "";
        }

        GetServiceRecordTranscriptResponse.TranscriptSection transcriptSection = response.getResult().getAudioText();
        if (transcriptSection == null || transcriptSection.getDataList() == null) {
            return "";
        }

        Map<String, String> roleByChannel = new HashMap<>();
        GetServiceRecordTranscriptResponse.SpeakerSection speakerSection = response.getResult().getSpeaker();
        if (speakerSection != null && speakerSection.getDataList() != null) {
            for (GetServiceRecordTranscriptResponse.SpeakerRole speakerRole : speakerSection.getDataList()) {
                if (speakerRole == null || StringUtils.isBlank(speakerRole.getChannel())) {
                    continue;
                }
                roleByChannel.put(speakerRole.getChannel(), formatRoleName(speakerRole.getRole(), speakerRole.getChannel()));
            }
        }

        List<GetServiceRecordTranscriptResponse.TranscriptSegment> segments = transcriptSection.getDataList().stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingLong(segment -> parseLong(segment.getStartTime())))
                .toList();

        StringBuilder builder = new StringBuilder();
        for (GetServiceRecordTranscriptResponse.TranscriptSegment segment : segments) {
            if (StringUtils.isBlank(segment.getText())) {
                continue;
            }

            String role = roleByChannel.getOrDefault(
                    segment.getChannel(),
                    formatRoleName(null, segment.getChannel())
            );

            if (!builder.isEmpty()) {
                builder.append(System.lineSeparator());
            }
            builder.append(role).append(": ").append(segment.getText().trim());
        }
        return builder.toString();
    }

    @Override
    public ServiceRecordExportResult exportRecentSevenDayTranscripts() {
        long endTime = Instant.now().toEpochMilli();
        long startTime = Instant.now().minus(7, ChronoUnit.DAYS).toEpochMilli();
        List<ListServiceRecordResponse.ServiceRecord> records = listServiceRecords(startTime, endTime);

        Path outputDirectory = Paths.get("file", "output").toAbsolutePath().normalize();
        createDirectories(outputDirectory);

        List<String> exportedFiles = new ArrayList<>();
        List<String> failedRecordIds = new ArrayList<>();

        for (ListServiceRecordResponse.ServiceRecord record : records) {
            if (record == null || StringUtils.isBlank(record.getRecordId())) {
                continue;
            }

            try {
                String transcriptText = getServiceRecordTranscriptText(record.getRecordId());
                String userName = resolveUserName(record);
                String fileName = sanitizeFileName(userName) + "_" + sanitizeFileName(record.getRecordId()) + ".md";
                Path filePath = outputDirectory.resolve(fileName);
                Files.writeString(filePath, transcriptText, StandardCharsets.UTF_8);
                exportedFiles.add(filePath.toString());
            } catch (Exception ex) {
                failedRecordIds.add(record.getRecordId());
                log.warn("导出服务记录转写失败，recordId={}", record.getRecordId(), ex);
            }
        }

        ServiceRecordExportResult result = new ServiceRecordExportResult();
        result.setTotalCount(records.size());
        result.setExportedCount(exportedFiles.size());
        result.setOutputDirectory(outputDirectory.toString());
        result.setExportedFiles(exportedFiles);
        result.setFailedRecordIds(failedRecordIds);
        return result;
    }

    private String resolveUserName(ListServiceRecordResponse.ServiceRecord record) {
        if (record.getUser() == null) {
            return "unknown";
        }
        if (StringUtils.isNotBlank(record.getUser().getName())) {
            return record.getUser().getName();
        }
        if (StringUtils.isBlank(record.getUser().getUserId())) {
            return "unknown";
        }

        try {
            GetUserDetailResult userDetail = userService.getUserDetail(record.getUser().getUserId());
            if (userDetail != null && StringUtils.isNotBlank(userDetail.getName())) {
                return userDetail.getName();
            }
        } catch (Exception ex) {
            log.warn("补充查询用户详情失败，userId={}", record.getUser().getUserId(), ex);
        }
        return "unknown";
    }

    private String formatRoleName(String role, String channel) {
        if (StringUtils.isBlank(role)) {
            return StringUtils.isBlank(channel) ? "未知角色" : "通道" + channel;
        }

        String normalizedRole = role.trim().toLowerCase();
        return switch (normalizedRole) {
            case "saler", "sales", "seller" -> "销售";
            case "customer", "client", "buyer" -> "客户";
            default -> role;
        };
    }

    private long parseLong(String value) {
        if (StringUtils.isBlank(value)) {
            return Long.MAX_VALUE;
        }
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException ex) {
            return Long.MAX_VALUE;
        }
    }

    private void createDirectories(Path outputDirectory) {
        try {
            Files.createDirectories(outputDirectory);
        } catch (IOException ex) {
            throw new RuntimeException("创建输出目录失败: " + outputDirectory, ex);
        }
    }

    private String sanitizeFileName(String value) {
        String normalized = StringUtils.defaultIfBlank(value, "unknown").trim();
        String sanitized = normalized.replaceAll("[\\\\/:*?\"<>|\\s]+", "_");
        return StringUtils.defaultIfBlank(sanitized, "unknown");
    }
}
