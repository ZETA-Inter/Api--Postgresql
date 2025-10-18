package com.example.Api_Postgresql.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomAcessDeniedHandler customAcessDeniedHandler;

    @Autowired
    private CustomAuthEntryPoint customAuthEntryPoint;

    @Value("${ADMIN_PASSWORD}")
    private String adminPassword;

    @Value("${TOKEN_AUTH}")
    private String token;

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder encoder) {
        UserDetails admin = User.withUsername("admin")
                .password(encoder.encode(adminPassword))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TokenAuthFilter tokenAuthFilter() {
        return new TokenAuthFilter(token);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, TokenAuthFilter tokenAuthFilter) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/v3/api-docs",
                                "/swagger-resources/**"
                        ).hasRole("ADMIN")
                        .requestMatchers("/api/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(tokenAuthFilter, BasicAuthenticationFilter.class)
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(customAuthEntryPoint)
                        .accessDeniedHandler(customAcessDeniedHandler)
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults());

        return http.build();
    }
}
