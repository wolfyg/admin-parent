/**    
* @Title: SlaveServiceImpl.java  
* @Package cn.ctx.admin.service.impl  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年12月25日 下午5:31:42  
* @version V1.0    
*/
package cn.ctx.service.provider.impl;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.ctx.service.mapper.slave1shiro.UserInfoMapper;
import cn.ctx.service.provider.SlaveService;

/**  
* @ClassName: SlaveServiceImpl  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author gyu
* @date 2017年12月25日 下午5:31:42  
*    
*/
@Service
public class SlaveServiceImpl implements SlaveService{

	@Autowired
	UserInfoMapper userInfoMapper;
	
	@Override
	public List<Map<String, Object>> getListData() {
		return userInfoMapper.selectByOddUserId();
	}

	//多数据源 value 指定数据源事物一定要写
	@Transactional(value="slaveOneTransactionManager")
	@Override
	public Map<String, Object> addSlaveData(String emoji) {
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			int i=userInfoMapper.addSlaveData(URLEncoder.encode(emoji,"utf-8"));
			if(i>0) {
				map.put("result","写入成功");
			}else {
				map.put("result","写入失败");
			}
		}catch (Exception e) {
			map.put("result","写入失败");
			//异常手动回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return map;
	}

}
