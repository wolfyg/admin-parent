/**    
* @Title: DicService.java  
* @Package cn.ctx.service.provider  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2018年5月7日 下午12:50:06  
* @version V1.0    
*/
package cn.ctx.service.provider;

import java.util.List;
import java.util.Map;

import cn.ctx.common.base.ResultBean;
import cn.ctx.service.model.AmDic;

/**  
* @ClassName: DicService  
* @Description: TODO(字典相關接口)  
* @author gyu
* @date 2018年5月7日 下午12:50:06  
*    
*/
public interface DicService {

	/**
	* @Title: getData
	* @Description: TODO(字典树)
	* @author gyu
	* @date 2018年5月7日下午12:51:01
	 */
	public List<Map<String,Object>> getData(AmDic dic);
	

	/**
	* @Title: getTableData
	* @Description: TODO(字典表格)
	* @param AmDic
	* @param page
	* @param pageSize
	* @author gyu
	* @date 2018年5月7日下午1:38:09
	 */
	public List<AmDic> getTableData(AmDic dic,int page,int pageSize);
	
	/**
	* @Title: getCount
	* @Description: TODO(数据大小)
	* @param AmDic
	* @author gyu
	* @date 2018年5月7日下午1:38:43
	 */
	public int getCount(AmDic menu);
	
	/**
	* @Title: addDic
	* @Description: TODO(新增字典)
	* @param dic
	* @author gyu
	* @date 2018年5月7日下午3:13:45
	 */
	public ResultBean addDic(AmDic dic);
	
	/**
	* @Title: getAmDicById
	* @Description: TODO(获取一条字典信息)
	* @param id
	* @author gyu
	* @date 2018年5月7日下午3:14:49
	 */
	public AmDic getAmDicById(Integer id);
	
	/**
	* @Title: batchDelDic
	* @Description: TODO(删除一条字典)
	* @param ids
	* @author gyu
	* @date 2018年5月7日下午3:16:09
	 */
	public ResultBean batchDelDic(String ids);
}
