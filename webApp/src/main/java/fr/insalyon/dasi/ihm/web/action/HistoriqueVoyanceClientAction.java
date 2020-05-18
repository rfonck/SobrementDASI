/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.SeanceVoyance;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rfonc
 */
public class HistoriqueVoyanceClientAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        Service service = new Service();
        HttpSession session = request.getSession();
        
        String idstring = session.getAttribute("id").toString();
        long id = Long.parseLong(idstring);
        Client client = service.rechercherClientParId(id);
        
        List<SeanceVoyance> liste = service.ConsulterHistoriqueSeances(client);
        
        request.setAttribute("historique", liste);
    }   
}