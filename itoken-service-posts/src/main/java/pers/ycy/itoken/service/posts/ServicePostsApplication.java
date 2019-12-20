package pers.ycy.itoken.service.posts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@EnableSwagger2
@SpringBootApplication(scanBasePackages = {"pers.ycy.itoken"})
@EnableEurekaClient
@MapperScan(basePackages = {"pers.ycy.itoken.common.mapper","pers.ycy.itoken.service.posts.mapper"})
public class ServicePostsApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServicePostsApplication.class,args);
	}
}
