/**    
* @Title: JmsConfiguration.java  
* @Package cn.ctx.admin.conf  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年12月13日 下午3:52:29  
* @version V1.0    
*/
package cn.ctx.common.framework;

import javax.jms.ConnectionFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.stereotype.Component;

/**  
* @ClassName: JmsConfiguration  
* @Description: TODO(queue和topic共存)  
* @author gyu
* @date 2017年12月13日 下午3:52:29  
*    
*/
@Component
@EnableJms
public class JmsConfiguration {

	/**
	* @Title: jmsListenerContainerTopic
	* @Description: TODO(topic模式的ListenerContainer)
	* @param ConnectionFactory
	* @author gyu
	* @date 2017年12月13日下午3:55:44
	 */
	@Bean
	public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ConnectionFactory connectionFactory){
		DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(connectionFactory);
        return bean;
	}

	/**
	* @Title: jmsListenerContainerQueue
	* @Description: TODO(queue模式的ListenerContainer)
	* @param ConnectionFactory
	* @author gyu
	* @date 2017年12月13日下午3:55:34
	 */
	@Bean
	public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ConnectionFactory connectionFactory){
		DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(connectionFactory);
        return bean;
	}
}
