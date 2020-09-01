package az.maqa.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import az.maqa.redis.model.Friend;

@Configuration
//@EnableCaching
@EnableRedisRepositories
public class RedisConfiguration {

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("localhost", 6379);
		return new JedisConnectionFactory(redisStandaloneConfiguration);
	}

	@Bean
	public RedisTemplate<String,Friend> redisTemplate() {
		RedisTemplate<String,Friend>  template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setEnableTransactionSupport(true);
		return template;
	}

}
