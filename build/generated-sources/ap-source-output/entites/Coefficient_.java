package entites;

import entites.DetailsBulletin;
import entites.Matiere;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-13T14:17:59")
@StaticMetamodel(Coefficient.class)
public class Coefficient_ { 

    public static volatile SingularAttribute<Coefficient, Short> valeurCoef;
    public static volatile SingularAttribute<Coefficient, Matiere> idMatiere;
    public static volatile SingularAttribute<Coefficient, Integer> idCoef;
    public static volatile SingularAttribute<Coefficient, Long> idGroupPedag;
    public static volatile ListAttribute<Coefficient, DetailsBulletin> detailsBulletinList;

}