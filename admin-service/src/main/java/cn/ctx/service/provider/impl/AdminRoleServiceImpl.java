/**    
* @Title: AdminRoleServiceImpl.java  
* @Package cn.ctx.admin.service.impl  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年11月10日 下午10:23:05  
* @version V1.0    
*/
package cn.ctx.service.provider.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ctx.service.mapper.primary.AmAdminRoleMapper;
import cn.ctx.service.model.AmAdminRole;
import cn.ctx.service.provider.AdminRoleService;

/**  
* @ClassName: AdminRoleServiceImpl  
* @Description: TODO(管理员角色逻辑)  
* @author gyu
* @date 2017年11月10日 下午10:23:05  
*    
*/
@Service
public class AdminRoleServiceImpl implements AdminRoleService{

	@Autowired
	private AmAdminRoleMapper adminRoleMapper;
	
	@Override
	public boolean addAdminRoole(AmAdminRole adminRole) {
		return adminRoleMapper.addAdminRoole(adminRole);
	}

}
