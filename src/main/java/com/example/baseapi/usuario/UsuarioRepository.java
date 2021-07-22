package com.example.baseapi.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Edward Reyes
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

    Usuario findByUserName(String userName);
    Usuario findByNoUsuario(Integer noUsuario);
    Usuario findByCorreo(String correo);
    Usuario findByResetPasswordToken(String token);
    boolean existsUsuarioByNoInmuebleAndNoUsuarioNot(Integer noInmueble, Integer noUsuario);
}
