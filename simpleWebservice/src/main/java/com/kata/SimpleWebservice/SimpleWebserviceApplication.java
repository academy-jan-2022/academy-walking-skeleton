package com.kata.SimpleWebservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

@SpringBootApplication
@EnableCaching
public class SimpleWebserviceApplication {

	@Autowired
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(SimpleWebserviceApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				String corsUrl = env.getProperty("cors.url");
				registry.addMapping("/getUsers").allowedOrigins(corsUrl);
				registry.addMapping("/createCategory").allowedOrigins(corsUrl);
				registry.addMapping("/allCategories").allowedOrigins(corsUrl);
			}
		};
	}

	@Bean
	public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory){
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().disableKeyPrefix()
				.entryTtl(Duration.ofMinutes(1)).disableCachingNullValues()
				.serializeValuesWith(RedisSerializationContext
						.SerializationPair
						.fromSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class)));

		return RedisCacheManager.builder(connectionFactory).cacheDefaults(config).build();
	}

	@Bean
	public RedisConnectionFactory connectionFactory(){
		return new JedisConnectionFactory();
	}

}
