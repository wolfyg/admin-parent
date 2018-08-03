/**
 * 
 */
package cn.ctx.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页
 * @author yg
 *
 */
@Controller
public class IndexController extends BaseController{

	@RequestMapping("/index")
	public Object index() {
		return "view/xzdz";
	}
}
