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
@Table(name = "GROUP_PEDAG", catalog = "scolaire2", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GroupPedag.findAll", query = "SELECT g FROM GroupPedag g"),
    @NamedQuery(name = "GroupPedag.findByIdGroupPedag", query = "SELECT g FROM GroupPedag g WHERE g.idGroupPedag = :idGroupPedag"),
    @NamedQuery(name = "GroupPedag.findByGroupPedagAbrev", query = "SELECT g FROM GroupPedag g WHERE g.groupPedagAbrev = :groupPedagAbrev"),
    @NamedQuery(name = "GroupPedag.findByLibGroupPedag", query = "SELECT g FROM GroupPedag g WHERE g.libGroupPedag = :libGroupPedag"),
    @NamedQuery(name = "GroupPedag.findByEffectif", query = "SELECT g FROM GroupPedag g WHERE g.effectif = :effectif"),
    @NamedQuery(name = "GroupPedag.findByMoyPass", query = "SELECT g FROM GroupPedag g WHERE g.moyPass = :moyPass"),
    @NamedQuery(name = "GroupPedag.findByIdSerie", query = "SELECT g FROM GroupPedag g WHERE g.idSerie = :idSerie"),
    @NamedQuery(name = "GroupPedag.findByIdPromo", query = "SELECT g FROM GroupPedag g WHERE g.idPromo = :idPromo")})
public class GroupPedag implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_GROUP_PEDAG", nullable = false)
    private Integer idGroupPedag;
    @Column(name = "GROUP_PEDAG_ABREV", length = 8)
    private String groupPedagAbrev;
    @Column(name = "LIB_GROUP_PEDAG", length = 35)
    private String libGroupPedag;
    @Column(name = "EFFECTIF")
    private Short effectif;
    @Column(name = "MOY_PASS")
    private Short moyPass;
    @Column(name = "ID_SERIE")
    private Integer idSerie;
    @Column(name = "ID_PROMO")
    private Integer idPromo;
    @OneToMany(mappedBy = "idGroupPedag")
    private List<Enseigner> enseignerList;

    public GroupPedag() {
    }

    public GroupPedag(Integer idGroupPedag) {
        this.idGroupPedag = idGroupPedag;
    }

    public Integer getIdGroupPedag() {
        return idGroupPedag;
    }

    public void setIdGroupPedag(Integer idGroupPedag) {
        this.idGroupPedag = idGroupPedag;
    }

    public String getGroupPedagAbrev() {
        return groupPedagAbrev;
    }

    public void setGroupPedagAbrev(String groupPedagAbrev) {
        this.groupPedagAbrev = groupPedagAbrev;
    }

    public String getLibGroupPedag() {
        return libGroupPedag;
    }

    public void setLibGroupPedag(String libGroupPedag) {
        this.libGroupPedag = libGroupPedag;
    }

    public Short getEffectif() {
        return effectif;
    }

    public void setEffectif(Short effectif) {
        this.effectif = effectif;
    }

    public Short getMoyPass() {
        return moyPass;
    }

    public void setMoyPass(Short moyPass) {
        this.moyPass = moyPass;
    }

    public Integer getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(Integer idSerie) {
        this.idSerie = idSerie;
    }

    public Integer getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(Integer idPromo) {
        this.idPromo = idPromo;
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
        hash += (idGroupPedag != null ? idGroupPedag.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupPedag)) {
            return false;
        }
        GroupPedag other = (GroupPedag) object;
        if ((this.idGroupPedag == null && other.idGroupPedag != null) || (this.idGroupPedag != null && !this.idGroupPedag.equals(other.idGroupPedag))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entites.GroupPedag[ idGroupPedag=" + idGroupPedag + " ]";
    }
    
}
