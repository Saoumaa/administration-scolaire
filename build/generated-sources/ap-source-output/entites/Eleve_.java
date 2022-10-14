package entites;

import entites.EleveGp;
import entites.Regime;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-13T14:17:58")
@StaticMetamodel(Eleve.class)
public class Eleve_ { 

    public static volatile SingularAttribute<Eleve, String> nomEl;
    public static volatile SingularAttribute<Eleve, EleveGp> eleveGp;
    public static volatile SingularAttribute<Eleve, Long> idEleve;
    public static volatile SingularAttribute<Eleve, Date> dateNais;
    public static volatile SingularAttribute<Eleve, String> matriculeEl;
    public static volatile ListAttribute<Eleve, Regime> regimeList;
    public static volatile SingularAttribute<Eleve, String> prenomEl;
    public static volatile SingularAttribute<Eleve, String> lieuNais;
    public static volatile SingularAttribute<Eleve, String> sexe;
    public static volatile SingularAttribute<Eleve, Date> dateInscri;
    public static volatile SingularAttribute<Eleve, String> photoIdent;

}