package pers.ycy.itoken.service.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pers.ycy.itoken.service.redis.service.RedisService;

@RestController
public class RedisController {

	@Autowired
	private RedisService redisService;

	@RequestMapping(value = "put",method = RequestMethod.POST)
	public String put(String key,String value,long second){
		redisService.put(key,value,second);
		return "ok";
	}
	@RequestMapping(value = "get",method = RequestMethod.GET)
	public String get(String key){
		Object o = redisService.get(key);
		if(o!=null){
			return String.valueOf(o);
		}
		return null;
	}

}
