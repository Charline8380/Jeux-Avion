/**
 * 
 */
package fr.afpa.cda.group4.projet.avion.app.modelDto;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Charline
 *
 */
public class JouerDto {

    private String  nomProfil;
    private Integer score;
    private Date    date_jeu;

    /**
     * Constructeur par defaut
     */
    public JouerDto() {
        super();

    }

    /**
     * Constructeur avec field
     * 
     * @param nomProfil
     * @param score
     * @param date_jeu
     */
    public JouerDto(final String nomProfil, final Integer score) {
        this.nomProfil = nomProfil;
        this.score = score;
        this.date_jeu = new Date();
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return " " + nomProfil + "\t\t" + simpleDateFormat.format(date_jeu) + "\t\t" + score;
    }

    public String getNomProfil() {
        return nomProfil;
    }

    public void setNomProfil(final String nomProfil) {
        this.nomProfil = nomProfil;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(final Integer score) {
        this.score = score;
    }

    public Date getDate_jeu() {
        return date_jeu;
    }

    public void setDate_jeu(final Date date_jeu) {
        this.date_jeu = date_jeu;
    }

}
