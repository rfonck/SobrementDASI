/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.SeanceVoyance;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rfonc
 */
public class TerminerSeanceAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {

        // Gestion de la Session: ici, enregistrer l'ID du Client authentifié
        HttpSession session = request.getSession();
        Service service = new Service();
        
        SeanceVoyance seance = (SeanceVoyance) session.getAttribute("seance");
        String commentaire = request.getParameter("commentaire");
        seance.setCommentaire(commentaire);
        service.finSeance(seance);
        


    }
    
}
