/**    
* @Title: DicController.java  
* @Package cn.ctx.web.controller.sysconf  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2018年5月7日 下午12:09:54  
* @version V1.0    
*/
package cn.ctx.web.controller.syssite;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import cn.ctx.service.model.AmDic;
import cn.ctx.service.provider.DicService;
import cn.ctx.web.controller.BaseController;

/**  
* @ClassName: DicController  
* @Description: TODO(数据字典相关)  
* @author gyu
* @date 2018年5月7日 下午12:09:54  
*    
*/
@Controller
public class DicController extends BaseController{
	@Autowired
	private DicService dicService;

	@RequestMapping(value = "/dic/list", produces = "application/json; charset=utf-8")
	public String index(AmDic dic) {
		return "hplus/dic/list";
	}
	

	
	@RequestMapping(value = "/dic/edit", produces = "application/json; charset=utf-8",method=RequestMethod.GET)
	public String from(AmDic dic,Model model) {
		AmDic dataDic=new AmDic();
		dataDic=dicService.getAmDicById(dic.getId());
		if(null != dic.getState() && dic.getState()==2) {
			dataDic.setState(dic.getState());
			model.addAttribute("dic", dataDic);
		}else {
			AmDic addDic=new AmDic();
			addDic.setId(dataDic.getId());
			addDic.setState(dic.getState());
			model.addAttribute("dic", addDic);
		}
		return "hplus/dic/from";
	}
	
	@RequestMapping(value = "/dic/edit", produces = "application/json; charset=utf-8",method=RequestMethod.POST)
	@ResponseBody
	public Object edit(AmDic dic) {
		try {
			return dicService.addDic(dic);
		}catch (Exception e) {
			e.printStackTrace();
			return NETWORK_ERROR();
		}
	}

	@RequestMapping(value = "/dic/getData", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object list(AmDic dic) {
		return dicService.getData(dic);
	}
	
	@RequestMapping(value = "/dic/getTableData", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object tableData(AmDic dic) {
		Page<AmDic> page = getPage();
		List<AmDic> list=dicService.getTableData(dic,page.getOffset(),page.getLimit());
		page.setRecords(list);
		page.setTotal(dicService.getCount(dic));
		return pageMap(page);
	}
	

	@RequestMapping(value = "/dic/batchDel", produces = "application/json; charset=utf-8")
	@ResponseBody	
	public Object batchDel(String ids) {
		try {
			return dicService.batchDelDic(ids);
		}catch(Exception e) {
			e.printStackTrace();
			return NETWORK_ERROR();
		}
	}
	
	
}
