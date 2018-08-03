/**    
* @Title: UserSecurityInterceptor.java  
* @Package cn.ctx.vote.web.Interceptor  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年10月14日 下午5:27:30  
* @version V1.0    
*/
package cn.ctx.web.Interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import cn.ctx.common.base.ResultBean;
import cn.ctx.common.framework.util.Code;
import cn.ctx.service.model.AmAdmin;
import cn.ctx.service.provider.PermissionService;
import cn.ctx.service.provider.impl.PermissionServiceImpl;

/**  
* @ClassName: UserSecurityInterceptor  
* @Description: TODO(PC端拦截器)  
* @author gyu
* @date 2017年10月14日 下午5:27:30  
*    
*/
public class UserSecurityInterceptor implements HandlerInterceptor {
	@Autowired
	private PermissionService permissionService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
	            HttpServletResponse response, Object handler) throws Exception {
	        //验证用户是否登陆
		 	AmAdmin admin = (AmAdmin)request.getSession().getAttribute("admin");
		 	String url=request.getRequestURI();
		 	String requestType = request.getHeader("X-Requested-With");
		 	//异步方式验证
		 	if("XMLHttpRequest".equals(requestType)){
	 			ResultBean result=new ResultBean();
		 		if(admin==null) {
		         	response.setContentType("text/html;charset=UTF-8");
		 			result.setMsg("登录超时请重新登录");
		 			result.setResult(Code.NEEDLOGIN);
		 			result.setIcon(Code.ICON_FAIL);
	     			response.getWriter().write(new Gson().toJson(result));
	     			return false;
		 		}else if(!url.substring(request.getContextPath().length()+1,url.length()).equals("manger/main")){//跳过主页
				    if (permissionService == null) {
			            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext()); 
			            permissionService = (PermissionService) factory.getBean(PermissionServiceImpl.class); 
			        } 
				    Map<String, List<Map<String,Object>>> mapList=permissionService.getAmSysFuncListToMap(admin.getId());
						if(mapList.toString().indexOf(url.substring(request.getContextPath().length()+1,url.length())) ==-1) {
				         	response.setContentType("text/html;charset=UTF-8");
				 			result.setMsg("你没有权限");
				 			result.setResult(Code.NOT_PERMISSION);
				 			result.setIcon(Code.ICON_FAIL);
			     			response.getWriter().write(new Gson().toJson(result));
		 	     			return false;
	 					}
		 		}
		 	}

		 	//普通方式验证
	 		if(admin==null) {
	         	response.setContentType("text/html;charset=UTF-8");
     			response.getWriter().write("<script type='text/javascript'>");
     			response.getWriter().write("alert('超时请重新登录');");
     			response.getWriter().write("window.top.location.href=('"+request.getContextPath()+"/manger/index');");
     			response.getWriter().write("</script>");
     			return false;
	 		}else if(!url.substring(request.getContextPath().length()+1,url.length()).equals("manger/main")){//跳过主页
			    if (permissionService == null) {
		            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext()); 
		            permissionService = (PermissionService) factory.getBean(PermissionServiceImpl.class); 
		        } 
			    Map<String, List<Map<String,Object>>> mapList=permissionService.getAmSysFuncListToMap(admin.getId());
					if(mapList.toString().indexOf(url.substring(request.getContextPath().length()+1,url.length())) ==-1) {
	 		         	response.setContentType("text/html;charset=UTF-8");
	 	     			response.getWriter().write("<script type='text/javascript'>");
	 	     			response.getWriter().write("alert('你没有权限');");
//	 	     			response.getWriter().write("window.top.location.href=('"+request.getContextPath()+"/manger/index');");
	 	     			response.getWriter().write("parent.location.reload();");
	 	     			response.getWriter().write("</script>");
	 	     			response.sendRedirect(request.getContextPath()+"/public/403");
	 	     			return false;
 					}
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
