package org.example.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.Collections;

public class RedisLockUtils {

	private static final Logger log = LoggerFactory.getLogger(RedisLockUtils.class);

	public static boolean lock(String prefix, String key, String value, long expiryTime, long timeOut) {
		Jedis jedis = null;
		try {
			jedis = JedisUtils.getJedis();
			String realKey = prefix + key;
			long deadTimeLine = System.currentTimeMillis() + timeOut; 
			while(true) {
				String result = jedis.set(realKey, value, "NX", "PX", expiryTime);
				if("OK".equals(result)) {
					log.info(">>>>>>>>>>>>>> key : {} lock success !!", realKey);
					return true;
				}
				log.info(">>>>>>>>>> 获取锁失败，重试");
				timeOut = deadTimeLine - System.currentTimeMillis();
				if(timeOut <= 0L) {
					log.info(">>>>>>>>>>> 重试超时");
					return false;
				}
			}
		} catch(Exception ex) {
			log.error(">>>>>>>>>>>>>> lock error !!", ex);
		} finally {
			JedisUtils.close(jedis);
		}
		return false;
	}
	
	
	public static boolean unLock(String prefix, String key, String value) {
		Jedis jedis = null;
		try {
			jedis = JedisUtils.getJedis();
			String realKey = prefix + key;
			String luaScript = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
			Object result = jedis.eval(luaScript, Collections.singletonList(realKey), Collections.singletonList(value));
			if("1".equals(String.valueOf(result))) {
				log.info(">>>>>>>>>>>>> key : {} unlock success", realKey);
				return true;
			}
			log.error(">>>>>>>>>>>>>> key : {} unlock failed", realKey);
		} catch(Exception ex) {
			log.error(">>>>>>>>>>>>>> unlock error !!", ex);
		} finally {
			JedisUtils.close(jedis);
		}
		return false;
	}
}
