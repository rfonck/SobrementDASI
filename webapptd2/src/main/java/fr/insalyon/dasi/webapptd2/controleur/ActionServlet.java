/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.webapptd2.controleur;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.service.Service;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;

/**
 *
 * @author jjmdu
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JpaUtil.init();
        response.setContentType("text/html;charset=UTF-8");
        String todo = request.getParameter("todo");
        String mail = request.getParameter("login");
        String password = request.getParameter("password");
        if(todo.equals("connecter"))
        {
        JsonObject container = new JsonObject();
        Service service = new Service();
        String type = service.identifierUtilisateur(mail,password);
        
        if(type.equals("client"))
        {
            Client c = service.connecterClient(mail, password);
            container.addProperty("connexion",true);
            JsonObject client = new JsonObject();
            client.addProperty("id",c.getId());
            client.addProperty("nom",c.getNom());
            client.addProperty("prenom",c.getPrenom());
            client.addProperty("mail",c.getEmail());
            container.add("client",client);
        }
        else
        {
            
            container.addProperty("connexion",false);
        }
        response.setContentType("application/json;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
                gson.toJson (container,out);
            }
        }
        
        
        
        
        
        
        JpaUtil.destroy();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
