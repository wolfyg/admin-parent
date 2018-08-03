/**    
* @Title: StringUtil.java  
* @Package cn.ctx.admin.util  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年11月7日 下午4:20:46  
* @version V1.0    
*/
package cn.ctx.common.framework.util;

/**  
* @ClassName: StringUtil  
* @Description: TODO(字符工具类)  
* @author gyu
* @date 2017年11月7日 下午4:20:46  
*    
*/
public class StringUtil {

	public StringUtil() {}
	
	/**
	* @Title: isNull
	* @Description: TODO(是空)
	* @param Object
	* @author gyu
	* @date 2017年11月7日下午4:23:08
	 */
	public static boolean isNull(Object obj) {
		if(obj==null) {
			return Boolean.TRUE;
		}else {
			return Boolean.FALSE;
		}
	}

	/**
	* @Title: isNull
	* @Description: TODO(不是空)
	* @param Object
	* @author gyu
	* @date 2017年11月7日下午4:23:08
	 */
	public static boolean isNotNull(Object obj) {
		if(obj!=null) {
			return Boolean.TRUE;
		}else {
			return Boolean.FALSE;
		}
	}
	
}
