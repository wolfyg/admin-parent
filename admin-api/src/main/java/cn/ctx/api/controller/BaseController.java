package cn.ctx.api.controller;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import cn.ctx.api.base.BaseConfig;
import cn.ctx.api.model.WxUser;
import cn.ctx.api.service.IWxUserService;
import cn.ctx.api.util.Constants;
import cn.ctx.api.util.HttpClientUtil;
import cn.ctx.api.util.ResultBean;
import cn.ctx.api.util.Utils;

/**
 * Controller基础类
 * @author yg
 *
 */
public class BaseController {
	@Autowired
	protected HttpSession session;
	@Autowired
	protected HttpServletRequest request;
	@Autowired
	protected HttpServletResponse response;
	@Autowired
	protected BaseConfig config;
	@Autowired
	protected IWxUserService userService;
	
	public ResultBean result=new ResultBean();

	/**
	 * @Description: TODO(分页工具)
	 * @param @param
	 *            totalRecords 总记录数
	 * @param @param
	 *            page 当前页
	 * @param @param
	 *            pageCount 总页数
	 * @param @param
	 *            pageSize 分页大小
	 * @param @return
	 * @author gyu
	 * @date 2016年11月10日 下午5:10:30
	 * @version V1.0
	 */
	protected Map<String, Integer> handlePagination(int totalRecords, int page, int pageSize) {
		Map<String, Integer> model = new HashMap<String, Integer>();
		if (page < 1) {
			page = 1;
		}
		int pageCount = totalRecords / pageSize;
		if (totalRecords % pageSize > 0) {
			pageCount++;
		}
		if (page > pageCount) {
			page = pageCount;
		}
		if (page < 1) {
			page = 1;
		}

		model.put("offset", page);
		model.put("pageSize", pageSize);
		model.put("pageCount", pageCount);
		model.put("totalRecords", totalRecords);
		return model;
	}

	public static Map<String, Object> objectToMap(Object obj) throws Exception {
		if (obj == null)
			return null;

		Map<String, Object> map = new HashMap<String, Object>();

		BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor property : propertyDescriptors) {
			String key = property.getName();
			if (key.compareToIgnoreCase("class") == 0) {
				continue;
			}
			Method getter = property.getReadMethod();
			Object value = getter != null ? getter.invoke(obj) : null;
			map.put(key, value);
		}

		return map;
	}

	public String emoji(String source) {
		if (source != null) {
			Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
					Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
			Matcher emojiMatcher = emoji.matcher(source);
			if (emojiMatcher.find()) {
				source = emojiMatcher.replaceAll("");
				return source;
			}
			return source;
		}
		return source;
	}

	/**
	 * @Title: userInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @author gyu
	 * @throws UnsupportedEncodingException 
	 * @date 2017年6月26日下午3:34:49
	 */
	public WxUser userInfo(String code) throws UnsupportedEncodingException {
		String content;
		WxUser user = new WxUser();
		try {
			System.out.println("code:" + code);
			content = HttpClientUtil.getContent(code, config.getWx_openid());
			JSONObject jsonObject = JSONObject.parseObject(content);
			Utils.getClass(this.getClass()).info("微信授权参数：" + jsonObject);
			String openid = jsonObject.getString("openid");
			globalAccessToken();
			String userInfoJson = getWxUserInfo(openid);
			jsonObject = JSONObject.parseObject(userInfoJson);
			String nickname = jsonObject.getString("nickname");
			String headimgurl = jsonObject.getString("headimgurl");
			user.setOpenid(openid);
			user.setNickname(nickname);
			user.setHeadimg(headimgurl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (user != null) {
			user.setNickname(URLEncoder.encode(user.getNickname(),"UTF-8"));
			
			userService.insertUpdateWxUser(user);
			
			Utils.getClass(this.getClass()).info("微信用户写入数据库：" + user.toString());
		}
		session.setAttribute("WxUser", user);
		Utils.getClass(this.getClass()).info("用户登录返回信息:" + user.toString());
		return user;
	}
	
	/**
	 * 获取用户信息
	 * @return
	 */
	public WxUser getWxUser() {
		return (WxUser)session.getAttribute("WxUser");
	}

	/**
	 * @Title: globalAccessToken
	 * @Description: TODO(全局系统缓存)
	 * @author gyu
	 * @date 2017年6月23日下午1:49:16
	 */
	public String globalAccessToken() {
		if (System.getProperty(Constants.ACCESS_TOKEN_KEY) == null) {
			getToken();
		} else {
			long expires = Long.valueOf(System.getProperty(Constants.TOKEN_EXPIRES_KEY));
			long curTime = System.currentTimeMillis() / 1000;
			if (expires - curTime <= 0) {
				getToken();
			}
		}
		return System.getProperty(Constants.ACCESS_TOKEN_KEY);
	}

	/**
	 * @Title: getToken
	 * @Description: TODO(获取token)
	 * @param appid
	 * @param secret
	 * @author gyu
	 * @date 2017年6月23日下午1:47:33
	 */
	public boolean getToken() {
		Utils.getClass(this.getClass()).info("获取全局access_token和jsapi_ticket");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("grant_type", "client_credential");
		map.put("appid", config.getWx_appid());
		map.put("secret", config.getWx_secret());
		
		String json = HttpClientUtil.get(config.getWx_token(), map);
		JSONObject jsonObject = JSONObject.parseObject(json);
		System.setProperty(Constants.ACCESS_TOKEN_KEY, (String) jsonObject.get("access_token"));// access_token
		System.setProperty(Constants.TOKEN_EXPIRES_KEY, String.valueOf((System.currentTimeMillis() / 1000 + Long.valueOf((Integer) jsonObject.get("expires_in")))));
		
		Map<String, Object> ticket = new HashMap<String, Object>();
		ticket.put("access_token", jsonObject.get("access_token"));
		ticket.put("type", "jsapi");
		json = HttpClientUtil.get(Constants.JSAPI_TICKET_URL, ticket);
		jsonObject = JSONObject.parseObject(json);
		System.setProperty(Constants.JSAPI_TICKET, (String) jsonObject.get("ticket"));// jsapi_ticket
		Utils.getClass(this.getClass()).info("获取全局:access_token=" + jsonObject.get("access_token") + " jsapi_ticket=" + jsonObject.get("ticket"));
		return true;
	}

	/**
	 * @Title: getVoteWxUser
	 * @Description: TODO(获取APP用户信息)
	 * @author gyu
	 * @date 2017年6月29日下午4:14:22
	 */
	public WxUser getVoteWxUser() {
		return (WxUser) request.getSession().getAttribute("WxUser");
	}

	/**
	 * @Title: getWxUserInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param access_token
	 *            调用接口凭证
	 * @param openid
	 *            普通用户的标识，对当前公众号唯一
	 * @param lang
	 *            返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
	 * @author gyu
	 * @date 2017年6月22日下午5:54:18
	 */
	public String getWxUserInfo(String openid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("access_token", System.getProperty(Constants.ACCESS_TOKEN_KEY));
		map.put("openid", openid);
		map.put("lang", "zh_CN");
		Utils.getClass(this.getClass()).info("微信token信息:" + map.toString());
		String json = HttpClientUtil.get(config.getWx_info(), map);
		Utils.getClass(this.getClass()).info("微信token获取用户信息:" + map.toString());
		return json;
	}
}
