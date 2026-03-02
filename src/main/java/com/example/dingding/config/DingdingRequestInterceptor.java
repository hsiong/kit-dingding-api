package com.example.dingding.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.annotation.Resource;

/**
 * 〈〉
 *
 * @author Hsiong
 * @version 1.0.0
 * @since 11/23/25
 */
public class DingdingRequestInterceptor implements RequestInterceptor {
	
	@Resource
	private DingdingConfig immsgConfig;
	
	@Override
	public void apply(RequestTemplate requestTemplate) {
		String accessToken = immsgConfig.getAccessToken();
		requestTemplate.query("accessToken", accessToken);
		
//		requestTemplate.target(url);
	}
}
