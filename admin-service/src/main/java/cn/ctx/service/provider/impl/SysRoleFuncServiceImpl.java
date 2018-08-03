/**    
* @Title: SysRoleFuncServiceImpl.java  
* @Package cn.ctx.admin.service.impl  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年11月11日 上午2:02:48  
* @version V1.0    
*/
package cn.ctx.service.provider.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ctx.common.framework.util.RedisUtil;
import cn.ctx.service.mapper.primary.AmSysRoleFuncMapper;
import cn.ctx.service.model.AmSysRoleFuncKey;
import cn.ctx.service.provider.SysRoleFuncService;

/**  
* @ClassName: SysRoleFuncServiceImpl  
* @Description: TODO(角色功能逻辑)  
* @author gyu
* @date 2017年11月11日 上午2:02:48  
*/
@Service
public class SysRoleFuncServiceImpl extends RedisUtil implements SysRoleFuncService{

	@Autowired
	private AmSysRoleFuncMapper roleFuncMapper;
	
	@Override
	public List<AmSysRoleFuncKey> getSysRoleFuncByRoleId(Integer roleId) {
		return roleFuncMapper.getSysRoleFuncByRoleId(roleId);
	}

	@Override
	public boolean addSysRoleFunc(AmSysRoleFuncKey roleFunc) {
		roleFuncMapper.delRoleFuncByRoleId(roleFunc.getRoleId());
		List<AmSysRoleFuncKey> listRoleFunc=new ArrayList<AmSysRoleFuncKey>();
		AmSysRoleFuncKey sysRoleFunc = null;
		String funcIds[]=roleFunc.getFuncIds().split(",");
		for(String id:funcIds) {
			sysRoleFunc=new AmSysRoleFuncKey();
			sysRoleFunc.setRoleId(roleFunc.getRoleId());
			sysRoleFunc.setFuncId(Integer.parseInt(id));
			listRoleFunc.add(sysRoleFunc);
		}
		//删除权限缓存
		cleanPermission();
		return roleFuncMapper.addSysRoleFunc(listRoleFunc);
	}

}
