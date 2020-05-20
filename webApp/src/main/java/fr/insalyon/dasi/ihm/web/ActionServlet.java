package fr.insalyon.dasi.ihm.web;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.ihm.web.action.Action;
import fr.insalyon.dasi.ihm.web.action.AfficherListeMediumAction;
import fr.insalyon.dasi.ihm.web.action.ConnecterAction;
import fr.insalyon.dasi.ihm.web.action.DeconnecterAction;
import fr.insalyon.dasi.ihm.web.action.DemanderVoyanceAction;
import fr.insalyon.dasi.ihm.web.action.HistoriqueVoyanceClientAction;
import fr.insalyon.dasi.ihm.web.action.InformationDemandeAction;
import fr.insalyon.dasi.ihm.web.action.InscrireAction;
import fr.insalyon.dasi.ihm.web.action.ProfilAstroAction;
import fr.insalyon.dasi.ihm.web.action.TestDemandeEntranteAction;
import fr.insalyon.dasi.ihm.web.serialisation.AfficherListeMediumSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.InscrireSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.ConnecterSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.DeconnecterSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.DemanderVoyanceSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.HistoriqueVoyanceClientSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.InformationDemandeSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.ProfilAstroSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.Serialisation;
import fr.insalyon.dasi.ihm.web.serialisation.TestDemandeEntranteSerialisation;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DASI Team
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        JpaUtil.init();
    }

    @Override
    public void destroy() {
        JpaUtil.destroy();
        super.destroy();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        request.setCharacterEncoding("UTF-8");

        String todo = request.getParameter("todo");

        Action action = null;
        Serialisation serialisation = null;

        if (todo != null) {
            switch (todo) {
                case "connecter":
                    action = new ConnecterAction();
                    serialisation = new ConnecterSerialisation();
                    break;
                case "afficherlistemedium":
                    action = new AfficherListeMediumAction();
                    serialisation = new AfficherListeMediumSerialisation();
                    break;
                case "inscrire":
                    action = new InscrireAction();
                    serialisation = new InscrireSerialisation();
                    break;
                case "deconnecter":
                    action = new DeconnecterAction();
                    serialisation = new DeconnecterSerialisation();
                    break;
                case "profilastro":
                    action = new ProfilAstroAction();
                    serialisation = new ProfilAstroSerialisation();
                    break;
                case "histovoyanceclient":
                    action = new HistoriqueVoyanceClientAction();
                    serialisation = new HistoriqueVoyanceClientSerialisation();
                    break;
                case "demandervoyance":
                    action = new DemanderVoyanceAction();
                    serialisation = new DemanderVoyanceSerialisation();
                    break;
                case "testdemandeentrante":
                    action = new TestDemandeEntranteAction();
                    serialisation = new TestDemandeEntranteSerialisation();
                    break;
                case "informationsdemande":
                    action = new InformationDemandeAction();
                    serialisation = new InformationDemandeSerialisation();
                    break;
            }
        }
        
        if (action != null) {
            action.executer(request);
            serialisation.serialiser(request, response);
        }
        else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erreur dans les paramètres de la requête");
        }

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
