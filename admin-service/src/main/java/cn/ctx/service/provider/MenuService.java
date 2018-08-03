/**    
* @Title: MenuService.java  
* @Package cn.ctx.admin.service  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年11月9日 上午11:49:43  
* @version V1.0    
*/
package cn.ctx.service.provider;

import java.util.LinkedHashMap;
import java.util.List;

import cn.ctx.common.base.ResultBean;
import cn.ctx.service.model.AmSysMenu;

/**  
* @ClassName: MenuService  
* @Description: TODO(菜单)  
* @author gyu
* @date 2017年11月9日 上午11:49:43  
*    
*/
public interface MenuService {

	/**
	* @Title: getCount
	* @Description: TODO(记录)
	* @param AmSysMenu
	* @author gyu
	* @date 2017年11月10日上午11:02:44
	 */
	public int getCount(AmSysMenu menu);
	
	/**
	* @Title: getAmSysMenuList
	* @Description: TODO(列表数据)
	* @param AmSysMenu
	* @param page
	* @param pageSize
	* @author gyu
	* @date 2017年11月10日上午11:02:21
	 */
	public List<AmSysMenu> getAmSysMenuList(AmSysMenu menu,int page,int pageSize);
	
	/**
	* @Title: addMenu
	* @Description: TODO(添加菜单)
	* @param AmSysMenu
	* @author gyu
	* @date 2017年11月10日上午11:02:05
	 */
	public ResultBean addMenu(AmSysMenu menu);
	
	/**
	* @Title: getAmSysMenuById
	* @Description: TODO(根据id获取菜单)
	* @param id
	* @author gyu
	* @date 2017年11月10日下午12:50:14
	 */
	public AmSysMenu getAmSysMenuById(Integer id);
	
	/**
	* @Title: delMenu
	* @Description: TODO(删除菜单)
	* @param ids
	* @author gyu
	* @date 2017年11月10日下午12:53:14
	 */
	public ResultBean batchDel(String ids);
	
	/**
	* @Title: getAllMenu
	* @Description: TODO(获取所有菜单)
	* @author gyu
	* @date 2017年11月10日下午4:09:46
	 */
	public List<AmSysMenu> getAllMenu();

	/**
	* @Title: getAllMenu
	* @Description: TODO(获取所有菜单)
	* @author gyu
	* @date 2017年11月10日下午4:09:46
	 */
	public List<LinkedHashMap<String,Object>> getAllMenuToMap();
}
