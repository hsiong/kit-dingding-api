package com.example.dingding.service;

import com.dingtalk.open.app.api.OpenDingTalkClient;
import com.dingtalk.open.app.api.OpenDingTalkStreamClientBuilder;
import com.dingtalk.open.app.api.callback.DingTalkStreamTopics;
import com.dingtalk.open.app.api.security.AuthClientCredential;
import com.example.dingding.config.dingding.DingdingConfig;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 〈https://opensource.dingtalk.com/developerpedia/docs/explore/tutorials/stream/bot/java〉
 *
 * @author Hsiong
 * @version 1.0.0
 * @since 3/23/26
 */
@Component
public class BotEchoTextListener {
	
	private final BotEchoTextConsumer botEchoTextConsumer;
	
	@Resource
	private DingdingConfig dingdingConfig;
	
	@Autowired
	public BotEchoTextListener(BotEchoTextConsumer botEchoTextConsumer) {
		this.botEchoTextConsumer = botEchoTextConsumer;
	}
	
	@PostConstruct
	public void init() throws Exception {
		// init stream client
		OpenDingTalkClient client = OpenDingTalkStreamClientBuilder
			.custom()
			.credential(new AuthClientCredential(dingdingConfig.getAppKey(), dingdingConfig.getAppSecret()))
			.registerCallbackListener(DingTalkStreamTopics.BOT_MESSAGE_TOPIC, botEchoTextConsumer)
			.build();
		client.start();
		
	}
}
