package com.example.dingding.service;

import com.dingtalk.open.app.api.OpenDingTalkClient;
import com.dingtalk.open.app.api.OpenDingTalkStreamClientBuilder;
import com.dingtalk.open.app.api.callback.DingTalkStreamTopics;
import com.dingtalk.open.app.api.security.AuthClientCredential;
import com.example.dingding.config.dingding.DingdingConfig;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 机器人消息监听器。
 * 初始化钉钉 Stream 客户端并注册消息回调监听器。
 * 参考文档：https://opensource.dingtalk.com/developerpedia/docs/explore/tutorials/stream/bot/java
 *
 * @author Hsiong
 * @version 1.0.0
 * @since 2026/3/23
 */
@Component
public class BotEchoTextListener {

    private final BotEchoTextConsumer botEchoTextConsumer;

    @Resource
    private DingdingConfig dingdingConfig;

    public BotEchoTextListener(BotEchoTextConsumer botEchoTextConsumer) {
        this.botEchoTextConsumer = botEchoTextConsumer;
    }

    
    /**
     * 初始化流式客户端并注册监听器。
     * 
     * @throws Exception 如果初始化过程中发生异常
     */
    @PostConstruct
    public void init() throws Exception {
        // 初始化 Stream 客户端
        OpenDingTalkClient client = OpenDingTalkStreamClientBuilder
            .custom()
            .credential(new AuthClientCredential(dingdingConfig.getAppKey(), dingdingConfig.getAppSecret()))
            .registerCallbackListener(DingTalkStreamTopics.BOT_MESSAGE_TOPIC, botEchoTextConsumer)
            .build();
        client.start();
    }
}
