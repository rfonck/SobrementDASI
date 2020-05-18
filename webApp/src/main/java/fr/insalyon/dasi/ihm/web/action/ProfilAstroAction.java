/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.service.Service;
import fr.insalyon.dasi.metier.modele.Client;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rfonc
 */
public class ProfilAstroAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        Service service = new Service();
        HttpSession session = request.getSession();
        String idstring = session.getAttribute("id").toString();
        long id = Long.parseLong(idstring);
        Client client = service.rechercherClientParId(id);
        request.setAttribute("signeAstro", client.getSigneAstrologique());
        request.setAttribute("signeChinois", client.getSigneChinois());
        request.setAttribute("animalTotem", client.getAnimalTotem());
        request.setAttribute("couleurBonheur", client.getCouleurBonheur());
    }
    
}