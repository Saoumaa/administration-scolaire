package entites;

import entites.Promotion;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-13T14:17:59")
@StaticMetamodel(Serie.class)
public class Serie_ { 

    public static volatile SingularAttribute<Serie, Integer> idSerie;
    public static volatile ListAttribute<Serie, Promotion> promotionList;
    public static volatile SingularAttribute<Serie, String> libSerie;

}