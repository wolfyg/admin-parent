/**    
* @Title: PermissionService.java  
* @Package cn.ctx.admin.service  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年11月2日 下午5:12:39  
* @version V1.0    
*/
package cn.ctx.service.provider;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.ctx.common.base.ResultBean;
import cn.ctx.service.model.AmSysFunc;
import cn.ctx.service.model.AmSysMenu;

/**  
* @ClassName: PermissionService  
* @Description: TODO(权限菜单接口)  
* @author gyu
* @date 2017年11月2日 下午5:12:39  
*    
*/
public interface PermissionService {

	/**
	* @Title: getAmSysMenuList
	* @Description: TODO(获取登录者主菜单权限)
	* @param adminId 
	* @author gyu
	* @date 2017年11月7日下午4:06:56
	 */
	List<AmSysMenu> getAmSysMenuList(Integer adminId);
	
	/**
	* @Title: getAmSysFuncList
	* @Description: TODO(功能菜单)
	* @param adminId
	* @author gyu
	* @date 2017年11月7日下午4:07:25
	 */
	List<AmSysFunc> getAmSysFuncList(Integer adminId);
	
	/**
	* @Title: getNodes
	* @Description: TODO(权限树)
	* @param adminId
	* @author gyu
	* @date 2017年11月7日下午4:07:47
	 */
	List<Map<String,Object>> getNodes(Integer adminId);
	
	/**
	* @Title: isExistAdminUser
	* @Description: TODO(是否存在管理员)
	* @param name
	* @param password
	* @param vCode
	* @author gyu
	* @date 2017年11月7日下午4:08:42
	 */
	public ResultBean login(String name,String password,String vCode);
	
	/**
	* @Title: loginOut
	* @Description: TODO(退出登录)
	* @param 
	* @author gyu
	* @date 2017年11月8日上午10:20:51
	 */
	public ResultBean loginOut();
	
	
	public List<LinkedHashMap<String,Object>> getAllMenuToMap(Integer adminId);
	
	
	public Map<String, List<Map<String,Object>>> getAmSysFuncListToMap(Integer adminId);
	
}
