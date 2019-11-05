package com.qhgctech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/*
 * 注册中心01
 */
@SpringBootApplication
@EnableEurekaServer
public class AppEureka01 {
	public static void main(String[] args) {
		SpringApplication.run(AppEureka01.class, args);
	}
}
