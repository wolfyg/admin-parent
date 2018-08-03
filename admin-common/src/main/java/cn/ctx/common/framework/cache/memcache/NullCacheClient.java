package cn.ctx.common.framework.cache.memcache;

import cn.ctx.common.framework.Entity;

public class NullCacheClient implements CacheClient {

	@Override
	public boolean has(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String get(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T get(String key, Class<T> cls) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void set(String key, String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void set(String key, Entity value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void set(String key, String value, int ttl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void set(String key, Entity value, int ttl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void del(String... key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean flushAll() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean flushAll(String... Servers) {
		// TODO Auto-generated method stub
		return false;
	}

}
