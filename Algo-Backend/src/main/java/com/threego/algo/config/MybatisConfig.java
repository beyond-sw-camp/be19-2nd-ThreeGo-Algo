package com.threego.algo.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan(basePackages = "com.threego.algo", annotationClass = Mapper.class)
@Configuration
public class MybatisConfig {
}