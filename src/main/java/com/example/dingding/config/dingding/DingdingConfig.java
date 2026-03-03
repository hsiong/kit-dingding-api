package com.example.dingding.config.dingding;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 〈〉
 *
 * @author Hsiong
 * @version 1.0.0
 * @since 11/23/25
 */
@Configuration
@ConfigurationProperties(prefix = "dingding")
@Data
public class DingdingConfig {
	
	/**
	 * 获取 token Host
	 */
	private String tokenUrl;
	/**
	 * 接口调用 Host
	 */
	private String baseUrl;
	
	private String appKey;
	private String appSecret;
	
	private String templateId;
	private String robotCode;
	
}
