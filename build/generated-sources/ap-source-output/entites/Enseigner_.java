package entites;

import entites.AnneeScolaire;
import entites.Enseignant;
import entites.GroupPedag;
import entites.Matiere;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-13T14:17:59")
@StaticMetamodel(Enseigner.class)
public class Enseigner_ { 

    public static volatile SingularAttribute<Enseigner, Character> pp;
    public static volatile SingularAttribute<Enseigner, AnneeScolaire> idAnneeScolaire;
    public static volatile SingularAttribute<Enseigner, Matiere> idMatiere;
    public static volatile SingularAttribute<Enseigner, Enseignant> idEns;
    public static volatile SingularAttribute<Enseigner, GroupPedag> idGroupPedag;
    public static volatile SingularAttribute<Enseigner, Long> enseignerCode;

}