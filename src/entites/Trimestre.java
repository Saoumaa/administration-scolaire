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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "TRIMESTRE", catalog = "scolaire2", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trimestre.findAll", query = "SELECT t FROM Trimestre t"),
    @NamedQuery(name = "Trimestre.findByIdTrimes", query = "SELECT t FROM Trimestre t WHERE t.idTrimes = :idTrimes"),
    @NamedQuery(name = "Trimestre.findByLibTrimes", query = "SELECT t FROM Trimestre t WHERE t.libTrimes = :libTrimes")})
public class Trimestre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_TRIMES", nullable = false)
    private Integer idTrimes;
    @Column(name = "LIB_TRIMES", length = 32)
    private String libTrimes;
    @OneToMany(mappedBy = "idTrimes")
    private List<BulletinNote> bulletinNoteList;
    @JoinColumn(name = "ID_ANNEE_SCOLAIRE", referencedColumnName = "ID_ANNEE_SCOLAIRE")
    @ManyToOne
    private AnneeScolaire idAnneeScolaire;

    public Trimestre() {
    }

    public Trimestre(Integer idTrimes) {
        this.idTrimes = idTrimes;
    }

    public Integer getIdTrimes() {
        return idTrimes;
    }

    public void setIdTrimes(Integer idTrimes) {
        this.idTrimes = idTrimes;
    }

    public String getLibTrimes() {
        return libTrimes;
    }

    public void setLibTrimes(String libTrimes) {
        this.libTrimes = libTrimes;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTrimes != null ? idTrimes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trimestre)) {
            return false;
        }
        Trimestre other = (Trimestre) object;
        if ((this.idTrimes == null && other.idTrimes != null) || (this.idTrimes != null && !this.idTrimes.equals(other.idTrimes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entites.Trimestre[ idTrimes=" + idTrimes + " ]";
    }
    
}
