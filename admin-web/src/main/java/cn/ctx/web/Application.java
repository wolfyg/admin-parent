package cn.ctx.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import cn.ctx.common.base.BaseConfig;

/**
 * 打war包
 * 1.禁用SpringBoot自带Tomcat
  <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <!-- 移除嵌入式tomcat插件--> 
            <!-- <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-tomcat</artifactId>
                </exclusion>
            </exclusions> -->
  </dependency>
 * 2.<packaging>jar</packaging> 改为 <packaging>war</packaging>
 * 
 * 3.加servlet
        <dependency>
		    <groupId>javax.servlet</groupId>
		    <artifactId>javax.servlet-api</artifactId>
		    <version>3.1.0</version>
		    <scope>provided</scope>
		</dependency>
 * 3.启动类 继承 SpringBootServletInitializer
 	@SpringBootApplication
	public class SpringBootStartApplication extends SpringBootServletInitializer {
		@Override
		protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringBootStartApplication.class);
		}
	}
 * 
 */

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
@EnableCaching// 开启缓存
@EnableTransactionManagement//开启事物
@MapperScan(basePackages= {"cn.ctx.service.mapper*"})
@ComponentScan(basePackages = {"cn.ctx"})
public class Application extends  SpringBootServletInitializer /**WebMvcConfigurerAdapter**/  {
	
    @Override  
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {  
        return application.sources(Application.class);  
    } 
	
    @RequestMapping("/")
    String home() {
        return "redirect:manger/index";
    }
    
//    /**
//    * @Title: containerCustomizer
//    * @Description: TODO(设置session过期时间)
//    * @author gyu
//    * @date 2017年10月15日上午12:56:21
//     */
//    @Bean
//    public EmbeddedServletContainerCustomizer containerCustomizer(){
//           return new EmbeddedServletContainerCustomizer() {
//               @Override
//               public void customize(ConfigurableEmbeddedServletContainer container) {
//                    container.setSessionTimeout(CacheConstants.USERNAME_TTL);//单位为S 此处session 过期时间为30分钟
//              }
//        };
//    }
}
