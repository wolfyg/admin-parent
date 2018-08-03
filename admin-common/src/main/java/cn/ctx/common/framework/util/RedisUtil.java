/**    
* @Title: RedisUtil.java  
* @Package cn.ctx.admin.util  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年11月7日 下午2:06:40  
* @version V1.0    
*/
package cn.ctx.common.framework.util;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import cn.ctx.common.base.CacheConstants;

/**  
* @ClassName: RedisUtil  
* @Description: TODO(redis 工具类)  
* @author gyu
* @date 2017年11月7日 下午2:06:40  
*    
*/
@Component  
public class RedisUtil {  

	private static Logger log=LoggerFactory.getLogger(RedisUtil.class);
	
    @Autowired  
    private RedisTemplate<String,String> redisTemplate;  

    private static Gson GSON=new Gson();
    
    public String writeGson(Object obj) {
    	try {
        	return GSON.toJson(obj);
    	}catch(Exception e) {
    		log.error("Gson转换异常",e.getMessage());
    		return null;
    	}
    }
    
    public Object readEntrty(String cache,Class clas) {
    	try {
        	return GSON.fromJson(cache, clas);
    	}catch (Exception e) {
    		log.error("缓存转对象异常",e.getMessage());
    		return null;
    	}
    }

    public List<Object> readList(String cache,Class<List> class1) {
    	try {
        	return GSON.fromJson(cache, (Type) class1);
    	}catch (Exception e) {
    		log.error("缓存转集合对象异常",e.getMessage());
    		return null;
    	}
    }
    
    /** 
     * 批量删除对应的value 
     * @param keys 
     */  
    public void remove(final String... keys) {  
        for (String key : keys) {  
            remove(key);  
        }  
    }  
  
    /** 
     * 批量删除key 
     * @param pattern 
     */  
    @SuppressWarnings("unchecked")  
    public void removePattern(final String pattern) {  
        Set<String> keys = redisTemplate.keys(pattern);  
        if (keys.size() > 0)  
            redisTemplate.delete(keys);  
    }  
  
    /** 
     * 删除对应的value 
     * @param key 
     */  
    @SuppressWarnings("unchecked")  
    public void remove(final String key) {  
        if (exists(key)) {  
            redisTemplate.delete(key);  
        }  
    }  
  
    /** 
     * 判断缓存中是否有对应的value 
     * @param key 
     * @return 
     */  
    @SuppressWarnings("unchecked")  
    public boolean exists(final String key) {  
        return redisTemplate.hasKey(key);  
    }  
  
    /** 
     * 读取缓存 
     * @param key 
     * @return 
     */  
    @SuppressWarnings("unchecked")  
    public String get(final String key) {  
        String result = null;  
        ValueOperations<String, String> operations = redisTemplate.opsForValue();  
        result = operations.get(key);  
        return result;  
    }  
  
    /** 
     * 写入缓存 
     * @param key 
     * @param value 
     * @return 
     */  
    @SuppressWarnings("unchecked")  
    public boolean set(final String key, String value) {  
        boolean result = false;  
        try {  
            ValueOperations<String, String> operations = redisTemplate.opsForValue();  
            operations.set(key, value);  
            result = true;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
  
    /** 
     * 写入缓存 
     * @param key 
     * @param value 
     * @param expireTime （单位:秒）
     * @return 
     */  
    @SuppressWarnings("unchecked")  
    public boolean set(final String key, String value, int expireTime) {  
        boolean result = false;  
        try {  
            ValueOperations<String, String> operations = redisTemplate.opsForValue();  
            operations.set(key, value);  
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);  
            result = true;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
    
    /**
    * @Title: cleanPermission
    * @Description: TODO(清空权限缓存)
    * @author gyu
    * @date 2017年11月13日下午3:14:00
     */
    public void cleanPermission() {
//    	List<AmAdmin> adminList=adminService.getAmAdminList(null,0,100);
//    	for(AmAdmin admin:adminList) {
//    		remove(String.format(CacheConstants.KEY_PERMISSIONBYID,admin.getId()));
//    	}
    }
    
    /**
    * @Title: cleanLogin
    * @Description: TODO(删除登录缓存)
    * @param userName
    * @author gyu
    * @date 2017年11月13日下午3:16:52
     */
    public void cleanLogin(String userName) {
		remove(String.format(CacheConstants.KEY_USERNAME,userName));
    }
}
