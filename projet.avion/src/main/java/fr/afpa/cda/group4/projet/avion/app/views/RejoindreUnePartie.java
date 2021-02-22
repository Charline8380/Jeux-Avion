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
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import fr.afpa.cda.group4.projet.avion.app.controllers.MenuController;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Joueur;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Partie;

/**
 * 
 * @author 
 *
 */
public class RejoindreUnePartie extends JPanel implements ActionListener {

	private static final long serialVersionUID = 2190815193086081705L;
    private JButton           retour;
    private Partie            partie;

    /**
     * Constructeur pour defaut
     * 
     * @param image
     * @param width
     * @param height
     */
    public RejoindreUnePartie(Partie partie, List<Partie> partiesDispos) {
        super();
        this.partie = partie;
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(600, 500));

        GridBagConstraints grille = new GridBagConstraints();
        Font police2 = new Font("Agency FB", Font.BOLD, 20);
		JPanel principal = new JPanel(new GridBagLayout());
		principal.setOpaque(false);


		RecherchePartie recherchePartie = new RecherchePartie(partie, partiesDispos);
		grille.gridx = 1;
		grille.gridy = 3;
		principal.add(recherchePartie, grille);
		this.add(principal, grille);

        //bouton Retour au menu
        JPanel boutonRetour = new JPanel(new GridBagLayout());
        retour = new JButton("Retour au menu");
        retour.setFont(police2);
        retour.setBackground(Color.BLACK);
        retour.setForeground(Color.WHITE);
        retour.setPreferredSize(new Dimension(200, 30));
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
