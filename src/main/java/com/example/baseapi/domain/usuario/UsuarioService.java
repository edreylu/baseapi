package com.example.baseapi.domain.usuario;


import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.*;

import com.example.baseapi.persistence.entity.DatosPersonales;
import com.example.baseapi.persistence.entity.Usuario;
import com.example.baseapi.persistence.repository.DatosPersonalesRepository;
import com.example.baseapi.persistence.repository.UsuarioRepository;
import com.example.baseapi.utils.EmailSender;
import com.example.baseapi.utils.Mensaje;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;


/**
 *
 * @author Edward Reyes
 */
@Slf4j
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final DatosPersonalesRepository datosPersonalesRepository;
    private final EmailSender emailSender;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private UsuarioDTO usuarioDTO;
    private Mensaje msg;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, DatosPersonalesRepository datosPersonalesRepository, EmailSender emailSender) {
        this.usuarioRepository = usuarioRepository;
        this.datosPersonalesRepository = datosPersonalesRepository;
        this.emailSender = emailSender;
    }

    public List<UsuarioDTO> findAll() {
        return UsuarioMapper.toUsuariosDTOs((List<Usuario>) usuarioRepository.findAll(Sort.by("noUsuario")));
    }

    public List<UsuarioDTO> findAll(int page, int size) {
        return UsuarioMapper.toUsuariosDTOs(usuarioRepository.findAll(PageRequest.of(page,size, Sort.by("noUsuario"))).getContent());
    }

    public UsuarioDTO findById(int id) {
        Usuario usuario = usuarioRepository.findByNoUsuario(id);
        return UsuarioMapper.toUsuarioDTO(usuario);
    }

    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        try {
            usuarioDTO.setEncrytedPassword(encoder.encode(usuarioDTO.getPassword()));
            usuarioDTO.setFechaAuditoria(Date.from(Instant.now()));
            usuarioDTO.setIdEstatus(1);
            usuarioDTO.setEnabled(1);
            usuarioRepository.save(UsuarioMapper.toUsuario(usuarioDTO));
            System.out.println("se guardo");
        } catch (Exception e) {
            System.out.println("no se guardo "+e.getMessage());
            throw e;
        }
        return usuarioDTO;
    }

    public UsuarioDTO update(UsuarioDTO usuarioDTO, int id) {
        try {
            Usuario usuario = UsuarioMapper.setToUpdate(usuarioRepository.findByNoUsuario(id), usuarioDTO);
            usuarioRepository.save(usuario);
        } catch (Exception e) {

        }
        return usuarioDTO;
    }

    public Mensaje updateNoInmueble(int noInmueble, int noUsuario) {
        boolean existsInmueble = usuarioRepository.existsUsuarioByNoInmuebleAndNoUsuarioNot(noInmueble, noUsuario);
        if (existsInmueble) {
            msg = Mensaje.CREATE("No se pudo asignar inmueble por que alguien mas lo tiene asignado", 2);
        } else {
            try {
                Usuario usuario = usuarioRepository.findByNoUsuario(noUsuario);
                usuario.setNoInmueble(noInmueble);
                usuarioRepository.save(usuario);
                msg = Mensaje.CREATE("Inmueble asignado correctamente", 1);
            } catch (Exception e) {
                msg = Mensaje.CREATE("No se pudo asignar por: " + e.getMessage(), 2);
            }
        }
        return msg;
    }

    public Mensaje deleteById(int id, int idEstatus) {

        String action = idEstatus == 1 ? "Activado" : "Inactivado";
        try {
            Usuario usuario = usuarioRepository.findByNoUsuario(id);
            usuario.setEnabled(idEstatus == 1 ? 2 : 1);
            usuarioRepository.save(usuario);
            msg = Mensaje.CREATE(action + " correctamente", 1);
        } catch (Exception e) {
            msg = Mensaje.CREATE("No se pudo " + action + " por: " + e.getMessage(), 2);
        }
        return msg;

    }

    public Mensaje updateResetPasswordToken(String token, String email, String resetPasswordLink) {
        DatosPersonales datosPersonales = datosPersonalesRepository.findByCorreo(email);
        if (Objects.nonNull(datosPersonales)) {
            Usuario usuario = usuarioRepository.findByNoPersonal(datosPersonales.getNoPersonal());
            if (Objects.nonNull(usuario)) {
                usuario.setResetPasswordToken(token);
                usuarioRepository.save(usuario);
                try {
                    emailSender.sendEmailReinicioPassword(email, resetPasswordLink);
                    msg = Mensaje.CREATE("Enviamos un link a su email para reiniciar su password.", 1);
                } catch (UnsupportedEncodingException | MessagingException e) {
                    msg = Mensaje.CREATE("Error mientras se enviaba email", 2);
                }
            }
            else {
                msg = Mensaje.CREATE("No se pudo encontrar ningun usuario el numero de personal: " + datosPersonales.getNoPersonal(), 2);
            }
        }
        else {
            msg = Mensaje.CREATE("No se pudo encontrar ningun dato personal con el Email: " + email, 2);
         }
        return msg;
    }

    public Mensaje getByResetPasswordToken(String token) {
        Usuario usuario = usuarioRepository.findByResetPasswordToken(token);
        if (Objects.nonNull(usuario)) {
            msg = Mensaje.CREATE("Token Valido", 1);
        } else {
            msg = Mensaje.CREATE("Token invalido", 2);
        }
        return msg;
    }

    public Mensaje updatePassword(String token, String newPassword) {
        try {
            Usuario usuario = usuarioRepository.findByResetPasswordToken(token);
            String encodedPassword = encoder.encode(newPassword);
            usuario.setEncrytedPassword(encodedPassword);
            usuario.setResetPasswordToken(null);
            usuarioRepository.save(usuario);
            msg = Mensaje.CREATE("Tu password ha sido cambiado", 1);
        } catch (Exception e) {
            msg = Mensaje.CREATE("Ha ocurrido un error al cambiar tu password", 2);
        }
        return msg;
    }

    public UsuarioDTO findByUserName(String userName) {
        Usuario usuario = usuarioRepository.findByUserName(userName.toUpperCase());
        return UsuarioMapper.toUsuarioDTO(usuario);
    }

    public Mensaje updatePerfil(UsuarioDTO usuarioDTO, String userName) {
        try {
            Usuario usuario = UsuarioMapper.setToUpdatePerfil(usuarioRepository.findByUserName(userName), usuarioDTO);
            usuarioRepository.save(usuario);
            msg = Mensaje.CREATE("Agregado correctamente", 1);
        } catch (Exception e) {
            msg = Mensaje.CREATE("No se pudo agregar por: " + e.getMessage(), 2);
        }
        return msg;
    }
}
