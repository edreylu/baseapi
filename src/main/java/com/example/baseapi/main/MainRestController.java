package com.example.baseapi.main;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.baseapi.role.RoleDTO;
import com.example.baseapi.usuario.UserDetailsServiceImpl;
import com.example.baseapi.usuario.UsuarioDTO;
import com.example.baseapi.usuario.UsuarioService;
import com.example.baseapi.usuarioRole.UsuarioRoleDTO;
import com.example.baseapi.usuarioRole.UsuarioRoleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
@Slf4j
@RequestMapping("/")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MainRestController {
    private final UsuarioService usuarioService;
    private final UsuarioRoleService usuarioRoleService;

    public MainRestController(UsuarioRoleService usuarioRoleService, UsuarioService usuarioService) {
        this.usuarioRoleService = usuarioRoleService;
        this.usuarioService = usuarioService;
    }

    @SneakyThrows
    @GetMapping("token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response){
        String autorizationHeader = request.getHeader(AUTHORIZATION);
        if(autorizationHeader != null && autorizationHeader.startsWith("Bearer ")){
            try {
                String refresh_token = autorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("1nm3s".getBytes());
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                UsuarioDTO usuario = usuarioService.findByUserName(username);
                UsuarioRoleDTO usuarioRole = usuarioRoleService.findByNoUsuario(usuario.getNoUsuario());
                String access_token = JWT.create()
                        .withSubject(usuario.getUserName())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", usuarioRole.getRoles().stream().map(RoleDTO::getRoleName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String,String> tokens = new HashMap<>();
                tokens.put("access_token",access_token);
                tokens.put("refresh_token",refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }catch (Exception exception){
                response.setStatus(SC_FORBIDDEN);
                Map<String,String> errors = new HashMap<>();
                errors.put("error",exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), errors);
            }
        }else{
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
