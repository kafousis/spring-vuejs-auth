package com.github.kafousis.prototype.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
            "/h2-console/**"
    };

    // default authentication manager created by spring security
    // picks up userDetailsService and passwordEncoder automatically
    // as long as they are registered as Beans

//    @Autowired
//    private UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http

                // Cross-site request forgery (CSRF) is a web security vulnerability
                // If our stateless API uses token-based authentication, such as JWT,
                // we don't need CSRF protection, and we must disable it
                .csrf().disable()

                // Spring Security disables rendering within an iframe because it can cause security issues
                // H2 console runs within a frame so while Spring security is enabled,
                // frame options has to be disabled explicitly, in order to get the H2 console working
                .headers().frameOptions().disable();

        return http.build();
    }
}
