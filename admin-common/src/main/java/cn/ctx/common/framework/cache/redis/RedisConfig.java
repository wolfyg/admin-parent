/**    
* @Title: RedisConfig.java  
* @Package cn.ctx.admin.core.cache.redis  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年11月6日 下午3:56:05  
* @version V1.0    
*/
package cn.ctx.common.framework.cache.redis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**  
* @ClassName: RedisConfig  
* @Description: TODO(redis 配置)  
* @author gyu
* @date 2017年11月6日 下午3:56:05  
*    
*/
@Configuration
public class RedisConfig {
	public RedisConfig() {}

	@ConditionalOnMissingBean
	public RedisTemplate<String,String> redisTemplate(RedisConnectionFactory factory){
		StringRedisTemplate template = new StringRedisTemplate(factory);
		Jackson2JsonRedisSerializer jackson2JsonRedisSerialize =new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL,JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerialize.setObjectMapper(om);
		template.setValueSerializer(jackson2JsonRedisSerialize);
		template.afterPropertiesSet();
		return template;
	}
	
}
