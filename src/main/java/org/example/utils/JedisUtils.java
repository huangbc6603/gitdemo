package org.example.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {

    //Redis服务器IP
    private static String HOST = "127.0.0.1";
    
    //Redis的端口号
    private static int PORT = 123456;
    
    //访问密码
    private static String AUTH = null;
    
    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_ACTIVE = 1024;
    
    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 200;
    
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = 10000;
    
    private static int TIMEOUT = 15000;
    
    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;
    
    private static int SRM_REDIS_DEFAULT_DATABASE = 6;
    
	private static JedisPool jedisPool = null;

    private static JedisPool jedisAuthPool = null;

	/**
	 * 初始化Jedis连接池
	 * @param host
	 * @param port
	 * @param auth
	 * @param maxActive
	 * @param maxIdle
	 * @param maxWait
	 * @param timeout
	 * @param testOnBorrow
	 */
	public static void initJedisPoolConfig(//
			String host, int port, String auth,int db, //
			int maxActive, int maxIdle, int maxWait, int timeout, //
			Boolean testOnBorrow) {//
		try {
			AUTH = auth;
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(maxActive > 0 ? maxActive : MAX_ACTIVE);
			config.setMaxIdle(maxIdle > 0 ? maxIdle : MAX_IDLE);
			config.setMaxWaitMillis(maxWait > 0 ? maxWait : MAX_WAIT);
			config.setTestOnBorrow(testOnBorrow != null ? testOnBorrow : TEST_ON_BORROW);
			if (AUTH != null) {
				jedisPool = new JedisPool(config, host != null ? host : HOST, port > 0 ? port : PORT,
						timeout > 0 ? timeout : TIMEOUT, AUTH, db > 0 ? db : SRM_REDIS_DEFAULT_DATABASE, null);
			} else {
				jedisPool = new JedisPool(config, host != null ? host : HOST, port > 0 ? port : PORT,
						timeout > 0 ? timeout : TIMEOUT, null, db > 0 ? db : SRM_REDIS_DEFAULT_DATABASE, null);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public static void initJedisAuthPoolConfig(//
                                           String host, int port, String auth,int db, //
                                           int maxActive, int maxIdle, int maxWait, int timeout, //
                                           Boolean testOnBorrow) {//
        try {
            AUTH = auth;
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(maxActive > 0 ? maxActive : MAX_ACTIVE);
            config.setMaxIdle(maxIdle > 0 ? maxIdle : MAX_IDLE);
            config.setMaxWaitMillis(maxWait > 0 ? maxWait : MAX_WAIT);
            config.setTestOnBorrow(testOnBorrow != null ? testOnBorrow : TEST_ON_BORROW);
            if (AUTH != null) {
                jedisAuthPool = new JedisPool(config, host != null ? host : HOST, port > 0 ? port : PORT,
                        timeout > 0 ? timeout : TIMEOUT, AUTH, db > 0 ? db : SRM_REDIS_DEFAULT_DATABASE, null);
            } else {
                jedisAuthPool = new JedisPool(config, host != null ? host : HOST, port > 0 ? port : PORT,
                        timeout > 0 ? timeout : TIMEOUT, null, db > 0 ? db : SRM_REDIS_DEFAULT_DATABASE, null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取Jedis实例
     * @return
     */
    public static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Jedis getJedisAuth() {
        try {
            if (jedisAuthPool != null) {
                Jedis resource = jedisAuthPool.getResource();
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
   
    /**
     * 释放jedis资源
     * @param jedis
     */
    public static void close(final Jedis jedis) {
        if (jedis != null) {
        	jedis.close();
        }
    }
}
