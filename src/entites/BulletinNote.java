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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
  import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author OBAMA
 */
@Entity
@Table(name = "BULLETIN_NOTE", catalog = "scolaire2", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BulletinNote.findAll", query = "SELECT b FROM BulletinNote b"),
    @NamedQuery(name = "BulletinNote.findByIdBulletin", query = "SELECT b FROM BulletinNote b WHERE b.idBulletin = :idBulletin"),
    @NamedQuery(name = "BulletinNote.findByApreChefEtablis", query = "SELECT b FROM BulletinNote b WHERE b.apreChefEtablis = :apreChefEtablis"),
    @NamedQuery(name = "BulletinNote.findByMoyClasse", query = "SELECT b FROM BulletinNote b WHERE b.moyClasse = :moyClasse"),
    @NamedQuery(name = "BulletinNote.findByMoyTrim", query = "SELECT b FROM BulletinNote b WHERE b.moyTrim = :moyTrim"),
    @NamedQuery(name = "BulletinNote.findByPlusFaibleMoy", query = "SELECT b FROM BulletinNote b WHERE b.plusFaibleMoy = :plusFaibleMoy"),
    @NamedQuery(name = "BulletinNote.findByPlusForteMoy", query = "SELECT b FROM BulletinNote b WHERE b.plusForteMoy = :plusForteMoy"),
    @NamedQuery(name = "BulletinNote.findByRang", query = "SELECT b FROM BulletinNote b WHERE b.rang = :rang"),
    @NamedQuery(name = "BulletinNote.findByNotesSituation", query = "SELECT b FROM BulletinNote b WHERE b.notesSituation = :notesSituation")})
public class BulletinNote implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_BULLETIN", nullable = false)
    private Long idBulletin;
    @Column(name = "APRE_CHEF_ETABLIS", length = 255)
    private String apreChefEtablis;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MOY_CLASSE", precision = 4, scale = 2)
    private BigDecimal moyClasse;
    @Column(name = "MOY_TRIM", precision = 4, scale = 2)
    private BigDecimal moyTrim;
    @Column(name = "PLUS_FAIBLE_MOY", precision = 4, scale = 2)
    private BigDecimal plusFaibleMoy;
    @Column(name = "PLUS_FORTE_MOY", precision = 4, scale = 2)
    private BigDecimal plusForteMoy;
    @Column(name = "RANG")
    private Short rang;
    @Column(name = "NOTES_SITUATION")
    private Short notesSituation;
    @JoinColumn(name = "ID_ELEVE_GP", referencedColumnName = "ID_ELEVE_GP")
    @ManyToOne
    private EleveGp idEleveGp;
    @JoinColumn(name = "ID_TRIMES", referencedColumnName = "ID_TRIMES")
    @ManyToOne
    private Trimestre idTrimes;
    @OneToMany(mappedBy = "idBulletin")
    private List<DetailsBulletin> detailsBulletinList;

    public BulletinNote() {
    }

    public BulletinNote(Long idBulletin) {
        this.idBulletin = idBulletin;
    }

    public Long getIdBulletin() {
        return idBulletin;
    }

    public void setIdBulletin(Long idBulletin) {
        this.idBulletin = idBulletin;
    }

    public String getApreChefEtablis() {
        return apreChefEtablis;
    }

    public void setApreChefEtablis(String apreChefEtablis) {
        this.apreChefEtablis = apreChefEtablis;
    }

    public BigDecimal getMoyClasse() {
        return moyClasse;
    }

    public void setMoyClasse(BigDecimal moyClasse) {
        this.moyClasse = moyClasse;
    }

    public BigDecimal getMoyTrim() {
        return moyTrim;
    }

    public void setMoyTrim(BigDecimal moyTrim) {
        this.moyTrim = moyTrim;
    }

    public BigDecimal getPlusFaibleMoy() {
        return plusFaibleMoy;
    }

    public void setPlusFaibleMoy(BigDecimal plusFaibleMoy) {
        this.plusFaibleMoy = plusFaibleMoy;
    }

    public BigDecimal getPlusForteMoy() {
        return plusForteMoy;
    }

    public void setPlusForteMoy(BigDecimal plusForteMoy) {
        this.plusForteMoy = plusForteMoy;
    }

    public Short getRang() {
        return rang;
    }

    public void setRang(Short rang) {
        this.rang = rang;
    }

    public Short getNotesSituation() {
        return notesSituation;
    }

    public void setNotesSituation(Short notesSituation) {
        this.notesSituation = notesSituation;
    }

    public EleveGp getIdEleveGp() {
        return idEleveGp;
    }

    public void setIdEleveGp(EleveGp idEleveGp) {
        this.idEleveGp = idEleveGp;
    }

    public Trimestre getIdTrimes() {
        return idTrimes;
    }

    public void setIdTrimes(Trimestre idTrimes) {
        this.idTrimes = idTrimes;
    }

    @XmlTransient
    public List<DetailsBulletin> getDetailsBulletinList() {
        return detailsBulletinList;
    }

    public void setDetailsBulletinList(List<DetailsBulletin> detailsBulletinList) {
        this.detailsBulletinList = detailsBulletinList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBulletin != null ? idBulletin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BulletinNote)) {
            return false;
        }
        BulletinNote other = (BulletinNote) object;
        if ((this.idBulletin == null && other.idBulletin != null) || (this.idBulletin != null && !this.idBulletin.equals(other.idBulletin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entites.BulletinNote[ idBulletin=" + idBulletin + " ]";
    }
    
}
