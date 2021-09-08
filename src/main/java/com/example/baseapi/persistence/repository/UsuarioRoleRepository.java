package com.example.baseapi.persistence.repository;

import com.example.baseapi.persistence.entity.UsuarioRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRoleRepository extends JpaRepository<UsuarioRole, Integer> {

    @Query("Select ur from UsuarioRole ur where ur.usuario.noUsuario = :noUsuario and ur.role.noRole = :noRole ")
    UsuarioRole findByNoUsuarioAndNoRole(@Param("noUsuario") Integer noUsuario, @Param("noRole") Integer noRole);

}
