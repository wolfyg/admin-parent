package cn.ctx.service.mapper.primary;

import cn.ctx.service.model.AmAdminRole;

public interface AmAdminRoleMapper {
    int deleteByPrimaryKey(Integer adminid);

    int insert(AmAdminRole record);

    int insertSelective(AmAdminRole record);

    AmAdminRole selectByPrimaryKey(Integer adminid);

    int updateByPrimaryKeySelective(AmAdminRole record);

    int updateByPrimaryKey(AmAdminRole record);
    
    /**
    * @Title: addAdminRoole
    * @Description: TODO(添加更改管理角色)
    * @param AmAdminRole
    * @author gyu
    * @date 2017年11月10日下午10:25:34
     */
    boolean addAdminRoole(AmAdminRole adminRole);
}