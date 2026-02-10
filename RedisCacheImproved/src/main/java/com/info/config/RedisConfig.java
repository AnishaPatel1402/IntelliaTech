package com.info.config;

import java.time.Duration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import tools.jackson.databind.ObjectMapper;


@Configuration
@EnableCaching
public class RedisConfig {

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	@Bean
	public RedisCacheManager cacheManager(RedisConnectionFactory factory, ObjectMapper objectMapper) {

		// Serializer with Jackson ObjectMapper
		GenericJacksonJsonRedisSerializer serializer = new GenericJacksonJsonRedisSerializer(objectMapper);

		// Default cache configuration
		RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig()
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer))
				.disableCachingNullValues().entryTtl(Duration.ofMinutes(10)); // default TTL

		return RedisCacheManager.builder(factory).cacheDefaults(defaultConfig)

				// Cache for getUserById
				.withCacheConfiguration("users", defaultConfig.entryTtl(Duration.ofMinutes(5)))

				// Cache for getAllUsers
				.withCacheConfiguration("userList", defaultConfig.entryTtl(Duration.ofMinutes(2)))

				.build();
	}
}
