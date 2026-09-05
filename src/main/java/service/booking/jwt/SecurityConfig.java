package service.booking.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwt;

    public SecurityConfig(JwtFilter jwt) {
        this.jwt = jwt;
    }

    @Bean
    public SecurityFilterChain chain(HttpSecurity http) {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // Public GET endpoint for available rooms
                        .requestMatchers(HttpMethod.GET, "/api/reservation").permitAll()
                        .requestMatchers("/api/reservation/test").permitAll()
                        .requestMatchers("/connect/create").permitAll()
                        .requestMatchers("/connect/**").authenticated()
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll()
                )
                // REST API uses JWT, so we don't need HTTP sessions
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                // Check JWT before standard authentication filter
                .addFilterBefore(jwt, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}