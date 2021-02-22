/**
 * 
 */
package fr.afpa.cda.group4.projet.avion.app.service;

import java.util.List;

import fr.afpa.cda.group4.projet.avion.app.modelDo.JoueurDo;
import fr.afpa.cda.group4.projet.avion.app.modelDto.JouerDto;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Joueur;

/**
 * @author Charline
 *
 */
public interface IJoueurServive {

    /**
     * Permet de créer un joueur
     * 
     * @param joueur
     * @return joueur crée
     */
    Joueur createJoueur(final String login, final String motDePasse);

    /**
     * permet de chercher un joueur à partir de son Id
     * 
     * @param idJoueur
     * @return le joueur trouvé
     */
    Joueur findJoueurById(final Integer idJoueur);

    /**
     * Permet de chercher un joueur à partir de son login et son mot de passe
     * 
     * @param login
     * @param motDePasse
     * @return joueur
     */
    Joueur findJoueurByLoginEtMdp(final String login, final String motDePasse);

    /**
     * Permet de recuperer la liste de tous les joueurs
     * 
     * @return la liste de tous les joueurs
     */
    List<Joueur> findAllJoueurs();

    /**
     * Permet de recupere la liste de tous les joueurs avec leur scores
     * 
     * @return la map des couples joueurs et score associés
     */
    List<JouerDto> findAllBestJoueursEtScores();

    /**
     * Permet de mettre à jour un joueur
     * 
     * @param idJoueur
     * @param joueur
     * @return
     */
    boolean updateJoueur(final Integer idJoueur, final JoueurDo joueur);

    //void insererJouer(final JouerDo jouerDo);

}
