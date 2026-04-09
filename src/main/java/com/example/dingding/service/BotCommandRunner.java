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
 * 机器人命令启动器。
 * 在应用启动时初始化并启动钉钉机器人 Stream 客户端，注册消息回调监听器。
 * 参考文档：https://open.dingtalk.com/document/development/introduction-to-stream-mode
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
     * 启动机器人 Stream 客户端。
     */
    private void runBot() {
        try {
            OpenDingTalkStreamClientBuilder
                .custom()
                .credential(new AuthClientCredential(dingdingConfig.getAppKey(), dingdingConfig.getAppSecret()))
                // 注册机器人消息监听器
                .registerCallbackListener("/v1.0/im/bot/messages/get", robotMessage -> {
                    log.info("收到机器人消息：{}", robotMessage);
                    // 开发者根据自身业务需求，处理机器人回调
                    return new JSONObject();
                })
                .build()
                .start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        runBot();
    }
}
