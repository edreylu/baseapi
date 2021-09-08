package com.example.baseapi.domain.role;
import com.example.baseapi.persistence.entity.Role;
import com.example.baseapi.persistence.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolesService {

    private final RoleRepository roleRepository;

    public RolesService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<RoleDTO> findAll() {
        return RoleMapper.toRoleDTOs(roleRepository.findAll());
    }

    public RoleDTO findById(int id) {
        Optional<Role> role = roleRepository.findById(id);
        return RoleMapper.toRoleDTO(role.orElse(new Role()));
    }

    public RoleDTO save(RoleDTO roleDTO) {
        try {
            roleRepository.save(RoleMapper.toRole(roleDTO));
            System.out.println("se guardo");
        } catch (Exception e) {
            System.out.println("no se guardo "+e.getMessage());
            throw e;
        }
        return roleDTO;
    }

    public RoleDTO update(RoleDTO roleDTO, int id) {
        try {
            Role role = RoleMapper.toRole(roleDTO);
            roleRepository.save(role);
        } catch (Exception e) {

        }
        return roleDTO;
    }

    public void deleteById(int id) {

    }
}
