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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "ELEVE", catalog = "scolaire2", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Eleve.findAll", query = "SELECT e FROM Eleve e"),
    @NamedQuery(name = "Eleve.findByIdEleve", query = "SELECT e FROM Eleve e WHERE e.idEleve = :idEleve"),
    @NamedQuery(name = "Eleve.findByDateInscri", query = "SELECT e FROM Eleve e WHERE e.dateInscri = :dateInscri"),
    @NamedQuery(name = "Eleve.findByDateNais", query = "SELECT e FROM Eleve e WHERE e.dateNais = :dateNais"),
    @NamedQuery(name = "Eleve.findByLieuNais", query = "SELECT e FROM Eleve e WHERE e.lieuNais = :lieuNais"),
    @NamedQuery(name = "Eleve.findByMatriculeEl", query = "SELECT e FROM Eleve e WHERE e.matriculeEl = :matriculeEl"),
    @NamedQuery(name = "Eleve.findByNomEl", query = "SELECT e FROM Eleve e WHERE e.nomEl = :nomEl"),
    @NamedQuery(name = "Eleve.findByPrenomEl", query = "SELECT e FROM Eleve e WHERE e.prenomEl = :prenomEl"),
    @NamedQuery(name = "Eleve.findBySexe", query = "SELECT e FROM Eleve e WHERE e.sexe = :sexe"),
    @NamedQuery(name = "Eleve.findByPhotoIdent", query = "SELECT e FROM Eleve e WHERE e.photoIdent = :photoIdent")})
public class Eleve implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_ELEVE", nullable = false)
    private Long idEleve;
    @Column(name = "DATE_INSCRI")
    @Temporal(TemporalType.DATE)
    private Date dateInscri;
    @Column(name = "DATE_NAIS")
    @Temporal(TemporalType.DATE)
    private Date dateNais;
    @Column(name = "LIEU_NAIS", length = 64)
    private String lieuNais;
    @Column(name = "MATRICULE_EL", length = 10)
    private String matriculeEl;
    @Column(name = "NOM_EL", length = 255)
    private String nomEl;
    @Column(name = "PRENOM_EL", length = 255)
    private String prenomEl;
    @Column(name = "SEXE", length = 10)
    private String sexe;
    @Column(name = "PHOTO_IDENT", length = 255)
    private String photoIdent;
    @OneToMany(mappedBy = "idEleve")
    private List<Regime> regimeList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "eleve")
    private EleveGp eleveGp;

    public Eleve() {
    }

    public Eleve(Long idEleve) {
        this.idEleve = idEleve;
    }

    public Long getIdEleve() {
        return idEleve;
    }

    public void setIdEleve(Long idEleve) {
        this.idEleve = idEleve;
    }

    public Date getDateInscri() {
        return dateInscri;
    }

    public void setDateInscri(Date dateInscri) {
        this.dateInscri = dateInscri;
    }

    public Date getDateNais() {
        return dateNais;
    }

    public void setDateNais(Date dateNais) {
        this.dateNais = dateNais;
    }

    public String getLieuNais() {
        return lieuNais;
    }

    public void setLieuNais(String lieuNais) {
        this.lieuNais = lieuNais;
    }

    public String getMatriculeEl() {
        return matriculeEl;
    }

    public void setMatriculeEl(String matriculeEl) {
        this.matriculeEl = matriculeEl;
    }

    public String getNomEl() {
        return nomEl;
    }

    public void setNomEl(String nomEl) {
        this.nomEl = nomEl;
    }

    public String getPrenomEl() {
        return prenomEl;
    }

    public void setPrenomEl(String prenomEl) {
        this.prenomEl = prenomEl;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getPhotoIdent() {
        return photoIdent;
    }

    public void setPhotoIdent(String photoIdent) {
        this.photoIdent = photoIdent;
    }

    @XmlTransient
    public List<Regime> getRegimeList() {
        return regimeList;
    }

    public void setRegimeList(List<Regime> regimeList) {
        this.regimeList = regimeList;
    }

    public EleveGp getEleveGp() {
        return eleveGp;
    }

    public void setEleveGp(EleveGp eleveGp) {
        this.eleveGp = eleveGp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEleve != null ? idEleve.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Eleve)) {
            return false;
        }
        Eleve other = (Eleve) object;
        if ((this.idEleve == null && other.idEleve != null) || (this.idEleve != null && !this.idEleve.equals(other.idEleve))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entites.Eleve[ idEleve=" + idEleve + " ]";
    }
    
}
