package com.example.baseapi.persistence.entity;
import com.example.baseapi.persistence.entity.Role;
import com.example.baseapi.persistence.entity.Usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Edward Reyes
 */

@Entity
@Table(name = "USUARIOS_ROLES", uniqueConstraints = { @UniqueConstraint(name = "USUARIOS_ROLES_UK", columnNames = { "NO_USUARIO", "NO_ROL" }) })
public class UsuarioRole {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "NO_USUARIO", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "NO_ROL", nullable = false)
    private Role role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}