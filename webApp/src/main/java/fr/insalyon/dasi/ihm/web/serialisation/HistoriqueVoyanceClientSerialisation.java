/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.SeanceVoyance;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rfonc
 */
public class HistoriqueVoyanceClientSerialisation extends Serialisation {
    
    
  @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        List<SeanceVoyance> histo =  (List<SeanceVoyance>) request.getAttribute("historique");
        JsonObject container = new JsonObject();
        container.addProperty("cbon", true );        
        container.addProperty("tailleliste", histo.size() );
        container.addProperty("etataffichage", true);
        JsonArray liste = new JsonArray();
        for(int i=0; i<histo.size(); i++) {
            JsonObject seance = new JsonObject();
            seance.addProperty("date", Integer.toString(histo.get(i).getDebut().getTime().getDay()) + "/" + Integer.toString(histo.get(i).getDebut().getTime().getMonth()) + "/" + Integer.toString(1900+histo.get(i).getDebut().getTime().getYear()));
            seance.addProperty("medium", histo.get(i).getMedium().getDenomination());
            liste.add(seance);
        }
        container.add("seancevoyance", liste);

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}

