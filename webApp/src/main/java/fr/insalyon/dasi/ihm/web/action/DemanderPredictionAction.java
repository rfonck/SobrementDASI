/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author rfonc
 */
public class DemanderPredictionAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        

        Service service = new Service();

        int sante = Integer.parseInt(request.getParameter("sante"));
        int amour = Integer.parseInt(request.getParameter("amour"));
        int travail = Integer.parseInt(request.getParameter("travail"));

        Client client = new Client();
        List<String> liste = service.generateurVoyance(client, amour, travail, sante);
        request.setAttribute("prediction", liste.get(0));
    }
    
}

