package org.spring.springboot;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@ConfigurationProperties(prefix = "spring.cache.redis")
public class SpringCacheRedisConfig {
//    private Duration timeToLive = Duration.ZERO;
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
//        //StringRedisTemplate的构造方法中默认设置了stringSerializer
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        //set key serializer
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//        template.setKeySerializer(stringRedisSerializer);
//        template.setHashKeySerializer(stringRedisSerializer);
//
//        //set value serializer
//        template.setDefaultSerializer(new Jackson2JsonRedisSerializer(Object.class));
//
//        template.setConnectionFactory(lettuceConnectionFactory);
//        template.afterPropertiesSet();
//        return template;
//    }

//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory factory) {
//        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//
//        return RedisCacheManager.builder(factory).build();
////        //解决查询缓存转换异常的问题
////        ObjectMapper om = new ObjectMapper();
////        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
////        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
////        jackson2JsonRedisSerializer.setObjectMapper(om);
////
////        // 配置序列化（解决乱码的问题）
////        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
////                .entryTtl(timeToLive)
////                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
////                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
////                .disableCachingNullValues();
////
////        RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
////                .cacheDefaults(config)
////                .build();
////        return cacheManager;
//    }
}