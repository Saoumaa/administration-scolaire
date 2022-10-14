package entites;

import entites.AnneeScolaire;
import entites.BulletinNote;
import entites.Eleve;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-13T14:17:58")
@StaticMetamodel(EleveGp.class)
public class EleveGp_ { 

    public static volatile SingularAttribute<EleveGp, Long> idEleve;
    public static volatile SingularAttribute<EleveGp, AnneeScolaire> idAnneeScolaire;
    public static volatile SingularAttribute<EleveGp, String> regimeEleve;
    public static volatile SingularAttribute<EleveGp, BigDecimal> moyAnnuelle;
    public static volatile SingularAttribute<EleveGp, Integer> idGroupPedag;
    public static volatile SingularAttribute<EleveGp, Eleve> eleve;
    public static volatile SingularAttribute<EleveGp, Long> idEleveGp;
    public static volatile SingularAttribute<EleveGp, String> statutEleve;
    public static volatile ListAttribute<EleveGp, BulletinNote> bulletinNoteList;

}