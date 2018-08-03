/**    
* @Title: Constants.java  
* @Package cn.ctx.carpoolers.util  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年6月22日 下午4:54:05  
* @version V1.0    
*/
package cn.ctx.api.util;

/**  
 * @ClassName: Constants  
 * @Description: TODO(系统常量池)  
 * @author gyu
 * @date 2017年6月22日 下午4:54:05  
 *    
 */
public class Constants {

	/**
	 * 全局token 键
	 */
	public static final String ACCESS_TOKEN_KEY="access_token";
	/**
	 * 全局jsapi_ticket 键
	 */
	public static final String JSAPI_TICKET="ticket";
	/**
	 * 全局token有效期 键
	 */
	public static final String TOKEN_EXPIRES_KEY="expires_in";
	
	/**
	 * 微信JSSDKtoken
	 */
	public static String JSAPI_TICKET_URL="https://api.weixin.qq.com/cgi-bin/ticket/getticket";
	
	/**
	 * 微信APPID
	 */
	public static String APPID="wx005f236fbe63788c";
	
	public interface CODE{

		/**
		 * 删除-否
		 */
		public static int IS_DEL_NO=0;
		/**
		 * 删除-是
		 */
		public static int IS_DEL_YES=1;
		
		/**
		 * 成功
		 */
		public static int SUCCESS = 1;
		
		/**
		 * 失败
		 */
		public static int FAIL = 0;
		
		/**
		 * 异常
		 */
		public static int ERROR	= 9999;
		
		/**
		 * 来源微信
		 */
		public static String FROM="WX";
		
	}
	
	/**
	 * 数据字典
	 * @author lenovo
	 */
	public interface DIC{
		/**交管分类**/
		public static String JG="JG_TYPE";
		/**蚌埠市**/
		public static String BENGBU="BENGBUSHI";
		/**出行分类**/
		public static String TRAVEL="TRAVEL";
		/**交通违法举报**/
		public static String JTWFJB="JTWFJB";
		/**奖励类型**/
		public static String REWARDTYPE="REWARDTYPE";
		/**车辆类型**/
		public static String CARTYPE="CARTYPE";
		/**在线挪车**/
		public static String ONLINE_CAR="ONLINE_CAR";
		
	}
}
