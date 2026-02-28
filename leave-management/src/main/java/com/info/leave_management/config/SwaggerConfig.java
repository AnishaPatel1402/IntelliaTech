package com.info.leave_management.config;


import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
public class SwaggerConfig {

    @Bean
    public OpenAPI leaveManagementOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Employee Leave Management API")
                        .description("A project which built for managing the employee's leaves")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Anisha Patel")
                                .email("annipatel14@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0"))
                );

    }

}



//http://localhost:8080/swagger-ui/index.html
