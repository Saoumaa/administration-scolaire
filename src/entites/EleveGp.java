/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author OBAMA
 */
@Entity
@Table(name = "ELEVE_GP", catalog = "scolaire2", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EleveGp.findAll", query = "SELECT e FROM EleveGp e"),
    @NamedQuery(name = "EleveGp.findByIdEleveGp", query = "SELECT e FROM EleveGp e WHERE e.idEleveGp = :idEleveGp"),
    @NamedQuery(name = "EleveGp.findByRegimeEleve", query = "SELECT e FROM EleveGp e WHERE e.regimeEleve = :regimeEleve"),
    @NamedQuery(name = "EleveGp.findByStatutEleve", query = "SELECT e FROM EleveGp e WHERE e.statutEleve = :statutEleve"),
    @NamedQuery(name = "EleveGp.findByIdEleve", query = "SELECT e FROM EleveGp e WHERE e.idEleve = :idEleve"),
    @NamedQuery(name = "EleveGp.findByIdGroupPedag", query = "SELECT e FROM EleveGp e WHERE e.idGroupPedag = :idGroupPedag"),
    @NamedQuery(name = "EleveGp.findByMoyAnnuelle", query = "SELECT e FROM EleveGp e WHERE e.moyAnnuelle = :moyAnnuelle")})
public class EleveGp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_ELEVE_GP", nullable = false)
    private Long idEleveGp;
    @Column(name = "REGIME_ELEVE", length = 10)
    private String regimeEleve;
    @Column(name = "STATUT_ELEVE", length = 10)
    private String statutEleve;
    @Column(name = "ID_ELEVE")
    private Long idEleve;
    @Column(name = "ID_GROUP_PEDAG")
    private Integer idGroupPedag;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MOY_ANNUELLE", precision = 4, scale = 2)
    private BigDecimal moyAnnuelle;
    @OneToMany(mappedBy = "idEleveGp")
    private List<BulletinNote> bulletinNoteList;
    @JoinColumn(name = "ID_ANNEE_SCOLAIRE", referencedColumnName = "ID_ANNEE_SCOLAIRE")
    @ManyToOne
    private AnneeScolaire idAnneeScolaire;
    @JoinColumn(name = "ID_ELEVE_GP", referencedColumnName = "ID_ELEVE", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Eleve eleve;

    public EleveGp() {
    }

    public EleveGp(Long idEleveGp) {
        this.idEleveGp = idEleveGp;
    }

    public Long getIdEleveGp() {
        return idEleveGp;
    }

    public void setIdEleveGp(Long idEleveGp) {
        this.idEleveGp = idEleveGp;
    }

    public String getRegimeEleve() {
        return regimeEleve;
    }

    public void setRegimeEleve(String regimeEleve) {
        this.regimeEleve = regimeEleve;
    }

    public String getStatutEleve() {
        return statutEleve;
    }

    public void setStatutEleve(String statutEleve) {
        this.statutEleve = statutEleve;
    }

    public Long getIdEleve() {
        return idEleve;
    }

    public void setIdEleve(Long idEleve) {
        this.idEleve = idEleve;
    }

    public Integer getIdGroupPedag() {
        return idGroupPedag;
    }

    public void setIdGroupPedag(Integer idGroupPedag) {
        this.idGroupPedag = idGroupPedag;
    }

    public BigDecimal getMoyAnnuelle() {
        return moyAnnuelle;
    }

    public void setMoyAnnuelle(BigDecimal moyAnnuelle) {
        this.moyAnnuelle = moyAnnuelle;
    }

    @XmlTransient
    public List<BulletinNote> getBulletinNoteList() {
        return bulletinNoteList;
    }

    public void setBulletinNoteList(List<BulletinNote> bulletinNoteList) {
        this.bulletinNoteList = bulletinNoteList;
    }

    public AnneeScolaire getIdAnneeScolaire() {
        return idAnneeScolaire;
    }

    public void setIdAnneeScolaire(AnneeScolaire idAnneeScolaire) {
        this.idAnneeScolaire = idAnneeScolaire;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEleveGp != null ? idEleveGp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EleveGp)) {
            return false;
        }
        EleveGp other = (EleveGp) object;
        if ((this.idEleveGp == null && other.idEleveGp != null) || (this.idEleveGp != null && !this.idEleveGp.equals(other.idEleveGp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entites.EleveGp[ idEleveGp=" + idEleveGp + " ]";
    }
    
}
