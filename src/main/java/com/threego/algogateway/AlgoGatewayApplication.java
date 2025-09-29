package com.threego.algogateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AlgoGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgoGatewayApplication.class, args);
	}

}
