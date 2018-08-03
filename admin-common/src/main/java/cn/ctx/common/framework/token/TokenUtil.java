/**    
* @Title: TokenUtil.java  
* @Package cn.ctx.common.framework.token  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2018年2月27日 上午10:12:34  
* @version V1.0    
*/
package cn.ctx.common.framework.token;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.ctx.common.framework.ServiceException;

/**  
* @ClassName: TokenUtil  
* @Description: TODO(Token工具类)  
* @author gyu
* @date 2018年2月27日 上午10:12:34  
*/
@Component
public class TokenUtil {
	
	// token type
	public static final int TOKEN_LOGIN = 1;
	
	public static final int TOKEN_LOGIN_VERSION = 1;

	// configuration token key/ttl
	private static String TOKEN_LOGIN_KEY;
	
	public static int TOKEN_LOGIN_TTL;

	@Value("${token.login.key}")
	public void setTOKEN_LOGIN_KEY(String tOKEN_LOGIN_KEY) {
		TOKEN_LOGIN_KEY = tOKEN_LOGIN_KEY;
	}

	@Value("${token.login.ttl}")
	public void setTOKEN_LOGIN_TTL(int tOKEN_LOGIN_TTL) {
		// ttl in configuration file is minutes, convert to milliseconds
		TOKEN_LOGIN_TTL = tOKEN_LOGIN_TTL * 60 * 1000;
	}

	public static void registerTokens() {
		
		TokenManager.registerToken(TOKEN_LOGIN, LoginToken.class, TOKEN_LOGIN_KEY);
	}
	
	/**
	 * 创建令牌
	 * @param userId
	 * @return
	 * @throws TokenException
	 */
	public static String createToken(String userId) throws TokenException{
		registerTokens();
		LoginToken tk = new LoginToken();
		tk.setUserId(userId);
		String strToken = TokenManager.createToken(tk);
		return strToken;
	}
	
	/**
	* @Title: parseToken
	* @Description: TODO(解析token)
	* @param token
	* @author gyu
	 */
	public static Map<String,Object> parseToken(String token) throws TokenException, ServiceException{
		return TokenManager.parseToken(token);
	}

}

