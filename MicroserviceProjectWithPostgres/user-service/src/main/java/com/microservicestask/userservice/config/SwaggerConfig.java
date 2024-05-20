package com.microservicestask.userservice.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI()
    {
        return new OpenAPI()
                .info(new Info()
                        .title("Userservice API")
                        .description("This is User data project api Development")
                        .version("1.0")
                        .contact(new Contact().name("Dipali"))
                        .license(new License().name("Apache"))
                ).externalDocs(new ExternalDocumentation().url("learn.com").description("this is external document"));
    }

    
}
