package cn.ctx.service.mapper.primary;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.ctx.service.model.AmSysFunc;
import cn.ctx.service.model.AmSysRoleFuncKey;

public interface AmSysRoleFuncMapper {
    int deleteByPrimaryKey(AmSysRoleFuncKey key);

    int insert(AmSysRoleFuncKey record);

    int insertSelective(AmSysRoleFuncKey record);
    
    /**
    * @Title: getAmSysFuncList
    * @Description: TODO(功能菜单)
    * @param adminId 后台用户id
    * @author gyu
    * @date 2017年11月2日下午5:27:55
     */
    List<AmSysFunc> getAmSysFuncList(Integer adminId);
    
    
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
	boolean addSysRoleFunc(@Param("roleFuncList")List<AmSysRoleFuncKey> roleFuncList);
	
	/**
	* @Title: delRoleFuncByRoleId
	* @Description: TODO(删除角色对应的所有功能)
	* @param roleId
	* @author gyu
	* @date 2017年11月11日上午2:04:58
	 */
	boolean delRoleFuncByRoleId(Integer roleId);
	
	/**
	* @Title: getAmSysFuncListToMap
	* @Description: TODO(获取功能集合)
	* @param adminId
	* @author gyu
	* @date 2017年11月16日下午7:24:49
	 */
	List<Map<String,Object>> getAmSysFuncListToMap(Integer adminId);
}