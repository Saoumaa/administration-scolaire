/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "DETAILS_BULLETIN", catalog = "scolaire2", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetailsBulletin.findAll", query = "SELECT d FROM DetailsBulletin d"),
    @NamedQuery(name = "DetailsBulletin.findByIdDetailsBulletin", query = "SELECT d FROM DetailsBulletin d WHERE d.idDetailsBulletin = :idDetailsBulletin"),
    @NamedQuery(name = "DetailsBulletin.findByInt1", query = "SELECT d FROM DetailsBulletin d WHERE d.int1 = :int1"),
    @NamedQuery(name = "DetailsBulletin.findByInt2", query = "SELECT d FROM DetailsBulletin d WHERE d.int2 = :int2"),
    @NamedQuery(name = "DetailsBulletin.findByInt3", query = "SELECT d FROM DetailsBulletin d WHERE d.int3 = :int3"),
    @NamedQuery(name = "DetailsBulletin.findByInt4", query = "SELECT d FROM DetailsBulletin d WHERE d.int4 = :int4"),
    @NamedQuery(name = "DetailsBulletin.findByMoyInt", query = "SELECT d FROM DetailsBulletin d WHERE d.moyInt = :moyInt"),
    @NamedQuery(name = "DetailsBulletin.findByDevNote", query = "SELECT d FROM DetailsBulletin d WHERE d.devNote = :devNote"),
    @NamedQuery(name = "DetailsBulletin.findByCompoNote", query = "SELECT d FROM DetailsBulletin d WHERE d.compoNote = :compoNote"),
    @NamedQuery(name = "DetailsBulletin.findByMoy20", query = "SELECT d FROM DetailsBulletin d WHERE d.moy20 = :moy20"),
    @NamedQuery(name = "DetailsBulletin.findByMoyCoef", query = "SELECT d FROM DetailsBulletin d WHERE d.moyCoef = :moyCoef"),
    @NamedQuery(name = "DetailsBulletin.findByRang", query = "SELECT d FROM DetailsBulletin d WHERE d.rang = :rang"),
    @NamedQuery(name = "DetailsBulletin.findByAppreSigneProf", query = "SELECT d FROM DetailsBulletin d WHERE d.appreSigneProf = :appreSigneProf")})
public class DetailsBulletin implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_DETAILS_BULLETIN", nullable = false)
    private Long idDetailsBulletin;
    @Column(name = "INT1", length = 5)
    private String int1;
    @Column(name = "INT2", length = 5)
    private String int2;
    @Column(name = "INT3", length = 5)
    private String int3;
    @Column(name = "INT4", length = 5)
    private String int4;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MOY_INT", precision = 4, scale = 2)
    private BigDecimal moyInt;
    @Column(name = "DEV_NOTE", length = 5)
    private String devNote;
    @Column(name = "COMPO_NOTE", length = 5)
    private String compoNote;
    @Column(name = "MOY_20", precision = 4, scale = 2)
    private BigDecimal moy20;
    @Column(name = "MOY_COEF", precision = 4, scale = 2)
    private BigDecimal moyCoef;
    @Column(name = "RANG")
    private Short rang;
    @Column(name = "APPRE_SIGNE_PROF", length = 128)
    private String appreSigneProf;
    @JoinColumn(name = "ID_BULLETIN", referencedColumnName = "ID_BULLETIN")
    @ManyToOne
    private BulletinNote idBulletin;
    @JoinColumn(name = "ID_COEF", referencedColumnName = "ID_COEF")
    @ManyToOne
    private Coefficient idCoef;

    public DetailsBulletin() {
    }

    public DetailsBulletin(Long idDetailsBulletin) {
        this.idDetailsBulletin = idDetailsBulletin;
    }

    public Long getIdDetailsBulletin() {
        return idDetailsBulletin;
    }

    public void setIdDetailsBulletin(Long idDetailsBulletin) {
        this.idDetailsBulletin = idDetailsBulletin;
    }

    public String getInt1() {
        return int1;
    }

    public void setInt1(String int1) {
        this.int1 = int1;
    }

    public String getInt2() {
        return int2;
    }

    public void setInt2(String int2) {
        this.int2 = int2;
    }

    public String getInt3() {
        return int3;
    }

    public void setInt3(String int3) {
        this.int3 = int3;
    }

    public String getInt4() {
        return int4;
    }

    public void setInt4(String int4) {
        this.int4 = int4;
    }

    public BigDecimal getMoyInt() {
        return moyInt;
    }

    public void setMoyInt(BigDecimal moyInt) {
        this.moyInt = moyInt;
    }

    public String getDevNote() {
        return devNote;
    }

    public void setDevNote(String devNote) {
        this.devNote = devNote;
    }

    public String getCompoNote() {
        return compoNote;
    }

    public void setCompoNote(String compoNote) {
        this.compoNote = compoNote;
    }

    public BigDecimal getMoy20() {
        return moy20;
    }

    public void setMoy20(BigDecimal moy20) {
        this.moy20 = moy20;
    }

    public BigDecimal getMoyCoef() {
        return moyCoef;
    }

    public void setMoyCoef(BigDecimal moyCoef) {
        this.moyCoef = moyCoef;
    }

    public Short getRang() {
        return rang;
    }

    public void setRang(Short rang) {
        this.rang = rang;
    }

    public String getAppreSigneProf() {
        return appreSigneProf;
    }

    public void setAppreSigneProf(String appreSigneProf) {
        this.appreSigneProf = appreSigneProf;
    }

    public BulletinNote getIdBulletin() {
        return idBulletin;
    }

    public void setIdBulletin(BulletinNote idBulletin) {
        this.idBulletin = idBulletin;
    }

    public Coefficient getIdCoef() {
        return idCoef;
    }

    public void setIdCoef(Coefficient idCoef) {
        this.idCoef = idCoef;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetailsBulletin != null ? idDetailsBulletin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetailsBulletin)) {
            return false;
        }
        DetailsBulletin other = (DetailsBulletin) object;
        if ((this.idDetailsBulletin == null && other.idDetailsBulletin != null) || (this.idDetailsBulletin != null && !this.idDetailsBulletin.equals(other.idDetailsBulletin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entites.DetailsBulletin[ idDetailsBulletin=" + idDetailsBulletin + " ]";
    }
    
}
