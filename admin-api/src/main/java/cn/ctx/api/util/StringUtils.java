/**    
* @Title: StringUtils.java  
* @Package cn.ctx.carpoolers.util  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年6月25日 下午2:09:37  
* @version V1.0    
*/
package cn.ctx.api.util;

/**  
 * @ClassName: StringUtils  
 * @Description: TODO(字符串工具类)  
 * @author gyu
 * @date 2017年6月25日 下午2:09:37  
 *    
 */
public class StringUtils {
	public static boolean isBlank(String str) {
		if (str != null && !str.equals("")) {
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean isNotBlank(String str) {
		if (str != null && !str.equals("")) {
			return true;
		} else {
			return false;
		}
	}
}
