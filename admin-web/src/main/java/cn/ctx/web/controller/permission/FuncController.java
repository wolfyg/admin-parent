package cn.ctx.web.controller.permission;

/**    
* @Title: FunController.java  
* @Package cn.ctx.admin.controller.func  
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

import cn.ctx.service.model.AmSysFunc;
import cn.ctx.service.provider.FuncService;
import cn.ctx.service.provider.MenuService;
import cn.ctx.web.controller.BaseController;

/**  
* @ClassName: FunController  
* @Description: TODO(系统功能)  
* @author gyu
* @date 2017年11月10日 下午3:34:06  
*/
@Controller
public class FuncController extends BaseController{
	@Autowired
	private FuncService funcService;
	@Autowired
	private MenuService menuService;
	

	/**
	* @Title: index
	* @Description: TODO(菜单列表)
	* @param 
	* @author gyu
	* @date 2017年11月8日下午4:10:46
	 */
	@RequestMapping(value = "/func/list", produces = "application/json; charset=utf-8")
	public String index(AmSysFunc func) {
		return "hplus/permission/func/list";
	}
	

	
	@RequestMapping(value = "/func/edit", produces = "application/json; charset=utf-8",method=RequestMethod.GET)
	public String from(AmSysFunc func) {
		if(func.getId()!=null) {
			func=funcService.getAmSysFuncById(func.getId());
		}
		request.setAttribute("menuList", menuService.getAllMenuToMap());
		request.setAttribute("func", func);
		return "hplus/permission/func/from";
	}
	
	@RequestMapping(value = "/func/edit", produces = "application/json; charset=utf-8",method=RequestMethod.POST)
	@ResponseBody
	public Object edit(AmSysFunc func) {
		return funcService.addFunc(func);
	}

	@RequestMapping(value = "/func/getData", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object list(AmSysFunc func) {
		Page<AmSysFunc> page = getPage();
		List<AmSysFunc> list=funcService.getAmSysFuncList(func,page.getOffset(),page.getLimit());
		page.setRecords(list);
		page.setTotal(funcService.getCount(func));
		return pageMap(page);
	}

	@RequestMapping(value = "/func/del", produces = "application/json; charset=utf-8")
	@ResponseBody	
	public Object del(String ids) {
		return funcService.batchDel(ids);
	}
	
}
