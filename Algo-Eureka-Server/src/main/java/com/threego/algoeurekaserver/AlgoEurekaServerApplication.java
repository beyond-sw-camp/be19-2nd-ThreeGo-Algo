package com.threego.algoeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AlgoEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlgoEurekaServerApplication.class, args);
    }

}
