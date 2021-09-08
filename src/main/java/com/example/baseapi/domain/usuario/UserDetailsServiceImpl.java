package com.example.baseapi.domain.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.baseapi.persistence.entity.Usuario;
import com.example.baseapi.persistence.repository.RoleRepository;
import com.example.baseapi.persistence.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Edward Reyes
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        userName = Objects.nonNull(userName) ? userName.toUpperCase() : userName;
        Usuario usuario = this.usuarioRepository.findByUserName(userName);

        if (Objects.isNull(usuario)) {
            System.out.println("Usuario no encontrado! " + userName);
            throw new UsernameNotFoundException("User " + userName + " no fue encontrado en base de datos.");
        }

        if (usuario.getEnabled()==0) {
            System.out.println("Usuario Inactivo! " + userName);
            throw new UsernameNotFoundException("Usuario " + userName + " Esta inactivo!");
        }

        System.out.println("Found User: " + usuario);
        List<String> roleNames = this.roleRepository.findByUsuario(usuario.getNoUsuario());
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (Objects.nonNull(roleNames)) {
            for (String role : roleNames) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }

        UserDetails userDetails = (UserDetails) new User(usuario.getUserName(),usuario.getEncrytedPassword(), grantList);

        return userDetails;
    }

}
