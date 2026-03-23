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
	
	@Value("${dingding.bot-secret")
	private String botSecret;
	
	
	/**
	 * 验证机器人身份，生成签名字符串。
	 * 
	 * @throws Exception
	 */
	public void authBot() {
		try {
			Long timestamp = 1577262236757L;
			String appSecret = "this is a secret";
			String stringToSign = timestamp + "\n" + appSecret;
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(new SecretKeySpec(appSecret.getBytes("UTF-8"), "HmacSHA256"));
			byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
			String sign = new String(Base64.encodeBase64(signData, false));
			System.out.println(sign);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
