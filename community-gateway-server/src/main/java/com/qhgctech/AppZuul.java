package com.qhgctech;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableEurekaClient
@MapperScan(basePackages = { "com.qhgctech.dao"})
@EnableZuulProxy
@SpringBootApplication
public class AppZuul {
	
	public static void main(String[] args) {
		SpringApplication.run(AppZuul.class, args);
	}
}
