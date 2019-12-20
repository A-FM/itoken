package pers.ycy.itoken.web.posts.service.fallback;

import org.springframework.stereotype.Component;
import pers.ycy.itoken.common.hystrix.Fallback;
import pers.ycy.itoken.web.posts.service.PostService;

@Component
public class PostServiceFallback implements PostService {

	@Override
	public String page(int pageNum, int pageSize, String tbPostsPostJson) {
		return Fallback.badGateway();
	}

	@Override
	public String get(String postGuid) {
		return null;
	}

	@Override
	public String save(String tbPostsPostJson, String optsBy) {
		return Fallback.badGateway();
	}
}
