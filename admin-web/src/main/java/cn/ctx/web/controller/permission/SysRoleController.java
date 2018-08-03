/**    
* @Title: RoleController.java  
* @Package cn.ctx.admin.controller.permission  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年11月11日 上午12:40:23  
* @version V1.0    
*/
package cn.ctx.web.controller.permission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import cn.ctx.service.model.AmSysRole;
import cn.ctx.service.provider.FuncService;
import cn.ctx.service.provider.MenuService;
import cn.ctx.service.provider.SysRoleFuncService;
import cn.ctx.service.provider.SysRoleService;
import cn.ctx.web.controller.BaseController;

/**  
* @ClassName: RoleController  
* @Description: TODO(系统角色功能管理)  
* @author gyu
* @date 2017年11月11日 上午12:40:23  
*/
@Controller
public class SysRoleController extends BaseController{
	@Autowired
	private SysRoleService SysRoleService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private FuncService funcService;
	@Autowired
	private SysRoleFuncService sysRoleFuncService;

	/**
	* @Title: index
	* @Description: TODO(系统角色功能列表)
	* @param 
	* @author gyu
	* @date 2017年11月8日下午4:10:46
	 */
	@RequestMapping(value = "/sysrole/list", produces = "application/json; charset=utf-8")
	public String index(AmSysRole func) {
		return "hplus/permission/sysrole/list";
	}
	
	@RequestMapping(value = "/sysrole/edit", produces = "application/json; charset=utf-8",method=RequestMethod.GET)
	public String from(AmSysRole sysRole) {
		if(sysRole.getId()!=null) {
			request.setAttribute("myFunc",sysRoleFuncService.getSysRoleFuncByRoleId(sysRole.getId()));
			sysRole=SysRoleService.getAmSysRoleById(sysRole.getId());
		}
		request.setAttribute("menu", menuService.getAllMenu());
		request.setAttribute("func", funcService.getAllAmSysFunc());
		request.setAttribute("sysRole", sysRole);
		return "hplus/permission/sysrole/from";
	}
	
	@RequestMapping(value = "/sysrole/edit", produces = "application/json; charset=utf-8",method=RequestMethod.POST)
	@ResponseBody
	public Object edit(AmSysRole sysRole) {
		return SysRoleService.addSysRole(sysRole);
	}

	@RequestMapping(value = "/sysrole/getData", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object list(AmSysRole sysRole) {
		Page<AmSysRole> page = getPage();
		List<AmSysRole> list=SysRoleService.getAmSysRoleList(sysRole,page.getOffset(),page.getLimit());
		page.setRecords(list);
		page.setTotal(SysRoleService.getCount(sysRole));
		return pageMap(page);
	}

	@RequestMapping(value = "/sysrole/del", produces = "application/json; charset=utf-8")
	@ResponseBody	
	public Object del(String ids) {
		return SysRoleService.batchDel(ids);
	}
	
}
