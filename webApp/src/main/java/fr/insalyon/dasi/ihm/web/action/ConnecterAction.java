package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DASI Team
 */
public class ConnecterAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        
        HttpSession session = request.getSession();
        
        session.removeAttribute("type");                
        session.removeAttribute("id");
        
        Service service = new Service();
        String type = service.identifierUtilisateur(login, password);
        if(type.equals("employe")){
            Employe employe = service.connecterEmploye(login, password);
            session.setAttribute("type", "Employe");
            session.setAttribute("id", employe.getId());
            request.setAttribute("connexion", true);
            request.setAttribute("type", "Employe");
            request.setAttribute("id", employe.getId());

        }
        else if(type.equals("client")){
            Client client = service.connecterClient(login, password);
            session.setAttribute("type", "Client");
            session.setAttribute("id", client.getId());
            request.setAttribute("connexion", true);
            request.setAttribute("type", "Client");
            request.setAttribute("id", client.getId());                
         
        }
        else 
        {
            request.setAttribute("connexion", false);
            request.removeAttribute("type");            
            request.removeAttribute("id");
        }
    }   
}
