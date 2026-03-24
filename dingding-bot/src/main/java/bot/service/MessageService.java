package bot.service;

import bot.constant.MessageTypeEnum;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈〉
 *
 * @author Hsiong
 * @version 1.0.0
 * @since 3/23/26
 */
@Service
public class MessageService {
	
	public void sendMessageWebhook(String webhook, String content){
		sendMessageWebhook(webhook, content, null);
	}
	
	/**
	 * 通过 webhook 发送消息
	 * @param webhook
	 * @param content
	 * @param userIds
	 * @throws ApiException
	 */
	public void sendMessageWebhook(String webhook, String content, List<String> atUsers) {
		DingTalkClient client = new DefaultDingTalkClient(webhook);
		OapiRobotSendRequest request = new OapiRobotSendRequest();
		request.setMsgtype(MessageTypeEnum.MARKDOWN.getCode());
		request = MessageTypeEnum.getMarkdown(request, "机器人回复", content);
		
		OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
		if (atUsers != null) {
			at.setAtUserIds(atUsers);
		}
		at.setIsAtAll(false);
		request.setAt(at);
		OapiRobotSendResponse response = null;
		try {
			response = client.execute(request);
		} catch (ApiException e) {
			throw new RuntimeException("发送消息失败", e);
		}
		System.out.println(response.getBody());
	}
	
}
