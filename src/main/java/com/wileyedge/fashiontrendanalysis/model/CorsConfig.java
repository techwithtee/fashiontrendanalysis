package com.wileyedge.fashiontrendanalysis.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Configuration class to handle Cross-Origin Resource Sharing (CORS) settings.
 */
@Configuration
public class CorsConfig {

    /**
     * Configures and returns a CORS filter bean.
     *
     * This configuration allows requests from the frontend domain (http://localhost:63342)
     * and permits any HTTP method and header.
     *
     * @return A CorsFilter bean with the specified configuration.
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:63342");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter((CorsConfigurationSource) source);
    }
}
