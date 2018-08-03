/**    
* @Title: SlaveService.java  
* @Package cn.ctx.admin.service  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年12月25日 下午5:30:57  
* @version V1.0    
*/
package cn.ctx.service.provider;

import java.util.List;
import java.util.Map;

/**  
* @ClassName: SlaveService  
* @Description: TODO(从库测试)  
* @author gyu
* @date 2017年12月25日 下午5:30:57  
*    
*/
public interface SlaveService {
	List<Map<String,Object>> getListData();
	
	Map<String,Object> addSlaveData(String emoji);
}
