package com.example.baseapi.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface RoleRepository extends JpaRepository<Role, Integer>{

    @Query("Select ur.role.roleName from UsuarioRole ur where ur.usuario.noUsuario = :noUsuario ")
    List<String> findByUsuario(@Param("noUsuario")  Integer noUsuario);

}
