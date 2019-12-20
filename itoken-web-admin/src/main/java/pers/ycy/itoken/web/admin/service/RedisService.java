package pers.ycy.itoken.web.admin.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pers.ycy.itoken.web.admin.service.fallback.RedisServiceFallback;

@FeignClient(value = "itoken-service-redis",fallback = RedisServiceFallback.class)
@Service
public interface RedisService {
	@RequestMapping(value = "put",method = RequestMethod.POST)
	String put(@RequestParam(value = "key") String key, @RequestParam(value = "value") String value, @RequestParam(value = "second") long second);
	@RequestMapping(value = "get",method = RequestMethod.GET)
	String get(@RequestParam(value = "key") String key);
}
