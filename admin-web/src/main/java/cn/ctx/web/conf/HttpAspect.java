/**    
* @Title: HttpAspect.java  
* @Package cn.ctx.admin.core.aop  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年11月3日 下午11:21:11  
* @version V1.0    
*/
package cn.ctx.web.conf;

import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**  
* @ClassName: HttpAspect  
* @Description: TODO(HTTP切面)  
* @author gyu
* @date 2017年11月3日 下午11:21:11  
*    
*/
@Aspect
@Component
public class HttpAspect {
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpAspect.class);
    
    @Pointcut(value = "execution(* cn.ctx.admin.controller..*(..))")
    public void controller(){}
 
    public static Long CURTIME=0L;
    
    @Before("controller()")
    public void doBefore(JoinPoint joinPoint){
    	CURTIME=System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
 
        //URL
        LOGGER.info("URL={}",request.getRequestURL());
 
        //Method
        LOGGER.info("Method={}",request.getMethod());
 
        //IP
        LOGGER.info("IP={}",request.getRemoteAddr());
 
        //Class.Method
        LOGGER.info("CLass.Method={}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName()+"()");

        //params
        Enumeration enu=request.getParameterNames();  
        while(enu.hasMoreElements()){  
	        String paraName=(String)enu.nextElement();  
	        LOGGER.info("Parames={}",paraName+": "+request.getParameter(paraName)); 
        }  
        
        //Args
        LOGGER.info("Args={}",joinPoint.getArgs());
 
        //...其余操作
 
    }
 
    @After("controller()")
    public void doAfter(){
        LOGGER.info("HttpAspect doAfter Running : "+(new Date().getTime()-CURTIME)+"/MS\r\n");
    }
}
