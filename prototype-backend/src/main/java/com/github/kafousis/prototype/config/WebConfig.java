package com.github.kafousis.prototype.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
    Normally Spring Boot shows HTML from 'src/main/resources/static' or 'src/main/resources/public'
    We apply this configuration in order to show HTML from WAR file root folder
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
    }
}
