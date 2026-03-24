package bot.util;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * 〈〉
 *
 * @author Hsiong
 * @version 1.0.0
 * @since 2026/3/23
 */
@Component
public class BotUtil {
	
	@Value("${dingding.bot-secret}")
	private String botSecret;
	
	/**
	 * 钉钉要求：timestamp 与当前系统时间相差不能超过 1 小时
	 */
	private static final long MAX_DIFF_MILLIS = 60 * 60 * 1000L;
	
	
	/**
	 * 验证机器人身份，生成签名字符串。
	 * 
	 * @throws Exception
	 */
	public void authBot(String timestamp, String sign) {
		
		// 1. 校验时间戳
		long now = System.currentTimeMillis();
		if (Math.abs(now - Long.parseLong(timestamp)) > MAX_DIFF_MILLIS) {
			throw new RuntimeException("timestamp error: " + timestamp);
		}
		
		// 2. 校验签名
		try {
			String stringToSign = timestamp + "\n" + botSecret;
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(new SecretKeySpec(botSecret.getBytes("UTF-8"), "HmacSHA256"));
			byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
			String localSign = new String(Base64.encodeBase64(signData, false));
			if (!localSign.equals(sign)) {
				System.out.println("localSign=" + localSign + ", sign=" + sign);
				throw new RuntimeException(String.format("sign error: timestamp=%s, sign=%s", timestamp, sign));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
