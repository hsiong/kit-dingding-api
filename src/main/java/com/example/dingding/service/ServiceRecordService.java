package com.example.dingding.service;

import com.example.dingding.dto.GetServiceRecordTranscriptResponse;
import com.example.dingding.dto.GetUserDetailResult;
import com.example.dingding.dto.ListServiceRecordRequest;
import com.example.dingding.dto.ListServiceRecordResponse;
import com.example.dingding.dto.ServiceRecordExportResult;
import com.example.dingding.feign.DviFeignClient;
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
 * 服务记录服务。
 * 提供钉钉服务记录（录音转写）的查询、导出和管理功能。
 */
@Service
public class ServiceRecordService {

    private static final Logger log = LogManager.getLogger(ServiceRecordService.class);

    private static final int MAX_RESULTS = 20;

    @Resource
    private DviFeignClient dviFeignClient;

    @Resource
    private UserService userService;
    
    /**
     * 根据时间范围查询服务记录列表。
     * 采用分页查询方式，直至获取所有符合条件的记录。
     *
     * @param startTime 开始时间戳（毫秒）
     * @param endTime   结束时间戳（毫秒）
     * @return 包含所有服务记录的列表
     */
    public List<ListServiceRecordResponse.ServiceRecord> listServiceRecords(Long startTime, Long endTime) {
        List<ListServiceRecordResponse.ServiceRecord> records = new ArrayList<>();
        String nextToken = null;

        do {
            // 钉钉接口单页最多返回 20 条，这里循环带上 nextToken 拉取全部分页数据
            ListServiceRecordRequest request = new ListServiceRecordRequest();
            request.setMaxResults(MAX_RESULTS);
            request.setNextToken(nextToken);
            request.setEndTime(endTime);
            request.setStartTime(startTime);

            ListServiceRecordResponse response = dviFeignClient.listServiceRecords(request);

            if (response != null && response.getResult() != null) {
                records.addAll(response.getResult());
            }

            nextToken = response == null ? null : response.getNextToken();
        } while (StringUtils.isNotBlank(nextToken));

        return records;
    }

    /**
     * 获取服务记录的转写文本内容。
     * 
     * @param recordId 服务记录 ID
     * @return 转写文本字符串，若未找到则返回空字符串
     */
    public String getServiceRecordTranscriptText(String recordId) {
        GetServiceRecordTranscriptResponse response = dviFeignClient.getServiceRecordTranscript(recordId);
        if (response == null) {
            return "";
        }
        GetServiceRecordTranscriptResponse.Result responseResult = response.getResult();

        // 转写文本结果
        GetServiceRecordTranscriptResponse.TranscriptSection transcriptSection = responseResult.getAudioText();
        // 转写文本分段列表
        List<GetServiceRecordTranscriptResponse.TranscriptSegment> dataList = transcriptSection.getDataList();
        Map<String, String> roleByChannelMap = new HashMap<>();
        // 说话人角色识别结果
        GetServiceRecordTranscriptResponse.SpeakerSection speakerSection = responseResult.getSpeaker();
        for (GetServiceRecordTranscriptResponse.SpeakerRole speakerRole : speakerSection.getDataList()) {
            if (speakerRole == null || StringUtils.isBlank(speakerRole.getChannel())) {
                continue;
            }
            // 将接口中的角色标识映射成更适合导出文本阅读的中文角色名
            roleByChannelMap.put(speakerRole.getChannel(), formatRoleName(speakerRole.getRole()));
        }

        // 按分段开始时间排序后拼接，避免返回顺序不稳定影响导出文本可读性
        List<GetServiceRecordTranscriptResponse.TranscriptSegment> segments =
            dataList.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingLong(segment -> parseLong(segment.getStartTime())))
                .toList();

        StringBuilder builder = new StringBuilder();
        for (GetServiceRecordTranscriptResponse.TranscriptSegment segment : segments) {
            if (StringUtils.isBlank(segment.getText())) {
                continue;
            }

            String role = roleByChannelMap.getOrDefault(segment.getChannel(), "未知角色");

            if (!builder.isEmpty()) {
                builder.append(System.lineSeparator());
            }
            builder.append(role).append(": ").append(segment.getText().trim());
        }
        return builder.toString();
    }

    /**
     * 导出最近七天的服务记录转写文本。
     * 将每条转写记录保存为 Markdown 文件到 file/output 目录。
     * 
     * @return 导出结果，包含总记录数、成功导出文件列表、失败记录 ID 等
     */
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

    /**
     * 解析服务记录中的用户名。
     * 优先使用记录中已有的用户名称，若无则通过 userId 查询用户详情。
     * 
     * @param record 服务记录对象
     * @return 用户名称，若无法获取则返回"未知员工"
     */
    private String resolveUserName(ListServiceRecordResponse.ServiceRecord record) {
        String staffUnknownName = "未知员工";
        if (record.getUser() == null) {
            return staffUnknownName;
        }
        if (StringUtils.isNotBlank(record.getUser().getName())) {
            return record.getUser().getName();
        }
        if (StringUtils.isBlank(record.getUser().getUserId())) {
            return staffUnknownName;
        }

        try {
            GetUserDetailResult userDetail = userService.getUserDetail(record.getUser().getUserId());
            if (userDetail != null && StringUtils.isNotBlank(userDetail.getName())) {
                return userDetail.getName();
            }
        } catch (Exception ex) {
            log.warn("补充查询用户详情失败，userId={}", record.getUser().getUserId(), ex);
        }
        return staffUnknownName;
    }

    /**
     * 格式化角色名称。
     * 将英文角色标识转换为中文角色名。
     * 
     * @param role 角色标识字符串
     * @return 格式化后的角色名称
     */
    private String formatRoleName(String role) {
        String normalizedRole = role.trim().toLowerCase();
        return switch (normalizedRole) {
            case "saler", "sales", "seller" -> "员工";
            case "customer", "client", "buyer" -> "顾客";
            default -> "未知角色";
        };
    }

    /**
     * 解析长整型数值。
     * 
     * @param value 待解析的字符串
     * @return 解析后的长整型值，若解析失败则返回 Long.MAX_VALUE
     */
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

    /**
     * 创建输出目录。
     * 
     * @param outputDirectory 输出目录路径
     */
    private void createDirectories(Path outputDirectory) {
        try {
            Files.createDirectories(outputDirectory);
        } catch (IOException ex) {
            throw new RuntimeException("创建输出目录失败：" + outputDirectory, ex);
        }
    }

    /**
     * 清理文件名。
     * 移除文件名中非法字符，确保生成的文件名安全可用。
     * 
     * @param value 待清理的文件名
     * @return 清理后的文件名
     */
    private String sanitizeFileName(String value) {
        String normalized = StringUtils.defaultIfBlank(value, "unknown").trim();
        String sanitized = normalized.replaceAll("[\\\\/:*?\"<>|\\s]+", "_");
        return StringUtils.defaultIfBlank(sanitized, "unknown");
    }
}
