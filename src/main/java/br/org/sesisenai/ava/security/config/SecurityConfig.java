package br.org.sesisenai.ava.security.config;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    private final CorsConfigurationSource corsConfiguration;
    private final SecurityContextRepository repo;
//    private final FilterAuthentication filterAuthentication;

    @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(c -> c.configurationSource(corsConfiguration));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers("")

        );
        http.securityContext((context) -> context.securityContextRepository(repo));
        http.sessionManagement(config -> {
            config.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });
//        http.addFilterBefore(filterAuthentication, UsernamePasswordAuthenticationFilter.class);
        http.logout(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
