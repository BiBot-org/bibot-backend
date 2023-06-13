package com.coderecipe.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@EnableCaching
@Configuration
@RequiredArgsConstructor
public class CachingConfig {

    private final ObjectMapper objectMapper;

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> {
            configureCache(builder, "notice", Duration.ofSeconds(1200), new GenericJackson2JsonRedisSerializer());
            configureCache(builder, "noticeMain", Duration.ofSeconds(1200), new GenericJackson2JsonRedisSerializer(objectMapper));
            configureCache(builder, "user", Duration.ofSeconds(600), new GenericJackson2JsonRedisSerializer());
            configureCache(builder, "approval", Duration.ofSeconds(600), new GenericJackson2JsonRedisSerializer());
            configureCache(builder, "category", Duration.ofSeconds(600), new GenericJackson2JsonRedisSerializer());
            configureCache(builder, "payment", Duration.ofSeconds(600), new GenericJackson2JsonRedisSerializer());
            configureCache(builder, "card", Duration.ofSeconds(600), new GenericJackson2JsonRedisSerializer());
        };
    }

    private void configureCache(RedisCacheManager.RedisCacheManagerBuilder builder, String cacheName, Duration ttl, RedisSerializer<Object> valueSerializer) {
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
            .computePrefixWith(cacheNameStr -> cacheNameStr + "::")
            .entryTtl(ttl)
            .disableCachingNullValues()
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer));

        builder.withCacheConfiguration(cacheName, configuration);
    }
}