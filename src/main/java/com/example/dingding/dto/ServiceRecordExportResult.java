package com.example.dingding.dto;

import lombok.Data;

import java.util.List;

/**
 * 服务记录转写导出结果。
 */
@Data
public class ServiceRecordExportResult {

    private Integer totalCount;

    private Integer exportedCount;

    private String outputDirectory;

    private List<String> exportedFiles;

    private List<String> failedRecordIds;
}
