package cn.ctx.common.framework.cache.memcache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component  
public class CacheFactory {
	
	public static final String CLIENT_NULL = "no";
	public static final String CLIENT_MEMCACHED = "memcached";
	
	private static final String CONFIG_CACHE_TYPE = "cache.type";
	private static final String CONFIG_CACHE_ENDPOINT = "cache.endpoint";
	
	private static CacheClient client = null;
	
	private static String CACHETYPE;
	
	private static String ENDPOINT;

	@Value("${cache.type}")
	public void setCacheType(String cacheType) {
		CACHETYPE=cacheType;
	}

	@Value("${cache.endpoint}")
	public void setEndpoint(String endpoint) {
		ENDPOINT=endpoint;
	}
	
	
	public static synchronized CacheClient getInstance() {
		if (client == null) {
			if (CLIENT_MEMCACHED.equalsIgnoreCase(CACHETYPE)) {
				client = new MemCacheClient(ENDPOINT.split(";"));
			} else {
				client = new NullCacheClient();
			}
		}
		return client;
	}
}
