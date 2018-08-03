/**    
* @Title: MenuServiceImpl.java  
* @Package cn.ctx.admin.service.impl  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年11月9日 上午11:52:45  
* @version V1.0    
*/
package cn.ctx.service.provider.impl;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ctx.common.base.ResultBean;
import cn.ctx.common.framework.util.Code;
import cn.ctx.common.framework.util.NodeUtils;
import cn.ctx.common.framework.util.RedisUtil;
import cn.ctx.service.mapper.primary.AmSysMenuMapper;
import cn.ctx.service.model.AmSysMenu;
import cn.ctx.service.provider.MenuService;

/**  
* @ClassName: MenuServiceImpl  
* @Description: TODO(所有菜单实现类)  
* @author gyu
* @date 2017年11月9日 上午11:52:45  
*    
*/
@Service
public class MenuServiceImpl extends RedisUtil implements MenuService{

	@Autowired
	private AmSysMenuMapper menuMapper;
	
	@Override
	public int getCount(AmSysMenu menu) {
		return menuMapper.getCount(menu);
	}

	@Override
	public List<AmSysMenu> getAmSysMenuList(AmSysMenu menu,int page,int pageSize) {
		return menuMapper.getMenuList(menu,page,pageSize);
	}

	@Override
	public ResultBean addMenu(AmSysMenu menu) {
		ResultBean result=new ResultBean();
		try {
			boolean flag=menuMapper.addMenu(menu);
			if(flag) {
				result.setResult(Code.SUCCESS);
	 			result.setIcon(Code.ICON_SUCCESS);
				result.setMsg("编辑成功");
				//删除权限缓存
				cleanPermission();
			}else {
				result.setResult(Code.ERROR);
	 			result.setIcon(Code.ICON_FAIL);
				result.setMsg("编辑失败");
			}
		}catch (Exception e) {
			e.printStackTrace();
			result.setResult(Code.NETWORK_ERROR);
 			result.setIcon(Code.ICON_FAIL);
			result.setMsg("网络异常");
		}
		return result;
	}

	@Override
	public AmSysMenu getAmSysMenuById(Integer id) {
		return menuMapper.getAmSysMenuById(id);
	}

	@Override
	public ResultBean batchDel(String ids) {
		ResultBean result=new ResultBean();
		try {
			boolean flag=menuMapper.batchDel(Arrays.asList(ids.split(",")));
			if(flag) {
				result.setResult(Code.SUCCESS);
	 			result.setIcon(Code.ICON_SUCCESS);
				result.setMsg("删除成功");
				//删除权限缓存
				cleanPermission();
			}else {
				result.setResult(Code.ERROR);
	 			result.setIcon(Code.ICON_FAIL);
				result.setMsg("删除失败");
			}
		}catch (Exception e) {
			e.printStackTrace();
			result.setResult(Code.NETWORK_ERROR);
 			result.setIcon(Code.ICON_FAIL);
			result.setMsg("网络异常");
		}
		return result;
	}

	@Override
	public List<AmSysMenu> getAllMenu() {
		return menuMapper.getAllMenu();
	}

	@Override
	public List<LinkedHashMap<String, Object>> getAllMenuToMap() {
		NodeUtils node=new NodeUtils();
		return node.getSubs(menuMapper.getAllMenuToMap());
	}

}
