package fr.insalyon.dasi.metier.modele;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-16T15:37:29")
@StaticMetamodel(Utilisateur.class)
public class Utilisateur_ { 

    public static volatile SingularAttribute<Utilisateur, String> motDePasse;
    public static volatile SingularAttribute<Utilisateur, Calendar> dateNaissance;
    public static volatile SingularAttribute<Utilisateur, String> adresse;
    public static volatile SingularAttribute<Utilisateur, Long> id;
    public static volatile SingularAttribute<Utilisateur, String> nom;
    public static volatile SingularAttribute<Utilisateur, String> prenom;
    public static volatile SingularAttribute<Utilisateur, Integer> numTel;
    public static volatile SingularAttribute<Utilisateur, String> email;

}