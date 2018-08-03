package cn.ctx.api.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ctx.api.base.BaseConfig;
import cn.ctx.api.model.WxUser;
import cn.ctx.api.util.Constants;
import cn.ctx.api.util.Sign;
import cn.ctx.api.util.Utils;

/**
 * 微信授权
 * @author yg
 */
@Controller
public class WxAuthController extends BaseController{
	@Autowired
	private BaseConfig config;
	/**
	* @Title: wxauth
	* @Description: TODO(微信授权)
	* @param returnUrl 重定向
	* @author gyu
	* @date 2017年6月22日下午4:01:38
	 */
	@RequestMapping(value = "/auth", produces = "application/json; charset=utf-8")
	public String wxauth(String returnUrl) {
		Utils.getClass(this.getClass()).info("微信授权开始");
		return "redirect:"+(config.getWx_auth().replace("STATE", returnUrl));
	}
	
	/**
	* @Title: loading
	* @Description: TODO(微信授权loading)
	* @param code
	* @author gyu
	 * @throws UnsupportedEncodingException 
	* @date 2017年6月26日下午3:00:24
	 */
	@RequestMapping(value = "/openid", produces = "application/json; charset=utf-8")
	public String loading(String code,String state) throws UnsupportedEncodingException {
		Utils.getClass(this.getClass()).info("微信code："+code+",返回地址:"+state);
		WxUser user=userInfo(code);
		if(user==null){////重新授权
		 	String url=request.getRequestURL().toString();
			return "redirect:auth?returnUrl="+url;
		}else{
			return "redirect:"+state;
		}
	}

	/**
	* @Title: game
	* @Description: TODO(游戏)
	* @author gyu
	* @date 2017年7月13日上午10:55:19
	 */
	@RequestMapping(value = "/game", produces = "application/json; charset=utf-8")
	public String game(){
		Utils.getClass(this.getClass()).info("进入游戏页面");
		return "game/2048JD/index";
	}
	
	/**
	 * 
	* @Title: sign
	* @Description: TODO(jsapi配置签名)
	* @param url
	* @author gyu
	* @date 2017年7月12日上午9:44:41
	 */
	@RequestMapping(value = "/sign", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object sign(String url) {
		Utils.getClass(this.getClass()).info("jsapi配置请求参数:url="+url);
		Map<String, String> map=Sign.sign(config.getWx_appid(),System.getProperty(Constants.JSAPI_TICKET), url);
		Utils.getClass(this.getClass()).info("jsapi配置返回参数:"+map.toString());
		return map;
	}
	
	


}
