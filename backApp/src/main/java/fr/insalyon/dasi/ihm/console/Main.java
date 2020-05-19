package fr.insalyon.dasi.ihm.console;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.SeanceVoyance;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Romain FONCK et Jean Jacques MELDRUM
 */
public class Main {

    public static void main(String[] args) {
         
        JpaUtil.init();
        
        Service service = new Service();
        //on utilise le service suivant pour initialiser les Médiums et employés de base
       

        
        System.out.println("-------------------------------------------- " ); 
        System.out.println("--------test de déroulement de séance------- " ); 
        System.out.println("-------------------------------------------- " ); 
        
        System.out.println("  " ); 
        System.out.println("1. Le client se connecte " ); 
        System.out.println("  " ); 
        
        String email = "bastoche";
        String mdp = "TruiteFumée";
        
        String denomination = service.identifierUtilisateur(email, mdp);
        System.out.println("    -> " + denomination);
        
        Client client1 = service.connecterClient(email, mdp);
        System.out.println("    -> " + client1.toString());
        
        
        System.out.println("  " ); 
        System.out.println("2. Le client consulte son historique " ); 
        System.out.println("  " );
        
        List<SeanceVoyance> histo = service.ConsulterHistoriqueSeances(client1);
            
        for(int i = 0; i< histo.size() ; i++)
        {
                 System.out.println("       Séance de voyance n°  " + i +  " "+   histo.get(i).toString());
        }
        
        System.out.println("  " ); 
        System.out.println("3. Le client décide de demander à voir la liste des mediums " ); 
        System.out.println("  " );
                
        List<Medium> mediums = service.listerMedium();
        
        System.out.println("        nous vous proposons nos médiums : " );
        for(int i = 0; i< mediums.size() ; i++)
        {
                 System.out.println("       Médium n°  " + i +  " "+   mediums.get(i).toString());
        }
       
        
        System.out.println("  " ); 
        System.out.println("4. Il a choisi un médium pour sa consultation et le sollicite " ); 
        System.out.println("  " );
                
        Medium aSolliciter = service.rechercherMedium("Mme Irma");
        SeanceVoyance apte = service.solliciterMedium(aSolliciter, client1);
        
        System.out.println("       L'employé " + apte.getEmploye().toString() + " va interpréter ce rôle");
        service.inscrireDemande(client1, apte);
       

        JpaUtil.destroy();
    }
}
