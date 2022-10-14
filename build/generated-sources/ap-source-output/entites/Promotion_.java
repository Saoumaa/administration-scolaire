package entites;

import entites.Cycles;
import entites.Serie;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-13T14:17:58")
@StaticMetamodel(Promotion.class)
public class Promotion_ { 

    public static volatile SingularAttribute<Promotion, String> promoAbrev;
    public static volatile SingularAttribute<Promotion, String> libPromotion;
    public static volatile SingularAttribute<Promotion, Serie> idSerie;
    public static volatile SingularAttribute<Promotion, Integer> idPromo;
    public static volatile SingularAttribute<Promotion, Cycles> idCycles;

}