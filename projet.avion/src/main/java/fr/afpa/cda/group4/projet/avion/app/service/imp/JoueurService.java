/**
 * 
 */
package fr.afpa.cda.group4.projet.avion.app.service.imp;

import java.util.ArrayList;
import java.util.List;

import fr.afpa.cda.group4.projet.avion.app.autre.util.MD5Encryption;
import fr.afpa.cda.group4.projet.avion.app.dao.imp.JoueurDao;
import fr.afpa.cda.group4.projet.avion.app.dao.imp.ScoreDao;
import fr.afpa.cda.group4.projet.avion.app.modelDo.JouerDo;
import fr.afpa.cda.group4.projet.avion.app.modelDo.JoueurDo;
import fr.afpa.cda.group4.projet.avion.app.modelDo.ScoreDo;
import fr.afpa.cda.group4.projet.avion.app.modelDto.JouerDto;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Joueur;
import fr.afpa.cda.group4.projet.avion.app.service.IJoueurServive;

/**
 * @author Charline
 *
 */
public class JoueurService implements IJoueurServive {

    private JoueurDao joueurDao = new JoueurDao();
    private ScoreDao  scoreDao  = new ScoreDao();

    /**
     * Constructeur par defaut
     */
    public JoueurService() {
        super();
    }

    /**
     * permet de mapper un joueurDo ==> JoueurDto
     * 
     * @param joueurDo
     * @return joueurDto
     */
    private Joueur mapToJoueur(final JoueurDo joueurDo) {
        final Joueur joueurDto = new Joueur();
        joueurDto.setId(joueurDo.getId_joueur());
        joueurDto.setNom(joueurDo.getNom_profil());
        joueurDto.setPassword(joueurDo.getMot_de_passe());
        return joueurDto;
    }

    /**
     * Permet de mapper un joueurDto ==> joueurDo
     * 
     * @param joueurDto
     * @return joueurDo
     */
    private JoueurDo mapToJoueurDo(final Joueur joueurDto) {
        final JoueurDo joueurDo = new JoueurDo();
        joueurDo.setNom_profil(joueurDto.getNom());
        joueurDo.setMot_de_passe(joueurDto.getPassword());
        return joueurDo;
    } 

    /**
     * Permet de maper un jouerDo ==> jouerDto
     * 
     * @param jouerDo
     * @param joueurDo
     * @param scoreDo
     * @return
     */
    public JouerDto mapToJouerDto(final JouerDo jouerDo, final JoueurDo joueurDo, final ScoreDo scoreDo) {
        final JouerDto jouerDto = new JouerDto();
        jouerDto.setNomProfil(joueurDo.getNom_profil());
        jouerDto.setScore(scoreDo.getNb_points_score());
        jouerDto.setDate_jeu(jouerDo.getDate_jeu());

        return jouerDto;

    }

    @Override
    public Joueur createJoueur(final String login, final String motDePasse) {

        // Je crypte le mot de passe
        final String motDePasseCrypte = MD5Encryption.cryptage(motDePasse);

        final JoueurDo joueurDo = joueurDao.findJoueurByLoginEtMdp(login, motDePasseCrypte);

        if (joueurDo == null) {
            final Joueur joueurDTO = mapToJoueur((joueurDao.createJoueur(login, motDePasseCrypte)));
            return joueurDTO;
        }
        return null;
    }

    @Override
    public Joueur findJoueurById(final Integer idJoueur) {
        final JoueurDo joueurDo = joueurDao.findJoueurById(idJoueur);

        if (joueurDo != null) {
            final Joueur joueur = mapToJoueur(joueurDo);
            joueur.setNbViesRestantes(5);
            return joueur;
        }
        return null;
    }

    @Override
    public Joueur findJoueurByLoginEtMdp(final String login, final String motDePasse) {

        // Je crypte le mot de passe par l utilisateur
        final String motDePasseCrypte = MD5Encryption.cryptage(motDePasse);

        final JoueurDo joueurDo = joueurDao.findJoueurByLoginEtMdp(login, motDePasseCrypte);

        if (joueurDo != null) {
            final Joueur joueur = mapToJoueur(joueurDo);
            joueur.setNbViesRestantes(5);
            return joueur;
        }
        return null;
    }

    @Override
    public List<Joueur> findAllJoueurs() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean updateJoueur(final Integer idJoueur, final JoueurDo joueur) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<JouerDto> findAllBestJoueursEtScores() {
        final List<JouerDto> listeDesBestScores = new ArrayList<JouerDto>();
        final List<JouerDo> lesBestScores = joueurDao.findAllBestJoueursEtScores();

        for (JouerDo jouerDo : lesBestScores) {
            final JoueurDo joueurDo = joueurDao.findJoueurById(jouerDo.getJoueur().getId_joueur());
            final ScoreDo scoreDo = scoreDao.findScoreById(jouerDo.getScore().getId_score());
            final JouerDto jouerDto = mapToJouerDto(jouerDo, joueurDo, scoreDo);
            listeDesBestScores.add(jouerDto);
        }
        
        return listeDesBestScores;
    }

}
