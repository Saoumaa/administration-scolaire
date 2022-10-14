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
@Table(name = "ENSEIGNER", catalog = "scolaire2", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Enseigner.findAll", query = "SELECT e FROM Enseigner e"),
    @NamedQuery(name = "Enseigner.findByEnseignerCode", query = "SELECT e FROM Enseigner e WHERE e.enseignerCode = :enseignerCode"),
    @NamedQuery(name = "Enseigner.findByPp", query = "SELECT e FROM Enseigner e WHERE e.pp = :pp")})
public class Enseigner implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ENSEIGNER_CODE", nullable = false)
    private Long enseignerCode;
    @Column(name = "PP")
    private Character pp;
    @JoinColumn(name = "ID_ANNEE_SCOLAIRE", referencedColumnName = "ID_ANNEE_SCOLAIRE")
    @ManyToOne
    private AnneeScolaire idAnneeScolaire;
    @JoinColumn(name = "ID_ENS", referencedColumnName = "ID_ENS")
    @ManyToOne
    private Enseignant idEns;
    @JoinColumn(name = "ID_GROUP_PEDAG", referencedColumnName = "ID_GROUP_PEDAG")
    @ManyToOne
    private GroupPedag idGroupPedag;
    @JoinColumn(name = "ID_MATIERE", referencedColumnName = "ID_MATIERE")
    @ManyToOne
    private Matiere idMatiere;

    public Enseigner() {
    }

    public Enseigner(Long enseignerCode) {
        this.enseignerCode = enseignerCode;
    }

    public Long getEnseignerCode() {
        return enseignerCode;
    }

    public void setEnseignerCode(Long enseignerCode) {
        this.enseignerCode = enseignerCode;
    }

    public Character getPp() {
        return pp;
    }

    public void setPp(Character pp) {
        this.pp = pp;
    }

    public AnneeScolaire getIdAnneeScolaire() {
        return idAnneeScolaire;
    }

    public void setIdAnneeScolaire(AnneeScolaire idAnneeScolaire) {
        this.idAnneeScolaire = idAnneeScolaire;
    }

    public Enseignant getIdEns() {
        return idEns;
    }

    public void setIdEns(Enseignant idEns) {
        this.idEns = idEns;
    }

    public GroupPedag getIdGroupPedag() {
        return idGroupPedag;
    }

    public void setIdGroupPedag(GroupPedag idGroupPedag) {
        this.idGroupPedag = idGroupPedag;
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
        hash += (enseignerCode != null ? enseignerCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Enseigner)) {
            return false;
        }
        Enseigner other = (Enseigner) object;
        if ((this.enseignerCode == null && other.enseignerCode != null) || (this.enseignerCode != null && !this.enseignerCode.equals(other.enseignerCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entites.Enseigner[ enseignerCode=" + enseignerCode + " ]";
    }
    
}
