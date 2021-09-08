package com.example.baseapi.domain.usuarioRole;
import com.example.baseapi.domain.role.RoleDTO;
import com.example.baseapi.domain.usuario.UsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRoleDTO {

    private int id;
    private UsuarioDTO usuario;
    private List<RoleDTO> roles;

}
