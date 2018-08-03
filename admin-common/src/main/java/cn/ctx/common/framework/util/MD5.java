/**    
* @Title: MD5.java  
* @Package cn.ctx.admin.util  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年11月7日 下午3:57:53  
* @version V1.0    
*/
package cn.ctx.common.framework.util;

import java.security.MessageDigest;

/**  
* @ClassName: MD5  
* @Description: TODO(MD5 加密)  
* @author gyu
* @date 2017年11月7日 下午3:57:53  
*    
*/
public class MD5 {
	
	public final static String encry(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes("utf-8");
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
