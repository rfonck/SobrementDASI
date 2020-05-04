/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jjmdu
 */
public class AfficherListeMediumAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        

        Service service = new Service();
        request.setAttribute("listemedium", service.listerMedium());
        
    }
    
}

