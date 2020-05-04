package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author  Romain FONCK et Jean Jacques MELDRUM
 */
public class EmployeDao {
    
    public void creer(Employe employe) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(employe);
    }
    
    public Employe chercherParId(Long employeId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Employe.class, employeId); // renvoie null si l'identifiant n'existe pas
    }
    
    public Employe chercherParMail(String employeMail) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Employe> query = em.createQuery("SELECT c FROM Employe c WHERE c.email = :mail", Employe.class);
        query.setParameter("mail", employeMail); // correspond au paramètre ":mail" dans la requête
        List<Employe> employes = query.getResultList();
        Employe result = null;
        if (!employes.isEmpty()) {
            result = employes.get(0); // premier de la liste
        }
        return result;
    }
    
    public List<Employe> listerEmployers() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Employe> query = em.createQuery("SELECT c FROM Employe c ORDER BY c.nom ASC, c.prenom ASC", Employe.class);
        return query.getResultList();
    }
    
    public Employe rechercheEmployeApte(Medium medium){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Employe> query = em.createQuery("SELECT c FROM Employe c WHERE c.genre = :genre and c.consultationEnCours= false ORDER BY c.nombreSeance ASC", Employe.class);
        query.setParameter("genre", medium.getSexe()); // correspond au paramètre ":mail" dans la requête
        List<Employe> employes = query.getResultList();
        Employe result = null;
        if (!employes.isEmpty()) {
            result = employes.get(0); // premier de la liste
        }
        return result;
    }
    
    public int accepterSeance(Employe employe){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Employe> query = em.createQuery("UPDATE Employe c SET c.consultationEnCours = true WHERE c.id = :id", Employe.class);
        query.setParameter("id", employe.getId()); // correspond au paramètre ":mail" dans la requête
        int n = query.executeUpdate();
        return n;
    }
    
    public int finirSeance(Employe employe){
        EntityManager em = JpaUtil.obtenirContextePersistance();
 
        TypedQuery<Employe> query1 = em.createQuery("UPDATE Employe c SET c.consultationEnCours = false, c.nombreSeance = c.nombreSeance +1   WHERE c.id = :id", Employe.class);
        query1.setParameter("id", employe.getId()); // correspond au paramètre ":mail" dans la requête
        int n1 = query1.executeUpdate();

        return n1;
    }
}


