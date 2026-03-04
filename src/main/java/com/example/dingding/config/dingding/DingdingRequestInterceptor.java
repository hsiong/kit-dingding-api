package com.example.dingding.config.dingding;

import com.example.dingding.config.redis.RedisUtil;
import com.example.dingding.dto.GetAccessTokenResult;
import com.example.dingding.service.AccessTokenService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class DingdingRequestInterceptor implements RequestInterceptor {
	
	@Resource
	private AccessTokenService accessTokenService;
	
	@Resource
	private RedisUtil redisUtil;
	
	@Override
	public void apply(RequestTemplate requestTemplate) {
		
		String redisKey = "dingding_access_token";
		Object accessTokenObj = redisUtil.get(redisKey);
		String accessToken = null;
		if (accessTokenObj == null) { // 从缓存中获取
			accessToken = accessTokenObj.toString();
		} else { // 从 api 获取，并缓存
			GetAccessTokenResult tokenResult = accessTokenService.getAccessToken();
			accessToken = tokenResult.getAccessToken();
			redisUtil.set(redisKey, accessToken, tokenResult.getExpireIn());
		}
		
		requestTemplate.query("access_token", accessToken);
	}
}
