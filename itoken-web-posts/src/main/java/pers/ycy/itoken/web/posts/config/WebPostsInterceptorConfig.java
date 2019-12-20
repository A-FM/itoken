package pers.ycy.itoken.web.posts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pers.ycy.itoken.web.posts.interceptor.WebPostsInterceptor;

@Configuration
public class WebPostsInterceptorConfig implements WebMvcConfigurer {

	@Bean
	WebPostsInterceptor webPostsInterceptor(){
		return new WebPostsInterceptor();
	}


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(webPostsInterceptor()).addPathPatterns("/**").excludePathPatterns("/static/**");
	}
}
