package pers.ycy.itoken.web.posts.service.fallback;

import org.springframework.stereotype.Component;
import pers.ycy.itoken.common.hystrix.Fallback;
import pers.ycy.itoken.web.posts.service.RedisService;

@Component
public class RedisServiceFallback implements RedisService {

	@Override
	public String put(String key, String value, long second) {
		return Fallback.badGateway();
	}

	@Override
	public String get(String key) {
		return Fallback.badGateway();
	}
}
