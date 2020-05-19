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
public class TestDemandeEntranteAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {

        // Gestion de la Session: ici, enregistrer l'ID du Client authentifié
        HttpSession session = request.getSession();
        Service service = new Service();
        
        
        Long id = (Long) session.getAttribute("id");
        Employe employe = service.rechercherEmployeParId(id);
        SeanceVoyance seance = service.TestDemandeEntrante(employe);
        if(seance != null)
        {
            request.setAttribute("Cbon", true); 
        }
        else
        {
            request.setAttribute("Cbon", false);
        }
        

    }
    
}
