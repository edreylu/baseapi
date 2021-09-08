package com.example.baseapi.domain.role;
import com.example.baseapi.persistence.entity.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RoleMapper {

    public static RoleDTO toRoleDTO(Role role) {
        if (Objects.isNull(role)) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setNoRole(role.getNoRole());
        roleDTO.setRoleName(role.getRoleName());

        return roleDTO;
    }

    public static List<RoleDTO> toRoleDTOs(List<Role> roles) {
        if (Objects.isNull(roles)) {
            return null;
        }
        List<RoleDTO> list = new ArrayList<>(roles.size());
        for (Role role : roles) {
            list.add(toRoleDTO(role));
        }
        return list;
    }

    public static Role toRole(RoleDTO roleDTO) {
        if (Objects.isNull(roleDTO)) {
            return null;
        }

        Role role = new Role();
        role.setNoRole(roleDTO.getNoRole());
        role.setRoleName(roleDTO.getRoleName());

        return role;
    }

    public static Role setToUpdate(Role roleFound, RoleDTO roleDTO) {
        roleFound.setRoleName("ROLE_" + roleDTO.getRoleName());
        return roleFound;
    }
}
