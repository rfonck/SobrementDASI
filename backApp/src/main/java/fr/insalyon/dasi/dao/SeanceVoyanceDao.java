package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.SeanceVoyance;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author  Romain FONCK et Jean Jacques MELDRUM
 */
public class SeanceVoyanceDao {
    
    public void creer(SeanceVoyance seance) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(seance);
    }
    
    public Boolean creerDemande(Client client, SeanceVoyance seance) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        
        TypedQuery<SeanceVoyance> query = em.createQuery("SELECT c FROM SeanceVoyance c WHERE c.client = :clt and c.enCours = :bol", SeanceVoyance.class);
        query.setParameter("clt", client);
        query.setParameter("bol", true);
        if( query.getResultList().size() == 0)
        {
            em.persist(seance);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public SeanceVoyance getDemandeEntrante(Employe employe) {
    EntityManager em = JpaUtil.obtenirContextePersistance();
    TypedQuery<SeanceVoyance> query = em.createQuery("SELECT c FROM SeanceVoyance c WHERE c.employe = :employe and c.enCours = :bol", SeanceVoyance.class);
    query.setParameter("employe", employe);
    query.setParameter("bol", true);
    TypedQuery<SeanceVoyance> query2 = em.createQuery("DELETE FROM SeanceVoyance c WHERE c.employe = :employe and c.enCours = :bol", SeanceVoyance.class);
    SeanceVoyance seance = query.getSingleResult();
    query2.setParameter("employe", employe);
    query2.setParameter("bol", true);
    query2.executeUpdate();
    return seance;
    }
    
    public SeanceVoyance chercherParId(Long seanceVoyanceId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(SeanceVoyance.class, seanceVoyanceId); // renvoie null si l'identifiant n'existe pas
    }    
    
    public List<SeanceVoyance> listerSeanceVoyance() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<SeanceVoyance> query = em.createQuery("SELECT c FROM SeanceVoyance c ", SeanceVoyance.class);
        return query.getResultList();
    }
    static public List<SeanceVoyance> listerSeanceVoyanceParClient(Client client) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<SeanceVoyance> query = em.createQuery("SELECT c FROM SeanceVoyance c WHERE c.client = :clt", SeanceVoyance.class);
        query.setParameter("clt", client);
        return query.getResultList();
    }

    static public List<SeanceVoyance> listerSeanceVoyanceParEmploye(Employe employe) {
    EntityManager em = JpaUtil.obtenirContextePersistance();
    TypedQuery<SeanceVoyance> query = em.createQuery("SELECT c FROM SeanceVoyance c WHERE c.employe = :employe", SeanceVoyance.class);
    query.setParameter("employe", employe);
    return query.getResultList();
    }
    
    static public List<SeanceVoyance> listerSeanceVoyanceParMedium(Medium medium) {
    EntityManager em = JpaUtil.obtenirContextePersistance();
    TypedQuery<SeanceVoyance> query = em.createQuery("SELECT c FROM SeanceVoyance c WHERE c.medium = :medium", SeanceVoyance.class);
    query.setParameter("medium", medium);
    return query.getResultList();
    }

  
}
