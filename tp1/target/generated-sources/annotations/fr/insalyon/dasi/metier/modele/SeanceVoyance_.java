package fr.insalyon.dasi.metier.modele;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-16T15:37:29")
@StaticMetamodel(SeanceVoyance.class)
public class SeanceVoyance_ { 

    public static volatile SingularAttribute<SeanceVoyance, Calendar> debut;
    public static volatile SingularAttribute<SeanceVoyance, Employe> employe;
    public static volatile SingularAttribute<SeanceVoyance, Client> client;
    public static volatile SingularAttribute<SeanceVoyance, Calendar> fin;
    public static volatile SingularAttribute<SeanceVoyance, Long> id;
    public static volatile SingularAttribute<SeanceVoyance, Medium> medium;
    public static volatile SingularAttribute<SeanceVoyance, String> commentaire;
    public static volatile SingularAttribute<SeanceVoyance, Boolean> enCours;

}