package pers.ycy.itoken.web.posts.interceptor;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import pers.ycy.itoken.common.utils.CookieUtils;
import pers.ycy.itoken.common.web.constants.WebConstants;
import pers.ycy.itoken.common.web.interceptor.BaseInterceptorMethods;
import pers.ycy.itoken.web.posts.service.RedisService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebPostsInterceptor implements HandlerInterceptor {

	@Autowired
	private RedisService redisService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		return BaseInterceptorMethods.preHandleForLogin(request, response, handler, "http://localhost:8602/" + request.getServletPath());
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
		String token = CookieUtils.getCookieValue(request, WebConstants.SESSION_TOKEN);
		if (StringUtils.isNotBlank(token)) {
			String loginCode = redisService.get(token);
			if (StringUtils.isNotBlank(loginCode)) {
				BaseInterceptorMethods.postHandleForLogin(request, response, handler, modelAndView, redisService.get(loginCode), "http://localhost:8602/" + request.getServletPath());
			}
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

	}
}