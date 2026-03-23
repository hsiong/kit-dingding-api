package com.example.dingding.service;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.open.app.api.OpenDingTalkStreamClientBuilder;
import com.dingtalk.open.app.api.security.AuthClientCredential;
import com.example.dingding.config.dingding.DingdingConfig;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 〈〉
 *
 * @author Hsiong
 * @version 1.0.0
 * @since 2026/3/23
 */
@Component
@Slf4j
public class BotCommandRunner implements CommandLineRunner {
	
	@Resource
	private DingdingConfig dingdingConfig;
	
	/**
	 * 启动机器人 - stream
	 * https://open.dingtalk.com/document/development/introduction-to-stream-mode?spm=a2q3p.21071111.0.0.7a5d65eeKGZv7G
	 */
	private void runBot(){
		try {
			OpenDingTalkStreamClientBuilder
				.custom()
				.credential(new AuthClientCredential(dingdingConfig.getAppKey(), dingdingConfig.getAppSecret()))
				//注册机器人监听器
				.registerCallbackListener("/v1.0/im/bot/messages/get", robotMessage -> {
					log.info("receive robotMessage, {}", robotMessage);
					//开发者根据自身业务需求，处理机器人回调
					return new JSONObject();
				})
				.build().start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	@Override
	public void run(String... args) throws Exception {
		runBot();
	}
}
