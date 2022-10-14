package entites;

import entites.Promotion;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-13T14:17:59")
@StaticMetamodel(Cycles.class)
public class Cycles_ { 

    public static volatile SingularAttribute<Cycles, String> cyclesAbrev;
    public static volatile SingularAttribute<Cycles, String> libCycles;
    public static volatile ListAttribute<Cycles, Promotion> promotionList;
    public static volatile SingularAttribute<Cycles, Integer> idCycles;

}