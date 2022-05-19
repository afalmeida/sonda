package br.com.elo7.sonda.candidato.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import br.com.elo7.sonda.candidato.dto.OrderDTO;

@Configuration
public class RedisConfig {
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
	    return new JedisConnectionFactory();
	}

	@Bean
	public RedisTemplate<String, OrderDTO> redisTemplate() {
	    RedisTemplate<String, OrderDTO> template = new RedisTemplate<>();
	    template.setConnectionFactory(jedisConnectionFactory());
	    return template;
	}

}
