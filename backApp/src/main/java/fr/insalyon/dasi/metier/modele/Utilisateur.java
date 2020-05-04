
package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;

/**
 *
 * @author  Romain FONCK et Jean Jacques MELDRUM
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public  class Utilisateur implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected Long id;
    protected String nom;
    protected String prenom;
    @Temporal(javax.persistence.TemporalType.DATE)
    protected Calendar dateNaissance;
    protected String adresse;
    protected String email;
    protected int numTel;
    protected String motDePasse;

    public Utilisateur() {
    }

    public Utilisateur( String nom, String prenom, Calendar dateNaissance, String adresse, String email, int numTel, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.email = email;
        this.numTel = numTel;
        this.motDePasse = motDePasse;
    }

    public Long getid() {
        return id;
    }

    public String getnom() {
        return nom;
    }

    public String getprenom() {
        return prenom;
    }

    public Calendar getdateNaissance() {
        return dateNaissance;
    }

    public String getadresse() {
        return adresse;
    }

    public String getemail() {
        return email;
    }

    public int getnumTel() {
        return numTel;
    }

    public String getmotDePasse() {
        return motDePasse;
    }

    public void setnom(String nom) {
        this.nom = nom;
    }

    public void setprenom(String prenom) {
        this.prenom = prenom;
    }

    public void setdateNaissance(Calendar dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setadresse(String adresse) {
        this.adresse = adresse;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public void setnumTel(int numTel) {
        this.numTel = numTel;
    }

    public void setmotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    
    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + ", adresse=" + adresse + ", email=" + email + ", numTel=" + numTel + ", motDePasse=" + motDePasse + '}';
    }
    
    
}
