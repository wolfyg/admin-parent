package cn.ctx.service.mapper.slave1shiro;

import java.util.List;
import java.util.Map;

public interface UserInfoMapper {
	/**
	* @Title: selectByOddUserId
	* @Description: TODO(从库测试)
	* @author gyu
	* @date 2017年12月25日下午5:28:23
	 */
	List<Map<String,Object>> selectByOddUserId();
	
	int addSlaveData(String emoji);
}