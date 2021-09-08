package com.example.baseapi.persistence.repository;

import com.example.baseapi.persistence.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Edward Reyes
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

    Usuario findByUserName(String userName);
    Usuario findByNoUsuario(Integer noUsuario);
    Usuario findByNoPersonal(Integer noPersonal);
    Usuario findByResetPasswordToken(String token);
    boolean existsUsuarioByNoInmuebleAndNoUsuarioNot(Integer noInmueble, Integer noUsuario);
}
