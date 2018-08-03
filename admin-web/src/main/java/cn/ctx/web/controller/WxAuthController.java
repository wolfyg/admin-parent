package cn.ctx.web.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ctx.common.base.BaseConfig;
import cn.ctx.common.framework.util.Constants;
import cn.ctx.common.framework.util.Sign;
import cn.ctx.service.model.VoteWxUser;

@Controller
public class WxAuthController extends BaseController{
    protected static final Logger LOG = LoggerFactory.getLogger(WxAuthController.class);
	@Autowired
	private BaseConfig config;
	/**
	* @Title: wxauth
	* @Description: TODO(微信授权)
	* @param 
	* @author gyu
	* @date 2017年6月22日下午4:01:38
	 */
	@RequestMapping(value = "/auth", produces = "application/json; charset=utf-8")
	public String wxauth() {
		LOG.info("微信授权开始");
		return "redirect:"+config.getWx_auth();
	}
	
	/**
	* @Title: loading
	* @Description: TODO(微信授权loading)
	* @param code
	* @author gyu
	* @date 2017年6月26日下午3:00:24
	 */
	@RequestMapping(value = "/openid", produces = "application/json; charset=utf-8")
	public String loading(String code) {
		LOG.info("微信code："+code);
		VoteWxUser user=userInfo(code);
		if(user==null){////重新授权
			return "redirect:auth";
		}else{
			return "redirect:wx/index";
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
		LOG.info("进入游戏页面");
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
		LOG.info("jsapi配置请求参数:url="+url);
		Map<String, String> map=Sign.sign(System.getProperty(Constants.JSAPI_TICKET), url);
		LOG.info("jsapi配置返回参数:"+map.toString());
		return map;
	}
	
	


}
