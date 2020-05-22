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
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rfonc
 */
public class TopMediumSerialisation extends Serialisation {
    
    
  @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        JsonObject container = new JsonObject();
        
        HashMap<Employe, Integer> repatitionEmploye = (HashMap) request.getAttribute("repartitionEmploye");
        HashMap<Medium, Integer> repatitionMedium = (HashMap) request.getAttribute("repartitionMedium");
        List<Medium> topMedium = (List<Medium>) request.getAttribute("topMedium");

        container.addProperty("topmediumsize", topMedium.size() );
        JsonArray liste = new JsonArray();
        for(int i=0; i<topMedium.size(); i++) {
            JsonObject medium = new JsonObject();
            medium.addProperty("denomination", topMedium.get(i).getDenomination());
            liste.add(medium);
        }
        container.add("topmedium", liste);
        
        JsonArray repartitionM = new JsonArray();
        

        for (HashMap.Entry<Medium, Integer> e : repatitionMedium.entrySet()) {
          JsonObject entree = new JsonObject();
          entree.addProperty("medium", e.getKey().getDenomination());
          entree.addProperty("nb", e.getValue());
          repartitionM.add(entree);
        }
        container.add("repartitionmedium", repartitionM);
        
        
                JsonArray repartitionE = new JsonArray();
        

        for (HashMap.Entry<Employe, Integer> e : repatitionEmploye.entrySet()) {
          JsonObject entree = new JsonObject();
          entree.addProperty("employe", e.getKey().getNom());
          entree.addProperty("nb", e.getValue());
          repartitionE.add(entree);
        }
        container.add("repartitionemploye", repartitionE);
        container.addProperty("nbemploye", repatitionEmploye.size());
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
