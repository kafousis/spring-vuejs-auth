package com.github.kafousis.prototype.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            // other public endpoints of your API may be appended to this array
            "/h2-console/**",
            "/csrf/token"
    };

    // default authentication manager created by spring security
    // picks up userDetailsService and passwordEncoder automatically
    // as long as they are registered as Beans

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http

                // Cross-Origin Resource Sharing (CORS) is an HTTP-header based mechanism
                // that allows a server to indicate any origins (domain, scheme, or port)
                // other than its own from which a browser should permit loading resources.
                // CORS also relies on a mechanism by which browsers make a "preflight" request
                // to the server hosting the cross-origin resource, in order to check that the server
                // will permit the actual request. In that preflight, the browser sends headers
                // that indicate the HTTP method and headers that will be used in the actual request.
                .cors()

                .and()

                // No session will be created or used by Spring Security.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                // Cross-site request forgery (CSRF) is a web security vulnerability
                // An attack that forces an authenticated user to execute unwanted actions
                // Whenever cookies are involved, then CSRF protection is needed
                // In a stateless API that uses token-based authentication, we don't need CSRF protection

                // store the CSRF token in a cookie and allow frontend to access it
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                //.csrf().disable()

                .and()

                // Spring Security disables rendering within an iframe because it can cause security issues
                // H2 console runs within a frame so while Spring security is enabled,
                // frame options has to be disabled explicitly, in order to get the H2 console working
                .headers().frameOptions().disable()

                .and()

                .authorizeRequests()
                    .antMatchers(AUTH_WHITELIST).permitAll()
                    .antMatchers("/login**", "/error**").permitAll()
                .anyRequest().authenticated()

                .and()

                .formLogin()

                .and()

                .logout()
                    .deleteCookies("JSESSIONID");

        return http.build();
    }
}
