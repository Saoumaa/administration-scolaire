package entites;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-13T14:17:59")
@StaticMetamodel(Activite.class)
public class Activite_ { 

    public static volatile SingularAttribute<Activite, String> activiteSuperviseur;
    public static volatile SingularAttribute<Activite, String> activiteRapporteur;
    public static volatile SingularAttribute<Activite, Short> activiteAnnee;
    public static volatile SingularAttribute<Activite, String> activiteLibelle;
    public static volatile SingularAttribute<Activite, Long> activiteCode;
    public static volatile SingularAttribute<Activite, Date> activiteDate;
    public static volatile SingularAttribute<Activite, String> activiteRapport;

}