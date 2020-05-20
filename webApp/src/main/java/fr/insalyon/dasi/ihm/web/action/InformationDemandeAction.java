/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import java.util.Calendar;
import static java.util.Calendar.getInstance;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author rfonc
 */
public class InformationDemandeAction  extends Action{
    
      @Override
    public void executer(HttpServletRequest request) {
 
        
        Service service = new Service();
        Long idclient = Long.parseLong(request.getParameter("client"));
        Long idmedium = Long.parseLong(request.getParameter("medium"));
        
        Medium medium = service.rechercherMediumParId(idmedium);
        Client client = service.rechercherClientParId(idclient);
        
        request.setAttribute("client", client);
        request.setAttribute("medium", medium);
        request.setAttribute("historique", service.ConsulterHistoriqueSeances(client));
    }
}
