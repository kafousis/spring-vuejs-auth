package com.github.kafousis.prototype.config;

import com.github.kafousis.prototype.security.AuthFailureHandler;
import com.github.kafousis.prototype.security.AuthSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] AUTH_WHITELIST = {
            "/v3/api-docs/**", "/swagger-ui/**",
            "/h2-console/**", "/csrf/token",
            // public resources
            "/favicon.ico", "/img/**" ,  "/fonts/**" , "/css/**", "/js/**",
            // public endpoints
            "/", "/index.html",
            "/login", "/authenticate"
    };

    // default authentication manager created by spring security
    // picks up userDetailsService and passwordEncoder automatically
    // as long as they are registered as Beans

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // ---

    @Autowired
    private AuthSuccessHandler successHandler;

    @Autowired
    private AuthFailureHandler failureHandler;

    // ---

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
                //.cors()

                //.and()

                // Cross-site request forgery (CSRF) is a web security vulnerability
                // An attack that forces an authenticated user to execute unwanted actions
                // Whenever cookies are involved, then CSRF protection is needed
                // In a stateless API that uses token-based authentication, we don't need CSRF protection

                // store the CSRF token in a cookie with name XSRF-TOKEN
                .csrf()
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                    .ignoringAntMatchers(("/h2-console/**"))

                .and()
                // Spring Security disables rendering within an iframe because it can cause security issues
                // H2 console runs within a frame so while Spring security is enabled,
                // frame options has to be disabled explicitly, in order to get the H2 console working
                .headers().frameOptions().disable()

                .and()

                .authorizeHttpRequests()
                    .antMatchers(AUTH_WHITELIST).permitAll()
                    //.requestMatchers(new AntPathRequestMatcher("/openapi/openapi.yml")).permitAll()
                .anyRequest().authenticated()

                .and()

                // returns JSESSIONID cookie after successful login
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/authenticate")

                    // needs handlers, else it returns 404
                    .successHandler(successHandler)
                    .failureHandler(failureHandler)

                .and()

                .logout()
                    .logoutSuccessHandler(successHandler)
                    .deleteCookies("JSESSIONID")

                .and()

                    .exceptionHandling()
                        .authenticationEntryPoint(new Http403ForbiddenEntryPoint());

        return http.build();
    }
}
