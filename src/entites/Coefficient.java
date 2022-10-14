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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "COEFFICIENT", catalog = "scolaire2", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Coefficient.findAll", query = "SELECT c FROM Coefficient c"),
    @NamedQuery(name = "Coefficient.findByIdCoef", query = "SELECT c FROM Coefficient c WHERE c.idCoef = :idCoef"),
    @NamedQuery(name = "Coefficient.findByValeurCoef", query = "SELECT c FROM Coefficient c WHERE c.valeurCoef = :valeurCoef"),
    @NamedQuery(name = "Coefficient.findByIdGroupPedag", query = "SELECT c FROM Coefficient c WHERE c.idGroupPedag = :idGroupPedag")})
public class Coefficient implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_COEF", nullable = false)
    private Integer idCoef;
    @Column(name = "VALEUR_COEF")
    private Short valeurCoef;
    @Column(name = "ID_GROUP_PEDAG")
    private Long idGroupPedag;
    @OneToMany(mappedBy = "idCoef")
    private List<DetailsBulletin> detailsBulletinList;
    @JoinColumn(name = "ID_MATIERE", referencedColumnName = "ID_MATIERE")
    @ManyToOne
    private Matiere idMatiere;

    public Coefficient() {
    }

    public Coefficient(Integer idCoef) {
        this.idCoef = idCoef;
    }

    public Integer getIdCoef() {
        return idCoef;
    }

    public void setIdCoef(Integer idCoef) {
        this.idCoef = idCoef;
    }

    public Short getValeurCoef() {
        return valeurCoef;
    }

    public void setValeurCoef(Short valeurCoef) {
        this.valeurCoef = valeurCoef;
    }

    public Long getIdGroupPedag() {
        return idGroupPedag;
    }

    public void setIdGroupPedag(Long idGroupPedag) {
        this.idGroupPedag = idGroupPedag;
    }

    @XmlTransient
    public List<DetailsBulletin> getDetailsBulletinList() {
        return detailsBulletinList;
    }

    public void setDetailsBulletinList(List<DetailsBulletin> detailsBulletinList) {
        this.detailsBulletinList = detailsBulletinList;
    }

    public Matiere getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(Matiere idMatiere) {
        this.idMatiere = idMatiere;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCoef != null ? idCoef.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Coefficient)) {
            return false;
        }
        Coefficient other = (Coefficient) object;
        if ((this.idCoef == null && other.idCoef != null) || (this.idCoef != null && !this.idCoef.equals(other.idCoef))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entites.Coefficient[ idCoef=" + idCoef + " ]";
    }
    
}
