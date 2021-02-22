/**
 * 
 */
package fr.afpa.cda.group4.projet.avion.app.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import fr.afpa.cda.group4.projet.avion.app.controllers.MenuController;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Joueur;

/**
 * @author Charline
 *
 */
public class Menu extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1196838812411226235L;

    private JButton           jouer;
    private JButton           multijoueur;
    private JButton           scores;
    private JButton           param;
    private JButton           quitter;
    private Joueur            joueur;

    /**
     * Constructeur pour defaut
     * 
     * @param image
     * @param width
     * @param height
     */
    public Menu(Joueur joueur) {
        super();
        this.joueur = joueur;
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(200, 500));

        GridBagConstraints grille = new GridBagConstraints();
        Font police2 = new Font("Agency FB", Font.BOLD, 20);

        //bouton Jouer
        JPanel boutonJouerPan = new JPanel(new GridBagLayout());
        jouer = new JButton("Jouer");
        jouer.setFont(police2);
        jouer.setBackground(Color.BLACK);
        jouer.setForeground(Color.WHITE);
        jouer.setPreferredSize(new Dimension(200, 50));
        jouer.addActionListener(this);
        boutonJouerPan.add(jouer);
        grille.gridx = 1;
        grille.gridy = 0;
        this.add(boutonJouerPan, grille);

        //bouton multi_joueurs
        JPanel boutonMultiJoueursPan = new JPanel(new GridBagLayout());
        multijoueur = new JButton("Multijoueur");
        multijoueur.setFont(police2);
        multijoueur.setBackground(Color.BLACK);
        multijoueur.setForeground(Color.WHITE);
        multijoueur.setPreferredSize(new Dimension(200, 50));
        multijoueur.addActionListener(this);
        grille.gridx = 1;
        grille.gridy = 2;
        boutonMultiJoueursPan.add(multijoueur);
        this.add(boutonMultiJoueursPan, grille);

        //bouton Scores
        JPanel boutonScorePan = new JPanel(new GridBagLayout());
        scores = new JButton("Scores");
        scores.setFont(police2);
        scores.setBackground(Color.BLACK);
        scores.setForeground(Color.WHITE);
        scores.setPreferredSize(new Dimension(200, 50));
        scores.addActionListener(this);
        grille.gridx = 1;
        grille.gridy = 4;
        boutonScorePan.add(scores);
        this.add(boutonScorePan, grille);

        //bouton Paramètre
        JPanel boutonParamPan = new JPanel(new GridBagLayout());
        param = new JButton("Parametres");
        param.setFont(police2);
        param.setBackground(Color.BLACK);
        param.setForeground(Color.WHITE);
        param.setPreferredSize(new Dimension(200, 50));
        param.addActionListener(this);
        grille.gridx = 1;
        grille.gridy = 6;
        boutonParamPan.add(param);
        this.add(boutonParamPan, grille);

        //bouton Quitter
        JPanel boutonExitPan = new JPanel(new GridBagLayout());
        quitter = new JButton("Quitter");
        quitter.setFont(police2);
        quitter.setBackground(Color.BLACK);
        quitter.setForeground(Color.WHITE);
        quitter.setPreferredSize(new Dimension(200, 50));
        quitter.addActionListener(this);
        grille.gridx = 1;
        grille.gridy = 8;
        boutonExitPan.add(quitter);
        this.add(boutonExitPan, grille);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
 
        String code = ((JButton) e.getSource()).getText();
        if (code.equals("Jouer")) {
            System.out.println("Menu - Joueur = " + joueur.getNom());
            joueur.setMulti(false);
            MenuController.ecranChoixVaisseau(new ChoixVaisseauJPanel(joueur));
            e.getSource();
        }
        if (code.equals("Multijoueur")) {
            joueur.setMulti(true);
            MenuController.ecranChoixVaisseau(new ChoixVaisseauJPanel(joueur));
            //MenuController.ecranMultijoueurs(new MultijoueursJPanel(joueur));
            e.getSource();     	
        }
        if (code.equals("Scores")) {
            MenuController.ecranScore(new ScoreJPanel(joueur), new JPanel());
            e.getSource();
        }
        if (code.equals("Parametres")) {
            MenuController.ecranParametres(new ParametresJPanel(joueur));
            e.getSource();
        }
        if (code.equals("Quitter")) {
            MenuController.ecranAccueil(new AccueilJPanel());
            e.getSource();
        }

    }

    @Override
    public Insets getInsets() {
        Insets normal = super.getInsets();
        return new Insets(normal.top + 100, normal.left + 100, normal.bottom + 100, normal.right + 100);
    }

}
