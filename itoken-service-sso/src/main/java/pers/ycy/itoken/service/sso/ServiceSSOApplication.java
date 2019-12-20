package pers.ycy.itoken.service.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
/* 需要给入口知道扫描的路径,不然没法找到common-web的拦截器配置 */
@SpringBootApplication(scanBasePackages = "pers.ycy.itoken")
@MapperScan(basePackages = {"pers.ycy.itoken.service.sso.mapper"})
public class ServiceSSOApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceSSOApplication.class, args);
	}
}
