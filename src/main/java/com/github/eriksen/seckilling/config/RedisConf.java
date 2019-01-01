package com.github.eriksen.seckilling.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * RedisConf
 */
@Configuration
@EnableRedisRepositories(basePackages = "com.github.eriksen.seckilling.peresistence")
public class RedisConf {

  @Value("${spring.data.redis.host}")
  private String host;
  @Value("${spring.data.redis.port}")
  private int port;

  @Bean
  public JedisConnectionFactory redisConnectionFactory() {
    RedisStandaloneConfiguration conf = new RedisStandaloneConfiguration(host, port);
    return new JedisConnectionFactory(conf);
  }

  @Bean
  @Autowired
  public RedisTemplate<String, ?> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
    RedisTemplate<String, ?> template = new RedisTemplate<>();
    template.setConnectionFactory(redisConnectionFactory);
    return template;
  }
}