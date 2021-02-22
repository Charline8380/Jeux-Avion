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

import fr.afpa.cda.group4.projet.avion.app.audio.Musique;
import fr.afpa.cda.group4.projet.avion.app.controllers.MenuController;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Joueur;

/**
 * 
 * @author Rachel
 *
 */
public class Parametres extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;

    public static boolean     isBruitageOn     = true;
    private JButton           musique;
    private JButton           bruitage;
    private JButton           retour;
    private Joueur            joueur;

    /**
     * 
     * @param joueur
     */
    public Parametres(Joueur joueur) {
        super();
        this.joueur = joueur;
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(200, 500));

        GridBagConstraints grille = new GridBagConstraints();
        Font police2 = new Font("Agency FB", Font.BOLD, 20);

        //bouton Musique
        JPanel boutonMusique = new JPanel(new GridBagLayout());
        musique = new JButton("Désactiver la musique");
        musique.setFont(police2);
        musique.setBackground(Color.BLACK);
        musique.setForeground(Color.WHITE);
        musique.setPreferredSize(new Dimension(200, 50));
        musique.addActionListener(this);
        boutonMusique.add(musique);
        grille.gridx = 1;
        grille.gridy = 0;
        this.add(boutonMusique, grille);

        //bouton bruitage
        JPanel boutonBruitage = new JPanel(new GridBagLayout());
        bruitage = new JButton("Désactiver les bruitages");
        bruitage.setFont(police2);
        bruitage.setBackground(Color.BLACK);
        bruitage.setForeground(Color.WHITE);
        bruitage.setPreferredSize(new Dimension(200, 50));
        bruitage.addActionListener(this);
        grille.gridx = 1;
        grille.gridy = 2;
        boutonBruitage.add(bruitage);
        this.add(boutonBruitage, grille);

        //bouton Retour au menu
        JPanel boutonRetour = new JPanel(new GridBagLayout());
        retour = new JButton("Retour au menu");
        retour.setFont(police2);
        retour.setBackground(Color.BLACK);
        retour.setForeground(Color.WHITE);
        retour.setPreferredSize(new Dimension(200, 50));
        retour.addActionListener(this);
        grille.gridx = 1;
        grille.gridy = 4;
        boutonRetour.add(retour);
        this.add(boutonRetour, grille);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String code = ((JButton) e.getSource()).getText();
        if (code.equals("Désactiver la musique")) {
            musique.setText("Activer la musique");
            Musique.stop();
        }
        if (code.equals("Désactiver les bruitages")) {
            bruitage.setText("Activer les bruitages");
            isBruitageOn = false;
        }
        if (code.equals("Retour au menu")) {
            MenuController.ecranMenu(new PannelMenu(joueur));
            e.getSource();
        }

        if (code.equals("Activer la musique")) {
            musique.setText("Désactiver la musique");
            Musique.play();
        }
        if (code.equals("Activer les bruitages")) {
            bruitage.setText("Désactiver les bruitages");
            isBruitageOn = true;
        }
        if (code.equals("Retour au menu")) {
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
