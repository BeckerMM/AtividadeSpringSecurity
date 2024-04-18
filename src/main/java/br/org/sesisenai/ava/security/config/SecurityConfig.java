package br.org.sesisenai.ava.security.config;

import br.org.sesisenai.ava.security.Authentications.IsOnlyCourse;
import br.org.sesisenai.ava.security.Authentications.IsUser;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@AllArgsConstructor

public class SecurityConfig {
    private final CorsConfigurationSource corsConfiguration;
    private final SecurityContextRepository repo;
//    private final FilterAuthentication filterAuthentication;
    private final IsUser isUser;
    private final IsOnlyCourse isOnlyCourse;
    @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(c -> c.configurationSource(corsConfiguration));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/usuarios").permitAll()
                .requestMatchers(HttpMethod.GET, "api/cursos/").permitAll()
                .requestMatchers(HttpMethod.GET,"api/cursos/{id}").permitAll()
                .requestMatchers(HttpMethod.POST,"api/instrutor").permitAll()

                // Usuário
                .requestMatchers(HttpMethod.GET,"api/usuarios/{id}").access(isUser)
                .requestMatchers(HttpMethod.DELETE, "api/usuarios/{id}").access(isUser)
                .requestMatchers(HttpMethod.PUT, "api/usuarios/{id}").access(isUser)
                .requestMatchers(HttpMethod.PATCH, "api/usuarios/{id}/senai").access(isUser)

                // Aula
                .requestMatchers(HttpMethod.GET , "/api/cursos/{cursoId}/aulas").access(isOnlyCourse)
                .requestMatchers(HttpMethod.GET, "/api/cursos/{cursoId}/aulas/{aulaId}").access(isOnlyCourse)


                .anyRequest().authenticated()


        );
        http.securityContext((context) -> context.securityContextRepository(repo));

        http.formLogin(Customizer.withDefaults());
//      http.addFilterBefore(filterAuthentication, UsernamePasswordAuthenticationFilter.class);
        http.logout(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
