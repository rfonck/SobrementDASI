
package fr.insalyon.dasi.metier.modele;


import java.io.Serializable;
import javax.persistence.Entity;



/**
 *
 * @author  Romain FONCK et Jean Jacques MELDRUM
 */


@Entity
public class Cartomancien extends Medium implements Serializable {

    private String presentation;

    public Cartomancien(String denomination, String presentation, String sexe ) {
        super(sexe, denomination);
        this.presentation = presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    @Override
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getPresentation() {
        return presentation;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getSexe() {
        return sexe;
    }

    public Cartomancien() {
    }

    @Override
    public String toString() {
        return "Cartomancien : id=" + super.id + ", denomination=" + super.denomination + ", sexe=" + super.sexe + ", presentation=" + presentation ;
    }
    
}
