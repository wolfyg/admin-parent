package cn.ctx.web.conf.directive;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;

/**
* @ClassName: FreemarkerConfig  
* @Description: TODO(freemarker自定义标签配置)  
* @author gyu
* @date 2018年5月8日 上午11:51:17  
*
 */
@Component
public class FreemarkerConfig {
	  @Autowired
	  private Configuration configuration;
	  @Autowired
	  private PermissionVerifyDirective userTopicDirective;

	  @PostConstruct
	  public void setSharedVariable() throws TemplateModelException {
	    configuration.setSharedVariable("permis", userTopicDirective);
	  }


}
