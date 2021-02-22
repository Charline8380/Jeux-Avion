/**
 * 
 */
package fr.afpa.cda.group4.projet.avion.app.dao;

import java.util.List;

import fr.afpa.cda.group4.projet.avion.app.modelDo.ScoreDo;

/**
 * @author Charline
 *
 */
public interface IScoreDao {

    /**
     * Permet de créer un score
     * 
     * @param score
     * @return le score crée
     */
    ScoreDo createScore(final ScoreDo score);

    /**
     * Permet de chercher un score à partir de son ID
     * 
     * @param idScore
     * @return
     */
    ScoreDo findScoreById(final Integer idScore);

    /**
     * Permet d'afficher tous les scores atteinds
     * 
     * @return
     */
    List<ScoreDo> findAllScore();

}
