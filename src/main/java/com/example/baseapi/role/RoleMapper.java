package com.example.baseapi.role;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class RoleMapper {

    public RoleDTO toRoleDTO(Role role) {
        if (Objects.isNull(role)) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setNoRole(role.getNoRole());
        roleDTO.setRoleName(role.getRoleName());

        return roleDTO;
    }

    public List<RoleDTO> toRoleDTOs(List<Role> roles) {
        if (Objects.isNull(roles)) {
            return null;
        }
        List<RoleDTO> list = new ArrayList<>(roles.size());
        for (Role role : roles) {
            list.add(toRoleDTO(role));
        }
        return list;
    }

    public Role toRole(RoleDTO roleDTO) {
        if (Objects.isNull(roleDTO)) {
            return null;
        }

        Role role = new Role();
        role.setNoRole(roleDTO.getNoRole());
        role.setRoleName(roleDTO.getRoleName());

        return role;
    }

    public Role setToUpdate(Role roleFound, RoleDTO roleDTO) {
        roleFound.setRoleName("ROLE_" + roleDTO.getRoleName());
        return roleFound;
    }
}
