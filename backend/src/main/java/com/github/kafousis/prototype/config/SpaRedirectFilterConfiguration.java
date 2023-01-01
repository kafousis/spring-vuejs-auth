package com.github.kafousis.prototype.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Configuration
public class SpaRedirectFilterConfiguration {
    private final Logger LOGGER = LoggerFactory.getLogger(SpaRedirectFilterConfiguration.class);

    private final String[] REDIRECT_IGNORE_PATHS = {
            "/img", "/css", "/js", "/fonts", "/favicon.ico",
            "/h2-console", "/csrf/token", "/api",
            "/swagger-ui", "/v3/api-docs"
    };

    @Bean
    public FilterRegistrationBean spaRedirectFiler() {
        FilterRegistrationBean<OncePerRequestFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(createRedirectFilter());
        registration.addUrlPatterns("/*");
        registration.setName("frontendRedirectFiler");
        registration.setOrder(1);
        return registration;
    }

    private OncePerRequestFilter createRedirectFilter() {
        return new OncePerRequestFilter() {

            @Override
            protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {

                if (Arrays.stream(REDIRECT_IGNORE_PATHS).anyMatch(req.getRequestURI()::contains)){
                    LOGGER.info("NOT REDIRECTING "+req.getRequestURI());
                    chain.doFilter(req, res);
                }else{
                    // if the request path is not included in REDIRECT_IGNORE_PATHS
                    // redirect to root, so the VueJS router can resolve the view
                    LOGGER.info("URL {} entered directly into the Browser, redirecting...", req.getRequestURI());
                    RequestDispatcher rd = req.getRequestDispatcher("/");
                    rd.forward(req, res);
                }
            }
        };
    }
}
