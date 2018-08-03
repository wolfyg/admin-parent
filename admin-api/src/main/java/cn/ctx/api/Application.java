package cn.ctx.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import cn.ctx.api.base.BaseConfig;

/**
 * 
* @ClassName: Application.java
* @Description: TODO(程序启动) 
* @author gyu
* @date 2016年11月3日 上午9:19:17
 */
@Controller
@EnableWebMvc
@SpringBootApplication
//@EnableScheduling  //告诉Spring创建一个task executor，如果我们没有这个标注，所有@Scheduled标注都不会执行
@EnableConfigurationProperties({BaseConfig.class})  
public class Application extends  SpringBootServletInitializer {
	@Autowired
	protected HttpServletRequest request;
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping("/")
    String home() {
    	//returnUrl 设置微信回调默认页面
		return "redirect:auth?returnUrl=index";
    }
    
    @Override  
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {  
        return application.sources(Application.class);  
    } 
    
    /**
    * @Title: containerCustomizer
    * @Description: TODO(设置session过期时间)
    * @author gyu
    * @date 2017年10月15日上午12:56:21
     */
 /*   @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
           return new EmbeddedServletContainerCustomizer() {
               @Override
               public void customize(ConfigurableEmbeddedServletContainer container) {
                    container.setSessionTimeout(1800);//单位为S 此处session 过期时间为30分钟
              }
        };
    }*/
}
