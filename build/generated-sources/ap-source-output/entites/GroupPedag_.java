package entites;

import entites.Enseigner;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-13T14:17:58")
@StaticMetamodel(GroupPedag.class)
public class GroupPedag_ { 

    public static volatile SingularAttribute<GroupPedag, Short> effectif;
    public static volatile SingularAttribute<GroupPedag, String> libGroupPedag;
    public static volatile SingularAttribute<GroupPedag, Integer> idSerie;
    public static volatile SingularAttribute<GroupPedag, String> groupPedagAbrev;
    public static volatile SingularAttribute<GroupPedag, Short> moyPass;
    public static volatile ListAttribute<GroupPedag, Enseigner> enseignerList;
    public static volatile SingularAttribute<GroupPedag, Integer> idGroupPedag;
    public static volatile SingularAttribute<GroupPedag, Integer> idPromo;

}