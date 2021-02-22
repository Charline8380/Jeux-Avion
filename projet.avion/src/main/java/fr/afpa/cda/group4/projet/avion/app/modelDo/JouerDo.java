/**
 * 
 */
package fr.afpa.cda.group4.projet.avion.app.modelDo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Charline
 *
 */
public class JouerDo implements Comparable<JouerDo> {

    private Integer  id_jouer;
    private JoueurDo joueur;
    private ScoreDo  score;
    private Date     date_jeu;

    /**
     * Constructeur par defaut
     */
    public JouerDo() {
        super();
        joueur = new JoueurDo();
        score = new ScoreDo();
    }

    /**
     * Construteur avec field
     * 
     * @param id_jouer
     * @param joueur
     * @param score
     * @param date_jeu
     */
    public JouerDo(final Integer id_jouer, final JoueurDo joueur, final ScoreDo score) {
        this.id_jouer = id_jouer;
        this.joueur = joueur;
        this.score = score;
        this.date_jeu = new Date();
    }

    /**
     * Constructeur avec sans id
     * 
     * @param joueur
     * @param score
     * @param date_jeu
     */
    public JouerDo(final JoueurDo joueur, final ScoreDo score) {
        this.joueur = joueur;
        this.score = score;
        this.date_jeu = new Date();
    }

    @Override
    public int compareTo(final JouerDo jouer) {

        return this.getScore().getNb_points_score().compareTo(jouer.getScore().getNb_points_score());
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return joueur.getNom_profil() + "\t\t" + simpleDateFormat.format(date_jeu)+ "\t\t"+score.getNb_points_score() ;
        
    }

    public Integer getId_jouer() {
        return id_jouer;
    }

    public void setId_jouer(final Integer id_jouer) {
        this.id_jouer = id_jouer;
    }

    public JoueurDo getJoueur() {
        return joueur;
    }

    public void setJoueur(final JoueurDo joueur) {
        this.joueur = joueur;
    }

    public ScoreDo getScore() {
        return score;
    }

    public void setScore(final ScoreDo score) {
        this.score = score;
    }

    public Date getDate_jeu() {
        return date_jeu;
    }

    public void setDate_jeu(final Date date_jeu) {
        this.date_jeu = date_jeu;
    }

}
