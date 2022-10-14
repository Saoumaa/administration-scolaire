/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author OBAMA
 */
@Entity
@Table(name = "MATIERE", catalog = "scolaire2", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Matiere.findAll", query = "SELECT m FROM Matiere m"),
    @NamedQuery(name = "Matiere.findByIdMatiere", query = "SELECT m FROM Matiere m WHERE m.idMatiere = :idMatiere"),
    @NamedQuery(name = "Matiere.findByLibMatiere", query = "SELECT m FROM Matiere m WHERE m.libMatiere = :libMatiere")})
public class Matiere implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_MATIERE", nullable = false)
    private Integer idMatiere;
    @Column(name = "LIB_MATIERE", length = 64)
    private String libMatiere;
    @OneToMany(mappedBy = "idMatiere")
    private List<Coefficient> coefficientList;
    @OneToMany(mappedBy = "idMatiere")
    private List<Enseigner> enseignerList;

    public Matiere() {
    }

    public Matiere(Integer idMatiere) {
        this.idMatiere = idMatiere;
    }

    public Integer getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(Integer idMatiere) {
        this.idMatiere = idMatiere;
    }

    public String getLibMatiere() {
        return libMatiere;
    }

    public void setLibMatiere(String libMatiere) {
        this.libMatiere = libMatiere;
    }

    @XmlTransient
    public List<Coefficient> getCoefficientList() {
        return coefficientList;
    }

    public void setCoefficientList(List<Coefficient> coefficientList) {
        this.coefficientList = coefficientList;
    }

    @XmlTransient
    public List<Enseigner> getEnseignerList() {
        return enseignerList;
    }

    public void setEnseignerList(List<Enseigner> enseignerList) {
        this.enseignerList = enseignerList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMatiere != null ? idMatiere.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Matiere)) {
            return false;
        }
        Matiere other = (Matiere) object;
        if ((this.idMatiere == null && other.idMatiere != null) || (this.idMatiere != null && !this.idMatiere.equals(other.idMatiere))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entites.Matiere[ idMatiere=" + idMatiere + " ]";
    }
    
}
