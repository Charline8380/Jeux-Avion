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
public class FinDeJeuSolo extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1196838812411226235L;

    private JButton           rejouer;
    private JButton           score;
    private JButton           retourMenu;
    private Joueur            joueur;

    /**
     * Constructeur pour defaut
     * 
     * @param image
     * @param width
     * @param height
     */
    public FinDeJeuSolo(Joueur joueur) {
        super();
        this.joueur = joueur;
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(200, 500));

        GridBagConstraints grille = new GridBagConstraints();
        Font police2 = new Font("Agency FB", Font.BOLD, 20);

        //bouton Rejouer
        JPanel boutonJouerPan = new JPanel(new GridBagLayout());
        rejouer = new JButton("Rejouer");
        rejouer.setFont(police2);
        rejouer.setBackground(Color.BLACK);
        rejouer.setForeground(Color.WHITE);
        rejouer.setPreferredSize(new Dimension(200, 50));
        rejouer.addActionListener(this);
        boutonJouerPan.add(rejouer);
        grille.gridx = 1;
        grille.gridy = 0;
        this.add(boutonJouerPan, grille);

        //bouton Scores
        JPanel boutonScorePan = new JPanel(new GridBagLayout());
        score = new JButton("Consulter les scores");
        score.setFont(police2);
        score.setBackground(Color.BLACK);
        score.setForeground(Color.WHITE);
        score.setPreferredSize(new Dimension(200, 50));
        score.addActionListener(this);
        grille.gridx = 1;
        grille.gridy = 4;
        boutonScorePan.add(score);
        this.add(boutonScorePan, grille);

        //bouton Paramètre
        JPanel boutonParamPan = new JPanel(new GridBagLayout());
        retourMenu = new JButton("Retourner au menu");
        retourMenu.setFont(police2);
        retourMenu.setBackground(Color.BLACK);
        retourMenu.setForeground(Color.WHITE);
        retourMenu.setPreferredSize(new Dimension(200, 50));
        retourMenu.addActionListener(this);
        grille.gridx = 1;
        grille.gridy = 6;
        boutonParamPan.add(retourMenu);
        this.add(boutonParamPan, grille);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        joueur.setScore(0);
        String code = ((JButton) e.getSource()).getText();
        if (code.equals("Rejouer")) {
            MenuController.ecranChoixVaisseau(new ChoixVaisseauJPanel(joueur));
            e.getSource();
        }
        if (code.equals("Consulter les scores")) {
            MenuController.ecranScore(new ScoreJPanel(joueur),new JPanel());
            e.getSource();
        }
        if (code.equals("Retourner au menu")) {
            MenuController.ecranMenu(new PannelMenu(joueur));
            e.getSource();
        }

    }

    @Override
    public Insets getInsets() {
        Insets normal = super.getInsets();
        return new Insets(normal.top + 100, normal.left + 100, normal.bottom + 100, normal.right + 100);
    }

}
