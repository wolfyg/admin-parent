/**
 * 
 */
package cn.ctx.api.service;

import cn.ctx.api.model.WxUser;

/**
 * @author lenovo
 *
 */
public interface IWxUserService {

	/**
	 * 保存存在即更新微信用户
	 * @param user
	 * @return
	 */
	boolean insertUpdateWxUser(WxUser user);

	public WxUser selectByPrimaryKey(String id);
}
