package com.example.baseapi.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;
import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    private String jwtSecret = "1nm3s";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals("/auth/login") || request.getServletPath().equals("/auth/token/refresh")){
            filterChain.doFilter(request,response);
        }
        else{
            String autorizationHeader = request.getHeader(AUTHORIZATION);
            if(autorizationHeader != null && autorizationHeader.startsWith("Bearer ")){
                try {
                    String token = autorizationHeader.substring("Bearer ".length());
                        Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
                        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                        DecodedJWT decodedJWT = jwtVerifier.verify(token);
                        String username = decodedJWT.getSubject();
                        String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                        stream(roles).forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(username, null, authorities);
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        filterChain.doFilter(request, response);
                }catch (Exception exception){
                    log.error("Error login in {} ", exception.getMessage());
                    response.setStatus(SC_FORBIDDEN);
                    Map<String,String> errors = new HashMap<>();
                    errors.put("error",exception.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), errors);
                }
            }
            else{
                filterChain.doFilter(request,response);
            }
        }
    }
}
