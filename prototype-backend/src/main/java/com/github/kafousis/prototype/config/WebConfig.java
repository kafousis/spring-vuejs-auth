package com.github.kafousis.prototype.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebConfig implements WebMvcConfigurer, RepositoryRestConfigurer {

    /*
        Normally Spring Boot shows HTML from 'src/main/resources/static' or 'src/main/resources/public'
        We apply this configuration in order to show HTML from WAR file root folder
    */

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
    }

    /*
        Cross-Origin Resource Sharing (CORS) Configuration.
        All endpoints can be accessed from the app running at http://localhost:3000.

        Modification Verbs (POST, PUT, PATCH, DELETE) send An 'OPTIONS' Preflight request
        in order to check if the backend accepts requests from a given origin and verb.
        The response will contain the header Access-Control-Allow-Origin with origin accepted
    */

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // this allows the preflight OPTIONS requests
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowCredentials(true);
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry registry) {
        registry.addMapping("/**")
                // this allows the preflight OPTIONS requests
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowCredentials(true);
    }
}
