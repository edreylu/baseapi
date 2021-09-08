package com.example.baseapi.domain.usuarioRole;

import com.example.baseapi.persistence.entity.UsuarioRole;
import com.example.baseapi.persistence.repository.UsuarioRoleRepository;
import com.example.baseapi.domain.role.RoleDTO;
import com.example.baseapi.domain.role.RoleMapper;
import com.example.baseapi.persistence.repository.RoleRepository;
import com.example.baseapi.persistence.entity.Usuario;
import com.example.baseapi.domain.usuario.UsuarioDTO;
import com.example.baseapi.domain.usuario.UsuarioMapper;
import com.example.baseapi.persistence.repository.UsuarioRepository;
import com.example.baseapi.utils.Mensaje;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioRoleService {

    private final UsuarioRoleRepository usuarioRoleRepository;
    private final RoleRepository roleRepository;
    private final UsuarioRepository usuarioRepository;
    private Mensaje msg;

    public UsuarioRoleService(UsuarioRoleRepository usuarioRoleRepository, RoleRepository roleRepository, UsuarioRepository usuarioRepository) {
        this.usuarioRoleRepository = usuarioRoleRepository;
        this.roleRepository = roleRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioRoleDTO findByNoUsuario(int id) {
        Usuario usuario = usuarioRepository.findByNoUsuario(id);
        UsuarioDTO usuarioDTO = UsuarioMapper.toUsuarioDTO(usuario);
        List<RoleDTO> rolesDtos = RoleMapper.toRoleDTOs(roleRepository.findAll());

        rolesDtos.forEach(role -> {
            boolean selected = usuario.getUsuariosRoles().stream().anyMatch(userRole -> userRole.getRole().getNoRole() == role.getNoRole());
            role.setSelected(selected);
        });
        UsuarioRoleDTO usuarioRoleDTO = new UsuarioRoleDTO();
        usuarioRoleDTO.setRoles(rolesDtos);
        usuarioRoleDTO.setUsuario(usuarioDTO);
        return usuarioRoleDTO;
    }

    public Mensaje assignRoleToUser(UsuarioRoleDTO usuarioRoleDTO, int id) {

        try {
            usuarioRoleDTO.getRoles().forEach(roleDTO -> {
                UsuarioRole usuarioRole = new UsuarioRole();
                usuarioRole.setUsuario(usuarioRepository.findByNoUsuario(id));
                usuarioRole.setRole(RoleMapper.toRole(roleDTO));
                ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id");
                Example<UsuarioRole> example = Example.of(usuarioRole, exampleMatcher);
                boolean exists = usuarioRoleRepository.exists(example);
                if (roleDTO.isSelected()) {
                    if (!exists) {
                        usuarioRoleRepository.save(usuarioRole);
                    }
                } else {
                    if (exists) {
                        usuarioRole = usuarioRoleRepository.findByNoUsuarioAndNoRole(id, roleDTO.getNoRole());
                        usuarioRoleRepository.delete(usuarioRole);
                    }
                }
            });
            msg = Mensaje.CREATE("Pantallas asignadas correctamente", 1);
        } catch (Exception e) {
            msg = Mensaje.CREATE("Pantallas no se pudieron asignar", 2);
        }
        return msg;
    }
}
