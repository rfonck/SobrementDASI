/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;


import java.io.Serializable;
import javax.persistence.Entity;


/**
 *
 * @author  Romain FONCK et Jean Jacques MELDRUM
 */
@Entity
public class Spirite extends Medium implements Serializable  {
    

    private String presentation;
    private String support;

    public Spirite(String denomination, String presentation, String sexe,String support) {
        super(sexe, denomination);
        this.denomination = denomination;
        this.presentation = presentation;
        this.support = support;
    }
    
    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public void setSupport(String support) {
        this.support = support;
    }

    @Override
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getPresentation() {
        return presentation;
    }

    public String getSupport() {
        return support;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getSexe() {
        return sexe;
    }

    public Spirite() {
    }
    
    @Override
    public String toString() {
        return "Spirite : id=" + super.id + ", denomination=" + super.denomination + ", sexe=" + super.sexe + ", presentation=" + presentation + ", support=" + support ;
    }
       
}
