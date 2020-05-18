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
import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Cartomancien;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Spirite;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author jjmdu
 */
public class AfficherListeMediumSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Medium> listemedium = (List<Medium>)request.getAttribute("listemedium");
        
        JsonObject container = new JsonObject();
        container.addProperty("tailleliste", listemedium.size() );
        container.addProperty("etataffichage", true);
        JsonArray liste = new JsonArray();
        for(int i=0; i<listemedium.size(); i++) {
            JsonObject medium = new JsonObject();
            medium.addProperty("sexe", listemedium.get(i).getSexe());
            medium.addProperty("denomination", listemedium.get(i).getDenomination());
            if(listemedium.get(i) instanceof Cartomancien){
                Cartomancien carto = (Cartomancien) listemedium.get(i);
                medium.addProperty("type", "cartomancien");
                medium.addProperty("presentation", carto.getPresentation());
            }
            else if(listemedium.get(i) instanceof Astrologue){
                Astrologue carto = (Astrologue) listemedium.get(i);
                medium.addProperty("type", "astrologue");
                medium.addProperty("presentation", carto.getPresentation());
                medium.addProperty("formation", carto.getFormation());
                medium.addProperty("promotion", carto.getPromotion());
            }
            else if(listemedium.get(i) instanceof Spirite){
                Spirite carto = (Spirite) listemedium.get(i);
                medium.addProperty("type", "spirite");
                medium.addProperty("presentation", carto.getPresentation());
                medium.addProperty("support", carto.getSupport());
            }
            else{
                medium.addProperty("type", "none");
            }
            liste.add( medium);
        }
        container.add("listemedium", liste);
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }

}
