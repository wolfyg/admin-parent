/**    
* @Title: BaseConfig.java  
* @Package com.ctx.beautifulgirl.config.base  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年3月13日 下午5:46:06  
* @version V1.0    
*/
package cn.ctx.common.base;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
* @ClassName: BaseConfig  
* @Description: TODO(系统变量类)  
* @author gyu
* @date 2017年6月22日 下午3:56:13  
*
 */
@ConfigurationProperties(prefix = "base")  
public class BaseConfig {
	
	private String wx_auth;
	
	private String wx_openid;
	
	private String wx_appid;
	
	private String wx_secret;
	
	private String wx_token;
	
	private String wx_info;
	
	private String wx_signal;
	
	private String voteStartTime;
	
	private String voteEndTime;
	
	private int wxvoteNum;
	
	private int pcvoteNum;
	
	private int yxwzNum;
	
	public int getYxwzNum(){
		return yxwzNum;
	}

	public void setYxwzNum(int yxwzNum){
		this.yxwzNum = yxwzNum;
	}
	
	public int getPcvoteNum() {
		return pcvoteNum;
	}

	public void setPcvoteNum(int pcvoteNum) {
		this.pcvoteNum = pcvoteNum;
	}

	public int getWxvoteNum() {
		return wxvoteNum;
	}

	public void setWxvoteNum(int wxvoteNum) {
		this.wxvoteNum = wxvoteNum;
	}

	public String getVoteStartTime() {
		return voteStartTime;
	}

	public void setVoteStartTime(String voteStartTime) {
		this.voteStartTime = voteStartTime;
	}

	public String getVoteEndTime() {
		return voteEndTime;
	}

	public void setVoteEndTime(String voteEndTime) {
		this.voteEndTime = voteEndTime;
	}

	public String getWx_signal() {
		return wx_signal;
	}

	public void setWx_signal(String wx_signal) {
		this.wx_signal = wx_signal;
	}

	public String getWx_auth() {
		return wx_auth;
	}

	public void setWx_auth(String wx_auth) {
		this.wx_auth = wx_auth;
	}

	public String getWx_openid() {
		return wx_openid;
	}

	public void setWx_openid(String wx_openid) {
		this.wx_openid = wx_openid;
	}

	public String getWx_token() {
		return wx_token;
	}

	public void setWx_token(String wx_token) {
		this.wx_token = wx_token;
	}

	public String getWx_info() {
		return wx_info;
	}

	public void setWx_info(String wx_info) {
		this.wx_info = wx_info;
	}

	public String getWx_appid() {
		return wx_appid;
	}

	public void setWx_appid(String wx_appid) {
		this.wx_appid = wx_appid;
	}

	public String getWx_secret() {
		return wx_secret;
	}

	public void setWx_secret(String wx_secret) {
		this.wx_secret = wx_secret;
	}

}
