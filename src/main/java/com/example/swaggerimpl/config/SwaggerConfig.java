package com.example.swaggerimpl.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion){
        return new OpenAPI()
                .info(new Info()
                .title("Test Application")
                .version(appVersion)
                .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
