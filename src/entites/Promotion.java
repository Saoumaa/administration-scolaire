/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author OBAMA
 */
@Entity
@Table(name = "PROMOTION", catalog = "scolaire2", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Promotion.findAll", query = "SELECT p FROM Promotion p"),
    @NamedQuery(name = "Promotion.findByIdPromo", query = "SELECT p FROM Promotion p WHERE p.idPromo = :idPromo"),
    @NamedQuery(name = "Promotion.findByLibPromotion", query = "SELECT p FROM Promotion p WHERE p.libPromotion = :libPromotion"),
    @NamedQuery(name = "Promotion.findByPromoAbrev", query = "SELECT p FROM Promotion p WHERE p.promoAbrev = :promoAbrev")})
public class Promotion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_PROMO", nullable = false)
    private Integer idPromo;
    @Column(name = "LIB_PROMOTION", length = 25)
    private String libPromotion;
    @Column(name = "PROMO_ABREV", length = 5)
    private String promoAbrev;
    @JoinColumn(name = "ID_CYCLES", referencedColumnName = "ID_CYCLES")
    @ManyToOne
    private Cycles idCycles;
    @JoinColumn(name = "ID_SERIE", referencedColumnName = "ID_SERIE")
    @ManyToOne
    private Serie idSerie;

    public Promotion() {
    }

    public Promotion(Integer idPromo) {
        this.idPromo = idPromo;
    }

    public Integer getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(Integer idPromo) {
        this.idPromo = idPromo;
    }

    public String getLibPromotion() {
        return libPromotion;
    }

    public void setLibPromotion(String libPromotion) {
        this.libPromotion = libPromotion;
    }

    public String getPromoAbrev() {
        return promoAbrev;
    }

    public void setPromoAbrev(String promoAbrev) {
        this.promoAbrev = promoAbrev;
    }

    public Cycles getIdCycles() {
        return idCycles;
    }

    public void setIdCycles(Cycles idCycles) {
        this.idCycles = idCycles;
    }

    public Serie getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(Serie idSerie) {
        this.idSerie = idSerie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPromo != null ? idPromo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Promotion)) {
            return false;
        }
        Promotion other = (Promotion) object;
        if ((this.idPromo == null && other.idPromo != null) || (this.idPromo != null && !this.idPromo.equals(other.idPromo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entites.Promotion[ idPromo=" + idPromo + " ]";
    }
    
}
