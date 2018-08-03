/**    
* @Title: DicServiceImpl.java  
* @Package cn.ctx.service.provider.impl  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2018年5月7日 下午12:51:34  
* @version V1.0    
*/
package cn.ctx.service.provider.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ctx.common.base.ResultBean;
import cn.ctx.common.framework.util.Code;
import cn.ctx.common.framework.util.Constants;
import cn.ctx.service.mapper.primary.AmDicMapper;
import cn.ctx.service.model.AmDic;
import cn.ctx.service.provider.DicService;

/**  
* @ClassName: DicServiceImpl  
* @Description: TODO(字典相关实现)  
* @author gyu
* @date 2018年5月7日 下午12:51:34  
*    
*/
@Service
public class DicServiceImpl implements DicService {

	@Autowired
	private AmDicMapper dicMapper;
	
	@Override
	public List<Map<String,Object>> getData(AmDic dic) {

		if(dic.getId() == null ) dic.setId(Constants.DIC_ROOT);
		AmDic condition = new AmDic();
		condition.setPid(dic.getId());
		List<AmDic> list = dicMapper.getData(condition);
		List<Map<String,Object>> mapLists = new ArrayList<Map<String,Object>>();
		for(AmDic r : list){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", r.getId().toString());
			map.put("pid", r.getPid().toString());
			map.put("name", r.getName());
			//默认展开树
			condition.setPid(r.getId());
			List<AmDic> childs = dicMapper.getData(condition);
			if(childs.size() > 0) {
			    map.put( "isParent", "true");
			} else {
				map.put( "isParent", "false");
			}
			mapLists.add(map);
		}
		return mapLists;
	}

	@Override
	public List<AmDic> getTableData(AmDic dic, int page, int pageSize) {
		return dicMapper.getTableData(dic, page, pageSize);
	}

	@Override
	public int getCount(AmDic dic) {
		return dicMapper.getCount(dic);
	}

	@Override
	public ResultBean addDic(AmDic dic) {
		ResultBean result=new ResultBean();
		if(null != dic.getState() && dic.getState()==1) {
			dic.setPid(dic.getId());
			dic.setId(null);
			if(dicMapper.insertSelective(dic)>0) {
				result.setResult(Code.SUCCESS);
	 			result.setIcon(Code.ICON_SUCCESS);
				result.setMsg("添加成功");
			}else {
				result.setResult(Code.ERROR);
	 			result.setIcon(Code.ICON_FAIL);
				result.setMsg("编辑失败");
			}
		}else {
			if(dicMapper.updateByPrimaryKeySelective(dic)>0) {
				result.setResult(Code.SUCCESS);
	 			result.setIcon(Code.ICON_SUCCESS);
				result.setMsg("修改成功");
			}else {
				result.setResult(Code.ERROR);
	 			result.setIcon(Code.ICON_FAIL);
				result.setMsg("修改失败");
			}
		}
		return result;
	}

	@Override
	public AmDic getAmDicById(Integer id) {
		return dicMapper.selectByPrimaryKey(id);
	}

	@Override
	public ResultBean batchDelDic(String ids) {
		ResultBean result=new ResultBean();
		String idArr[]=ids.split(",");
		for(String id:idArr) {
			if(id.equals("0")) {
				result.setResult(Code.ERROR);
	 			result.setIcon(Code.ICON_FAIL);
				result.setMsg("根节点不能删除");
				return result;
			}
		}
		if(dicMapper.batchDel(Arrays.asList(idArr))) {
			result.setResult(Code.SUCCESS);
 			result.setIcon(Code.ICON_SUCCESS);
			result.setMsg("删除成功");
		}else {
			result.setResult(Code.ERROR);
 			result.setIcon(Code.ICON_FAIL);
			result.setMsg("删除失败");
		}
		return result;
	}

}
