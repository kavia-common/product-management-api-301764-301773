Global CORS is enabled via:
- com.example.productapibackend.config.CorsConfig (WebMvcConfigurer and CorsConfigurationSource)
- com.example.productapibackend.config.SecurityConfig (only effective if Spring Security is on the classpath)

Origins allowed:
- https://vscode-internal-22859-beta.beta01.cloud.kavia.ai
- https://*.beta01.cloud.kavia.ai

Endpoints covered include API routes and documentation:
- /docs (redirect)
- /swagger-ui.html
- /swagger-ui/**
- /api-docs/**
- /v3/api-docs/**

Adjust ALLOWED_ORIGINS in CorsConfig as necessary for additional preview domains.
