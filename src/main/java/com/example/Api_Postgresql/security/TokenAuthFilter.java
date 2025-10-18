package com.example.Api_Postgresql.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class TokenAuthFilter extends OncePerRequestFilter {

    private final String userToken;
    private final String adminToken;

    public TokenAuthFilter(String userToken, String adminToken) {
        this.userToken = userToken;
        this.adminToken = adminToken;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();
        if (path.startsWith("/swagger") || path.startsWith("/v3/api-docs") ||
                path.equals("/login") || path.equals("/logout")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!path.startsWith("/api/")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token ausente");
            return;
        }

        UsernamePasswordAuthenticationToken authentication;

        if (authHeader.equals("Bearer " + adminToken)) {
            authentication = new UsernamePasswordAuthenticationToken(
                    "adminUser",
                    null,
                    List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
            );
        } else if (authHeader.equals("Bearer " + userToken)) {
            authentication = new UsernamePasswordAuthenticationToken(
                    "normalUser",
                    null,
                    List.of(new SimpleGrantedAuthority("ROLE_USER"))
            );
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido");
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
