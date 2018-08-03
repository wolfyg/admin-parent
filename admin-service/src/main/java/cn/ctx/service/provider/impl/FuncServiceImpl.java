/**    
* @Title: FunServiceImpl.java  
* @Package cn.ctx.admin.service.impl  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年11月10日 下午3:35:40  
* @version V1.0    
*/
package cn.ctx.service.provider.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ctx.common.base.ResultBean;
import cn.ctx.common.framework.util.Code;
import cn.ctx.common.framework.util.RedisUtil;
import cn.ctx.service.mapper.primary.AmSysFuncMapper;
import cn.ctx.service.model.AmSysFunc;
import cn.ctx.service.provider.FuncService;

/**  
* @ClassName: FunServiceImpl  
* @Description: TODO(系统功能逻辑)  
* @author gyu
* @date 2017年11月10日 下午3:35:40  
*    
*/
@Service
public class FuncServiceImpl extends RedisUtil implements FuncService{

	@Autowired
	private AmSysFuncMapper funcMapper;
	
	@Override
	public int getCount(AmSysFunc func) {
		return funcMapper.getCount(func);
	}

	@Override
	public List<AmSysFunc> getAmSysFuncList(AmSysFunc menu,int page,int pageSize) {
		return funcMapper.getAmSysFuncList(menu,page,pageSize);
	}

	@Override
	public ResultBean addFunc(AmSysFunc menu) {
		ResultBean result=new ResultBean();
		try {
			boolean flag=funcMapper.addFunc(menu);
			if(flag) {
				result.setResult(Code.SUCCESS);
	 			result.setIcon(Code.ICON_SUCCESS);
				result.setMsg("编辑成功");
				//更新权限
				cleanPermission();
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
	public AmSysFunc getAmSysFuncById(Integer id) {
		return funcMapper.getAmSysFuncById(id);
	}

	@Override
	public ResultBean batchDel(String ids) {
		ResultBean result=new ResultBean();
		try {
			boolean flag=funcMapper.batchDel(Arrays.asList(ids.split(",")));
			if(flag) {
				result.setResult(Code.SUCCESS);
	 			result.setIcon(Code.ICON_SUCCESS);
				result.setMsg("删除成功");
				//更新权限
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
	public List<AmSysFunc> getAllAmSysFunc() {
		return funcMapper.getAllAmSysFunc();
	}

	@Override
	public List<AmSysFunc> getAmSysFuncByMenuId(Integer menuId) {
		return funcMapper.getAmSysFuncByMenuId(menuId);
	}

}
