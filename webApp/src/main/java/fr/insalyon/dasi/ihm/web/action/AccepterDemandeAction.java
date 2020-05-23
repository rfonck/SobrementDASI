/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.SeanceVoyance;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rfonc
 */
public class AccepterDemandeAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        HttpSession session = request.getSession();
        Service service = new Service();
        
        Long idClient = Long.parseLong(request.getParameter("client"));
        Long idMedium = Long.parseLong(request.getParameter("medium"));        
        Long idEmploye = (Long) session.getAttribute("id");
        
        
        Employe employe = service.rechercherEmployeParId(idEmploye);
        Medium medium = service.rechercherMediumParId(idMedium);
        Client client = service.rechercherClientParId(idClient);
        
        SeanceVoyance seance = service.AccepterConsultation(client, employe, medium);
        
        session.setAttribute("seance", seance);
        session.setAttribute("enCours", true);
    }
}

    