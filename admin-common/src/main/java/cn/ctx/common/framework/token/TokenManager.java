package cn.ctx.common.framework.token;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import cn.ctx.common.framework.ServiceException;
import cn.ctx.common.framework.UTDataInputStream;
import cn.ctx.common.framework.util.Constants;

public class TokenManager {
	
	private static final Logger LOG = LoggerFactory.getLogger(TokenManager.class);
	
	private static Map<Integer, TokenRegistry> tokenRegistry = new HashMap<Integer, TokenRegistry>();
	
	@SuppressWarnings("rawtypes")
	public static void registerToken(int tokenType, Class tokenClass, String secretKey) {
		tokenRegistry.put(tokenType, new TokenRegistry(tokenClass, secretKey));
	}
	
	public static String createToken(Token token) throws TokenException {
		if (token == null) {
			return null;
		}

		TokenRegistry tkReg = tokenRegistry.get(token.getType());
		if (tkReg == null) {
			throw new TokenException("Unsupported token type: " + token.getType());
		}
		
		byte[] data;
		try {
			data = token.getBytes();
		} catch (IOException e) {
			LOG.error("IO Error in createToken");
			throw new TokenException("IO Error");
		}
		
		String encodedRawData = encodeBase64(data);
		byte[] encodedData = encodedRawData.getBytes(CryptUtil.UTF8);
		byte[] hash = CryptUtil.HMAC_SHA256(encodedData, tkReg.getSecretKeyBytes());
		
		String hashString = encodeBase64(hash);
		return hashString + "." + new String(encodedData, CryptUtil.UTF8);
	}
	
	public static Map<String,Object> parseToken(String token) throws TokenException,ServiceException {
		return parseToken(token, true);
	}

	public static Map<String,Object> parseToken(String token, boolean checkExpiration) throws TokenException,ServiceException {
		Map<String,Object> mapToken = new HashMap<String,Object>();
		if (StringUtils.isEmpty(token)) {
			mapToken.put("code", Constants.Token.TOKEN_NOT_NULL);
			mapToken.put("msg", "令牌不能为空");
			return mapToken;
		}

		String[] s = token.split("\\.", 2);
		if (s.length != 2 || StringUtils.isEmpty(s[0]) || StringUtils.isEmpty(s[1])) {
			mapToken.put("code", Constants.Token.INVALID_TOKEN);
			mapToken.put("msg", "无效的令牌");
			return mapToken;
		}
		byte[] hash = decodeBase64(s[0]);
		byte[] encodedData = s[1].getBytes(CryptUtil.UTF8);
		byte[] data = decodeBase64(s[1]);
		UTDataInputStream in = new UTDataInputStream(new ByteArrayInputStream(data));
		try {
			int tokenType;
			TokenRegistry tkReg;
			try {
				tokenType = in.readByte();
				tkReg = tokenRegistry.get(tokenType);
				if (tkReg == null) {
					mapToken.put("code", Constants.Token.TOKEN_UNSUPPORTED_TYPE);
					mapToken.put("msg", "无效的类型");
					return mapToken;
				}
			} catch (IOException e) {
				mapToken.put("code", Constants.Token.TOKEN_READ_ERROR);
				mapToken.put("msg", "令牌读取错误");
				return mapToken;
			}
			
			// verify token digest
			byte[] expectedHash = CryptUtil.HMAC_SHA256(encodedData, tkReg.getSecretKeyBytes());
			if (!Arrays.equals(hash, expectedHash)) {
				mapToken.put("code", Constants.Token.INVALID_TOKEN);
				mapToken.put("msg", "无效的令牌");
				return mapToken;
			}
	
			// read token
			Token result = readToken(tokenType, in);
			// check if token expired
			if (checkExpiration && result.isExpired()) {
				mapToken.put("code", Constants.Token.TOKEN_EXPIRED);
				mapToken.put("msg", "令牌过期");
				return mapToken;
			}
			mapToken.put("code", Constants.Token.TOKEN_SUCCESS);
			mapToken.put("msg", "获取令牌成功");
			mapToken.put("token", result);
			return mapToken;
		} finally {
			try {
				in.close();
			} catch (IOException e) {
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private static Token readToken(int tokenType, UTDataInputStream in) throws TokenException {
		TokenRegistry tkReg = tokenRegistry.get(tokenType);
		if (tkReg == null) {
			throw new TokenException("Unsupported token type: " + tokenType);
		}
		
		Token token = null;
		try {
			Class tokenClass = tkReg.getTokenClass();
			token = (Token) tokenClass.newInstance();
			token.setVersion(in.readByte());
			token.readFrom(in);
		} catch (IOException e) {
			LOG.error("IO Error in readToken");
			throw new TokenException("IO Error");
		} catch (Exception e) {
			LOG.error("Read token error: " + e.getMessage());
			throw new TokenException("Read token error: " + e.getMessage());
		}
		return token;
	}
	
	private static String encodeBase64(byte[] hash) {
		String s = Base64.encodeBase64URLSafeString(hash);
		return s.replaceAll("=", "");
	}
	
	private static byte[] decodeBase64(String hash) {
		while (hash.length() % 4 != 0) {
			hash += '=';
		}
		return Base64.decodeBase64(hash);
	}
	
	static class TokenRegistry {
		@SuppressWarnings("rawtypes")
		Class tokenClass;
		String secretKey;
		byte[] secretKeyBytes;
		
		@SuppressWarnings("rawtypes")
		TokenRegistry(Class tokenClass, String secretKey) {
			this.tokenClass = tokenClass;
			this.secretKey = secretKey;
			this.secretKeyBytes = secretKey.getBytes(CryptUtil.UTF8);
		}
		
		@SuppressWarnings("rawtypes")
		public final Class getTokenClass() {
			return tokenClass;
		}
		
		public final String getSecretKey() {
			return secretKey;
		}
		
		public final byte[] getSecretKeyBytes() {
			return secretKeyBytes;
		}
	}

}
