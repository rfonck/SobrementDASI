package fr.insalyon.dasi.metier.service;

import fr.insalyon.dasi.dao.ClientDao;
import fr.insalyon.dasi.dao.EmployeDao;
import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.dao.MediumDao;
import fr.insalyon.dasi.dao.SeanceVoyanceDao;
import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Spirite;
import fr.insalyon.dasi.metier.modele.Cartomancien;
import fr.insalyon.dasi.metier.modele.SeanceVoyance;
import fr.insalyon.dasi.util.AstroTest;
import fr.insalyon.dasi.util.Message;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author  Romain FONCK et Jean Jacques MELDRUM
 */
public class Service {

    protected ClientDao clientDao = new ClientDao();
    protected EmployeDao employeDao = new EmployeDao();
    protected SeanceVoyanceDao seanceVoyanceDao = new SeanceVoyanceDao();
    protected MediumDao mediumDao = new MediumDao();
    protected AstroTest interfaceProfil = new AstroTest(); 

    
    public Client rechercherClientParId(Long id) {
        Client resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = clientDao.chercherParId(id);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherClientParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public SeanceVoyance rechercherSeanceVoyanceParId(Long id) {
        SeanceVoyance resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = seanceVoyanceDao.chercherParId(id);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherSeanceVoyanceParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public Employe rechercherEmployeParMail(String mail) {
        Employe resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = employeDao.chercherParMail(mail);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherEmployeParMail(mail)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
        
    public Client rechercherClientParMail(String mail) {
        Client resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = clientDao.chercherParMail(mail);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherClientParMail(mail)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    

    
    public Medium rechercherMedium(String nom) {
        Medium resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = mediumDao.chercherParDenomination(nom);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherMedium(nom)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public List<Client> listerClients() {
        List<Client> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = clientDao.listerClients();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerClients()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    /**
     * Service :  inscrireClient(Client nouveauClient)

description : Ce service va inscrire un client dans la base de donnée à partir des informations fournies.Algorithme : On vérifie d’abord que les informations transmises sont correctes (numéro de téléphone répondant aux standards, email valide, date de naissance vraisemblable)
 
Si les vérifications sont passées avec succès, on inscrit le nouveau client dans la base de donnée.On renvoie alors un booléen true.

Sinon on renvoie un booléen false.
     * @param client
     * @return */
    
        public Long inscrireClient(Client client) {
        Long resultat = null;
        List<String> caracteristiquesAstrologiques =  GenererProfilAstro(client);
        
        client.setAnimalTotem(caracteristiquesAstrologiques.get(3));
        client.setCouleurBonheur(caracteristiquesAstrologiques.get(2));    
        client.setSigneAstrologique(caracteristiquesAstrologiques.get(0));
        client.setSigneChinois(caracteristiquesAstrologiques.get(1));          
        
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            clientDao.creer(client);
            JpaUtil.validerTransaction();
            resultat = 1L;
            Message.envoyerMail("predictif@gmail.com",client.getEmail(), "Sujet : Bienvenue chez PREDICT’IF",
            "Corps : Bonjour "+ client.getPrenom() + ", nous vous confirmons votre inscription au service PREDICT’IF. \nRendez-vous  vite  sur  notre  site  pour  consulter  votre profil  astrologique  et  profiter  des  dons incroyables de nos mediums");
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service inscrireClient(client)", ex);
            JpaUtil.annulerTransaction();
            resultat = 0L;
            Message.envoyerMail("predictif@gmail.com",client.getEmail(), "Sujet : Bienvenue chez PREDICT’IF",
            "Corps : Bonjour "+ client.getPrenom() + ", votre inscription au service PREDICT’IF a malencontreusement échoué. \n Merci de recommencer ultérieurement.");
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    /**

Service :  identifierUtilisateur(String email, string motDePasse)

description : Ce service identifie un utilisateur à partir de l’adresse mail et du mot de passe passés en paramètre.Il différencie les employés et les clients.Algorithme : On vérifie si l’adresse mail et le mot de passe sont associé à un utilisateur.Si un utilisateur existe on renvoie son type (String)
Sinon on renvoie un NULL
     * @param mail
     * @param motDePasse
     * @return  **/
         
    public String identifierUtilisateur(String mail, String motDePasse) {
        String resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche du client
            Client client = clientDao.chercherParMail(mail);
            if (client != null) {
                // Vérification du mot de passe
                if (client.getMotDePasse().equals(motDePasse)) {
                    resultat = "client";
                }
            }
            if(resultat == null){
                Employe employe = employeDao.chercherParMail(mail);
                if (employe != null) {
                    // Vérification du mot de passe
                    if (employe.getMotDePasse().equals(motDePasse)) {
                        resultat = "employe";
                    }
                }
            }
            if(resultat == null){
               resultat = "echec";
            }            
            
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service identifierUtilisateur(mail,motDePasse)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
        
    /**

Service : connecterClient(String email, string motDePasse)

description : Renvoie le client associé à l’adresse mail et au mot de passe.Algorithme : Ce service réalise une sélection sur la table contenant les clients.Les contraintes sur la table imposent qu’un seul tuple sera retourné.Le service réalise un objet client grâce aux données récupérées.

Il renvoie ensuite cet objet.  
     * @param mail
     * @param motDePasse
     * @return 
**/
    public Client connecterClient(String mail, String motDePasse) {
        Client resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche du client
            Client client = clientDao.chercherParMail(mail);
            if (client != null) {
                // Vérification du mot de passe
                if (client.getMotDePasse().equals(motDePasse)) {
                    resultat = client;
                }
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service connecterClient(mail,motDePasse)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }  
    
    /**
Service : connecterEmploye(String email, string motDePasse)

description : Renvoie l’employé associé à l’adresse mail et au mot de passe.Algorithme : Ce service réalise une sélection sur la table contenant les employés.Les contraintes sur la table imposent qu’un seul tuple sera retourné.Le service réalise un objet employe grâce aux données récupérées.

Il renvoie ensuite cet objet.  
     * @param mail
     * @param motDePasse
     * @return 
**/
    public Employe connecterEmploye(String mail, String motDePasse) {
        Employe resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche du client
            Employe employe = employeDao.chercherParMail(mail);
            if (employe != null) {
                // Vérification du mot de passe
                if (employe.getMotDePasse().equals(motDePasse)) {
                    resultat = employe;
                }
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service connecterEmploye(mail,motDePasse)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
        
    /**
Service :  consulterListeMedium()


description :  Ce service permet de récupérer une liste contenant tous les médiums de la base de donnée et leurs caractéristiques.Algorithme : Cet algorithme renvoie une liste complète d’objets “medium”.

Ce sont des objets dont les données sont stockées dans la base de donnée du serveur.


* 
     * @return 
**/
    public List<Medium> listerMedium() {
        List<Medium> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = mediumDao.listerMediums();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerMediums()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
  
    /**
    Service :  consulterHistoriqueSeances(Client client)

description : Cette fonction fournit l’historique des consultations du client passé en paramètre.Algorithme : Ce service effectue une sélection dans la base de donnée de toutes les séances dont le client est celui passé en paramètre.On crée ensuite une liste d’objets “seanceVoyance” que l’on renvoie.
  
     * @param client
     * @return 
**/
    
    public List<SeanceVoyance> ConsulterHistoriqueSeances(Client client) {
        List<SeanceVoyance> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = SeanceVoyanceDao.listerSeanceVoyanceParClient(client);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service ConsulterHistoriqueSeances", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    /**
Service : soliciterMedium(médium X, employe e)

description : Cette fonction permet de chercher l’employé le plus apte à faire la consultation.Renvoie un objet de type employé.Algorithme : 
Un premier tri est fait selon le genre, puis l’employé avec le moins de consultations est renvoyé.Si aucun employé n’est dispo renvoyer un employé vide
     * @param medium
     * @param client
     * @return 
**/

    public Employe solliciterMedium (Medium medium, Client client){
        Employe employe = null;
        JpaUtil.creerContextePersistance();
        try {
            employe = employeDao.rechercheEmployeApte(medium);
            String num = Integer.toString(employe.getNumTel());
            Message.envoyerNotification( num ,"Pour : "+ employe.getprenom() + " "+ employe.getNom() + ", Tel : "+num +" \n Message : Bonjour "+ employe.getprenom() + ". Consultation requise pour "+ client.getprenom() +" " + client.getprenom() + " \nMédium à incarner : " + medium.getDenomination());
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service SolliciterMedium", ex);
            employe = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return employe;
    }
    
    public Long inscrireSeanceVoyance(SeanceVoyance seance) {
        Long resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            seanceVoyanceDao.creer(seance);
            JpaUtil.validerTransaction();
            resultat = seance.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service inscrireSeanceVoyance(client)", ex);
            JpaUtil.annulerTransaction();
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
 
    /**
 Service : accepterConsultation(Employe employe)
 
 Decription : Cette fonction change le statut de l'employé et de la consultation
 
 Algorithme : Ce service change le bool consultationEnCours d'employe et le bool enCours de SeanceVoyance et d'autre trucs
     * @param client
     * @param medium
     * @param employe
     * @return 
 **/

    public SeanceVoyance AccepterConsultation(Client client, Employe employe, Medium medium){
    SeanceVoyance seance = new SeanceVoyance(client, employe, medium);
    JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            employeDao.accepterSeance(employe);
            Message.envoyerNotification(Integer.toString(client.getNumTel()), "Pour : "+ client.getPrenom() + " "+ client.getNom()+", Tel : "+ client.getnumTel()+ "\n" +
"Message : Bonjour "+ client.getPrenom() + ". J’ai bien reçu votre demande de consultation du "+ Calendar.getInstance().getTime().toString() + ".\n" +
"Vous pouvez dès à présent me contacter au "+ Integer.toString(employe.getNumTel()) + ". A tout de suite ! Médiumiquement\n" +
"vôtre, "+ medium.getDenomination() );
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service AccepterConsultation(seance, employe)", ex);
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return seance;
    
}

    /**
Service :  GenererProfilAstro(Client client)

description : Cette fonction renvoie un objet du type profilAstro personnalisé au client passé au paramètre.Algorithme : Ce service réalise une requête au service web externe IfAstroNet et renvoie le résultat.
     * @param client 
     * @return 
*/

    public List<String> GenererProfilAstro(Client client)  {

        List<String> profilAstro;     
        
        try {
            profilAstro = interfaceProfil.getProfil( client.getPrenom() , client.getDateNaissance().getTime());
        } catch (IOException ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service GenererProfilAstro(client)", ex);
            profilAstro = null;
        } finally {
        }
        
        return profilAstro;
    }


    /**
Service :  soumettreNote(seanceVoyance seance, String commentaire)

description : Ce service ajoute le commentaire à la séance passé en paramètre.Algorithme : Ce service crée ou modifie l’attribut Commentaire de l’objet seanceVoyance passé en commentaire pour lui donner la valeur du commentaire passé en paramètre.
     * @param seance
     * @param commentaire
     * @return 
**/

    public SeanceVoyance SoumettreNote(SeanceVoyance seance, String commentaire){
    
    seance.setCommentaire(commentaire);
    return seance;
}

    /**


Service :  string generateurVoyance (int noteAmour, int noteTravail , int noteSanté)

description : Prend les trois notes en paramètre et renvoie une prédiction adaptée

Algorithme : Pour chaque notes de chaque type (Amour, Travail, Santé) une phrase est associée.Il y a donc 12 phrases différentes à créer.La fonction renverra un string contenant toutes les prédictions.
     * @param client
     * @param noteAmour
     * @param noteTravail
     * @param noteSante
     * @return 
**/

    public List<String> generateurVoyance(Client client, int noteAmour, int noteTravail , int noteSante) {
    
       
    List<String> prediction = null ;
    
    try {
        prediction = interfaceProfil.getPredictions(client.getCouleurBonheur(), client.getAnimalTotem(), noteAmour, noteSante, noteTravail);
    
    } catch (IOException ex) {
        Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service GenererProfilAstro(client)", ex);
 
    } finally {
    }
    return prediction;
}

    /**
Service : finSeance(seanceVoyance seance) 

description : Ce service archive la séance.

Algorithme : Ce service change l’attribut “fin” de l’objet séance passée en paramètre au Timestamp actuel, puis elle l’enregistre dans la base de donnée et la détruit. On renvoie true si la procédure s’est déroulée correctement et false sinon

     **/
    
    public void finSeance(SeanceVoyance seance) {
        seance.FinaliserSeance();
        JpaUtil.creerContextePersistance();
        try {
            Employe employe = seance.getEmploye();
            JpaUtil.ouvrirTransaction();
            employeDao.finirSeance(employe);
            seanceVoyanceDao.creer(seance);
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service finSeance(seance)", ex);
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }
    
    /**

Service : RepartitionMedium

Description : Ce service renvoie la liste des médiums avec le nombre de consultations associé.

Algorithme : Ce service va utiliser une requête jpql pour déterminer le nombre de consultations de chaque médium, puis il va renvoyer une liste contenant tous les médiums avec leur nombre de consultation.

*/

    public HashMap RepartitionMedium() 
    {
        
        HashMap<Medium, Integer> lhm = new HashMap<>();
        List<Medium> tous;
        JpaUtil.creerContextePersistance();
        try {
            tous = mediumDao.listerMediums();
        for (int i = 0; i< tous.size(); i++) {
            int a = SeanceVoyanceDao.listerSeanceVoyanceParMedium(tous.get(i)).size();
            lhm.put(tous.get(i),a);
        }
         } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service RepartitionMedium()", ex);
            lhm.put(null, null);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return lhm;
    }
    
    
    /**
Service : RepartitionEmploye 

Description : Ce service renvoie la liste des employés avec le nombre de consultations associé

Algorithme : Ce service va utiliser une requête jpql pour déterminer le nombre de consultations de chaque employé, puis il va renvoyer une liste contenant tous les employés avec leur nombre de consultation.

*/

    public HashMap RepartitionEmploye() 
    {
        HashMap<Employe, Integer> lhm = new HashMap<>();
        List<Employe> tous;
        JpaUtil.creerContextePersistance();
        try {
            tous = employeDao.listerEmployers();
        for (int i = 0; i< tous.size(); i++) {
            int a = SeanceVoyanceDao.listerSeanceVoyanceParEmploye(tous.get(i)).size();
            lhm.put(tous.get(i),a );
        }
         } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service RepartitionEmploye()", ex);
            lhm.put(null, null);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return lhm;
    }
    
    
/**
Service : List<Medium>TopMedium() 

Description : Ce service renvoie la liste des 5 premiers médiums de l’agence dans l'ordre décroissant de leur popularité.

Algorithme : Ce service va utiliser une requête jpql pour déterminer le nombre de consultations de chaque médium, puis il va renvoyer une liste contenant les 5 premiers. 

**/
    public List<Medium> topMedium() {
        List<Medium> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = mediumDao.topMediums();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service topMediums()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    
    
 /*
Service : InitialisationMediumsEmployes() 

description : Ce service enrengistre les médiums et les employés présents de base.


     **/
    
    public void InitialisationMediumsEmployes() {
        Medium aurel = new Spirite("Gwenaëlle", "Spécialiste des grandes conversations au-delà de TOUTES les frontières.", "F", "Boule de cristal");       
        Medium romain = new Spirite("Professeur Tran", "Marc de café, boule de cristal, oreilles de lapin", "H", "Votre avenir est devant vous: regardons-le ensemble!");
        Medium jj = new Astrologue("Serena", "École Normale Supérieure d’Astrologie (ENS-Astro)", "F", "2006", "Basée  à  Champigny-sur-Marne, Serena vous révèlera votre  avenir  pour éclairer  votre passé.");   
        Medium bastien = new Cartomancien( "Mme Irma", "Comprenez votre entourage grâce à mes cartes! Résultats rapides.", "F"); 
        Medium agathe = new Cartomancien( "Endora", "Mes cartes répondront à toutes vos questions personnelles.", "F"); 
        Calendar aujourdhui = Calendar.getInstance(); 
        Employe thomas  = new Employe( "Nom", "Prenom", aujourdhui, "Adresse", "Email", 1029384756, "MotDePasse" ,"F",false,12);
        Employe michou  = new Employe( "Blaze", "Prenom", aujourdhui, "Adresse", "Yoyoyo", 1029384756, "MotDePasse" ,"F",false,4);
        
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            mediumDao.creer(aurel);
            mediumDao.creer(romain);
            mediumDao.creer(jj);
            mediumDao.creer(bastien);
            mediumDao.creer(agathe);
            employeDao.creer(thomas);
            employeDao.creer(michou);            
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service InitialisationMediums()", ex);
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

}
