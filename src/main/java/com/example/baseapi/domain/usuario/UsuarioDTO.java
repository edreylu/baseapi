package com.example.baseapi.domain.usuario;
import com.example.baseapi.domain.datosPersonales.DatosPersonalesDTO;
import com.example.baseapi.domain.estatus.EstatusDTO;
import com.example.baseapi.domain.unidadResponsable.UnidadResponsableDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Edward Reyes
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO implements Serializable{
    private int noUsuario;
    private String userName;
    private String password;
    private String encrytedPassword;
    private Date fechaAuditoria;
    private Integer noInmueble;
    private EstatusDTO estatus;
    private Integer idEstatus;
    private Integer enabled;
    private DatosPersonalesDTO datosPersonales;
    private UnidadResponsableDTO unidadResponsable;

    public UsuarioDTO(int noUsuario, String userName, Date fechaAuditoria, EstatusDTO estatus, Integer enabled,
                      DatosPersonalesDTO datosPersonales, UnidadResponsableDTO unidadResponsable) {
        this.noUsuario = noUsuario;
        this.userName = userName;
        this.fechaAuditoria = fechaAuditoria;
        this.estatus = estatus;
        this.enabled = enabled;
        this.datosPersonales = datosPersonales;
        this.unidadResponsable = unidadResponsable;
    }

}