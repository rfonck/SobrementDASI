
package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import java.util.Calendar;
import java.util.TimeZone;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Romain FONCK et Jean Jacques MELDRUM
 */
@Entity
public class SeanceVoyance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar debut;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar fin;
    private Boolean enCours;
    private String commentaire;
    
    @ManyToOne
    private Client client;
    @ManyToOne
    private Employe employe;
    @ManyToOne
    private Medium medium;    

    public SeanceVoyance(Calendar debut, Calendar fin, Boolean enCours, String commentaire, Client client, Employe employe, Medium medium) {
        this.debut = debut;
        this.fin = fin;
        this.enCours = enCours;
        this.commentaire = commentaire;
        this.client = client;
        this.employe = employe;
        this.medium = medium;
    }



    public SeanceVoyance(Client client, Employe employe, Medium medium) {
        this.debut = Calendar.getInstance();
        this.client = client;
        this.employe = employe;
        this.medium = medium;
        this.enCours = true;
    }
    
    public void FinaliserSeance (){
        this.fin = Calendar.getInstance(); 
        this.enCours = false;   
    }
    
       
    public SeanceVoyance() {
    }
    
    public Long getId() {
        return id;
    }

    public Calendar getDebut() {
        return debut;
    }

    public Calendar getFin() {
        return fin;
    }

    public Boolean getEnCours() {
        return enCours;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public Client getClient() {
        return client;
    }

    public Employe getEmploye() {
        return employe;
    }

    public Medium getMedium() {
        return medium;
    }



    public void setDebut(Calendar debut) {
        this.debut = debut;
    }

    public void setFin(Calendar fin) {
        this.fin = fin;
    }

    public void setEnCours(Boolean enCours) {
        this.enCours = enCours;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    @Override
    public String toString() {
        return "SeanceVoyance{" + "id=" + id + ", debut=" + debut + ", fin=" + fin + ", enCours=" + enCours + ", commentaire=" + commentaire + ", client=" + client + ", employe=" + employe + ", medium=" + medium + '}';
    }



    

}
