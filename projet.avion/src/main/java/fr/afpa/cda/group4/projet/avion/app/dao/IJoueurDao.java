/**
 * 
 */
package fr.afpa.cda.group4.projet.avion.app.dao;

import java.util.List;

import fr.afpa.cda.group4.projet.avion.app.modelDo.JouerDo;
import fr.afpa.cda.group4.projet.avion.app.modelDo.JoueurDo;

/**
 * @author Charline
 *
 */
public interface IJoueurDao {

    /**
     * Permet de créer un joueur
     * 
     * @param joueur
     * @return joueur crée
     */
    JoueurDo createJoueur(final String login, final String motDePasse);

    /**
     * permet de chercher un joueur à partir de son Id
     * 
     * @param idJoueur
     * @return le joueur trouvé
     */
    JoueurDo findJoueurById(final Integer idJoueur);
    /**
     * Permet de chercher un joueur à partir de son login et son mot de passe
     * 
     * @param login
     * @param motDePasse
     * @return joueur
     */
    JoueurDo findJoueurByLoginEtMdp(final String login, final String motDePasse);

    /**
     * Permet de recuperer la liste de tous les joueurs
     * 
     * @return la liste de tous les joueurs
     */
    List<JoueurDo> findAllJoueurs();

    /**
     * Permet de recupere la liste de tous les joueurs avec leur scores
     * 
     * @return la map des couples joueurs et score associés
     */
    List<JouerDo> findAllBestJoueursEtScores();

    /**
     * Permet de mettre à jour un joueur
     * 
     * @param idJoueur
     * @param joueur
     * @return
     */
    boolean updateJoueur(final Integer idJoueur, final JoueurDo joueur);

    /**
     * Permet d'inserer une ligne joueur score date dans jouerDo de la BDD
     * 
     * @param jouerDo
     */
    void insererJouer(final JouerDo jouerDo);

}
