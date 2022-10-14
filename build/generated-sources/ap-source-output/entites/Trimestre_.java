package entites;

import entites.AnneeScolaire;
import entites.BulletinNote;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-13T14:17:59")
@StaticMetamodel(Trimestre.class)
public class Trimestre_ { 

    public static volatile SingularAttribute<Trimestre, Integer> idTrimes;
    public static volatile SingularAttribute<Trimestre, AnneeScolaire> idAnneeScolaire;
    public static volatile SingularAttribute<Trimestre, String> libTrimes;
    public static volatile ListAttribute<Trimestre, BulletinNote> bulletinNoteList;

}