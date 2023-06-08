package com.coderecipe.config.security;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openApi() {
        Info info = new Info()
                .title("Bibot API Server Docs").version("v1.0.0")
                .description("Bibot API 문서 페이지");

        String jwtSchemeName = "jwtAuth";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);

        Components components = new Components().addSecuritySchemes(
                jwtSchemeName, new SecurityScheme().name(jwtSchemeName).type(SecurityScheme.Type.HTTP)
                        .scheme("bearer").bearerFormat("JWT")
        );

        return new OpenAPI().info(info).addSecurityItem(securityRequirement).components(components);

    }
}
