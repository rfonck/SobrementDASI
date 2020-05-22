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
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rfonc
 */
public class TopMediumAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        Service service = new Service();

        List<Medium> topMedium = service.topMedium();
        HashMap repartitionEmploye = service.RepartitionEmploye();
        HashMap repartitionMedium = service.RepartitionMedium();
        
        request.setAttribute("topMedium", topMedium);
        request.setAttribute("repartitionEmploye", repartitionEmploye);
        request.setAttribute("repartitionMedium", repartitionMedium);        
    }   
}