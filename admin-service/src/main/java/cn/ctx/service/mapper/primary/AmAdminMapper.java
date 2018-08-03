package cn.ctx.service.mapper.primary;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ctx.service.model.AmAdmin;

public interface AmAdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AmAdmin record);

    int insertSelective(AmAdmin record);

    AmAdmin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AmAdmin record);

    int updateByPrimaryKey(AmAdmin record);
    
    /**
    * @Title: isExistAdminUser
    * @Description: TODO(是否存在管理员)
    * @param name
    * @param password
    * @author gyu
    * @date 2017年11月7日下午4:10:37
     */
    public AmAdmin isExistAdminUser(@Param("name")String name,@Param("password")String password);
    
    /**
	* @Title: getCount
	* @Description: TODO(记录)
	* @param AmAdmin
	* @author gyu
	* @date 2017年11月10日上午11:02:44
	 */
	public int getCount(@Param("admin")AmAdmin admin);
	
	/**
	* @Title: getAmAdminList
	* @Description: TODO(列表数据)
	* @param AmAdmin
	* @param page
	* @param pageSize
	* @author gyu
	* @date 2017年11月10日上午11:02:21
	 */
	public List<AmAdmin> getAmAdminList(@Param("admin")AmAdmin admin,@Param("page")int page,@Param("pageSize")int pageSize);
	
	/**
	* @Title: addMenu
	* @Description: TODO(添加用户)
	* @param AmAdmin
	* @author gyu
	* @date 2017年11月10日上午11:02:05
	 */
	public boolean addUser(AmAdmin menu);
	
	/**
	* @Title: getAmAdminById
	* @Description: TODO(根据id获取用户)
	* @param id
	* @author gyu
	* @date 2017年11月10日下午12:50:14
	 */
	public AmAdmin getAmAdminById(Integer id);
	
	/**
	* @Title: delMenu
	* @Description: TODO(删除用户)
	* @param ids
	* @author gyu
	* @date 2017年11月10日下午12:53:14
	 */
	public boolean batchDel(@Param("ids")List<String> ids);

	/**
	* @Title: isExistUser
	* @Description: TODO(是否存在用户)
	* @param username
	* @author gyu
	* @date 2017年11月11日下午4:40:17
	 */
	public int isExistUser(String username);
	
}