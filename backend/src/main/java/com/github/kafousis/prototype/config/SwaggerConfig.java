package com.github.kafousis.prototype.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    // Springdoc-OpenAPI generates the documentation based on our application REST APIs
    // OpenAPI generated document: http://localhost:8080/v3/api-docs

    // Swagger-UI: collection of HTML, Javascript, and CSS files, generates a user interface based on the OpenAPI specification
    // default-ui-url = http://localhost:8080/swagger-ui/index.html
    // custom-ui-url = http://localhost:8080/swagger-ui

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()
                .info(new Info().title("Spring - Vue.js Auth")
                        .contact(new Contact().name("Giannis Kafousis").email("g.kafousis@gmail.com").url("https://www.linkedin.com/in/gkafousis/")));
    }
}