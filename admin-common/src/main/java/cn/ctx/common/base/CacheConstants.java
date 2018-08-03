/**    
* @Title: Constants.java  
* @Package cn.ctx.admin.base  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年10月31日 下午3:35:24  
* @version V1.0    
*/
package cn.ctx.common.base;

/**  
* @ClassName: Constants  
* @Description: TODO(常量)  
* @author gyu
* @date 2017年10月31日 下午3:35:24  
*    
*/
public class CacheConstants {


    //这里的单引号不能少，否则会报错，被识别是一个对象;  
    public static final String CACHE_KEY = "'admin_'";  
     
    /** 
     * value属性表示使用哪个缓存策略，缓存策略在ehcache.xml 
    */  
    public static final String CACHE_NAME = "content";  
    

    /**
     * 登录
     */
	public static int USERNAME_TTL = 24 * 3600;
	public static String KEY_USERNAME="username%s";
	

	/**
	 * 权限
	 */
	public static int PERMISSION_TTL = 24 * 3600;
	public static String KEY_PERMISSIONBYID="permission%s";
	
	/**
	 * 任务状态
	 */
	public static String TASKSTATUS="taskStatus%s";
}
