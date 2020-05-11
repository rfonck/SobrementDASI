package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
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

        Service service = new Service();
        String type = service.identifierUtilisateur(login, password);
        if(type.equals("client")){
            Client client = service.connecterClient(login, password);
            request.setAttribute("client", client);

            // Gestion de la Session: ici, enregistrer l'ID du Client authentifié
            HttpSession session = request.getSession();
            if (client != null) {
                session.setAttribute("idClient", client.getId());
            }
            else {
                session.removeAttribute("idClient");
            }
        }
    }
    
}
