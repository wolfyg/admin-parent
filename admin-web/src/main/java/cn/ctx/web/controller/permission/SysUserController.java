package cn.ctx.web.controller.permission;

/**    
* @Title: sysontroller.java  
* @Package cn.ctx.admin.controller.sys  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年11月10日 下午3:34:06  
* @version V1.0    
*/

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import cn.ctx.service.model.AmAdmin;
import cn.ctx.service.provider.AdminService;
import cn.ctx.service.provider.SysRoleService;
import cn.ctx.web.controller.BaseController;

/**  
* @ClassName: sysontroller  
* @Description: TODO(系统用户)  
* @author gyu
* @date 2017年11月10日 下午16:09:06  
*/
@Controller
public class SysUserController extends BaseController{
	@Autowired
	private AdminService adminService;
	@Autowired
	private SysRoleService roleService;
	

	/**
	* @Title: index
	* @Description: TODO(用户列表)
	* @param 
	* @author gyu
	* @date 2017年11月8日下午4:10:46
	 */
	@RequestMapping(value = "/sysuser/list", produces = "application/json; charset=utf-8")
	public String index(AmAdmin admin) {
		return "hplus/permission/sysuser/list";
	}
	

	
	@RequestMapping(value = "/sysuser/edit", produces = "application/json; charset=utf-8",method=RequestMethod.GET)
	public String from(AmAdmin admin) {
		if(admin.getId()!=null) {
			admin=adminService.getAmAdminById(admin.getId());
		}
		request.setAttribute("sysRole", roleService.getAllSysRole());
		request.setAttribute("admin", admin);
		return "hplus/permission/sysuser/from";
	}
	
	@RequestMapping(value = "/sysuser/edit", produces = "application/json; charset=utf-8",method=RequestMethod.POST)
	@ResponseBody
	public Object edit(AmAdmin admin) {
		return adminService.addUser(admin);
	}

	@RequestMapping(value = "/sysuser/getData", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object list(AmAdmin admin) {
		Page<AmAdmin> page = getPage();
		List<AmAdmin> list=adminService.getAmAdminList(admin,page.getOffset(),page.getLimit());
		page.setRecords(list);
		page.setTotal(adminService.getCount(admin));
		return pageMap(page);
	}

	@RequestMapping(value = "/sysuser/del", produces = "application/json; charset=utf-8")
	@ResponseBody	
	public Object del(String ids) {
		return adminService.batchDel(ids);
	}
	
}
