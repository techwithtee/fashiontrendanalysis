package com.wileyedge.fashiontrendanalysis;

import com.wileyedge.fashiontrendanalysis.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

/**
 * Security configuration for the Fashion Trend Analysis application.
 * This class sets up the security settings such as authentication, authorization, and password encoding.
 */
@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    /**
     * Provides a bean for password encoding using BCrypt.
     *
     * @return A BCrypt password encoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Creating a BCrypt password encoder bean
        return new BCryptPasswordEncoder();
    }

    /**
     * Provides a bean for building MVC request matchers.
     *
     * @param introspector The handler mapping introspector.
     * @return A builder for MVC request matchers.
     */
    @Bean
    public MvcRequestMatcher.Builder mvc(@Qualifier("customMvcHandlerMappingIntrospector") HandlerMappingIntrospector introspector) {
        // Creating a bean for building MVC request matchers
        return new MvcRequestMatcher.Builder(introspector);
    }

    /**
     * Provides a bean for introspecting handler mappings.
     *
     * @return A handler mapping introspector.
     */
    @Bean
    public HandlerMappingIntrospector customMvcHandlerMappingIntrospector() {
        // Creating a bean for introspecting handler mappings
        return new HandlerMappingIntrospector();
    }

    /**
     * Configures and provides a bean for the security filter chain.
     *
     * @param http The HTTP security configuration.
     * @return The security filter chain.
     * @throws Exception if an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Configuring authorization rules
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(new MvcRequestMatcher(customMvcHandlerMappingIntrospector(), "/**"))
                        .permitAll()
                )
                // Configuring the form login
                .formLogin(form -> form
                        .loginPage("/login")
                )
                // Configuring the logout behavior
                .logout(logout -> logout
                        .logoutSuccessUrl("/login")
                );

        // Building and returning the security filter chain
        return http.build();
    }
}
