/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.service.Service;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rfonc
 */
public class InscrireAction extends Action{
    
      @Override
    public void executer(HttpServletRequest request) {
        
        Calendar aujourdhui = Calendar.getInstance();
        
        String nom_de_famille = request.getParameter("nom_de_famille");
        String prenom = request.getParameter("prenom");
        String adresse_electronique = request.getParameter("adresse_electronique");
        String Date_de_naissance = request.getParameter("Date_de_naissance");
        String numero_de_telephone = request.getParameter("numero_de_telephone");
        String adresse_postale = request.getParameter("adresse_postale");
        String mot_de_passe = request.getParameter("mot_de_passe");
    
        int num;
        try {
           num = Integer.parseInt(numero_de_telephone);
        }
        catch (NumberFormatException e)
        {
           num = 0;
        }

        Client client = new Client(prenom,nom_de_famille, aujourdhui, adresse_postale, adresse_electronique, num, mot_de_passe);
        
        Service service = new Service();
        long id = service.inscrireClient(client);
        if(id != 0){
            request.setAttribute("inscription", true);
        }
        else {
            request.setAttribute("inscription", false);
        
        }
    }
}
