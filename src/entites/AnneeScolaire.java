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
@Table(name = "ANNEE_SCOLAIRE", catalog = "scolaire2", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AnneeScolaire.findAll", query = "SELECT a FROM AnneeScolaire a"),
    @NamedQuery(name = "AnneeScolaire.findByIdAnneeScolaire", query = "SELECT a FROM AnneeScolaire a WHERE a.idAnneeScolaire = :idAnneeScolaire"),
    @NamedQuery(name = "AnneeScolaire.findByAnneeDebut", query = "SELECT a FROM AnneeScolaire a WHERE a.anneeDebut = :anneeDebut"),
    @NamedQuery(name = "AnneeScolaire.findByAnneeFin", query = "SELECT a FROM AnneeScolaire a WHERE a.anneeFin = :anneeFin")})
public class AnneeScolaire implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_ANNEE_SCOLAIRE", nullable = false)
    private Integer idAnneeScolaire;
    @Column(name = "ANNEE_DEBUT")
    private Short anneeDebut;
    @Column(name = "ANNEE_FIN")
    private Short anneeFin;
    @OneToMany(mappedBy = "idAnneeScolaire")
    private List<Trimestre> trimestreList;
    @OneToMany(mappedBy = "idAnneeScolaire")
    private List<Enseigner> enseignerList;
    @OneToMany(mappedBy = "idAnneeScolaire")
    private List<Regime> regimeList;
    @OneToMany(mappedBy = "idAnneeScolaire")
    private List<EleveGp> eleveGpList;

    public AnneeScolaire() {
    }

    public AnneeScolaire(Integer idAnneeScolaire) {
        this.idAnneeScolaire = idAnneeScolaire;
    }

    public Integer getIdAnneeScolaire() {
        return idAnneeScolaire;
    }

    public void setIdAnneeScolaire(Integer idAnneeScolaire) {
        this.idAnneeScolaire = idAnneeScolaire;
    }

    public Short getAnneeDebut() {
        return anneeDebut;
    }

    public void setAnneeDebut(Short anneeDebut) {
        this.anneeDebut = anneeDebut;
    }

    public Short getAnneeFin() {
        return anneeFin;
    }

    public void setAnneeFin(Short anneeFin) {
        this.anneeFin = anneeFin;
    }

    @XmlTransient
    public List<Trimestre> getTrimestreList() {
        return trimestreList;
    }

    public void setTrimestreList(List<Trimestre> trimestreList) {
        this.trimestreList = trimestreList;
    }

    @XmlTransient
    public List<Enseigner> getEnseignerList() {
        return enseignerList;
    }

    public void setEnseignerList(List<Enseigner> enseignerList) {
        this.enseignerList = enseignerList;
    }

    @XmlTransient
    public List<Regime> getRegimeList() {
        return regimeList;
    }

    public void setRegimeList(List<Regime> regimeList) {
        this.regimeList = regimeList;
    }

    @XmlTransient
    public List<EleveGp> getEleveGpList() {
        return eleveGpList;
    }

    public void setEleveGpList(List<EleveGp> eleveGpList) {
        this.eleveGpList = eleveGpList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAnneeScolaire != null ? idAnneeScolaire.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AnneeScolaire)) {
            return false;
        }
        AnneeScolaire other = (AnneeScolaire) object;
        if ((this.idAnneeScolaire == null && other.idAnneeScolaire != null) || (this.idAnneeScolaire != null && !this.idAnneeScolaire.equals(other.idAnneeScolaire))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entites.AnneeScolaire[ idAnneeScolaire=" + idAnneeScolaire + " ]";
    }
    
}
