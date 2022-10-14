/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author OBAMA
 */
@Entity
@Table(name = "ACTIVITE", catalog = "scolaire2", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Activite.findAll", query = "SELECT a FROM Activite a"),
    @NamedQuery(name = "Activite.findByActiviteCode", query = "SELECT a FROM Activite a WHERE a.activiteCode = :activiteCode"),
    @NamedQuery(name = "Activite.findByActiviteAnnee", query = "SELECT a FROM Activite a WHERE a.activiteAnnee = :activiteAnnee"),
    @NamedQuery(name = "Activite.findByActiviteDate", query = "SELECT a FROM Activite a WHERE a.activiteDate = :activiteDate"),
    @NamedQuery(name = "Activite.findByActiviteLibelle", query = "SELECT a FROM Activite a WHERE a.activiteLibelle = :activiteLibelle"),
    @NamedQuery(name = "Activite.findByActiviteRapport", query = "SELECT a FROM Activite a WHERE a.activiteRapport = :activiteRapport"),
    @NamedQuery(name = "Activite.findByActiviteRapporteur", query = "SELECT a FROM Activite a WHERE a.activiteRapporteur = :activiteRapporteur"),
    @NamedQuery(name = "Activite.findByActiviteSuperviseur", query = "SELECT a FROM Activite a WHERE a.activiteSuperviseur = :activiteSuperviseur")})
public class Activite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ACTIVITE_CODE", nullable = false)
    private Long activiteCode;
    @Column(name = "ACTIVITE_ANNEE")
    private Short activiteAnnee;
    @Column(name = "ACTIVITE_DATE")
    @Temporal(TemporalType.DATE)
    private Date activiteDate;
    @Column(name = "ACTIVITE_LIBELLE", length = 255)
    private String activiteLibelle;
    @Column(name = "ACTIVITE_RAPPORT", length = 255)
    private String activiteRapport;
    @Column(name = "ACTIVITE_RAPPORTEUR", length = 255)
    private String activiteRapporteur;
    @Column(name = "ACTIVITE_SUPERVISEUR", length = 255)
    private String activiteSuperviseur;

    public Activite() {
    }

    public Activite(Long activiteCode) {
        this.activiteCode = activiteCode;
    }

    public Long getActiviteCode() {
        return activiteCode;
    }

    public void setActiviteCode(Long activiteCode) {
        this.activiteCode = activiteCode;
    }

    public Short getActiviteAnnee() {
        return activiteAnnee;
    }

    public void setActiviteAnnee(Short activiteAnnee) {
        this.activiteAnnee = activiteAnnee;
    }

    public Date getActiviteDate() {
        return activiteDate;
    }

    public void setActiviteDate(Date activiteDate) {
        this.activiteDate = activiteDate;
    }

    public String getActiviteLibelle() {
        return activiteLibelle;
    }

    public void setActiviteLibelle(String activiteLibelle) {
        this.activiteLibelle = activiteLibelle;
    }

    public String getActiviteRapport() {
        return activiteRapport;
    }

    public void setActiviteRapport(String activiteRapport) {
        this.activiteRapport = activiteRapport;
    }

    public String getActiviteRapporteur() {
        return activiteRapporteur;
    }

    public void setActiviteRapporteur(String activiteRapporteur) {
        this.activiteRapporteur = activiteRapporteur;
    }

    public String getActiviteSuperviseur() {
        return activiteSuperviseur;
    }

    public void setActiviteSuperviseur(String activiteSuperviseur) {
        this.activiteSuperviseur = activiteSuperviseur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (activiteCode != null ? activiteCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Activite)) {
            return false;
        }
        Activite other = (Activite) object;
        if ((this.activiteCode == null && other.activiteCode != null) || (this.activiteCode != null && !this.activiteCode.equals(other.activiteCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entites.Activite[ activiteCode=" + activiteCode + " ]";
    }
    
}
