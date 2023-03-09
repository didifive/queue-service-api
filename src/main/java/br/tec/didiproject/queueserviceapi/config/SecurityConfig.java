package br.tec.didiproject.queueserviceapi.config;

import br.tec.didiproject.queueserviceapi.filters.FilterAuthentication;
import br.tec.didiproject.queueserviceapi.services.AuthService;
import br.tec.didiproject.queueserviceapi.services.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static br.tec.didiproject.queueserviceapi.enums.constants.v1.MappingRoutesV1.PATH_AUTH;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs", "/swagger-resources", "/swagger-resources/**", "/configuration/ui"
            , "/configuration/security", "/swagger-ui.html", "/webjars/**"
            // -- Swagger UI v3 (OpenAPI)
            , "/v3/api-docs/**", "/swagger-ui/**", "/api-docs/**"
            // other public endpoints of your API may be appended to this array
    };

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http
            , AuthService authService
            , UsuarioService usuarioService) throws Exception {
        http.cors()
                .and()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/").permitAll()
                        .requestMatchers(HttpMethod.POST, PATH_AUTH).permitAll()
                        .requestMatchers(HttpMethod.POST, PATH_AUTH + "/refresh/*/*").permitAll()
                        .requestMatchers(AUTH_WHITELIST).permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new FilterAuthentication(authService, usuarioService),
                        UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
