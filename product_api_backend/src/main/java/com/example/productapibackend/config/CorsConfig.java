package com.example.productapibackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * Global CORS configuration.
 * Allows the preview origins to access API endpoints and docs (including redirected swagger-ui).
 */
@Configuration
public class CorsConfig {

    private static final String[] ALLOWED_ORIGINS = new String[] {
            // Preview domains allowed to access the backend
            "https://vscode-internal-22859-beta.beta01.cloud.kavia.ai",
            "https://*.beta01.cloud.kavia.ai"
    };

    private static final String[] ALLOWED_METHODS = new String[] {
            "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"
    };

    private static final String[] ALLOWED_HEADERS = new String[] {
            HttpHeaders.AUTHORIZATION,
            HttpHeaders.CONTENT_TYPE,
            HttpHeaders.ACCEPT,
            HttpHeaders.ORIGIN,
            HttpHeaders.CACHE_CONTROL,
            "X-Requested-With"
    };

    private static final String[] EXPOSED_HEADERS = new String[] {
            HttpHeaders.LOCATION,
            HttpHeaders.LINK,
            HttpHeaders.RETRY_AFTER,
            HttpHeaders.AUTHORIZATION,
            "X-Total-Count"
    };

    // PUBLIC_INTERFACE
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        // This is used by Spring MVC handler mappings for simple cases
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Apply to all endpoints including redirects like /docs -> /swagger-ui.html
                registry.addMapping("/**")
                        .allowedOrigins(ALLOWED_ORIGINS)
                        .allowedMethods(ALLOWED_METHODS)
                        .allowedHeaders(ALLOWED_HEADERS)
                        .exposedHeaders(EXPOSED_HEADERS)
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }

    /**
     * PUBLIC_INTERFACE
     * Provide a CorsConfigurationSource bean so that Spring Security (if present) can call http.cors()
     * and use the same rules applied globally. Safe to declare even if Spring Security is not on the classpath.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList(ALLOWED_ORIGINS));
        configuration.setAllowedMethods(Arrays.asList(ALLOWED_METHODS));
        configuration.setAllowedHeaders(Arrays.asList(ALLOWED_HEADERS));
        configuration.setExposedHeaders(Arrays.asList(EXPOSED_HEADERS));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Match all paths, including Springdoc endpoints (/api-docs, /swagger-ui.html, /swagger-ui/**)
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
