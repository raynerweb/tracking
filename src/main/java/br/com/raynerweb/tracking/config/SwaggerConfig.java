package br.com.raynerweb.tracking.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Tracking API",
        version = "1.0",
        description = "Tracking Micro Service"))
public class SwaggerConfig {
}
