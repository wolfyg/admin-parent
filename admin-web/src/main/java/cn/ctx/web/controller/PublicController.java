/**    
* @Title: PublicController.java  
* @Package cn.ctx.admin.controller  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年11月11日 下午4:36:27  
* @version V1.0    
*/
package cn.ctx.web.controller;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ctx.common.framework.util.VerifyCodeUtils;
import cn.ctx.common.framework.util.vcode.GifCaptcha;
import cn.ctx.common.framework.util.vcode.SpecCaptcha;
import cn.ctx.service.provider.AdminService;

/**  
* @ClassName: PublicController  
* @Description: TODO(公共处理方法)  
* @author gyu
* @date 2017年11月11日 下午4:36:27  
*    
*/
@RequestMapping("/public")
@Controller
public class PublicController {
	public static final Logger log=LoggerFactory.getLogger(PublicController.class);
	@Autowired
	private AdminService adminService;
	@Autowired
	private HttpSession session;
	

	/**
	* @Title: isExistUser
	* @Description: TODO(验证用户是否存在)
	* @param username
	* @author gyu
	* @date 2017年11月11日下午4:55:28
	 */
	@RequestMapping(value = "/isExistUser", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object isExistUser(String username) {
		return adminService.isExistUser(username);
	}
	
	

	@RequestMapping(value = "/403", produces = "application/json; charset=utf-8")
	public Object fourzorethree(String username) {
		return "hplus/error/403";
	}
	
	/**
	 * 获取验证码
	 * @param response
	 */
	@RequestMapping(value="getVCode",method=RequestMethod.GET)
	public void getVCode(HttpServletResponse response,HttpServletRequest request){
		try {
			response.setHeader("Pragma", "No-cache");  
	        response.setHeader("Cache-Control", "no-cache");  
	        response.setDateHeader("Expires", 0);  
	        response.setContentType("image/jpg");  
	        
	        //生成随机字串  
	        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);  
	        //存入会话session  
	        session.setAttribute(VerifyCodeUtils.V_CODE, verifyCode.toLowerCase());  
	        //生成图片  
	        int w = 146, h = 33;  
	        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode); 
		} catch (Exception e) {
			log.error("获取验证码异常：%s",e.getMessage());
		}
	}
	
	/**
	 * 获取验证码（Gif版本）
	 * @param response
	 */
	@RequestMapping(value="getGifCode",method=RequestMethod.GET)
	public void getGifCode(HttpServletResponse response,HttpServletRequest request){
		try {
			response.setHeader("Pragma", "No-cache");  
	        response.setHeader("Cache-Control", "no-cache");  
	        response.setDateHeader("Expires", 0);  
	        response.setContentType("image/gif");  
	        /**
	         * gif格式动画验证码
	         * 宽，高，位数。
	         */
	        GifCaptcha captcha = new GifCaptcha(146,42,4);
	        //输出
	        ServletOutputStream out = response.getOutputStream();
	        captcha.out(out);
	        out.flush();
	       //存入会话session  
	        System.out.println( captcha.text().toLowerCase());
	        session.setAttribute(VerifyCodeUtils.V_CODE, captcha.text().toLowerCase());  
		} catch (Exception e) {
			log.error("获取验证码异常：%s",e.getMessage());
		}
	}
	/**
	 * 获取验证码（jpg版本）
	 * @param response
	 */
	@RequestMapping(value="getJPGCode",method=RequestMethod.GET)
	public void getJPGCode(HttpServletResponse response,HttpServletRequest request){
		try {
			response.setHeader("Pragma", "No-cache");  
			response.setHeader("Cache-Control", "no-cache");  
			response.setDateHeader("Expires", 0);  
			response.setContentType("image/jpg");  
			/**
			 * jgp格式验证码
			 * 宽，高，位数。
			 */
			SpecCaptcha captcha = new SpecCaptcha(146,33,4);
			//输出
			captcha.out(response.getOutputStream());
			HttpSession session = request.getSession(true);  
			//存入Session
			session.setAttribute(VerifyCodeUtils.V_CODE,captcha.text().toLowerCase());  
		} catch (Exception e) {
			log.error("获取验证码异常：%s",e.getMessage());
		}
	}
}
