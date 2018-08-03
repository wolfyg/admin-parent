package cn.ctx.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ctx.api.mapper.WxUserMapper;
import cn.ctx.api.model.WxUser;
import cn.ctx.api.service.IWxUserService;
import cn.ctx.api.util.Utils;

@Service
public class WxUserService implements IWxUserService {

	@Autowired
	private WxUserMapper userMapper;
	
	//请把try移到上层去，这里的示范是不对的哦
	@Override
	public boolean insertUpdateWxUser(WxUser user) {
		try {
			return userMapper.insertUpdateWxUser(user);
		}catch (Exception e) {
			Utils.getClass(this.getClass()).error("保存或更新微信用户失败:"+e.getMessage());
			return false;
		}
	}

	@Override
	public WxUser selectByPrimaryKey(String id) {
		WxUser wxUser = userMapper.selectByPrimaryKey(id);
		return wxUser;
	}

}
