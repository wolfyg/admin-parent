/**    
* @Title: SysRoleServiceImpl.java  
* @Package cn.ctx.admin.service.impl  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年11月10日 下午10:32:05  
* @version V1.0    
*/
package cn.ctx.service.provider.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.ctx.common.base.ResultBean;
import cn.ctx.common.framework.util.Code;
import cn.ctx.common.framework.util.RedisUtil;
import cn.ctx.service.mapper.primary.AmSysRoleFuncMapper;
import cn.ctx.service.mapper.primary.AmSysRoleMapper;
import cn.ctx.service.model.AmSysRole;
import cn.ctx.service.model.AmSysRoleFuncKey;
import cn.ctx.service.provider.SysRoleService;

/**  
* @ClassName: SysRoleServiceImpl  
* @Description: TODO(系统角色逻辑)  
* @author gyu
* @date 2017年11月10日 下午10:32:05  
*    
*/
@Service
public class SysRoleServiceImpl extends RedisUtil implements SysRoleService{

	@Autowired
	private AmSysRoleMapper sysRoleMapper;
	@Autowired
	private AmSysRoleFuncMapper sysRoleFuncMapper;
	
	@Override
	public List<AmSysRole> getAllSysRole() {
		return sysRoleMapper.getAllSysRole();
	}

	@Override
	public ResultBean addSysRole(AmSysRole sysRole) {
		ResultBean result=new ResultBean();
		try {
			if(StringUtils.isEmpty(sysRole.getFuncIds())) {
				result.setResult(Code.ERROR);
	 			result.setIcon(Code.ICON_FAIL);
				result.setMsg("请勾选权限");
			}else {
				sysRoleMapper.addSysRole(sysRole);
				String funcIdArr[]=sysRole.getFuncIds().split(",");
				AmSysRoleFuncKey sysRoleFunc=null;
				List<AmSysRoleFuncKey> sysRoleFuncList=new ArrayList<AmSysRoleFuncKey>();;
				for(String funcId:funcIdArr) {
					sysRoleFunc=new AmSysRoleFuncKey();
					sysRoleFunc.setRoleId(sysRole.getId());
					sysRoleFunc.setFuncId(Integer.parseInt(funcId));
					sysRoleFuncList.add(sysRoleFunc);
				}
				sysRoleFuncMapper.delRoleFuncByRoleId(sysRole.getId());
				sysRoleFuncMapper.addSysRoleFunc(sysRoleFuncList);
				result.setResult(Code.SUCCESS);
	 			result.setIcon(Code.ICON_SUCCESS);
				result.setMsg("编辑成功");
			}
			//更新权限
			cleanPermission();
		}catch (Exception e) {
			e.printStackTrace();
			result.setResult(Code.NETWORK_ERROR);
 			result.setIcon(Code.ICON_FAIL);
			result.setMsg("网络异常");
		}
		
		return result;
	}

	@Override
	public int getCount(AmSysRole sysRole) {
		return sysRoleMapper.getCount(sysRole);
	}

	@Override
	public List<AmSysRole> getAmSysRoleList(AmSysRole sysRole, int page, int pageSize) {
		return sysRoleMapper.getAmSysRoleList(sysRole, page, pageSize);
	}

	@Override
	public AmSysRole getAmSysRoleById(Integer id) {
		return sysRoleMapper.getAmSysRoleById(id);
	}

	@Override
	public ResultBean batchDel(String ids) {
		ResultBean result=new ResultBean();
		try {
			String idArr[]=ids.split(",");
			for(String id:idArr) {
				AmSysRole sysRole=sysRoleMapper.getAmSysRoleById(Integer.parseInt(id));
				if(sysRoleMapper.isUseRole(Integer.parseInt(id))!=0) {
					if(sysRole.getIsSys()==1) {
						result.setMsg("超级管理员不能删除");
					}else {
						result.setMsg("编号为:"+id+"的角色有关联账户不能删除");
					}
					result.setResult(Code.ERROR);
		 			result.setIcon(Code.ICON_FAIL);
					return result;
				}
			}
			boolean flag=sysRoleMapper.batchDel(Arrays.asList(idArr));
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

}
