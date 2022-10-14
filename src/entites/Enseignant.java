/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author OBAMA
 */
@Entity
@Table(name = "ENSEIGNANT", catalog = "scolaire2", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Enseignant.findAll", query = "SELECT e FROM Enseignant e"),
    @NamedQuery(name = "Enseignant.findByIdEns", query = "SELECT e FROM Enseignant e WHERE e.idEns = :idEns"),
    @NamedQuery(name = "Enseignant.findByDateNais", query = "SELECT e FROM Enseignant e WHERE e.dateNais = :dateNais"),
    @NamedQuery(name = "Enseignant.findByDatePriseServ", query = "SELECT e FROM Enseignant e WHERE e.datePriseServ = :datePriseServ"),
    @NamedQuery(name = "Enseignant.findByDiplome", query = "SELECT e FROM Enseignant e WHERE e.diplome = :diplome"),
    @NamedQuery(name = "Enseignant.findByLieuNais", query = "SELECT e FROM Enseignant e WHERE e.lieuNais = :lieuNais"),
    @NamedQuery(name = "Enseignant.findByMatriculeEns", query = "SELECT e FROM Enseignant e WHERE e.matriculeEns = :matriculeEns"),
    @NamedQuery(name = "Enseignant.findByNomEns", query = "SELECT e FROM Enseignant e WHERE e.nomEns = :nomEns"),
    @NamedQuery(name = "Enseignant.findByPrenomEns", query = "SELECT e FROM Enseignant e WHERE e.prenomEns = :prenomEns"),
    @NamedQuery(name = "Enseignant.findBySexe", query = "SELECT e FROM Enseignant e WHERE e.sexe = :sexe"),
    @NamedQuery(name = "Enseignant.findByStatut", query = "SELECT e FROM Enseignant e WHERE e.statut = :statut")})
public class Enseignant implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_ENS", nullable = false)
    private Integer idEns;
    @Column(name = "DATE_NAIS")
    @Temporal(TemporalType.DATE)
    private Date dateNais;
    @Column(name = "DATE_PRISE_SERV")
    @Temporal(TemporalType.DATE)
    private Date datePriseServ;
    @Column(name = "DIPLOME", length = 255)
    private String diplome;
    @Column(name = "LIEU_NAIS", length = 64)
    private String lieuNais;
    @Column(name = "MATRICULE_ENS", length = 10)
    private String matriculeEns;
    @Column(name = "NOM_ENS", length = 64)
    private String nomEns;
    @Column(name = "PRENOM_ENS", length = 64)
    private String prenomEns;
    @Column(name = "SEXE", length = 10)
    private String sexe;
    @Column(name = "STATUT", length = 25)
    private String statut;
    @OneToMany(mappedBy = "idEns")
    private List<Enseigner> enseignerList;

    public Enseignant() {
    }

    public Enseignant(Integer idEns) {
        this.idEns = idEns;
    }

    public Integer getIdEns() {
        return idEns;
    }

    public void setIdEns(Integer idEns) {
        this.idEns = idEns;
    }

    public Date getDateNais() {
        return dateNais;
    }

    public void setDateNais(Date dateNais) {
        this.dateNais = dateNais;
    }

    public Date getDatePriseServ() {
        return datePriseServ;
    }

    public void setDatePriseServ(Date datePriseServ) {
        this.datePriseServ = datePriseServ;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getLieuNais() {
        return lieuNais;
    }

    public void setLieuNais(String lieuNais) {
        this.lieuNais = lieuNais;
    }

    public String getMatriculeEns() {
        return matriculeEns;
    }

    public void setMatriculeEns(String matriculeEns) {
        this.matriculeEns = matriculeEns;
    }

    public String getNomEns() {
        return nomEns;
    }

    public void setNomEns(String nomEns) {
        this.nomEns = nomEns;
    }

    public String getPrenomEns() {
        return prenomEns;
    }

    public void setPrenomEns(String prenomEns) {
        this.prenomEns = prenomEns;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
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
        hash += (idEns != null ? idEns.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Enseignant)) {
            return false;
        }
        Enseignant other = (Enseignant) object;
        if ((this.idEns == null && other.idEns != null) || (this.idEns != null && !this.idEns.equals(other.idEns))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entites.Enseignant[ idEns=" + idEns + " ]";
    }
    
}
