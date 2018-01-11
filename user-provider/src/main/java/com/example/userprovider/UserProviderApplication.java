package com.example.userprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Lazy;

/**
 * 在Spring Boot启动类上用@EnableHystrixDashboard注解和@EnableCircuitBreaker注解。
 * 需要特别注意的是我们之前的Feign服务由于内置断路器支持，
 * 所以没有@EnableCircuitBreaker注解，但要使用Dashboard则必须加，
 * 如果不加，Dashboard无法接收到来自Feign内部断路器的监控数据，
 * 会报“Unable to connect to Command Metric Stream”错误
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker   //
@EnableHystrixDashboard
@Lazy(value = false)
public class UserProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserProviderApplication.class, args);
	}
}
