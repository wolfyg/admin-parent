package cn.ctx.service.quartz;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import cn.ctx.common.base.CacheConstants;
import cn.ctx.common.framework.util.Code;
import cn.ctx.common.framework.util.RedisUtil;
import cn.ctx.service.model.AmAdmin;
import cn.ctx.service.provider.AdminService;

/**
* @ClassName: TaskTest  
* @Description: TODO(任务测试类)  
* @author gyu
* @date 2017年11月16日 上午10:46:37  
*
 */
@Component 
public class TaskTest implements ApplicationContextAware{
	public final Logger log = LoggerFactory.getLogger(TaskTest.class);
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private RedisUtil redisUtil;
	// 上下文
	private static ApplicationContext applicationContext;
	
	public AdminService getAdminServiceInstance() {
		if(adminService==null) {
			adminService = applicationContext.getBean(AdminService.class);
		}
		return adminService;
	}
	public RedisUtil getRedisUtilInstance() {
		if(redisUtil==null) {
			redisUtil = (RedisUtil) applicationContext.getBean("redisUtil");
		}
		return redisUtil;
	}
	
	public void run() {
		for (int i = 0; i < 5; i++) {
			try {
				if(!isStop("run")) {
					return;
				}
				Thread.sleep(1000);
				log.info(i+"方法......................................" + (new Date()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void run1() {
		getAdminServiceInstance();
		getRedisUtilInstance();
		for (int i = 0; i < 10; i++) {
			try {
				if(!isStop("run1")) {
					return;
				}
				AmAdmin admin=adminService.getAmAdmin(1);
				System.out.println(admin.getUsername());
				Thread.sleep(5000);
				log.info(i+"调用数据库方法......................................" + (new Date()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	* @Title: isStop
	* @Description: TODO(任务是否停止)
	* @param method 方法名
	* @return false 停止 true 运行
	* @author gyu
	* @date 2017年11月16日上午10:50:56
	 */
	public boolean isStop(String method) {
		String taskStatus=redisUtil.get(String.format(CacheConstants.TASKSTATUS,method));
		if(taskStatus.equals(Code.TASK_OFF)) {
			System.out.println("run1任务停止状态为:"+taskStatus);
			log.info("方法为["+method+"]的任务停止状态为:"+taskStatus);
			return false;
		}
		return true;
	}
	
	/**
	 * 初始化上下文
	 */
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		String strArr[]=applicationContext.getBeanDefinitionNames();
		System.out.println("打印所有注册的Bean--开始");
		for(String str:strArr) {
			System.out.println(str);
		}
		System.out.println("打印所有注册的Bean--结束");
		this.applicationContext = applicationContext;
	}
}
