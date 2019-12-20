package pers.ycy.itoken.common.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pers.ycy.itoken.common.web.interceptor.ConstantsInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Bean
	public ConstantsInterceptor constantsInterceptor() {
		return new ConstantsInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 常量拦截器
		registry.addInterceptor(constantsInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/static/**");
	}
}
