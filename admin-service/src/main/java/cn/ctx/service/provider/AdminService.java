/**    
* @Title: AdminService.java  
* @Package cn.ctx.admin.service  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年10月31日 下午3:27:53  
* @version V1.0    
*/
package cn.ctx.service.provider;

import java.util.List;

import cn.ctx.common.base.ResultBean;
import cn.ctx.service.model.AmAdmin;

/**  
* @ClassName: AdminService  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author gyu
* @date 2017年10月31日 下午3:27:53  
*    
*/
public interface AdminService {

	boolean insertAmAdmin(AmAdmin record);
	
	boolean delAmAdmin(int id);
	
	boolean updateAmAdmin(AmAdmin record);
	
	AmAdmin getAmAdmin(Integer id);
	
	List<AmAdmin> getAmAdminList();
	

	/**
	* @Title: getCount
	* @Description: TODO(记录)
	* @param AmAdmin
	* @author gyu
	* @date 2017年11月10日上午11:02:44
	 */
	public int getCount(AmAdmin func);
	
	/**
	* @Title: getAmAdminList
	* @Description: TODO(列表数据)
	* @param AmAdmin
	* @param page
	* @param pageSize
	* @author gyu
	* @date 2017年11月10日上午11:02:21
	 */
	public List<AmAdmin> getAmAdminList(AmAdmin menu,int page,int pageSize);
	
	/**
	* @Title: addMenu
	* @Description: TODO(添加用户)
	* @param AmAdmin
	* @author gyu
	* @date 2017年11月10日上午11:02:05
	 */
	public ResultBean addUser(AmAdmin menu);
	
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
	public ResultBean batchDel(String ids);
	
	
	/**
	* @Title: isExistUser
	* @Description: TODO(是否存在用户)
	* @param username
	* @author gyu
	* @date 2017年11月11日下午4:40:17
	 */
	public Boolean isExistUser(String username);
}
