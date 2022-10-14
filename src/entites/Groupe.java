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
@Table(name = "GROUPE", catalog = "scolaire2", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Groupe.findAll", query = "SELECT g FROM Groupe g"),
    @NamedQuery(name = "Groupe.findByGroupeCode", query = "SELECT g FROM Groupe g WHERE g.groupeCode = :groupeCode"),
    @NamedQuery(name = "Groupe.findByGroupeLibelle", query = "SELECT g FROM Groupe g WHERE g.groupeLibelle = :groupeLibelle")})
public class Groupe implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "GROUPE_CODE", nullable = false, length = 32)
    private String groupeCode;
    @Column(name = "GROUPE_LIBELLE", length = 32)
    private String groupeLibelle;
    @OneToMany(mappedBy = "groupeCode")
    private List<Users> usersList;

    public Groupe() {
    }

    public Groupe(String groupeCode) {
        this.groupeCode = groupeCode;
    }

    public String getGroupeCode() {
        return groupeCode;
    }

    public void setGroupeCode(String groupeCode) {
        this.groupeCode = groupeCode;
    }

    public String getGroupeLibelle() {
        return groupeLibelle;
    }

    public void setGroupeLibelle(String groupeLibelle) {
        this.groupeLibelle = groupeLibelle;
    }

    @XmlTransient
    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupeCode != null ? groupeCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groupe)) {
            return false;
        }
        Groupe other = (Groupe) object;
        if ((this.groupeCode == null && other.groupeCode != null) || (this.groupeCode != null && !this.groupeCode.equals(other.groupeCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entites.Groupe[ groupeCode=" + groupeCode + " ]";
    }
    
}
