/**    
* @Title: Constants.java  
* @Package cn.ctx.carpoolers.util  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年6月22日 下午4:54:05  
* @version V1.0    
*/
package cn.ctx.common.framework.util;

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
	 * 删除-否
	 */
	public static int IS_DEL_NO=0;
	

	public static final Integer DIC_ROOT = -1;
	/**
	 * 删除-是
	 */
	public static int IS_DEL_YES=1;
	
	/**
	 * 来源微信
	 */
	public static String FROM="WX";
	
	/**
	 * 微信JSSDKtoken
	 */
	public static String JSAPI_TICKET_URL="https://api.weixin.qq.com/cgi-bin/ticket/getticket";
	
	public static String APPID="wxa628316ca08962d9";
	
	/**
	* @ClassName: Token  
	* @Description: TODO(token 状态)  
	* @author gyu
	* @date 2018年2月27日 上午10:21:28  
	 */
	public interface Token {
		public static final String TOKEN_SUCCESS = "token_success";
		public static final String TOKEN_EXPIRED = "token_expired";
		public static final String TOKEN_NOT_NULL = "token_not_null";
		public static final String TOKEN_UNSUPPORTED_TYPE = "unsupported_type";
		public static final String TOKEN_READ_ERROR = "readToken_error";
		public static final String INVALID_TOKEN = "invalid_token";
	}
}
