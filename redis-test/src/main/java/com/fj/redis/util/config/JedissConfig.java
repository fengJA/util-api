package com.fj.redis.util.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * <p>
 * </p>
 *
 * @author fengjia
 * @since 2021-03-25
 */
public class JedissConfig {
    private static JedisPool jedisPool;
    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 连接池的数据库最大连接数，
        jedisPoolConfig.setMaxTotal(20);
        // 连接池的数据库最大空闲数，超过后标记为不可用
        jedisPoolConfig.setMaxIdle(10);
        GenericObjectPoolConfig poolConfig;
        jedisPool = new JedisPool(jedisPoolConfig, "ip", 6379, 1000);
    }

    public static Jedis getJedis() throws Exception {
        if (null != jedisPool){
            return jedisPool.getResource();
        }
        throw new Exception("this is err");
    }
}
