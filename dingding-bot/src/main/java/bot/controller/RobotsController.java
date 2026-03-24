package bot.controller;

import bot.dto.DingTalkMessageDTO;
import bot.service.MessageService;
import bot.util.BotUtil;
import com.alibaba.fastjson.JSONObject;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * 〈〉
 * 
 * @author Hsiong
 * @version 1.0.0
 * @since 2026/3/4
 */
@RestController
public class RobotsController {
	
	@Resource
	private BotUtil botUtil;
	
	@Resource
	private MessageService messageService;
	
	/**
	 * header 验证
	 * https://open.dingtalk.com/document/development/receive-message
	 * 
	 * 下方 value = "/robots", 决定下方设置机器人消息接收地址，例如：https://example.com/robots
	 * https://open.dingtalk.com/document/dingstart/robot-receive-message?spm=ding_open_doc.document.0.0.4b7765d799Ne06
	 * 
	 * 回复消息
	 * https://open.dingtalk.com/document/dingstart/robot-reply-and-send-messages?spm=ding_open_doc.document.0.0.3ecc75eaaq0rzg
	 */
	@RequestMapping(value = "/robots", method = RequestMethod.POST)
	public String helloRobots(@RequestHeader("timestamp") @NotBlank(message = "timestamp不能为空") String timestamp,
							  @RequestHeader("sign") @NotBlank(message = "sign不能为空") String sign,
							  @RequestBody(required = false) JSONObject json) {
		// 验证机器人身份
		botUtil.authBot(timestamp, sign);
		
		// 机器人接收消息内容
		System.out.println(json);
		DingTalkMessageDTO dto = json.toJavaObject(DingTalkMessageDTO.class);
		String content = dto.getText().getContent().replaceAll(" ", "");
		System.out.println("机器人接收消息内容:" + content);
		
		// todo 记录
		String conversationId = dto.getConversationId();
		String senderStaffId = dto.getSenderStaffId(); // 使用 senderStaffId，为发送者 userId 值。
		String title = dto.getConversationTitle();
		String chatbotUserId = dto.getChatbotUserId();
		
		
		// 机器人发送消息
		String sessionWebhook = dto.getSessionWebhook();
		List<String> atUsers = Collections.singletonList(senderStaffId);
		messageService.sendMessageWebhook(sessionWebhook, content, atUsers);
		
		return null;
	}
}
