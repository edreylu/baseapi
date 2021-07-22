package com.example.baseapi.usuario;
import java.util.Calendar;
import java.util.Objects;

/**
 *
 * @author Edward Reyes
 */

public class UsuarioDTO {
    private int noUsuario;
    private String userName;
    private String password;
    private String encrytedPassword;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombreCompleto;
    private String correo;
    private String telefono;
    private String telefono2;
    private Calendar fechaAuditoria;
    private Integer noPersonal;
    private Integer noInmueble;
    private Integer idEstatus;
    private Integer enabled;


    public int getNoUsuario() {
        return noUsuario;
    }

    public void setNoUsuario(int noUsuario) {
        this.noUsuario = noUsuario;
    }

    public String getUserName() {
        return Objects.nonNull(userName) ? userName.toUpperCase() : userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEncrytedPassword() {
        return encrytedPassword;
    }

    public String getPassword() {
        return Objects.nonNull(password) ? password.toUpperCase() : password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEncrytedPassword(String encrytedPassword) {
        this.encrytedPassword = encrytedPassword;
    }

    public String getNombre() {
        return Objects.nonNull(nombre) ? nombre.toUpperCase() : nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return Objects.nonNull(apellidoPaterno) ? apellidoPaterno.toUpperCase() : apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return Objects.nonNull(apellidoMaterno) ? apellidoMaterno.toUpperCase() : apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public Calendar getFechaAuditoria() {
        return fechaAuditoria;
    }

    public void setFechaAuditoria(Calendar fechaAuditoria) {
        this.fechaAuditoria = fechaAuditoria;
    }

    public Integer getNoPersonal() {
        return noPersonal;
    }

    public void setNoPersonal(Integer noPersonal) {
        this.noPersonal = noPersonal;
    }

    public Integer getNoInmueble() {
        return noInmueble;
    }

    public void setNoInmueble(Integer noInmueble) {
        this.noInmueble = noInmueble;
    }

    public Integer getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }


}
