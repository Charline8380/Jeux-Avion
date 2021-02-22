/**
 * 
 */
package fr.afpa.cda.group4.projet.avion.app.controllers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.afpa.cda.group4.projet.avion.app.properties.Constantes;


/**
 * @author Charline
 *
 */
public class AttenteJoueurController {

    /**
     * Joueur Pret ==> Astronaute main levée
     * 
     * @param police2
     * @param num
     * @param nom 
     * @throws IOException
     */
    public static void miseEnAttenteJoueur(final JPanel champJoueurPan, final Font police2, final Integer num, String nom) throws IOException {

        final JPanel panLigne = new JPanel(new GridBagLayout());
        panLigne.setOpaque(false);
        panLigne.setPreferredSize(new Dimension(200, 350));
        GridBagConstraints grille = new GridBagConstraints();
        //image astronaute2
        JLabel labImg = new JLabel();
        labImg.setOpaque(false);
        labImg.setIcon(new ImageIcon(Constantes.getDOSSIER_IMAGES() + "astronaute2.png"));
        labImg.setBounds(new Rectangle(new Point(200, 300), labImg.getPreferredSize()));
        grille.gridx = 1;
        grille.gridy = 1;
        panLigne.add(labImg);
        champJoueurPan.add(panLigne);
        //legende
        //final JLabel legende = new JLabel(" Joueur " + (num + 1));
        final JLabel legende = new JLabel(" "+nom);
        legende.setFont(police2);
        legende.setPreferredSize(new Dimension(200, 40));
        legende.setBackground(Color.BLACK);
        legende.setForeground(Color.WHITE);
        grille.gridx = 1;
        grille.gridy = 3;
        panLigne.add(legende);
        champJoueurPan.add(panLigne);

    }

    /**
     * 
     * @param champJoueurPan
     * @param police2
     * @param num
     */
    public static void miseEnDispoJoueur(final JPanel champJoueurPan, final Font police2, final Integer num) {

        final JPanel panLigne = new JPanel(new GridBagLayout());
        panLigne.setOpaque(false);
        panLigne.setPreferredSize(new Dimension(200, 350));
        GridBagConstraints grille = new GridBagConstraints();
        //image astronaute2
        JLabel labImg = new JLabel();
        labImg.setOpaque(false);
        labImg.setIcon(new ImageIcon(Constantes.getDOSSIER_IMAGES() + "astronaute1.png"));
        labImg.setBounds(new Rectangle(new Point(200, 300), labImg.getPreferredSize()));
        grille.gridx = 1;
        grille.gridy = 1;
        panLigne.add(labImg);
        champJoueurPan.add(panLigne);
        //legende
        final JLabel legende = new JLabel(" Joueur " + (num + 1));
        legende.setFont(police2);
        legende.setPreferredSize(new Dimension(200, 40));
        legende.setBackground(Color.BLACK);
        legende.setForeground(Color.WHITE);
        grille.gridx = 1;
        grille.gridy = 3;
        panLigne.add(legende);
        champJoueurPan.add(panLigne);

    }

}
