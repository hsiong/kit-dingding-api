package com.example.dingding.dto;

import lombok.Data;

/**
 * 〈〉
 *
 * @author Hsiong
 * @version 1.0.0
 * @since 3/3/26
 */
@Data
public class DingResult<T> {
	
	private T result;
	
	private Boolean success;
	
	private String errcode;
	
	private String errmsg;
	
	private String requestId; 
	
}
