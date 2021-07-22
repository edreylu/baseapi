package com.example.baseapi.usuario;

import com.example.baseapi.usuarioRole.UsuarioRole;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Edward Reyes
 */

@Entity
@Table(name = "USUARIOS", uniqueConstraints = { @UniqueConstraint(name = "USUARIOS_UK", columnNames = "USERNAME") })
public class Usuario {

    @Id
    @GeneratedValue
    @Column(name = "NO_USUARIO", nullable = false)
    private Integer noUsuario;

    @Column(name = "USERNAME", length = 30, nullable = false)
    private String userName;

    @Column(name = "ENCRYPTED_PASSWORD", length = 300, nullable = false)
    private String encrytedPassword;

    @Column(name = "NOMBRE", length = 30, nullable = false)
    private String nombre;

    @Column(name = "APELLIDO_PATERNO", length = 30, nullable = false)
    private String apellidoPaterno;

    @Column(name = "APELLIDO_MATERNO", length = 30, nullable = true)
    private String apellidoMaterno;

    @Column(name = "CORREO", length = 50, nullable = false)
    private String correo;

    @Column(name = "TELEFONO", length = 20, nullable = false)
    private String telefono;

    @Column(name = "TELEFONO2", length = 20, nullable = true)
    private String telefono2;

    @Column(name = "FECHA_AUDITORIA", updatable = false, nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar fechaAuditoria;

    @Column(name = "NOPERSONAL", length = 19, nullable = true)
    private Integer noPersonal;

    @Column(name = "NO_INMUEBLE", length = 19, nullable = true)
    private Integer noInmueble;

    @Column(name = "IDESTATUS", length = 2, nullable = true)
    private Integer idEstatus;

    @Column(name = "ENABLED", length = 1, nullable = false)
    private Integer enabled;

    @Column(name = "RESET_PASSWORD_TOKEN", length = 30, nullable = true)
    private String resetPasswordToken;

    @OneToMany(mappedBy = "usuario")
    private List<UsuarioRole> usuariosRoles;

    public Integer getNoUsuario() {
        return noUsuario;
    }

    public void setNoUsuario(Integer noUsuario) {
        this.noUsuario = noUsuario;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEncrytedPassword() {
        return encrytedPassword;
    }

    public void setEncrytedPassword(String encrytedPassword) {
        this.encrytedPassword = encrytedPassword;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public List<UsuarioRole> getUsuariosRoles() {
        return usuariosRoles;
    }

    public void setUsuariosRoles(List<UsuarioRole> usuariosRoles) {
        this.usuariosRoles = usuariosRoles;
    }
}
