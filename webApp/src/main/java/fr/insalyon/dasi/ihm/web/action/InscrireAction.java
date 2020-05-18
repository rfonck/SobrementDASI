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
import java.lang.Integer;
import static java.util.Calendar.getInstance;

/**
 *
 * @author rfonc
 */
public class InscrireAction extends Action{
    
      @Override
    public void executer(HttpServletRequest request) {
        
        String nom_de_famille = request.getParameter("nom_de_famille");
        String prenom = request.getParameter("prenom");
        String adresse_electronique = request.getParameter("adresse_electronique");
        String Date_de_naissance = request.getParameter("Date_de_naissance");
        String numero_de_telephone = request.getParameter("numero_de_telephone");
        String adresse_postale = request.getParameter("adresse_postale");
        String mot_de_passe = request.getParameter("mot_de_passe");
        Calendar date = getInstance();
        //Integer.parseInt(Date_de_naissance.substring(0, 1))
        int Cbon = 1;
        
        
        try {
           date.set(Integer.parseInt(Date_de_naissance.substring(0,4)), Integer.parseInt(Date_de_naissance.substring(5,7))-1, Integer.parseInt(Date_de_naissance.substring(8,10)));
        }
        catch (NumberFormatException e)
        {
            Cbon = 0;
        }
        
        int num = 0;
        try {
           num = Integer.parseInt(numero_de_telephone);
        }
        catch (NumberFormatException e)
        {
           Cbon = 0;
        }

        Client client = new Client(prenom,nom_de_famille, date, adresse_postale, adresse_electronique, num, mot_de_passe);
        
        Service service = new Service();
        Long id = service.inscrireClient(client);
        
        
        if (id != 0 && Cbon == 1 ) {
            request.setAttribute("inscription", true);
        }
        else {
            request.removeAttribute("inscription");
        }        
        

    }
}
