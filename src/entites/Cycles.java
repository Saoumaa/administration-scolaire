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
@Table(name = "CYCLES", catalog = "scolaire2", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cycles.findAll", query = "SELECT c FROM Cycles c"),
    @NamedQuery(name = "Cycles.findByIdCycles", query = "SELECT c FROM Cycles c WHERE c.idCycles = :idCycles"),
    @NamedQuery(name = "Cycles.findByCyclesAbrev", query = "SELECT c FROM Cycles c WHERE c.cyclesAbrev = :cyclesAbrev"),
    @NamedQuery(name = "Cycles.findByLibCycles", query = "SELECT c FROM Cycles c WHERE c.libCycles = :libCycles")})
public class Cycles implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CYCLES", nullable = false)
    private Integer idCycles;
    @Column(name = "CYCLES_ABREV", length = 10)
    private String cyclesAbrev;
    @Column(name = "LIB_CYCLES", length = 25)
    private String libCycles;
    @OneToMany(mappedBy = "idCycles")
    private List<Promotion> promotionList;

    public Cycles() {
    }

    public Cycles(Integer idCycles) {
        this.idCycles = idCycles;
    }

    public Integer getIdCycles() {
        return idCycles;
    }

    public void setIdCycles(Integer idCycles) {
        this.idCycles = idCycles;
    }

    public String getCyclesAbrev() {
        return cyclesAbrev;
    }

    public void setCyclesAbrev(String cyclesAbrev) {
        this.cyclesAbrev = cyclesAbrev;
    }

    public String getLibCycles() {
        return libCycles;
    }

    public void setLibCycles(String libCycles) {
        this.libCycles = libCycles;
    }

    @XmlTransient
    public List<Promotion> getPromotionList() {
        return promotionList;
    }

    public void setPromotionList(List<Promotion> promotionList) {
        this.promotionList = promotionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCycles != null ? idCycles.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cycles)) {
            return false;
        }
        Cycles other = (Cycles) object;
        if ((this.idCycles == null && other.idCycles != null) || (this.idCycles != null && !this.idCycles.equals(other.idCycles))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entites.Cycles[ idCycles=" + idCycles + " ]";
    }
    
}
