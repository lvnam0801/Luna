package com.lvnam0801.Luna.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@SuppressWarnings("null") CorsRegistry registry) {
                registry.addMapping("/**") // Allow all endpoints
                        .allowedOriginPatterns("*") // Allow frontend domain
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed HTTP methods
                        .allowedHeaders("*") // Allow all headers
                        .allowCredentials(true); // Allow cookies/session authentication
                
                // registry.addMapping("/**") // Allow all endpoints
                //         .allowedOrigins("https://93ee1d70-bb88-4863-950c-348c9043deb6-00-3f35tu0e7ij45.riker.replit.dev") // Allow specific frontend
                //         .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed methods
                //         .allowedHeaders("*");
            }
        };
    }
}