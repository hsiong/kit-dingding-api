package com.example.dingding.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApiResponse<T> {
    private Long errcode;
    private String errmsg;

    @JsonProperty("request_id")
    private String requestId;

    private Boolean success;
    private T result;
}
