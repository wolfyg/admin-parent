/**    
* @Title: AdminServiceImpl.java  
* @Package cn.ctx.admin.service.impl  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年10月31日 下午3:28:24  
* @version V1.0    
*/
package cn.ctx.service.provider.impl;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.ctx.common.base.CacheConstants;
import cn.ctx.common.base.ResultBean;
import cn.ctx.common.framework.util.Code;
import cn.ctx.common.framework.util.MD5;
import cn.ctx.common.framework.util.RedisUtil;
import cn.ctx.service.mapper.primary.AmAdminMapper;
import cn.ctx.service.mapper.primary.AmAdminRoleMapper;
import cn.ctx.service.mapper.primary.AmSysRoleMapper;
import cn.ctx.service.model.AmAdmin;
import cn.ctx.service.model.AmAdminRole;
import cn.ctx.service.model.AmSysRole;
import cn.ctx.service.provider.AdminService;

/**  
* @ClassName: AdminServiceImpl  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author gyu
* @date 2017年10月31日 下午3:28:24  
*    
*/
@Service
public class AdminServiceImpl extends RedisUtil implements AdminService{
	private static Logger logger=LoggerFactory.getLogger(AdminServiceImpl.class);
	@Autowired
	private AmAdminMapper adminMapper;
	@Autowired
	private AmAdminRoleMapper adminRoleMapper;
	@Autowired
	private AmSysRoleMapper sysRoleMapper;
	@Autowired
	private HttpSession session;
	
	
	/**
	 * @Cacheable(value = Constants.CACHE_NAME)  查询所有，不要key,默认以方法名+参数值+内容 作为key  
	 * @Cacheable(value = Constants.CACHE_NAME, key="#id")  根据ID查询，ID 我们默认是唯一的	
	 * @CacheEvict(value = Constants.CACHE_NAME, key="#id")  通过ID删除 
	 * @CacheEvict(value = Constants.CACHE_NAME,allEntries=true) allEntries 表示调用之后，清空缓存，默认false,  
	 */
	
	@Override
	@Cacheable(value=CacheConstants.CACHE_NAME,key="'admin_'+#id")  
	public AmAdmin getAmAdmin(Integer id) {
		logger.info("getAmAdmin數據庫！！！");
		return adminMapper.selectByPrimaryKey(1);
	}

	@CacheEvict(value=CacheConstants.CACHE_NAME,allEntries=true)
	@Override
	public boolean insertAmAdmin(AmAdmin record) {
		logger.info("insertAmAdmin數據庫！！！");
		return adminMapper.insert(record)>0?true:false;
	}

	@CacheEvict(value=CacheConstants.CACHE_NAME,key="'admin_'+#record.id")
	@Override
	public boolean updateAmAdmin(AmAdmin record) {
		logger.info("updateAmAdmin數據庫！！！");
		return adminMapper.updateByPrimaryKey(record)!=-1;
	}

	@CacheEvict(value=CacheConstants.CACHE_NAME,allEntries=true)
	@Override
	public boolean delAmAdmin(int id) {
		logger.info("delAmAdmin數據庫！！！");
		return adminMapper.deleteByPrimaryKey(id)!=-1;
	}

	
	@Cacheable(value=CacheConstants.CACHE_NAME,key="'admin_'")
	@Override
	public List<AmAdmin> getAmAdminList() {
		logger.info("getAmAdminList數據庫！！！");
		return null;
	}

	
	@Override
	public int getCount(AmAdmin func) {
		return adminMapper.getCount(func);
	}

	@Override
	public List<AmAdmin> getAmAdminList(AmAdmin menu,int page,int pageSize) {
		return adminMapper.getAmAdminList(menu,page,pageSize);
	}

	@Override
	public ResultBean addUser(AmAdmin admin) {
		ResultBean result=new ResultBean();
		try {
			AmSysRole sysRole= sysRoleMapper.selectByPrimaryKey(admin.getRoleId());
			admin.setNickName(sysRole.getName());
			if(StringUtils.isNotEmpty(admin.getPassword())) {
				admin.setPassword(MD5.encry(admin.getUsername()+admin.getPassword()));
			}
			boolean flag=adminMapper.addUser(admin);
			AmAdminRole adminRole=new AmAdminRole();
			adminRole.setAdminId(admin.getId());
			adminRole.setRoleId(admin.getRoleId());
			adminRoleMapper.addAdminRoole(adminRole);
			if(flag) {
				//删除登录缓存
				cleanLogin(admin.getUsername());
				//删除权限缓存
				cleanPermission();
				result.setResult(Code.SUCCESS);
	 			result.setIcon(Code.ICON_SUCCESS);
				result.setMsg("编辑成功");
			}else {
				result.setResult(Code.ERROR);
	 			result.setIcon(Code.ICON_FAIL);
				result.setMsg("编辑失败");
			}
		}catch (Exception e) {
			e.printStackTrace();
			result.setResult(Code.NETWORK_ERROR);
 			result.setIcon(Code.ICON_FAIL);
			result.setMsg("网络异常");
		}
		return result;
	}

	@Override
	public AmAdmin getAmAdminById(Integer id) {
		return adminMapper.getAmAdminById(id);
	}

	@Override
	public ResultBean batchDel(String ids) {
		ResultBean result=new ResultBean();
		try {
			List<String> idList=Arrays.asList(ids.split(","));
			for(String id:idList) {
				//删除登录缓存
				cleanLogin(adminMapper.getAmAdminById(Integer.parseInt(id)).getUsername());
				AmAdmin admin=adminMapper.getAmAdminById(Integer.parseInt(id));
				if(admin.getId()==1) {//id为1的是定义为超级管理员
					result.setResult(Code.ERROR);
		 			result.setIcon(Code.ICON_FAIL);
					result.setMsg("超级管理员不能删除");
					return result;
				}
			}
			boolean flag=adminMapper.batchDel(idList);
			if(flag) {
				result.setResult(Code.SUCCESS);
	 			result.setIcon(Code.ICON_SUCCESS);
				result.setMsg("删除成功");
				//删除权限缓存
				cleanPermission();
			}else {
				result.setResult(Code.ERROR);
	 			result.setIcon(Code.ICON_FAIL);
				result.setMsg("删除失败");
			}
		}catch (Exception e) {
			e.printStackTrace();
			result.setResult(Code.NETWORK_ERROR);
 			result.setIcon(Code.ICON_FAIL);
			result.setMsg("网络异常");
		}
		return result;
	}

	@Override
	public Boolean isExistUser(String username) {
		return adminMapper.isExistUser(username)>0?Boolean.FALSE:Boolean.TRUE;
	}
}
