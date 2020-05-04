package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Cartomancien;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Spirite;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author  Romain FONCK et Jean Jacques MELDRUM
 */
public class MediumDao {
    
    
    
    public void creer(Medium mediums) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(mediums);
    }
    
    public void creerMedium (Cartomancien carto){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(carto);
    }
    
    public void creerMedium (Astrologue astro){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(astro);
    }
    
    public void creerMedium (Spirite sprite){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(sprite);
    }
    
    
    public Medium chercherParId(Long mediumsId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Medium.class, mediumsId); // renvoie null si l'identifiant n'existe pas
    }
    
    

    public Medium chercherParDenomination(String denomination) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Medium> query = em.createQuery("SELECT a FROM Medium a WHERE a.denomination = :denomination", Medium.class);
        query.setParameter("denomination", denomination); // correspond au paramètre ":mail" dans la requête
        List<Medium> clients = query.getResultList();
        Medium result = null;
        if (!clients.isEmpty()) {
            result = clients.get(0); // premier de la liste
        }
        return result;
    }
 
    
    public List<Medium> listerMediums() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        String ordre = "SELECT m FROM Medium m ";
        Query query = em.createQuery(ordre);
        List<Medium> lst = query.getResultList();
        return query.getResultList();
    }
    
    public List<Medium> topMediums() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        Query query = em.createQuery("SELECT s.medium FROM SeanceVoyance s GROUP BY s.medium ORDER BY count(s) ");
        return query.setMaxResults(5).getResultList();
    }
    
    

}
