package br.uniesp.si.techback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth

                        // 1. Permissões de Acesso Público
                        .requestMatchers(
                                "/api/usuarios/**",
                                "/api/planos/**",
                                "/v3/api-docs/**", // Rota do OpenAPI
                                "/swagger-ui/**",  // Rota do Swagger UI
                                "/swagger-ui.html",
                                "/h2-console/**"   // Console do H2 (se estiver ativo)
                        ).permitAll()

                        // 2. Permissões Específicas de Escrita (ADMIN)
                        // Usamos hasAuthority("ADMIN") para evitar problemas com prefixo ROLE_
                        // (Assumindo que o usuário Admin está com a Authority "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/series/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/series/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/series/**").hasAuthority("ADMIN")

                        // 3. Rotas de Leitura (Séries) - Requer apenas autenticação
                        // Como não há '/api' em /series no seu request, vou liberar a rota raiz, mas o correto é usar /api/series
                        .requestMatchers(HttpMethod.GET, "/series/**").authenticated()

                        // 4. Todas as outras requisições requerem autenticação (usuário padrão ou Admin)
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()) // Permite Basic Authentication
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin())); // Configuração segura para o H2 Console

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}