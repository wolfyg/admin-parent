/**
 * 
 */
package cn.ctx.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;

/**
 * 公共页面
 * @author yg
 *
 */
@RequestMapping("/wx")
@Controller
public class PublicPageController {
	
	/**
	 * 地图页面
	 * @param returnUrl
	 * @param json
	 * @return
	 */
	@RequestMapping("/gdMap")
	public ModelAndView gdMap(String returnUrl,JsonObject json) {
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("view/xzdz");
		modelView.addObject("addr", json);
		modelView.addObject("returnUrl",returnUrl);
		return modelView;
	}

	@RequestMapping("/return")
	public ModelAndView returnUrl(String returnUrl,String json) {
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName(returnUrl);
		modelView.addObject("addr", json);
		return modelView;
	}
	
}
