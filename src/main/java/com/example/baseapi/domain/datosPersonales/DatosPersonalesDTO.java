package com.example.baseapi.domain.datosPersonales;
/**
 *
 * @author Edward Reyes
 */
public class DatosPersonalesDTO {

    private int noPersonal;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombre;
    private String nombreCompleto;
    private String correo;
    private String telefonoFijo;
    private String telefonoMovil;

    public DatosPersonalesDTO() {
        // TODO Auto-generated constructor stub
    }


    public DatosPersonalesDTO(int noPersonal, String apellidoPaterno, String apellidoMaterno, String nombre,
                              String correo, String telefonoFijo, String telefonoMovil) {
        this.noPersonal = noPersonal;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombre = nombre;
        this.correo = correo;
        this.telefonoFijo = telefonoFijo;
        this.telefonoMovil = telefonoMovil;
    }


    public int getNoPersonal() {
        return noPersonal;
    }

    public void setNoPersonal(int noPersonal) {
        this.noPersonal = noPersonal;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

}

