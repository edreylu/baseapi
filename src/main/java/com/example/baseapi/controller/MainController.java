package com.example.baseapi.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.baseapi.filter.JwtResponse;
import com.example.baseapi.filter.JwtUtils;
import com.example.baseapi.filter.LoginForm;
import com.example.baseapi.domain.role.RoleDTO;
import com.example.baseapi.domain.usuario.UsuarioDTO;
import com.example.baseapi.domain.usuario.UsuarioService;
import com.example.baseapi.domain.usuarioRole.UsuarioRoleDTO;
import com.example.baseapi.domain.usuarioRole.UsuarioRoleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
@Slf4j
@RequestMapping("/auth")
@RestController
@CrossOrigin(origins = "*")
public class MainController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    private final UsuarioService usuarioService;
    private final UsuarioRoleService usuarioRoleService;

    public MainController(UsuarioRoleService usuarioRoleService, UsuarioService usuarioService) {
        this.usuarioRoleService = usuarioRoleService;
        this.usuarioService = usuarioService;
    }

    @SneakyThrows
    @PostMapping("/login")
    @ApiOperation("Get login to authenticate the user")
    @ApiResponse(code = 200, message = "ok")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginForm loginForm) throws IOException {
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        log.info("User name is {}", username);
        log.info("Password is {}", password);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        String jwtRefresh = jwtUtils.generateRefreshJwtToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        UsuarioDTO usuarioDTO = usuarioService.findByUserName(username);
        return ResponseEntity.ok(new JwtResponse(
                userDetails.getUsername(),
                roles,
                jwt,
                usuarioDTO.getDatosPersonales().getNombreCompleto()));
    }

    @SneakyThrows
    @GetMapping("token/refresh")
    @ApiOperation("Get refresh token to authenticate the user")
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
