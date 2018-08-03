/**    
* @Title: SysRoleFuncService.java  
* @Package cn.ctx.admin.service  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年11月11日 上午1:58:35  
* @version V1.0    
*/
package cn.ctx.service.provider;

import java.util.List;

import cn.ctx.service.model.AmSysRoleFuncKey;

/**  
* @ClassName: SysRoleFuncService  
* @Description: TODO(角色功能接口)  
* @author gyu
* @date 2017年11月11日 上午1:58:35  
*    
*/
public interface SysRoleFuncService {

	/**
	* @Title: getSysRoleFuncByRoleId
	* @Description: TODO(根据角色id获取功能列表)
	* @param roleId 角色id
	* @author gyu
	* @date 2017年11月11日上午2:00:15
	 */
	List<AmSysRoleFuncKey> getSysRoleFuncByRoleId(Integer roleId);
	
	/**
	* @Title: addSysRoleFunc
	* @Description: TODO(添加角色功能)
	* @param AmSysRoleFuncKey
	* @author gyu
	* @date 2017年11月11日上午2:02:09
	 */
	boolean addSysRoleFunc(AmSysRoleFuncKey roleFunc);
}
