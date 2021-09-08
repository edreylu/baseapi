package com.example.baseapi.domain.estatus;

import java.io.Serializable;

/**
 *
 * @author Edward Reyes
 */
public class EstatusDTO implements Serializable {

    private int idEstatus;
    private String clave;
    private String descripcion;
    private String colorhex;
    private String explicacion;

    public EstatusDTO() {

    }


    public EstatusDTO(int idEstatus, String clave, String descripcion, String colorhex, String explicacion) {
        this.idEstatus = idEstatus;
        this.clave = clave;
        this.descripcion = descripcion;
        this.colorhex = colorhex;
        this.explicacion = explicacion;
    }


    public int getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(int idEstatus) {
        this.idEstatus = idEstatus;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getExplicacion() {
        return explicacion;
    }

    public void setExplicacion(String explicacion) {
        this.explicacion = explicacion;
    }

    public String getColorhex() {
        return colorhex;
    }

    public void setColorhex(String colorhex) {
        this.colorhex = colorhex;
    }

}
