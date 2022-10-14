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
@Table(name = "REGIME", catalog = "scolaire2", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Regime.findAll", query = "SELECT r FROM Regime r"),
    @NamedQuery(name = "Regime.findByRegimeCode", query = "SELECT r FROM Regime r WHERE r.regimeCode = :regimeCode"),
    @NamedQuery(name = "Regime.findByRegimeLibelle", query = "SELECT r FROM Regime r WHERE r.regimeLibelle = :regimeLibelle")})
public class Regime implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "REGIME_CODE", nullable = false)
    private Integer regimeCode;
    @Column(name = "REGIME_LIBELLE", length = 23)
    private String regimeLibelle;
    @JoinColumn(name = "ID_ANNEE_SCOLAIRE", referencedColumnName = "ID_ANNEE_SCOLAIRE")
    @ManyToOne
    private AnneeScolaire idAnneeScolaire;
    @JoinColumn(name = "ID_ELEVE", referencedColumnName = "ID_ELEVE")
    @ManyToOne
    private Eleve idEleve;

    public Regime() {
    }

    public Regime(Integer regimeCode) {
        this.regimeCode = regimeCode;
    }

    public Integer getRegimeCode() {
        return regimeCode;
    }

    public void setRegimeCode(Integer regimeCode) {
        this.regimeCode = regimeCode;
    }

    public String getRegimeLibelle() {
        return regimeLibelle;
    }

    public void setRegimeLibelle(String regimeLibelle) {
        this.regimeLibelle = regimeLibelle;
    }

    public AnneeScolaire getIdAnneeScolaire() {
        return idAnneeScolaire;
    }

    public void setIdAnneeScolaire(AnneeScolaire idAnneeScolaire) {
        this.idAnneeScolaire = idAnneeScolaire;
    }

    public Eleve getIdEleve() {
        return idEleve;
    }

    public void setIdEleve(Eleve idEleve) {
        this.idEleve = idEleve;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (regimeCode != null ? regimeCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Regime)) {
            return false;
        }
        Regime other = (Regime) object;
        if ((this.regimeCode == null && other.regimeCode != null) || (this.regimeCode != null && !this.regimeCode.equals(other.regimeCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entites.Regime[ regimeCode=" + regimeCode + " ]";
    }
    
}
