/**
 * 
 */
package fr.afpa.cda.group4.projet.avion.app.modelDo;

/**
 * @author Charline
 *
 */
public class ScoreDo {

    private Integer id_score;
    private Integer nb_points_score;

    /**
     * Constructeur par defaut
     */
    public ScoreDo() {
        super();
    }

    /**
     * Constructeur sans id
     * 
     * @param nb_points_score
     * @param dateJeu
     * @param listeDesJoueurs
     */
    public ScoreDo(final Integer nb_points_score) {
        this.nb_points_score = nb_points_score;

    }

    /**
     * Constructeur avec tous les fieds
     * 
     * @param id_score
     * @param nb_points_score
     * @param dateJeu
     * @param listeDesJoueurs
     */
    public ScoreDo(final Integer id_score, final Integer nb_points_score) {
        this.id_score = id_score;
        this.nb_points_score = nb_points_score;

    }

    @Override
    public String toString() {
        return "Score " + id_score + " = " + nb_points_score + ";";
    }

    public Integer getId_score() {
        return id_score;
    }

    public void setId_score(final Integer id_score) {
        this.id_score = id_score;
    }

    public Integer getNb_points_score() {
        return nb_points_score;
    }

    public void setNb_points_score(final Integer nb_points_score) {
        this.nb_points_score = nb_points_score;
    }

}
