package entites;

import entites.EleveGp;
import entites.Enseigner;
import entites.Regime;
import entites.Trimestre;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-13T14:17:59")
@StaticMetamodel(AnneeScolaire.class)
public class AnneeScolaire_ { 

    public static volatile SingularAttribute<AnneeScolaire, Integer> idAnneeScolaire;
    public static volatile ListAttribute<AnneeScolaire, Regime> regimeList;
    public static volatile SingularAttribute<AnneeScolaire, Short> anneeDebut;
    public static volatile ListAttribute<AnneeScolaire, Trimestre> trimestreList;
    public static volatile SingularAttribute<AnneeScolaire, Short> anneeFin;
    public static volatile ListAttribute<AnneeScolaire, Enseigner> enseignerList;
    public static volatile ListAttribute<AnneeScolaire, EleveGp> eleveGpList;

}