package fr.afpa.cda.group4.projet.avion.app.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.afpa.cda.group4.projet.avion.app.audio.Musique;
import fr.afpa.cda.group4.projet.avion.app.controllers.MenuController;
import fr.afpa.cda.group4.projet.avion.app.dao.imp.JoueurDao;
import fr.afpa.cda.group4.projet.avion.app.modelDo.JouerDo;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Joueur;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Partie;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Vaisseau;
import fr.afpa.cda.group4.projet.avion.app.serveurAccess.ConnexionServeur;
import fr.afpa.cda.group4.projet.avion.app.views.AccueilJPanel;
import fr.afpa.cda.group4.projet.avion.app.views.AttenteJoueurJPanel;
import fr.afpa.cda.group4.projet.avion.app.views.FenetreMeteorite;
import fr.afpa.cda.group4.projet.avion.app.views.PannelMenu;
import fr.afpa.cda.group4.projet.avion.app.views.ScoreJPanel;

public class App extends JFrame {

    /**
     * 
     */
    private static final long      serialVersionUID = 2453749133359493683L;

    public static FenetreMeteorite fenetreMeteorite;

    // attribut Intance du Singleton
    private static App             fenetre          = null;

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
     * Contructeur par defaut
     */
    public App() {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1500, 800);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setAlwaysOnTop(false);

        this.setVisible(true);

    }

    /**
     * Constructeur
     * 
     * @param pan
     */
    public static void changeEcran(final JPanel pan) {
        // fenetre = new App();
        fenetre = App.getInstance();
        fenetre.repaint();
        fenetre.revalidate();
        fenetre.setContentPane(pan);
        fenetre.setResizable(false);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setVisible(true);
    }

    /**
     * Permet de passer à l'écran de jeu Solo
     * 
     * @param fen
     * @param vaisseau
     */
    public static void changeEcranMeteorite(final FenetreMeteorite fen, Vaisseau vaisseau) {
        fenetreMeteorite = new FenetreMeteorite(vaisseau);
        fenetreMeteorite.setResizable(false);
        fenetreMeteorite.setVisible(true);
        JFrame frame = App.getInstance();
        frame.setVisible(false);
        fenetreMeteorite = new FenetreMeteorite(vaisseau);
        fenetreMeteorite.setResizable(false);
        fenetreMeteorite.setVisible(true);
    }

    /**
     * Permet de repeindre la fenetre en cours
     * 
     * @param joueur
     */
    public static void netoyerEcran(final Joueur joueur) {
        fenetre = App.getInstance();
        fenetre.repaint();
        fenetre.revalidate();
        fenetre.setContentPane(new PannelMenu(joueur));
    }

    /**
     * Permet de passer à l'écran des best Scores
     * 
     * @param scoreJPanel
     * @param panelDesScores
     */
    public static void changeEcranScore(final ScoreJPanel scoreJPanel,final JPanel panelDesScores) {
        fenetre = App.getInstance();
        fenetre.repaint();
        fenetre.revalidate();
        scoreJPanel.setPanelDesScores(panelDesScores);
        fenetre.setContentPane(scoreJPanel);
        fenetre.setResizable(false);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setVisible(true);

    }

    /**
     * Connexion au serveur
     * 
     * @param partie
     * @param isHote
     */
    public static void connexionServeur(final Partie partie, boolean isHote) {
        ConnexionServeur connexionServeur = new ConnexionServeur(partie, isHote);
    }

    public static void main(final String[] args) throws IOException {

        final JoueurDao joueurDao = new JoueurDao();

        // Test liste des 10 tops scores
        List<JouerDo> listeJoueurScore = new ArrayList<JouerDo>();
        int cpt = 0;
        listeJoueurScore = joueurDao.findAllBestJoueursEtScores();
        for (JouerDo jouer : listeJoueurScore) {
            cpt++;
            System.out.println(" " + cpt + " -\t" + jouer);
        }
        

    //    MenuController.ecranAttenteJoueur(new AttenteJoueurJPanel());


        Musique.playSound("resources/audio.wav");
        MenuController.ecranAccueil(new AccueilJPanel());

    }

}
