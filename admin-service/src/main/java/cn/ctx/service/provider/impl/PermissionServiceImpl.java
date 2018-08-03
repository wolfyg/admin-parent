/**    
* @Title: PermissionServiceImpl.java  
* @Package cn.ctx.admin.service.impl  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年11月2日 下午5:12:53  
* @version V1.0    
*/
package cn.ctx.service.provider.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import cn.ctx.common.base.CacheConstants;
import cn.ctx.common.base.ResultBean;
import cn.ctx.common.framework.util.Code;
import cn.ctx.common.framework.util.MD5;
import cn.ctx.common.framework.util.NodeUtils;
import cn.ctx.common.framework.util.RedisUtil;
import cn.ctx.common.framework.util.StringUtil;
import cn.ctx.common.framework.util.VerifyCodeUtils;
import cn.ctx.service.mapper.primary.AmAdminMapper;
import cn.ctx.service.mapper.primary.AmSysMenuMapper;
import cn.ctx.service.mapper.primary.AmSysRoleFuncMapper;
import cn.ctx.service.model.AmAdmin;
import cn.ctx.service.model.AmSysFunc;
import cn.ctx.service.model.AmSysMenu;
import cn.ctx.service.provider.PermissionService;

/**  
* @ClassName: PermissionServiceImpl  
* @Description: TODO(登录,权限菜单实现)  
* @author gyu
* @date 2017年11月2日 下午5:12:53  
*    
*/
@Service
public class PermissionServiceImpl extends RedisUtil implements PermissionService{
	private static Logger log=LoggerFactory.getLogger(PermissionServiceImpl.class);

	@Autowired
	private AmSysMenuMapper menuMapper;

	@Autowired
	private AmSysRoleFuncMapper funcMapper;
	
	@Autowired
	private AmAdminMapper adminMapper;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public List<AmSysMenu> getAmSysMenuList(Integer adminId) {
		
		return menuMapper.getAmSysMenuList(adminId);
	}

	@Override
	public List<AmSysFunc> getAmSysFuncList(Integer adminId) {
		return funcMapper.getAmSysFuncList(adminId);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Map<String, Object>> getNodes(Integer id) {
		String key = String.format(CacheConstants.KEY_PERMISSIONBYID,id);
		String Cache = get(key);
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		if (Cache == null || Cache.trim().equals("")) {
			List<Map<String,Object>> mapLists = new ArrayList<Map<String,Object>>();
			List<AmSysMenu> menuList=getAmSysMenuList(id);
			List<AmSysFunc> funcList=getAmSysFuncList(id);
			Map<String,Object> map = null;
			for(AmSysMenu menu:menuList) {
				map = new LinkedHashMap<String,Object>();
				map.put("id", menu.getId());
				map.put("pId", menu.getParentId());
				map.put("name", menu.getName());
				map.put("icon", menu.getIcon());
				map.put("open", Boolean.FALSE);
				map.put("isShow", menu.getIsShow());
				mapLists.add(map);
				for(AmSysFunc func:funcList) {
					if(menu.getId()==func.getMenuId()) {
						map = new LinkedHashMap<String,Object>();
						map.put("pId", func.getMenuId());
						map.put("name", func.getName());
						map.put("file", func.getUrl());
						map.put("isShow", func.getIsShow());
						mapLists.add(map);
					}
				}
			}
			if(mapLists.size()>0) {
				set(key, writeGson(mapLists), CacheConstants.PERMISSION_TTL);
				list=mapLists;
			}
		}else {
			list = (List) readList(Cache, List.class);
		}
		return list;
	}

	@Override
	public ResultBean login(String name, String password,String vCode) {
		ResultBean result=new ResultBean();
		System.out.println(session.getAttribute(VerifyCodeUtils.V_CODE));
		try {
			if(StringUtil.isNull(name) && StringUtil.isNull(password)) {
				result.setResult(Code.ERROR);
				result.setMsg("账号密码不能为空");
	 			result.setIcon(Code.ICON_FAIL);
			}else if(!session.getAttribute(VerifyCodeUtils.V_CODE).equals(vCode)){
				result.setResult(Code.ERROR);
				result.setMsg("验证码错误");
	 			result.setIcon(Code.ICON_FAIL);
			}else {
				String key = String.format(CacheConstants.KEY_USERNAME,name);
				String Cache = get(key);
				AmAdmin admin=null;
				if (Cache == null || Cache.trim().equals("")) {
					//保险机制
					synchronized (this) {
						if (StringUtil.isNotNull(Cache)) {
							admin = (AmAdmin) readEntrty(Cache, AmAdmin.class);
							result.setResult(Code.SUCCESS);
				 			result.setIcon(Code.ICON_SUCCESS);
							result.setData(admin);
							result.setMsg("登录成功");
							return result;
						}
						admin=adminMapper.isExistAdminUser(name, MD5.encry(name+password));
						if(StringUtil.isNotNull(admin)) {
							set(key, writeGson(admin),CacheConstants.USERNAME_TTL);
						}
					}
				} else {
					admin = (AmAdmin) readEntrty(Cache, AmAdmin.class);
					set(key, writeGson(admin),CacheConstants.USERNAME_TTL);
				}

				if(StringUtil.isNull(admin)) {
					result.setResult(Code.ERROR);
		 			result.setIcon(Code.ICON_FAIL);
					result.setMsg("用户不存在");
				}else {
					if(admin.getLocked()!=1) {
						result.setResult(Code.ERROR);
			 			result.setIcon(Code.ICON_FAIL);
						result.setMsg("此账号已锁定,请联系管理员");
					}else {
						result.setResult(Code.SUCCESS);
			 			result.setIcon(Code.ICON_SUCCESS);
						result.setData(admin);
						result.setMsg("登录成功");
						session.setAttribute("admin", admin);
					}
				}
			}
		}catch (Exception e) {
			log.error("登录异常",e.getMessage());
			result.setResult(Code.NETWORK_ERROR);
 			result.setIcon(Code.ICON_FAIL);
			result.setMsg("登录异常请稍后");
		}
		return result;
	}

	@Override
	public ResultBean loginOut() {
		ResultBean result=new ResultBean();
		try {
			AmAdmin admin=(AmAdmin)session.getAttribute("admin");
			//删除登录缓存
			cleanLogin(admin.getUsername());
			//删除菜单缓存
			cleanPermission();
			//删除session
			session.removeAttribute("admin");
			result.setResult(Code.SUCCESS);
			result.setMsg("退出登录成功");
 			result.setIcon(Code.ICON_SUCCESS);
		}catch (Exception e) {
			log.error("退出登录异常",e.getMessage());
			result.setResult(Code.NETWORK_ERROR);
 			result.setIcon(Code.ICON_FAIL);
			result.setMsg("退出登录异常请稍后");
		}
		return result;
	}

	@Override
	public List<LinkedHashMap<String, Object>> getAllMenuToMap(Integer adminId) {
		List<LinkedHashMap<String, Object>> list=new ArrayList<LinkedHashMap<String,Object>>();
		List<LinkedHashMap<String,Object>> parentMenu=menuMapper.getAmSysParentMenuList(adminId);
		List<LinkedHashMap<String,Object>> childMenu=menuMapper.getAmSysChildMenuList(adminId);
		list.addAll(parentMenu);
		list.addAll(childMenu);
        NodeUtils tnu = new NodeUtils();
        return tnu.getSubs(list);
	}

	@Override
	public Map<String, List<Map<String,Object>>> getAmSysFuncListToMap(Integer adminId) {
        Map<String,List<Map<String,Object>>> map = new LinkedHashMap<String, List<Map<String,Object>>>();
        List<Map<String,Object>> lr = funcMapper.getAmSysFuncListToMap(adminId);
        List<String> actions = new ArrayList<String>();
        for (Map<String,Object> r : lr) {
            actions.add(r.get("url").toString());
            String menu_id = r.get("menuId").toString();
            List<Map<String,Object>> temp = (List<Map<String, Object>>) map.get(menu_id);
            if(null == temp) {
                temp = new ArrayList<Map<String,Object>>();
                temp.add(r);
                map.put(menu_id, temp);
            }else{
                temp.add(r);
            }
        }
        System.out.println(new Gson().toJson(map));
        return map;
	}

}
