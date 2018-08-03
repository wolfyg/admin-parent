package cn.ctx.service.mapper.primary;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ctx.service.model.AmSysMenu;

public interface AmSysMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AmSysMenu record);

    int insertSelective(AmSysMenu record);

    AmSysMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AmSysMenu record);

    int updateByPrimaryKey(AmSysMenu record);
    
    /**
    * @Title: getAmSysMenuList
    * @Description: TODO(获取登录者主菜单权限)
    * @param adminId 
    * @author gyu
    * @date 2017年11月2日下午5:28:32
     */
    List<AmSysMenu> getAmSysMenuList(Integer adminId);


    /**
    * @Title: getAmSysMenuList
    * @Description: TODO(获取登录者主菜单权限)
    * @param adminId 
    * @author gyu
    * @date 2017年11月2日下午5:28:32
     */
    List<LinkedHashMap<String,Object>> getAmSysParentMenuList(Integer adminId);
    
    /**
    * @Title: getAmSysMenuList
    * @Description: TODO(获取登录者子菜单权限)
    * @param adminId 
    * @author gyu
    * @date 2017年11月2日下午5:28:32
     */
    List<LinkedHashMap<String,Object>> getAmSysChildMenuList(Integer adminId);
    
    
    /**
    * @Title: getCount
    * @Description: TODO(总记录)
    * @param AmSysMenu
    * @author gyu
    * @date 2017年11月9日上午11:54:46
     */
    int getCount(@Param("menu")AmSysMenu menu);
    
    /**
    * @Title: getAmSysMenuList
    * @Description: TODO(菜单列表)
    * @param AmSysMenu 
    * @author gyu
    * @date 2017年11月9日上午11:55:04
     */
    List<AmSysMenu> getMenuList(@Param("menu")AmSysMenu menu,@Param("page")int page,@Param("pageSize")int pageSize);
    
	/**
	* @Title: addMenu
	* @Description: TODO(添加菜单)
	* @param AmSysMenu
	* @author gyu
	* @date 2017年11月10日上午11:02:05
	 */
	public boolean addMenu(AmSysMenu menu);
	
	/**
	* @Title: getAmSysMenuById
	* @Description: TODO(根据id获取菜单)
	* @param id
	* @author gyu
	* @date 2017年11月10日下午12:50:14
	 */
	public AmSysMenu getAmSysMenuById(Integer id);

	/**
	* @Title: delMenu
	* @Description: TODO(删除菜单)
	* @param ids
	* @author gyu
	* @date 2017年11月10日下午12:53:14
	 */
	public boolean batchDel(@Param("ids")List<String> ids);

	/**
	* @Title: getAllMenu
	* @Description: TODO(获取所有菜单)
	* @author gyu
	* @date 2017年11月10日下午4:09:46
	 */
	public List<AmSysMenu> getAllMenu();
	

	/**
	* @Title: getAllMenu
	* @Description: TODO(获取所有菜单)
	* @author gyu
	* @date 2017年11月10日下午4:09:46
	 */
	public List<LinkedHashMap<String,Object>> getAllMenuToMap();
}
