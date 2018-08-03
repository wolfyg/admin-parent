/**    
* @Title: TestController.java  
* @Package cn.ctx.admin.controller  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年10月31日 下午3:41:47  
* @version V1.0    
*/
package cn.ctx.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Queue;
import javax.jms.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import cn.ctx.common.framework.ServiceException;
import cn.ctx.common.framework.cache.memcache.CacheClient;
import cn.ctx.common.framework.cache.memcache.CacheFactory;
import cn.ctx.common.framework.token.TokenException;
import cn.ctx.common.framework.token.TokenUtil;
import cn.ctx.service.model.AmAdmin;
import cn.ctx.service.model.AmSysFunc;
import cn.ctx.service.model.AmSysMenu;
import cn.ctx.service.provider.AdminService;
import cn.ctx.service.provider.FuncService;
import cn.ctx.service.provider.JMSService;
import cn.ctx.service.provider.MenuService;
import cn.ctx.service.provider.SlaveService;

/**  
* @ClassName: AdminController  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author gyu
* @date 2017年10月31日 下午3:41:47  
*    
*/
@Controller
public class TestController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private FuncService funcService;
	
	@Autowired
	private JMSService jmsService;
	
	@Autowired
	private Queue queue;
	
	@Autowired
	private Topic topic;
	
	@Autowired
	private SlaveService slaveService;
	
	@RequestMapping(value = "/admin", produces = "application/json; charset=utf-8")
	public Object getAmAdmin(int id,int type) {
		if(type==1) {//增
			AmAdmin record= new AmAdmin();
			record.setLastLoginip("192.168.23.126");
			record.setLocked(1);
			record.setNickName("測試");
			return adminService.insertAmAdmin(record);
		}else if(type==2) {//刪
			return adminService.delAmAdmin(id);
		}else if(type==3) {//改
			AmAdmin record= new AmAdmin();
			record.setId(id);
			record.setLastLoginip("192.168.23.126");
			record.setLocked(1);
			record.setNickName("測試");
			return adminService.updateAmAdmin(record);
		}else if(type==4) {//查單個
			return adminService.getAmAdmin(id);
		}else if(type==5) {//查所有
			return adminService.getAmAdminList();
		}else {
			CacheClient client = CacheFactory.getInstance();
			String key = String.format("test");
			String EventCache = client.get(key);
			Map<String,Object> data=new HashMap<String,Object>();
			data.put("name", "这是一个缓存测试");
			String json="";
			if (EventCache == null) {
				json=new Gson().toJson(data);
				System.out.println("存入缓存:"+json);
				client.set(key, json ,100);
			} else {
				json=client.get(key);
				System.out.println("读取缓存:"+json);
			}
			return json;
		}
		
	}
	
	
	@RequestMapping(value = "/index", produces = "application/json; charset=utf-8")
	public Object index() {
		return "ztree/index";
	}
	
	@RequestMapping(value = "/ztree", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object ztree() {
        List<Map<String,Object>> tree = new ArrayList<Map<String,Object>>();  
		List<AmSysMenu> list=menuService.getAllMenu();
		for(AmSysMenu menu:list) {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("id", menu.getId());
			map.put("pId", menu.getParentId());
			map.put("name", menu.getName());
			map.put("open", Boolean.FALSE);
			tree.add(map);
			List<AmSysFunc> listFunc=funcService.getAmSysFuncByMenuId(menu.getId());
			for(AmSysFunc func:listFunc) {
				map = new LinkedHashMap<String,Object>();
				map.put("pId", func.getMenuId());
				map.put("name", func.getName());
				map.put("file", func.getUrl());
				map.put("isShow", func.getIsShow());
				tree.add(map);
			}
		}
		return tree;
	}
	
	
	@RequestMapping(value = "/layerpage", produces = "application/json; charset=utf-8")
	public Object layerpage() {
		return "ztree/layerpage";
	}
	
	@RequestMapping(value = "/jms", produces = "application/json; charset=utf-8")
	@ResponseBody
	public void jms() {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("name", "点消息队列");
		jmsService.sendSMS(queue, map);
		map.put("name", "广播消息队列");
		jmsService.sendSMS(topic, map);
	}
	
	@RequestMapping(value = "/slaveData", produces = "application/json; charset=utf-8")
	public @ResponseBody Object slaveData(String emoji) {
		return slaveService.addSlaveData(emoji);
	}
	
	@RequestMapping(value = "/openlayers", produces = "application/json; charset=utf-8")
	public Object openlayers() {
		return "openlayers/map";
	}
	
	@RequestMapping(value = "/amq", produces = "application/json; charset=utf-8")
	public Object amq() {
		return "amq/amq";
	}
	
	@RequestMapping(value = "/netty", produces = "application/json; charset=utf-8")
	public Object netty() {
		return "netty/websocket";
	}

	@RequestMapping(value = "/token", produces = "application/json; charset=utf-8")
	public @ResponseBody Object token() throws TokenException, ServiceException {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("token", TokenUtil.createToken("232323232"));
		map.put("User", TokenUtil.parseToken((String)map.get("token")));
		return map;
	}
}
