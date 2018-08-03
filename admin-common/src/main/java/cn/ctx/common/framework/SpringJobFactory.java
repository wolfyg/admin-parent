/**    
* @Title: SpringJobFactory.java  
* @Package cn.ctx.admin.conf  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年11月14日 上午11:20:18  
* @version V1.0    
*/
package cn.ctx.common.framework;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**  
* @ClassName: SpringJobFactory  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author gyu
* @date 2017年11月14日 上午11:20:18  
*    
*/
@Component
public class SpringJobFactory extends AdaptableJobFactory {

  @Autowired
  private AutowireCapableBeanFactory capableBeanFactory;

  @Override
  protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
    Object jobInstance = super.createJobInstance(bundle);
    capableBeanFactory.autowireBean(jobInstance);
    return jobInstance;
  }
}
