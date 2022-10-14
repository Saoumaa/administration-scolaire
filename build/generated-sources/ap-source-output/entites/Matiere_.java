package entites;

import entites.Coefficient;
import entites.Enseigner;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-13T14:17:59")
@StaticMetamodel(Matiere.class)
public class Matiere_ { 

    public static volatile SingularAttribute<Matiere, String> libMatiere;
    public static volatile ListAttribute<Matiere, Coefficient> coefficientList;
    public static volatile SingularAttribute<Matiere, Integer> idMatiere;
    public static volatile ListAttribute<Matiere, Enseigner> enseignerList;

}