/**    
* @Title: HplusController.java  
* @Package cn.ctx.admin.controller  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年11月3日 下午1:55:45  
* @version V1.0    
*/
package cn.ctx.web.controller.permission;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ctx.service.model.AmAdmin;
import cn.ctx.service.provider.PermissionService;
import cn.ctx.web.controller.BaseController;

/**  
* @ClassName: HplusController  
* @Description: TODO(登录相关)  
* @author gyu
* @date 2017年11月3日 下午1:55:45  
*    
*/
@Controller
public class LoginController extends BaseController{
	@Autowired
	private PermissionService permissionService;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpSession session;
	
	@RequestMapping(value = "/index", produces = "application/json; charset=utf-8")
	public String index(AmAdmin admin) {
		return "hplus/login";
	}

	@RequestMapping(value = "/login", produces = "application/json; charset=utf-8", method=RequestMethod.POST)
	@ResponseBody
	public Object login(AmAdmin admin) {
		return permissionService.login(admin.getUsername(), admin.getPassword(),admin.getvCode());
	}
	

//	@RequestMapping(value = "/main", produces = "application/json; charset=utf-8")
//	public String ztree() {
//		AmAdmin admin=(AmAdmin)session.getAttribute("admin");
//		request.setAttribute("menu",permissionService.getNodes(admin.getId()));
//		request.setAttribute("admin", admin);
//		return "hplus/index";
//	}
	
	@RequestMapping(value = "/main", produces = "application/json; charset=utf-8")
	public String ztree() {
		AmAdmin admin=(AmAdmin)session.getAttribute("admin");
		request.setAttribute("menuList",permissionService.getAllMenuToMap(admin.getId()));
		request.setAttribute("menuFunc", permissionService.getAmSysFuncListToMap(admin.getId()));
		return "hplus/index";
	}

	@RequestMapping(value = "/homepage", produces = "application/json; charset=utf-8")
	public String Homepage() {
		return "redirect:http://www.zi-han.net/theme/hplus/basic_gallery.html";
	}
	

	@RequestMapping(value = "/loginout", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object loginout() {
		return permissionService.loginOut();
	}

	@RequestMapping(value = "/a", produces = "application/json; charset=utf-8")
	public String a() {
		return "redirect:http://www.baidu.com";
	}
	
	
	
}
