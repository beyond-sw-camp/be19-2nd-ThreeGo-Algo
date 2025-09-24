package com.threego.algo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Algo - API 명세서",
                version = "v1.0.0"
        )
)
@Configuration
public class SwaggerConfig {
}
