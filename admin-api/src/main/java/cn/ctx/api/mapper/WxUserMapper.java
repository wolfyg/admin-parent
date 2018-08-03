package cn.ctx.api.mapper;

import cn.ctx.api.model.WxUser;

public interface WxUserMapper {
    int deleteByPrimaryKey(String openid);

    int insert(WxUser record);

    int insertSelective(WxUser record);

    WxUser selectByPrimaryKey(String openid);

    int updateByPrimaryKeySelective(WxUser record);

    int updateByPrimaryKey(WxUser record);

	/**
	 * 保存存在即更新微信用户
	 * @param user
	 * @return
	 */
	boolean insertUpdateWxUser(WxUser user);
}