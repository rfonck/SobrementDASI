
package fr.insalyon.dasi.metier.modele;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
/**
 *
 * @author  Romain FONCK et Jean Jacques MELDRUM
 */
@Entity
public class Employe extends Utilisateur implements Serializable {
    
    private  String genre;
    private boolean consultationEnCours;
    private  int nombreSeance;

    public Employe() {
    }

    public Employe(String Nom, String Prenom, Calendar DateNaissance, String Adresse, String Email, int NumTel, String MotDePasse,String genre, boolean consultationEnCours, int nombreSeance) {
        super(Nom, Prenom, DateNaissance, Adresse, Email, NumTel, MotDePasse);
        this.genre = genre;
        this.consultationEnCours = consultationEnCours;
        this.nombreSeance = nombreSeance;
    }


    public String getgenre() {
        return genre;
    }

    public boolean isconsultationEnCours() {
        return consultationEnCours;
    }

    public int getnombreSeance() {
        return nombreSeance;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Calendar getDateNaissance() {
        return dateNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEmail() {
        return email;
    }

    public int getNumTel() {
        return numTel;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setgenre(String genre) {
        this.genre = genre;
    }

    public void setconsultationEnCours(boolean consultationEnCours) {
        this.consultationEnCours = consultationEnCours;
    }

    public void setnombreSeance(int nombreSeance) {
        this.nombreSeance = nombreSeance;
    }

    public void setNom(String Nom) {
        this.nom = Nom;
    }

    public void setPrenom(String Prenom) {
        this.prenom = Prenom;
    }

    public void setDateNaissance(Calendar DateNaissance) {
        this.dateNaissance = DateNaissance;
    }

    public void setAdresse(String Adresse) {
        this.adresse = Adresse;
    }

    public void setEmail(String Email) {
        this.email = Email;
    }

    public void setNumTel(int NumTel) {
        this.numTel = NumTel;
    }
  
    public void setMotDePasse(String MotDePasse) {
        this.motDePasse = MotDePasse;
    }

    @Override
    public String toString() {
        return "Employe : "+ "nom=" + super.nom + ", prenom" + super.prenom + ", adresse=" + super.adresse + ", email=" + super.email + ", motDePasse=" + super.motDePasse + ", genre=" + genre + ", consultationEnCours=" + consultationEnCours + ", nombreSeance=" + nombreSeance + '}';
    }

}
