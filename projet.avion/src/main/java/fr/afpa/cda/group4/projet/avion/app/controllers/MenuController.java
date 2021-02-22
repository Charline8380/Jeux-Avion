/**
 * 
 */
package fr.afpa.cda.group4.projet.avion.app.controllers;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.afpa.cda.group4.projet.avion.app.main.App;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Joueur;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Partie;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Vaisseau;
import fr.afpa.cda.group4.projet.avion.app.views.AccueilJPanel;
import fr.afpa.cda.group4.projet.avion.app.views.AttenteJoueurJPanel;
import fr.afpa.cda.group4.projet.avion.app.views.ChoixVaisseauJPanel;
import fr.afpa.cda.group4.projet.avion.app.views.ConnexionJPanel;
import fr.afpa.cda.group4.projet.avion.app.views.CreerUnePartieJPanel;
import fr.afpa.cda.group4.projet.avion.app.views.FenetreJeuMulti;
import fr.afpa.cda.group4.projet.avion.app.views.FenetreMeteorite;
import fr.afpa.cda.group4.projet.avion.app.views.InscriptionJPanel;
import fr.afpa.cda.group4.projet.avion.app.views.MultijoueursJPanel;
import fr.afpa.cda.group4.projet.avion.app.views.PannelFinJeuSolo;
import fr.afpa.cda.group4.projet.avion.app.views.PannelMenu;
import fr.afpa.cda.group4.projet.avion.app.views.ParametresJPanel;
import fr.afpa.cda.group4.projet.avion.app.views.RejoindreUnePartieJPanel;
import fr.afpa.cda.group4.projet.avion.app.views.ScoreJPanel;

/**
 * @author Charline
 *
 */
public class MenuController {

    //attribut Intance du Singleton
    private static App fenetre = null;

    /**
     * reservation du Singleton s'il n'existe pas
     * 
     * @return
     */
    public static App getInstance() {
        if (fenetre == null) {
            fenetre = new App();
        }
        return fenetre;
    }

    /**
     * Constructeur par defaut
     */
    public MenuController() {
        super();
    }

    public static void ecranAccueil(final AccueilJPanel accueilJPanel) {
        App.changeEcran(accueilJPanel);
    }

    public static void ecranConnexion(final ConnexionJPanel connexionJPanel) {
        App.changeEcran(connexionJPanel);
    }

    public static void ecranInscription(final InscriptionJPanel inscriptionJPanel) {
        App.changeEcran(inscriptionJPanel);
    }

    public static void ecranMenu(final PannelMenu pannelMenu) {
        App.changeEcran(pannelMenu);
    }

    public static void ecranJouer(final PannelMenu pannelMenu) {
        App.changeEcran(pannelMenu);
    }

    public static void ecranChoixVaisseau(final ChoixVaisseauJPanel choixVaisseauJPanel) {
        App.changeEcran(choixVaisseauJPanel);
    }

    public static void ecranJeu(FenetreMeteorite fenetreJeu, Joueur joueur) {
        App.netoyerEcran(joueur);
        JFrame frame = App.getInstance();
        frame.setVisible(false);
        fenetreJeu = new FenetreMeteorite(joueur);
        (new Thread(fenetreJeu)).start();
    }
    public static void ecranJeuMulti( FenetreJeuMulti fenetreJeu, Partie partie, Joueur joueur) {
    	App.netoyerEcran(joueur);
    	JFrame frame = App.getInstance();
    	frame.setVisible(false);
        fenetreJeu=new FenetreJeuMulti(partie, joueur);
    	(new Thread(fenetreJeu)).start();
    }

    public static void ecranScore(final ScoreJPanel scoreJPanel, final JPanel panelDesScores) {
        App.changeEcranScore(scoreJPanel, panelDesScores);
    }

    public static void ecranParametres(final ParametresJPanel ecranParametres) {
        App.changeEcran(ecranParametres);
    }

    public static void ecranFinJeuSolo(final PannelFinJeuSolo pannelFinJeuSolo, JFrame frame) {
        frame.dispose();
        App.changeEcran(pannelFinJeuSolo);
    }

    public static void ecranMultijoueurs(final MultijoueursJPanel multijoueursJPanel) {
        App.changeEcran(multijoueursJPanel);
    }

    public static void ecranCreerUnePartie(final CreerUnePartieJPanel creerUnePartieJPanel) {
        App.changeEcran(creerUnePartieJPanel);
    }

    public static void ecranRejoindreUnePartie(final RejoindreUnePartieJPanel rejoindreUnePartieJPanel) {
        App.changeEcran(rejoindreUnePartieJPanel);
    }

    public static void ecranAttenteJoueur(AttenteJoueurJPanel attenteJoueurJPanel) {
        App.changeEcran(attenteJoueurJPanel);
        
    }

}
