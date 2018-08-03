/**    
* @Title: UserSecurityInterceptor.java  
* @Package cn.ctx.vote.web.Interceptor  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年10月14日 下午5:27:30  
* @version V1.0    
*/
package cn.ctx.api.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.ctx.api.model.WxUser;

/**  
* @ClassName: UserSecurityInterceptor  
* @Description: TODO(PC端拦截器)  
* @author gyu
* @date 2017年10月14日 下午5:27:30  
*    
*/
public class UserSecurityInterceptor implements HandlerInterceptor {
	 @Override
	    public boolean preHandle(HttpServletRequest request,
	            HttpServletResponse response, Object handler) throws Exception {
	         //验证用户是否登陆
		 	WxUser wuser = (WxUser)request.getSession().getAttribute("WxUser");
		 	String url=request.getRequestURL().toString();
	 		if(wuser==null) {
	         	response.setContentType("text/html;charset=UTF-8");
     			response.getWriter().write("<script type='text/javascript'>");
     			response.getWriter().write("alert('登录超时，正在重新登录');");
     			response.getWriter().write("window.top.location.href=('"+request.getContextPath()+"/auth?returnUrl="+url+"');");
     			response.getWriter().write("</script>");
     			return false;
	 		}
	        return true;
	    }

	    @Override
	    public void postHandle(HttpServletRequest request,
	            HttpServletResponse response, Object handler,
	            ModelAndView modelAndView) throws Exception {
	    }

	    @Override
	    public void afterCompletion(HttpServletRequest request,
	            HttpServletResponse response, Object handler, Exception ex)
	            throws Exception {
	    }
}
