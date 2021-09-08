package com.example.baseapi.domain.unidadResponsable;

import com.example.baseapi.domain.estatus.EstatusDTO;
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
public class UnidadResponsableDTO implements Serializable{

    private String claveUr;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFinal;
    private EstatusDTO estatus;

}