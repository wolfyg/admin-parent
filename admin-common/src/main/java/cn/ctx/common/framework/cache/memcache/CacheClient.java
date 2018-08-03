package cn.ctx.common.framework.cache.memcache;

import cn.ctx.common.framework.Entity;

public interface CacheClient {

	boolean has(String key);

	String get(String key);

	/**
	 * Can only load Entity object from cache
	 * @param key
	 * @param cls
	 * @return
	 */
	<T> T get(String key, Class<T> cls);

	void set(String key, String value);

	void set(String key, Entity value);

	/**
	 * Set cache data
	 * @param key
	 * @param value
	 * @param ttl cache TTL, unit: seconds
	 */
	void set(String key, String value, int ttl);

	void set(String key, Entity value, int ttl);

	void del(String... key);
	
	/**
	* @Title: flushAll  
	* @Description: TODO(刷新所有缓存)  
	* @return boolean  
	* @throws
	 */
	boolean flushAll();
	
	/**
	* @Title: flushAll  
	* @Description: TODO(可选的主机阵列刷新（主机：端口）)  
	* @param @param Servers
	* @return boolean    返回类型  
	* @throws
	 */
	boolean flushAll(String... Servers);
}
