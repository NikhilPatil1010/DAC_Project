package com.backend.config;



import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${spring.application.name:IMart Backend}")
    private String appName;

    @Value("${server.servlet.context-path:/api}")
    private String contextPath;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("IMart E-Commerce API")
                        .version("1.0.0")
                        .description("""
                            ## 📚 IMart E-Commerce Backend API Documentation
                            
                            Welcome to the IMart API documentation. This API provides endpoints for:
                            
                            - **Authentication**: User registration and login
                            - **Products**: Browse, search, and filter products
                            - **Cart**: Add, remove, and manage shopping cart items
                            - **Orders**: Place and track orders
                            - **Profile**: User profile management
                            
                            ### 🚀 Quick Start
                            1. Register a new user or use existing credentials
                            2. Get JWT token from `/api/auth/login`
                            3. Click "Authorize" button above and enter: `Bearer {your_token}`
                            4. Start testing API endpoints
                            
                            ### 🔐 Authentication
                            - All protected endpoints require JWT token
                            - Add token to Authorization header: `Bearer {token}`
                            """
                        )
                        .contact(new Contact()
                                .name("IMart Support")
                                .email("support@imart.com")
                                .url("https://imart.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Local Development Server"),
                        new Server()
                                .url("https://api.imart.com")
                                .description("Production Server")
                ))
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new Components()
                        .addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()));
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer")
                .description("Enter JWT token. Format: Bearer {token}");
    }
}