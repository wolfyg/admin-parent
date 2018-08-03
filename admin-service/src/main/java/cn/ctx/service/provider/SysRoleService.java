/**    
* @Title: SysRoleService.java  
* @Package cn.ctx.admin.service  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年11月10日 下午10:17:02  
* @version V1.0    
*/
package cn.ctx.service.provider;

import java.util.List;

import cn.ctx.common.base.ResultBean;
import cn.ctx.service.model.AmSysRole;

/**  
* @ClassName: SysRoleService  
* @Description: TODO(系统角色接口)  
* @author gyu
* @date 2017年11月10日 下午10:17:02  
*    
*/
public interface SysRoleService {

	/**
	* @Title: getAllSysRole
	* @Description: TODO(所有系统角色)
	* @author gyu
	* @date 2017年11月10日下午10:17:46
	 */
	List<AmSysRole> getAllSysRole();
	
	/**
	* @Title: addSysRole
	* @Description: TODO(添加系统角色)
	* @param AmSysRole
	* @author gyu
	* @date 2017年11月10日下午10:18:40
	 */
	ResultBean addSysRole(AmSysRole role);
	

	/**
	* @Title: getCount
	* @Description: TODO(记录)
	* @param AmSysRole
	* @author gyu
	* @date 2017年11月10日上午11:02:44
	 */
	public int getCount(AmSysRole func);
	
	/**
	* @Title: getAmSysRoleList
	* @Description: TODO(列表数据)
	* @param AmSysRole
	* @param page
	* @param pageSize
	* @author gyu
	* @date 2017年11月10日上午11:02:21
	 */
	public List<AmSysRole> getAmSysRoleList(AmSysRole menu,int page,int pageSize);
	
	/**
	* @Title: getAmSysRoleById
	* @Description: TODO(根据id获取角色)
	* @param id
	* @author gyu
	* @date 2017年11月10日下午12:50:14
	 */
	public AmSysRole getAmSysRoleById(Integer id);
	
	/**
	* @Title: delMenu
	* @Description: TODO(删除角色)
	* @param ids
	* @author gyu
	* @date 2017年11月10日下午12:53:14
	 */
	public ResultBean batchDel(String ids);
	
}
