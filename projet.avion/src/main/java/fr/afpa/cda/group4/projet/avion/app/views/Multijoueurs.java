package fr.afpa.cda.group4.projet.avion.app.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import fr.afpa.cda.group4.projet.avion.app.controllers.ConnexionController;
import fr.afpa.cda.group4.projet.avion.app.controllers.MenuController;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Joueur;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Partie;
import fr.afpa.cda.group4.projet.avion.app.serveurAccess.ConnexionServeur;

/**
 * 
 * @author Rachel
 *
 */
public class Multijoueurs extends JPanel implements ActionListener {

	private static final long serialVersionUID = -807357702285844824L;
	public static boolean     isBruitageOn     = true;
    private JButton           creer;
    private JButton           rejoindre;
    private JButton           retour;
    private Partie            partie; 

    /**
     * 
     * @param joueur
     */
    public Multijoueurs(Joueur joueur) {
        super();
        this.partie = new Partie(joueur);
        System.out.println("Partie avec joueur : "+partie.getJoueurs().get(0));
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(200, 500));

        GridBagConstraints grille = new GridBagConstraints();
        Font police2 = new Font("Agency FB", Font.BOLD, 20);

        //bouton Créer une partie
        JPanel boutonCreer = new JPanel(new GridBagLayout());
        creer = new JButton("Créer une partie");
        creer.setFont(police2);
        creer.setBackground(Color.BLACK);
        creer.setForeground(Color.WHITE);
        creer.setPreferredSize(new Dimension(200, 50));
        creer.addActionListener(this);
        boutonCreer.add(creer);
        grille.gridx = 1;
        grille.gridy = 0;
        this.add(boutonCreer, grille);

        //bouton Rejoindre une partie
        JPanel boutonRejoindre = new JPanel(new GridBagLayout());
        rejoindre = new JButton("Rejoindre une partie");
        rejoindre.setFont(police2);
        rejoindre.setBackground(Color.BLACK);
        rejoindre.setForeground(Color.WHITE);
        rejoindre.setPreferredSize(new Dimension(200, 50));
        rejoindre.addActionListener(this);
        grille.gridx = 1;
        grille.gridy = 2;
        boutonRejoindre.add(rejoindre);
        this.add(boutonRejoindre, grille);

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
        if (code.equals("Créer une partie")) {
        	ConnexionServeur.getInstance(partie, true);
        	MenuController.ecranCreerUnePartie(new CreerUnePartieJPanel(partie));
            e.getSource();
        }
        if (code.equals("Rejoindre une partie")) {
        	ConnexionServeur.getInstance(partie, false);
        	MenuController.ecranRejoindreUnePartie(new RejoindreUnePartieJPanel(partie, new ArrayList<Partie>()));
        	e.getSource();
        }

        if (code.equals("Retour au menu")) {
            MenuController.ecranMenu(new PannelMenu(partie.getJoueurs().get(0)));
            e.getSource();
        }

    }

    @Override
    public Insets getInsets() {
        Insets normal = super.getInsets();
        return new Insets(normal.top + 100, normal.left + 100, normal.bottom + 100, normal.right + 100);
    }

}
