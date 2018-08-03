package cn.ctx.common.framework.cache.memcache;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

import cn.ctx.common.framework.DataHelper;
import cn.ctx.common.framework.Entity;

public class MemCacheClient implements CacheClient {

	private static Logger LOG=LoggerFactory.getLogger(MemCacheClient.class);

	private SockIOPool pool;
	private MemCachedClient client;

	public MemCacheClient(String[] servers) {
		try {
			// 获取连接池的一个实例
			pool = SockIOPool.getInstance();

			// 设置服务器和权重
			pool.setServers(servers);
			Integer[] weights = { 1,2 };
			pool.setWeights(weights);

			// 设置连接池设置5初始，5 min，250最大康斯并设置最大空闲时间为CONN 1分钟
			pool.setInitConn(5);
			pool.setMinConn(5);
			pool.setMaxConn(1000);
			pool.setMaxIdle(1000 * 60);

			// 设置睡眠为维护线程会唤醒每X秒，保持池大小
			pool.setMaintSleep(30);

			// 设置一些TCP设置禁用Nagle设置读取和连接超时1秒
			pool.setNagle(false);
			pool.setSocketTO(1000);
			pool.setSocketConnectTO(1000);

			// 初始化连接池
			pool.initialize();

			client = new MemCachedClient();
			client.setDefaultEncoding("ISO-8859-1");
		} catch (Exception e) {
			LOG.error("Init memcached client error",  e);
		}
	}

	protected void finalize() {
		try {
			if (pool != null) {
				pool.shutDown();
			}
		} catch (Exception e) {
			LOG.error("Shutdown connection pool error", e);
		}
	}

	@Override
	public boolean has(String key) {
		boolean found = false;
		try {
			found = client.keyExists(key);
		} catch (Exception e) {
			LOG.error("Chech cache error", e);
		}
		return found;
	}

	@Override
	public String get(String key) {
		String value = null;
		try {
			value = (String) client.get(key);
		} catch (Exception e) {
			LOG.error("Get cache error", e);
		}
		return value;
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public <T> T get(String key, Class<T> cls) {
		T value = null;
		try {
			String data = (String) client.get(key);
			if (data != null) {
				value = (T) DataHelper.readEntity(data, cls);
			}
		} catch (Exception e) {
			LOG.error("Get cache error", e);
		}
		return value;
	}

	@Override
	public void set(String key, String value) {
		try {
			if (value == null) {
				value = "";
			}
			client.set(key, value);
		} catch (Exception e) {
			LOG.error("Set cache error", e);
		}
	}

	@Override
	public void set(String key, Entity value) {
		try {
			client.set(key, DataHelper.writeEntity(value));
		} catch (Exception e) {
			LOG.error("Set cache error", e);
		}
	}

	@Override
	public void set(String key, String value, int ttl) {
		try {
			if (value == null) {
				value = "";
			}
			Date expireTime = DateUtils.addSeconds(new Date(), ttl);
			client.set(key, value, expireTime);
		} catch (Exception e) {
			LOG.error("Set cache error", e);
		}
	}

	@Override
	public void set(String key, Entity value, int ttl) {
		try {
			Date expireTime = DateUtils.addSeconds(new Date(), ttl);
			client.set(key, DataHelper.writeEntity(value), expireTime);
		} catch (Exception e) {
			LOG.error("Set cache error", e);
		}
	}

	@Override
	public void del(String... key) {
		try {
			for (String k : key) {
				client.delete(k);
			}
		} catch (Exception e) {
			LOG.error("Delete cache error",  e);
		}
	}

	@Override
	public boolean flushAll() {
		return client.flushAll();
	}

	@Override
	public boolean flushAll(String... Servers) {
		return client.flushAll(Servers);
	}

}
