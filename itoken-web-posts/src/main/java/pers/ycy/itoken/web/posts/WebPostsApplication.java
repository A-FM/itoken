package pers.ycy.itoken.web.posts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/* 加载了 common-web中的静态资源路径补全拦截器 当下的登录拦截器. */
@SpringBootApplication(scanBasePackages = "pers.ycy.itoken")
@EnableDiscoveryClient
@EnableFeignClients
public class WebPostsApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebPostsApplication.class,args);
	}
}
