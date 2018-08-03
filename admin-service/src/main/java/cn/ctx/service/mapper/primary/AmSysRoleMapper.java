package cn.ctx.service.mapper.primary;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ctx.service.model.AmSysRole;

public interface AmSysRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AmSysRole record);

    int insertSelective(AmSysRole record);

    AmSysRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AmSysRole record);

    int updateByPrimaryKey(AmSysRole record);
    
    /**
    * @Title: getAllSysRole
    * @Description: TODO(所有角色)
    * @author gyu
    * @date 2017年11月10日下午10:33:40
     */
    public List<AmSysRole> getAllSysRole();

	/**
	* @Title: getCount
	* @Description: TODO(记录)
	* @param AmSysRole
	* @author gyu
	* @date 2017年11月10日上午11:02:44
	 */
	public int getCount(@Param("sysRole")AmSysRole sysRole);
	
	/**
	* @Title: getAmSysRoleList
	* @Description: TODO(列表数据)
	* @param AmSysRole
	* @param page
	* @param pageSize
	* @author gyu
	* @date 2017年11月10日上午11:02:21
	 */
	public List<AmSysRole> getAmSysRoleList(@Param("sysRole")AmSysRole sysRole,@Param("page")int page,@Param("pageSize")int pageSize);
	
	/**
	* @Title: getAmSysRoleById
	* @Description: TODO(根据id获取角色)
	* @param id
	* @author gyu
	* @date 2017年11月10日下午12:50:14
	 */
	public AmSysRole getAmSysRoleById(Integer id);

	/**
	* @Title: addSysRole
	* @Description: TODO(添加系统角色)
	* @param AmSysRole
	* @author gyu
	* @date 2017年11月10日下午10:18:40
	 */
	boolean addSysRole(AmSysRole sysRole);
	
	/**
	* @Title: delMenu
	* @Description: TODO(删除角色)
	* @param ids
	* @author gyu
	* @date 2017年11月10日下午12:53:14
	 */
	public boolean batchDel(@Param("ids")List<String> ids);
	
	
	/**
	* @Title: isExistRole
	* @Description: TODO(改角色是否被使用)
	* @param roleId
	* @author gyu
	* @date 2017年11月13日下午2:28:55
	 */
	public int isUseRole(Integer roleId);
}