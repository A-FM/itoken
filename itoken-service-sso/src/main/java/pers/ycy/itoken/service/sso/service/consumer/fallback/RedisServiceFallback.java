package pers.ycy.itoken.service.sso.service.consumer.fallback;

import org.springframework.stereotype.Component;
import pers.ycy.itoken.common.hystrix.Fallback;
import pers.ycy.itoken.service.sso.service.consumer.RedisService;

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
