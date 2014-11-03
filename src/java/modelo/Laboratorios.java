/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @web http://www.diegoacuario.blogspot.com
 * @author diegoacuario
 */
@Entity
@Table(name = "laboratorios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Laboratorios.findAll", query = "SELECT l FROM Laboratorios l"),
    @NamedQuery(name = "Laboratorios.findByIdLaboratorio", query = "SELECT l FROM Laboratorios l WHERE l.idLaboratorio = :idLaboratorio"),
    @NamedQuery(name = "Laboratorios.findByCodigo", query = "SELECT l FROM Laboratorios l WHERE l.codigo = :codigo"),
    @NamedQuery(name = "Laboratorios.findByNombre", query = "SELECT l FROM Laboratorios l WHERE l.nombre = :nombre"),
    @NamedQuery(name = "Laboratorios.findByDescripcion", query = "SELECT l FROM Laboratorios l WHERE l.descripcion = :descripcion")})
public class Laboratorios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_laboratorio")
    private Integer idLaboratorio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLaboratorio")
    private List<Equipos> equiposList;

    public Laboratorios() {
    }

    public Laboratorios(Integer idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public Laboratorios(Integer idLaboratorio, String codigo, String nombre, String descripcion) {
        this.idLaboratorio = idLaboratorio;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Laboratorios(String codigo, String nombre, String descripcion) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(Integer idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Equipos> getEquiposList() {
        return equiposList;
    }

    public void setEquiposList(List<Equipos> equiposList) {
        this.equiposList = equiposList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLaboratorio != null ? idLaboratorio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Laboratorios)) {
            return false;
        }
        Laboratorios other = (Laboratorios) object;
        if ((this.idLaboratorio == null && other.idLaboratorio != null) || (this.idLaboratorio != null && !this.idLaboratorio.equals(other.idLaboratorio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Laboratorios[ idLaboratorio=" + idLaboratorio + " ]";
    }

}
