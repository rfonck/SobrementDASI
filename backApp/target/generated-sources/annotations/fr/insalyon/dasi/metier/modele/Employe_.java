package fr.insalyon.dasi.metier.modele;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-19T21:03:03")
@StaticMetamodel(Employe.class)
public class Employe_ extends Utilisateur_ {

    public static volatile SingularAttribute<Employe, String> genre;
    public static volatile SingularAttribute<Employe, Integer> nombreSeance;
    public static volatile SingularAttribute<Employe, Boolean> consultationEnCours;

}