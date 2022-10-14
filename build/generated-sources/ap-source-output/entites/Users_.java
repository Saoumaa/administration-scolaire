package entites;

import entites.Groupe;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-13T14:17:59")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, String> userLogin;
    public static volatile SingularAttribute<Users, String> userPrenom;
    public static volatile SingularAttribute<Users, String> userPswd;
    public static volatile SingularAttribute<Users, Character> userActif;
    public static volatile SingularAttribute<Users, String> userNom;
    public static volatile SingularAttribute<Users, Groupe> groupeCode;
    public static volatile SingularAttribute<Users, Integer> userCode;

}