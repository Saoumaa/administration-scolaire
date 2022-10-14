package entites;

import entites.Enseigner;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-13T14:17:58")
@StaticMetamodel(Enseignant.class)
public class Enseignant_ { 

    public static volatile SingularAttribute<Enseignant, String> matriculeEns;
    public static volatile SingularAttribute<Enseignant, Date> datePriseServ;
    public static volatile SingularAttribute<Enseignant, Date> dateNais;
    public static volatile SingularAttribute<Enseignant, String> lieuNais;
    public static volatile ListAttribute<Enseignant, Enseigner> enseignerList;
    public static volatile SingularAttribute<Enseignant, Integer> idEns;
    public static volatile SingularAttribute<Enseignant, String> sexe;
    public static volatile SingularAttribute<Enseignant, String> nomEns;
    public static volatile SingularAttribute<Enseignant, String> prenomEns;
    public static volatile SingularAttribute<Enseignant, String> statut;
    public static volatile SingularAttribute<Enseignant, String> diplome;

}