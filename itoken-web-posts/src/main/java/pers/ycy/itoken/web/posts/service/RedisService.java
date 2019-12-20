package pers.ycy.itoken.web.posts.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pers.ycy.itoken.web.posts.service.fallback.RedisServiceFallback;

@FeignClient(value = "itoken-service-redis",fallback = RedisServiceFallback.class)
@Service
public interface RedisService {
	@RequestMapping(value = "put",method = RequestMethod.POST)
	public String put(@RequestParam(value = "key") String key, @RequestParam(value = "value") String value, @RequestParam(value = "second") long second);
	@RequestMapping(value = "get",method = RequestMethod.GET)
	public String get(@RequestParam(value = "key") String key);
}
