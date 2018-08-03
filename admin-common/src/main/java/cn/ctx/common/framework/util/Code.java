/**    
* @Title: Code.java  
* @Package cn.ctx.admin.util  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年11月7日 下午4:49:24  
* @version V1.0    
*/
package cn.ctx.common.framework.util;

/**  
* @ClassName: Code  
* @Description: TODO(返回码工具类)  
* @author gyu
* @date 2017年11月7日 下午4:49:24  
*    
*/
public interface Code {

	/**成功:1**/
	public static Integer SUCCESS=1;
	/**异常:0**/
	public static Integer ERROR=0;
	/**网络异常:9999**/
	public static Integer NETWORK_ERROR =9999;
	
	/**需要登录:-1**/
	public static Integer NEEDLOGIN = -1;
	/**需要登录:-2**/
	public static Integer NOT_PERMISSION = -2;
	/**成功图标:1**/
	public static Integer ICON_SUCCESS = 1;
	/**失败图标:2**/
	public static Integer ICON_FAIL = 2;
	/**问题图标:3**/
	public static Integer ICON_PROBLEM = 3;
	/**锁:4**/
	public static Integer ICON_LOCK = 4;
	
	/**任务关闭:0**/
	public static String TASK_OFF = "0";

	/**任务开启:1**/
	public static String TASK_ON = "1";
	
}
