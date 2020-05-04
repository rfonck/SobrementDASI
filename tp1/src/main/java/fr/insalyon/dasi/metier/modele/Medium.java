
package fr.insalyon.dasi.metier.modele;



import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;



/**
 *
 * @author  Romain FONCK et Jean Jacques MELDRUM
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED) 
public class Medium implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Long id;
    protected String sexe;
    protected String denomination;

    public Medium(String sexe, String denomination) {
        this.sexe = sexe;
        this.denomination = denomination;
    }

    public Medium() {
    }

    public String getDenomination() {
        return denomination;
    }

    public Long getId() {
        return id;
    }

    public String getSexe() {
        return sexe;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    @Override
    public String toString() {
        return "Medium{" + "id=" + id + ", sexe=" + sexe + ", denomination=" + denomination + '}';
    }

    
    
    
}
