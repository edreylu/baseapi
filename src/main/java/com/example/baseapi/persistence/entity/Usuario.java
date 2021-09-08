package com.example.baseapi.persistence.entity;


import com.example.baseapi.domain.unidadResponsable.UnidadResponsable;

import java.io.Serializable;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Edward Reyes
 */
@Entity
@Table(name = "USUARIOS", uniqueConstraints = { @UniqueConstraint(name = "USUARIOS_UK", columnNames = "USERNAME") })
public class Usuario implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "NO_USUARIO", nullable = false)
    private Integer noUsuario;

    @Column(name = "USERNAME", length = 30, nullable = false)
    private String userName;

    @Column(name = "ENCRYPTED_PASSWORD", length = 300, nullable = false)
    private String encrytedPassword;

    @Basic(optional = false)
    @Column(name = "FECHA_AUDITORIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAuditoria;

    @Column(name = "NO_INMUEBLE")
    private Integer noInmueble;

    @Column(name = "CLAVE_UR", insertable = false, updatable = false)
    private String clavaUr;

    @Column(name = "NO_PERSONAL", insertable = false, updatable = false)
    private Integer noPersonal;

    @Basic(optional = false)
    @Column(name = "ENABLED")
    private Integer enabled;

    @Column(name = "RESET_PASSWORD_TOKEN", length = 30, nullable = true)
    private String resetPasswordToken;

    @Column(name = "ID_ESTATUS", length = 2, insertable = false, updatable = false)
    private Integer idEstatus;

    @OneToMany(mappedBy = "usuario")
    private List<UsuarioRole> usuariosRoles;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "NO_PERSONAL", nullable = false)
    private DatosPersonales datosPersonales;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CLAVE_UR", nullable = false)
    private UnidadResponsable unidadResponsable;

    @JoinColumn(name = "ID_ESTATUS", referencedColumnName = "ID_ESTATUS")
    @ManyToOne(optional = false)
    private Estatus estatus;


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

    public Date getFechaAuditoria() {
        return fechaAuditoria;
    }

    public void setFechaAuditoria(Date fechaAuditoria) {
        this.fechaAuditoria = fechaAuditoria;
    }

    public Integer getNoInmueble() {
        return noInmueble;
    }

    public void setNoInmueble(Integer noInmueble) {
        this.noInmueble = noInmueble;
    }

    public String getClavaUr() {
        return clavaUr;
    }

    public void setClavaUr(String clavaUr) {
        this.clavaUr = clavaUr;
    }

    public Integer getNoPersonal() {
        return noPersonal;
    }

    public void setNoPersonal(Integer noPersonal) {
        this.noPersonal = noPersonal;
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

    public Integer getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }

    public List<UsuarioRole> getUsuariosRoles() {
        return usuariosRoles;
    }

    public void setUsuariosRoles(List<UsuarioRole> usuariosRoles) {
        this.usuariosRoles = usuariosRoles;
    }

    public DatosPersonales getDatosPersonales() {
        return datosPersonales;
    }

    public void setDatosPersonales(DatosPersonales datosPersonales) {
        this.datosPersonales = datosPersonales;
    }

    public UnidadResponsable getUnidadResponsable() {
        return unidadResponsable;
    }

    public void setUnidadResponsable(UnidadResponsable unidadResponsable) {
        this.unidadResponsable = unidadResponsable;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }
}
