/**    
* @Title: ActiveMQConfiguration.java  
* @Package cn.ctx.admin.conf  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年12月13日 下午3:00:50  
* @version V1.0    
*/
package cn.ctx.common.framework;

import javax.jms.Queue;
import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

/**
 * @ClassName: ActiveMQConfiguration
 * @Description: TODO(MQ配置)
 * @author gyu
 * @date 2017年12月13日 下午3:00:50
 */
@Configuration
@EnableJms
public class ActiveMQConfiguration {

	/**
	 * 定义点对点队列
	 * @return
	 */
	@Bean
	public Queue queue() {
		return new ActiveMQQueue("sample.queue");

	}

	/**
	 * 定义一个主题
	 * @return
	 */
	@Bean
	public Topic topic() {
		return new ActiveMQTopic("sample.topic");
	}

}
