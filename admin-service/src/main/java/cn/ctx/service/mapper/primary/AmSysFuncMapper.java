package cn.ctx.service.mapper.primary;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ctx.service.model.AmSysFunc;

public interface AmSysFuncMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AmSysFunc record);

    int insertSelective(AmSysFunc record);

    AmSysFunc selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AmSysFunc record);

    int updateByPrimaryKey(AmSysFunc record);
    
    

	/**
	* @Title: getCount
	* @Description: TODO(记录)
	* @param AmSysFunc
	* @author gyu
	* @date 2017年11月10日上午11:02:44
	 */
	public int getCount(@Param("func")AmSysFunc func);
	
	/**
	* @Title: getAmSysFuncList
	* @Description: TODO(列表数据)
	* @param AmSysFunc
	* @param page
	* @param pageSize
	* @author gyu
	* @date 2017年11月10日上午11:02:21
	 */
	public List<AmSysFunc> getAmSysFuncList(@Param("func")AmSysFunc func,@Param("page")int page,@Param("pageSize")int pageSize);
	
	/**
	* @Title: addMenu
	* @Description: TODO(添加功能)
	* @param AmSysFunc
	* @author gyu
	* @date 2017年11月10日上午11:02:05
	 */
	public boolean addFunc(AmSysFunc menu);
	
	/**
	* @Title: getAmSysFuncById
	* @Description: TODO(根据id获取功能)
	* @param id
	* @author gyu
	* @date 2017年11月10日下午12:50:14
	 */
	public AmSysFunc getAmSysFuncById(Integer id);
	
	/**
	* @Title: batchDel
	* @Description: TODO(删除功能)
	* @param ids
	* @author gyu
	* @date 2017年11月10日下午12:53:14
	 */
	public boolean batchDel(@Param("ids")List<String> ids);

	/**
	* @Title: getAllAmSysFunc
	* @Description: TODO(获取所有功能)
	* @author gyu
	* @date 2017年11月11日上午1:31:32
	 */
	public List<AmSysFunc> getAllAmSysFunc();

	/**
	* @Title: getAmSysFuncByMenuId
	* @Description: TODO(根据菜单Id获取对应菜单功能)
	* @param menuId 
	* @author gyu
	* @date 2017年11月16日下午2:20:12
	 */
	public List<AmSysFunc> getAmSysFuncByMenuId(Integer menuId);
}