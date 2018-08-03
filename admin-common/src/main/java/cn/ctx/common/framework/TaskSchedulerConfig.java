/**    
* @Title: TaskSchedulerConfig.java  
* @Package cn.ctx.admin.conf  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年11月13日 下午6:33:42  
* @version V1.0    
*/
package cn.ctx.common.framework;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**  
* @ClassName: TaskSchedulerConfig  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author gyu
* @date 2017年11月13日 下午6:33:42  
*    
*/
@Component
public class TaskSchedulerConfig {

    @Autowired
    private SpringJobFactory springJobFactory;
    
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
       return new ThreadPoolTaskScheduler();
    }
    

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
      SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
      schedulerFactoryBean.setJobFactory(springJobFactory);
      return schedulerFactoryBean;
    }

    @Bean
    public Scheduler scheduler() {
      return schedulerFactoryBean().getScheduler();
    }
}
