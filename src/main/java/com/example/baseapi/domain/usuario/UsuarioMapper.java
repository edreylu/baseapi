package com.example.baseapi.domain.usuario;

import com.example.baseapi.domain.datosPersonales.DatosPersonalesMapper;
import com.example.baseapi.domain.estatus.EstatusMapper;
import com.example.baseapi.persistence.entity.Usuario;
import com.example.baseapi.domain.unidadResponsable.UnidadResponsableMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UsuarioMapper {

    public static UsuarioDTO toUsuarioDTO(Usuario usuario) {
        if (Objects.isNull(usuario)) {
            return null;
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setNoUsuario(usuario.getNoUsuario());
        usuarioDTO.setUserName(usuario.getUserName());
        usuarioDTO.setEncrytedPassword(usuario.getEncrytedPassword());
        usuarioDTO.setFechaAuditoria(usuario.getFechaAuditoria());
        usuarioDTO.setEstatus(EstatusMapper.toEstatusDTO(usuario.getEstatus()));
        usuarioDTO.setEnabled(usuario.getEnabled());
        usuarioDTO.setDatosPersonales(DatosPersonalesMapper.toPersonalDTO(usuario.getDatosPersonales()));
        usuarioDTO.setUnidadResponsable(UnidadResponsableMapper.toUnidadResponsableDTO(usuario.getUnidadResponsable()));
        usuarioDTO.setNoInmueble(usuario.getNoInmueble());
        return usuarioDTO;
    }

    public static List<UsuarioDTO> toUsuariosDTOs(List<Usuario> usuarios) {
        if (Objects.isNull(usuarios)) {
            return null;
        }

        List<UsuarioDTO> list = new ArrayList<>(usuarios.size());
        for (Usuario usuario : usuarios) {
            list.add(toUsuarioDTO(usuario));
        }

        return list;
    }

    public static Usuario toUsuario(UsuarioDTO usuarioDTO) {
        if (Objects.isNull(usuarioDTO)) {
            return null;
        }
        Usuario usuario = new Usuario();

        usuario.setNoUsuario(usuarioDTO.getNoUsuario());
        usuario.setUserName(usuarioDTO.getUserName());
        usuario.setEncrytedPassword(usuarioDTO.getEncrytedPassword());
        usuario.setFechaAuditoria(usuarioDTO.getFechaAuditoria());
        usuario.setEstatus(EstatusMapper.toEstatus(usuarioDTO.getEstatus()));
        usuario.setDatosPersonales(DatosPersonalesMapper.toPersonal(usuarioDTO.getDatosPersonales()));
        usuario.setUnidadResponsable(UnidadResponsableMapper.toUnidadResponsable(usuarioDTO.getUnidadResponsable()));
        usuario.setEnabled(usuarioDTO.getEnabled());
        usuario.setNoInmueble(usuarioDTO.getNoInmueble());
        return usuario;
    }

    public static Usuario setToUpdate(Usuario usuarioFound, UsuarioDTO usuarioDTO) {
        usuarioFound.setUserName(usuarioDTO.getUserName());
        return usuarioFound;
    }

    public static Usuario setToUpdatePerfil(Usuario usuarioFound, UsuarioDTO usuarioDTO) {
        return usuarioFound;
    }
}
