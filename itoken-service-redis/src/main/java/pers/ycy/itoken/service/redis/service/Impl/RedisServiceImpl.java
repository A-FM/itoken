package pers.ycy.itoken.service.redis.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import pers.ycy.itoken.service.redis.service.RedisService;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public void put(String key, Object value, long second) {
		redisTemplate.opsForValue().set(key,value,second, TimeUnit.SECONDS);
	}

	@Override
	public Object get(String key) {
		return redisTemplate.opsForValue().get(key);
	}
}
