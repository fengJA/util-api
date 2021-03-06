package com.fj.redis.util.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {
     @Bean
     public RedissonClient redissonClient() {
         Config config = new Config();
         config.useSingleServer()
                      .setAddress("192.168.4.48:6379");

         RedissonClient redisson = Redisson.create(config);

         return redisson;
    }
}
