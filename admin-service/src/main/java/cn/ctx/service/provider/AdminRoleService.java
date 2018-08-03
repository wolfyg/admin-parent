/**    
* @Title: AdminRoleService.java  
* @Package cn.ctx.admin.service  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年11月10日 下午10:21:17  
* @version V1.0    
*/
package cn.ctx.service.provider;

import cn.ctx.service.model.AmAdminRole;

/**  
* @ClassName: AdminRoleService  
* @Description: TODO(管理员角色)  
* @author gyu
* @date 2017年11月10日 下午10:21:17  
*    
*/
public interface AdminRoleService {

	/**
	* @Title: addAdminRoole
	* @Description: TODO(添加更新管理员角色)
	* @param AmAdminRole
	* @author gyu
	* @date 2017年11月10日下午10:22:18
	 */
	boolean addAdminRoole(AmAdminRole adminRole);
}
