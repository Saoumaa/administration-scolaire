package entites;

import entites.AnneeScolaire;
import entites.Eleve;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-13T14:17:58")
@StaticMetamodel(Regime.class)
public class Regime_ { 

    public static volatile SingularAttribute<Regime, Integer> regimeCode;
    public static volatile SingularAttribute<Regime, AnneeScolaire> idAnneeScolaire;
    public static volatile SingularAttribute<Regime, Eleve> idEleve;
    public static volatile SingularAttribute<Regime, String> regimeLibelle;

}