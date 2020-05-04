package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;

/**
 *
 * @author  Romain FONCK et Jean Jacques MELDRUM
 */

@Entity
public class Client extends Utilisateur implements Serializable {

    private String signeAstrologique;
    private String signeChinois;
    private String animalTotem;
    private String couleurBonheur;

    public Client(String nom, String prenom, Calendar dateNaissance, String adresse, String email, int numTel, String motDePasse) {
        super(nom, prenom, dateNaissance, adresse, email, numTel, motDePasse);
    }

    
    public Client(String Nom, String Prenom, Calendar DateNaissance, String Adresse, String Email, int NumTel, String MotDePasse,String signeAstrologique, String signeChinois, String animalTotem, String couleurBonheur) {
        super(Nom, Prenom, DateNaissance, Adresse, Email, NumTel, MotDePasse);
        this.signeAstrologique = signeAstrologique;
        this.signeChinois = signeChinois;
        this.animalTotem = animalTotem;
        this.couleurBonheur = couleurBonheur;
    }

    public void setId(Long Id) {
        this.id = Id;
    }
        
    public void setSigneAstrologique(String signeAstrologique) {
        this.signeAstrologique = signeAstrologique;
    }

    public void setSigneChinois(String signeChinois) {
        this.signeChinois = signeChinois;
    }

    public void setAnimalTotem(String animalTotem) {
        this.animalTotem = animalTotem;
    }

    public void setCouleurBonheur(String couleurBonheur) {
        this.couleurBonheur = couleurBonheur;
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

    public String getSigneAstrologique() {
        return signeAstrologique;
    }

    public String getSigneChinois() {
        return signeChinois;
    }

    public String getAnimalTotem() {
        return animalTotem;
    }

    public String getCouleurBonheur() {
        return couleurBonheur;
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

    public Client() {
    
    }

    @Override
    public String toString() {
        return "Client : " + "nom=" + super.nom + "prenom" + super.prenom + "adresse=" + super.adresse + "email=" + super.email + "motDePasse=" + super.motDePasse + "signeAstrologique=" + signeAstrologique + ", signeChinois=" + signeChinois + ", animalTotem=" + animalTotem + ", couleurBonheur=" + couleurBonheur ;
    }





}
