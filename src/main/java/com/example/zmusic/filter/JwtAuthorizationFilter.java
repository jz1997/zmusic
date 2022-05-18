package com.example.zmusic.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.zmusic.constants.AuthenticationConfigConstants;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(AuthenticationConfigConstants.HEADER_STRING);

        if (header == null || !header.startsWith(AuthenticationConfigConstants.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken token = getUsernamePasswordAuthenticationToken(request);

        SecurityContextHolder.getContext().setAuthentication(token);

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(AuthenticationConfigConstants.HEADER_STRING);
        if (token != null) {
            String username = JWT.require(Algorithm.HMAC512(AuthenticationConfigConstants.SECRET))
                    .build()
                    .verify(token.replace(AuthenticationConfigConstants.TOKEN_PREFIX, ""))
                    .getSubject();
            if (username == null) {
                return null;
            }

            return new UsernamePasswordAuthenticationToken(
                    username, null, new ArrayList<>()
            );
        }
        return null;
    }
}
