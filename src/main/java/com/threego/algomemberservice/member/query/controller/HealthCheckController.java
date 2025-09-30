package com.threego.algomemberservice.member.query.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    private Environment env;

    @Autowired
    public HealthCheckController(Environment env) {
        this.env = env;
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "OK. 포트는 " + env.getProperty("local.server.port");
    }
}

