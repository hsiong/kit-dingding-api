package test;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈〉
 *
 * @author Hsiong
 * @version 1.0.0
 * @since 2026/3/4
 */
@RestController
public class RobotsController {
	// 下方 value = "/robots", 决定下方设置机器人消息接收地址，例如：https://example.com/robots
	@RequestMapping(value = "/robots", method = RequestMethod.POST)
	public String helloRobots(@RequestBody(required = false) JSONObject json) {
		System.out.println(json);
		//机器人接收消息内容
		String content = json.getJSONObject("text").get("content").toString().replaceAll(" ", "");
		System.out.println("机器人接收消息内容:" + content);
		return null;
	}
}