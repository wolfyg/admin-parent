package cn.ctx.common.framework.token;

import java.nio.charset.Charset;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CryptUtil {

	public static final Charset UTF8 = Charset.forName("UTF-8");

	private static Logger LOG = LoggerFactory.getLogger(CryptUtil.class);

	private static final String AES = "AES";
	private static final String AES_CBC = "AES/CBC/PKCS5Padding";
	private static final String MD5 = "MD5";
	private static final String SHA1 = "SHA-1";
	private static final String SHA256 = "SHA-256";
	private static final String HMAC_SHA1 = "HmacSHA1";
	private static final String HMAC_SHA256 = "HmacSHA256";

	private static final byte[] iv = "<P't8B5]$7^J]aQq".getBytes();

	private static SecretKey secretKey;
	
	
	
	static {
		byte[] key1 = "g,,W\"zxV4&A<x#-SjnmGGc&0RZ6&wCNX".getBytes();
		byte[] key2 = "IfU/%%,ymQ!826E4@fS$QMKM{Yx4)]>9".getBytes();
		byte hash1[] = SHA1(key1);
		byte hash2[] = SHA256(key2);
		byte key[] = new byte[hash1.length];
		for (int i = 0; i < hash1.length; i++) {
			key[i] = (byte) (hash1[i] ^ hash2[i]);
		}
		secretKey = new SecretKeySpec(key, 0, 16, AES);
	}

	public static String MD5(String str) {
		return Hex.encodeHexString(MD5(str.getBytes(UTF8)));
	}

	public static byte[] MD5(byte[] data) {
		try {
			MessageDigest md = MessageDigest.getInstance(MD5);
			md.update(data);
			return md.digest();
		} catch (Exception e) {
			LOG.error("MD5 error", e);
			throw new RuntimeException(e);
		}
	}
	
	public static String SHA1(String str) {
		return Hex.encodeHexString(SHA1(str.getBytes(UTF8)));
	}

	public static byte[] SHA1(byte[] data) {
		try {
			MessageDigest md = MessageDigest.getInstance(SHA1);
			md.update(data);
			return md.digest();
		} catch (Exception e) {
			LOG.error("SHA1 error", e);
			throw new RuntimeException(e);
		}
	}

	public static String SHA256(String str) {
		return Hex.encodeHexString(SHA256(str.getBytes(UTF8)));
	}

	public static byte[] SHA256(byte[] data) {
		try {
			MessageDigest md = MessageDigest.getInstance(SHA256);
			md.update(data);
			return md.digest();
		} catch (Exception e) {
			LOG.error("SHA256 error", e);
			throw new RuntimeException(e);
		}
	}

	public static String HMAC_SHA1(String data, String key) {
		return HMAC(HMAC_SHA1, data, key);
	}

	public static byte[] HMAC_SHA1(byte[] data, byte[] key) {
		return HMAC(HMAC_SHA1, data, key);
	}

	public static String HMAC_SHA256(String data, String key) {
		return HMAC(HMAC_SHA256, data, key);
	}

	public static byte[] HMAC_SHA256(byte[] data, byte[] key) {
		return HMAC(HMAC_SHA256, data, key);
	}

	public static String HMAC(String algorithm, String data, String key) {
		return Hex.encodeHexString(HMAC(algorithm, data.getBytes(UTF8), key.getBytes(UTF8)));
	}

	public static byte[] HMAC(String algorithm, byte[] data, byte[] key) {
		try {
			SecretKeySpec signingKey = new SecretKeySpec(key, algorithm);
			Mac mac = Mac.getInstance(algorithm);
			mac.init(signingKey);
			return mac.doFinal(data);
		} catch (Exception e) {
			LOG.error("HMAC error", e);
			throw new RuntimeException(e);
		}
	}

	public static String encodePassword(String username, String password) {
		String saltedPass = password + "{" + username + "}";
		String hash = MD5(saltedPass);
		return hash;
	}
	
	public static String decrypt(String str) {
		try {
			return new String(decrypt(Hex.decodeHex(str.toCharArray())));
		} catch (DecoderException e) {
			LOG.error("decrypt error", e);
			throw new RuntimeException(e);
		}
	}

	public static byte[] decrypt(byte[] data) {
		try {
			IvParameterSpec ivSpec = new IvParameterSpec(iv);
			Cipher aes = Cipher.getInstance(AES_CBC);
			aes.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
			return aes.doFinal(data);
		} catch (Exception e) {
			LOG.error("Decrypt error", e);
			throw new RuntimeException(e);
		}
	}
	
	public static String encrypt(String str) {
		return Hex.encodeHexString(encrypt(str.getBytes(UTF8)));
	}

	public static byte[] encrypt(byte[] data) {
		try {
			IvParameterSpec ivSpec = new IvParameterSpec(iv);
			Cipher aes = Cipher.getInstance(AES_CBC);
			aes.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
			return aes.doFinal(data);
		} catch (Exception e) {
			LOG.error("Encrypt error", e);
			throw new RuntimeException(e);
		}
	}



}
