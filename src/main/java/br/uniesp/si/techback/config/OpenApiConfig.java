package br.uniesp.si.techback.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 1. Define o esquema de segurança Basic Auth
@Configuration
@SecurityScheme(
        name = "basicAuth", // Nome que será usado para referenciar a segurança
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("IESPFLIX API")
                        .version("v0.0.1")
                        .description("Documentação da API de streaming IESPFLIX."));
    }
}