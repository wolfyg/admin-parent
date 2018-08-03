/**    
* @Title: PermissionVerifyTempl.java  
* @Package cn.ctx.web.controller  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2018年5月8日 上午10:30:40  
* @version V1.0    
*/
package cn.ctx.web.conf.directive;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.ctx.service.model.AmAdmin;
import cn.ctx.service.provider.PermissionService;
import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @ClassName: PermissionVerifyTempl
 * @Description: TODO(权限判断标签模板)
 * @author gyu
 * @date 2018年5月8日 上午10:30:40
 * 
 */
@Component
public class PermissionVerifyDirective implements TemplateDirectiveModel {

	@Autowired
	private HttpSession session;
	@Autowired
	private PermissionService permissionService;

	@Override
	public void execute(Environment environment, Map map, TemplateModel[] templateModels,
			TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
		boolean page = false;
		if (map.containsKey("url") && map.get("url") != null) {
			AmAdmin admin = (AmAdmin) session.getAttribute("admin");
			Map<String, List<Map<String, Object>>> mapList = permissionService.getAmSysFuncListToMap(admin.getId());
			//检查当前用户是否具备一定的功能权限
			if (mapList.toString().indexOf(map.get("url").toString()) != -1) {
				page = true;
				DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
				// 设置变量
				environment.setVariable("data", builder.build().wrap(page));
				templateDirectiveBody.render(environment.getOut());
			}
		}
	}

}
