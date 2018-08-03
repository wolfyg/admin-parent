/**    
* @Title: PermissionController.java  
* @Package cn.ctx.admin.controller  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年11月8日 下午3:21:42  
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

import cn.ctx.service.model.AmSysMenu;
import cn.ctx.service.provider.MenuService;
import cn.ctx.web.controller.BaseController;

/**  
* @ClassName: PermissionController  
* @Description: TODO(菜单管理)  
* @author gyu
* @date 2017年11月8日 下午3:21:42  
*    
*/
@Controller
public class PermissionController extends BaseController{

	@Autowired
	private MenuService menuService;
	
	/**
	* @Title: index
	* @Description: TODO(菜单列表)
	* @param 
	* @author gyu
	* @date 2017年11月8日下午4:10:46
	 */
	@RequestMapping(value = "/permission/list", produces = "application/json; charset=utf-8")
	public String index(AmSysMenu menu) {
		return "hplus/permission/permiss/list";
	}
	
	@RequestMapping(value = "/permission/edit", produces = "application/json; charset=utf-8",method=RequestMethod.GET)
	public String from(AmSysMenu menu) {
		if(menu.getId()!=null) {
			menu=menuService.getAmSysMenuById(menu.getId());
		}
		request.setAttribute("menuList", menuService.getAllMenuToMap());
		request.setAttribute("menu", menu);
		return "hplus/permission/permiss/from";
	}
	
	@RequestMapping(value = "/permission/edit", produces = "application/json; charset=utf-8",method=RequestMethod.POST)
	@ResponseBody
	public Object edit(AmSysMenu menu) {
		return menuService.addMenu(menu);
	}

	@RequestMapping(value = "/permission/getData", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object list(AmSysMenu menu) {
		Page<AmSysMenu> page = getPage();
		List<AmSysMenu> list=menuService.getAmSysMenuList(menu,page.getOffset(),page.getLimit());
		page.setRecords(list);
		page.setTotal(menuService.getCount(menu));
		return pageMap(page);
	}

	@RequestMapping(value = "/permission/del", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object del(String ids) {
		return menuService.batchDel(ids);
	}
	
	
	
	
}
