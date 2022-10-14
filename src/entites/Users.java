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
//import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author OBAMA
 */
@Entity
@Table(name = "USERS", catalog = "scolaire2", schema = "public")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUserCode", query = "SELECT u FROM Users u WHERE u.userCode = :userCode"),
    @NamedQuery(name = "Users.findByUserNom", query = "SELECT u FROM Users u WHERE u.userNom = :userNom"),
    @NamedQuery(name = "Users.findByUserPrenom", query = "SELECT u FROM Users u WHERE u.userPrenom = :userPrenom"),
    @NamedQuery(name = "Users.findByUserLogin", query = "SELECT u FROM Users u WHERE u.userLogin = :userLogin"),
    @NamedQuery(name = "Users.findByUserPswd", query = "SELECT u FROM Users u WHERE u.userPswd = :userPswd"),
    @NamedQuery(name = "Users.findByUserActif", query = "SELECT u FROM Users u WHERE u.userActif = :userActif")})
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "USER_CODE", nullable = false)
    private Integer userCode;
    @Column(name = "USER_NOM", length = 64)
    private String userNom;
    @Column(name = "USER_PRENOM", length = 64)
    private String userPrenom;
    @Column(name = "USER_LOGIN", length = 32)
    private String userLogin;
    @Column(name = "USER_PSWD", length = 32)
    private String userPswd;
    @Column(name = "USER_ACTIF")
    private Character userActif;
    @JoinColumn(name = "GROUPE_CODE", referencedColumnName = "GROUPE_CODE")
    @ManyToOne
    private Groupe groupeCode;

    public Users() {
    }

    public Users(Integer userCode) {
        this.userCode = userCode;
    }

    public Integer getUserCode() {
        return userCode;
    }

    public void setUserCode(Integer userCode) {
        this.userCode = userCode;
    }

    public String getUserNom() {
        return userNom;
    }

    public void setUserNom(String userNom) {
        this.userNom = userNom;
    }

    public String getUserPrenom() {
        return userPrenom;
    }

    public void setUserPrenom(String userPrenom) {
        this.userPrenom = userPrenom;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPswd() {
        return userPswd;
    }

    public void setUserPswd(String userPswd) {
        this.userPswd = userPswd;
    }

    public Character getUserActif() {
        return userActif;
    }

    public void setUserActif(Character userActif) {
        this.userActif = userActif;
    }

    public Groupe getGroupeCode() {
        return groupeCode;
    }

    public void setGroupeCode(Groupe groupeCode) {
        this.groupeCode = groupeCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userCode != null ? userCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userCode == null && other.userCode != null) || (this.userCode != null && !this.userCode.equals(other.userCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entites.Users[ userCode=" + userCode + " ]";
    }
    
}
