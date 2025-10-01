package com.threego.algomemberservice.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan(basePackages = "com.threego.algomemberservice", annotationClass = Mapper.class)
@Configuration
public class MybatisConfig {
}