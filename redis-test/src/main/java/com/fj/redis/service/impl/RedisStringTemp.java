package com.fj.redis.service.impl;

import com.fj.redis.util.config.JedissConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * </p>
 *
 * @author FJ
 * @since 2021-03-25
 */
public class RedisStringTemp {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String SHOP_KEY = "fjgirl";

    private String addShop() throws Exception {
        String value = UUID.randomUUID().toString() + Thread.currentThread().getName();
        // 10秒以后过期
        Boolean flage = redisTemplate.opsForValue().setIfAbsent(SHOP_KEY, value, 10, TimeUnit.SECONDS);
        try{
            redisTemplate.expire(SHOP_KEY, 10L, TimeUnit.SECONDS);
            if (!flage){
                return "失败了";
            }else {
                String goods = redisTemplate.opsForValue().get("userIdgoods:001");
                int num = goods != null ? 0 : Integer.parseInt(goods);
                if (num > 0){
                    int result = num - 1;
                    redisTemplate.opsForValue().set("userIdgoods:001", result + "");
                    System.out.println();
                    return "成功了，还剩" + result;
                }else {
                    return "已售罄";
                }
            }
        }finally {
            // 方法一：不能保证finally的原子性
//            if (value.equalsIgnoreCase(redisTemplate.opsForValue().get(SHOP_KEY))){
//                redisTemplate.delete(SHOP_KEY);
//            }
            // 方法二：采用事务
//            while (true){
//                redisTemplate.watch(SHOP_KEY);
//                if (value.equalsIgnoreCase(redisTemplate.opsForValue().get(SHOP_KEY))){
//                    redisTemplate.setEnableTransactionSupport(true);
//                    redisTemplate.multi();
//                    redisTemplate.delete(SHOP_KEY);
//                    List<Object> exec = redisTemplate.exec();
//                    if (exec == null){
//                        continue;
//                    }
//                }
//                redisTemplate.unwatch();
//                break;
//            }
            Jedis jedis = JedissConfig.getJedis();
            String script = "if redis.call(\"get\",KEYS[1]) == ARGV[1]\n" +
                    "then\n" +
                    "    return redis.call(\"del\",KEYS[1])\n" +
                    "else\n" +
                    "    return 0\n" +
                    "end";
            try{
                Object eval = jedis.eval(script, Collections.singletonList(SHOP_KEY), Collections.singletonList(value));
                if ("1".equals(eval)){
                    return "sucess deleted";
                }else {
                    return "fail";
                }
            }finally {
                if (null != jedis){
                    jedis.close();
                }
            }

        }
    }


}
