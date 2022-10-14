package entites;

import entites.DetailsBulletin;
import entites.EleveGp;
import entites.Trimestre;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-13T14:17:59")
@StaticMetamodel(BulletinNote.class)
public class BulletinNote_ { 

    public static volatile SingularAttribute<BulletinNote, Trimestre> idTrimes;
    public static volatile SingularAttribute<BulletinNote, BigDecimal> plusForteMoy;
    public static volatile SingularAttribute<BulletinNote, String> apreChefEtablis;
    public static volatile SingularAttribute<BulletinNote, BigDecimal> moyClasse;
    public static volatile SingularAttribute<BulletinNote, BigDecimal> plusFaibleMoy;
    public static volatile SingularAttribute<BulletinNote, BigDecimal> moyTrim;
    public static volatile SingularAttribute<BulletinNote, Short> notesSituation;
    public static volatile SingularAttribute<BulletinNote, Long> idBulletin;
    public static volatile ListAttribute<BulletinNote, DetailsBulletin> detailsBulletinList;
    public static volatile SingularAttribute<BulletinNote, Short> rang;
    public static volatile SingularAttribute<BulletinNote, EleveGp> idEleveGp;

}