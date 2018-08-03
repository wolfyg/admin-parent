/**    
* @Title: FunService.java  
* @Package cn.ctx.admin.service  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年11月10日 下午3:35:10  
* @version V1.0    
*/
package cn.ctx.service.provider;

import java.util.List;

import cn.ctx.common.base.ResultBean;
import cn.ctx.service.model.AmSysFunc;

/**  
* @ClassName: FunService  
* @Description: TODO(系统功能接口)  
* @author gyu
* @date 2017年11月10日 下午3:35:10  
*    
*/
public interface FuncService {

	/**
	* @Title: getCount
	* @Description: TODO(记录)
	* @param AmSysFunc
	* @author gyu
	* @date 2017年11月10日上午11:02:44
	 */
	public int getCount(AmSysFunc func);
	
	/**
	* @Title: getAmSysFuncList
	* @Description: TODO(列表数据)
	* @param AmSysFunc
	* @param page
	* @param pageSize
	* @author gyu
	* @date 2017年11月10日上午11:02:21
	 */
	public List<AmSysFunc> getAmSysFuncList(AmSysFunc menu,int page,int pageSize);
	
	/**
	* @Title: addMenu
	* @Description: TODO(添加功能)
	* @param AmSysFunc
	* @author gyu
	* @date 2017年11月10日上午11:02:05
	 */
	public ResultBean addFunc(AmSysFunc menu);
	
	/**
	* @Title: getAmSysFuncById
	* @Description: TODO(根据id获取功能)
	* @param id
	* @author gyu
	* @date 2017年11月10日下午12:50:14
	 */
	public AmSysFunc getAmSysFuncById(Integer id);
	
	/**
	* @Title: delMenu
	* @Description: TODO(删除功能)
	* @param ids
	* @author gyu
	* @date 2017年11月10日下午12:53:14
	 */
	public ResultBean batchDel(String ids);
	
	/**
	* @Title: getAllAmSysFunc
	* @Description: TODO(获取所有功能)
	* @author gyu
	* @date 2017年11月11日上午1:31:32
	 */
	public List<AmSysFunc> getAllAmSysFunc();
	
	/**
	* @Title: getAmSysFuncByMenuId
	* @Description: TODO(根据菜单Id获取对应菜单功能)
	* @param menuId 
	* @author gyu
	* @date 2017年11月16日下午2:20:12
	 */
	public List<AmSysFunc> getAmSysFuncByMenuId(Integer menuId);
}
