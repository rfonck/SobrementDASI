/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.SeanceVoyance;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rfonc
 */
public class DemanderVoyanceAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {

        // Gestion de la Session: ici, enregistrer l'ID du Client authentifié
        HttpSession session = request.getSession();
        Service service = new Service();
        Long id = Long.getLong(request.getParameter("id"));
        Client client = service.rechercherClientParId((Long) session.getAttribute("id"));
        Medium medium = service.rechercherMediumParId(id);
        SeanceVoyance seance = service.solliciterMedium(medium, client);
        Boolean bol = service.inscrireDemande(client, seance);
        request.setAttribute("Cbon", bol);
    }
    
}
