
package fr.insalyon.dasi.metier.modele;
import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author  Romain FONCK et Jean Jacques MELDRUM
 */
@Entity
public class Astrologue extends Medium implements Serializable {
    
    private String formation;
    private String promotion;
    private String presentation;
    
    public Astrologue(String denomination,String presentation,String sexe, String formation, String promotion  ) {
        super(sexe,denomination);
        this.denomination = denomination;
        this.formation = formation;
        this.promotion = promotion;
        this.presentation = presentation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    @Override
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getFormation() {
        return formation;
    }

    public String getPromotion() {
        return promotion;
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
   
    public Astrologue() {
    }

   @Override
    public String toString() {
        return "Astrologue : id=" + id + ", denomination=" + super.denomination + ", sexe=" + super.sexe  + ", formation=" + formation + ", promotion=" + promotion +  ", presentation=" + presentation ;
    }
    
}
