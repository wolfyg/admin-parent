package cn.ctx.service.mapper.primary;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ctx.service.model.AmDic;

public interface AmDicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AmDic record);

    int insertSelective(AmDic record);

    AmDic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AmDic record);

    int updateByPrimaryKey(AmDic record);
    
	public List<AmDic> getData(AmDic dic);
	
	/**
	* @Title: getTableData
	* @Description: TODO(字典表格)
	* @param AmDic
	* @param page
	* @param pageSize
	* @author gyu
	* @date 2018年5月7日下午1:38:09
	 */
	public List<AmDic> getTableData(@Param("dic")AmDic dic,@Param("page")int page,@Param("pageSize")int pageSize);
	
	/**
	* @Title: getCount
	* @Description: TODO(数据大小)
	* @param AmDic
	* @author gyu
	* @date 2018年5月7日下午1:38:43
	 */
	public int getCount(@Param("dic")AmDic dic);
	
	/**
	* @Title: batchDel
	* @Description: TODO(批量删除)
	* @param ids
	* @author gyu
	* @date 2018年5月7日下午4:40:09
	 */
	public boolean batchDel(@Param("list")List<String> ids);
}