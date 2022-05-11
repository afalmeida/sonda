package br.com.elo7.sonda.candidato.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import br.com.elo7.sonda.candidato.entity.OrderEntity;

@Configuration
public class RedisConfig {
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
	    return new JedisConnectionFactory();
	}

	@Bean
	public RedisTemplate<String, OrderEntity> redisTemplate() {
	    RedisTemplate<String, OrderEntity> template = new RedisTemplate<>();
	    template.setConnectionFactory(jedisConnectionFactory());
	    return template;
	}

}
