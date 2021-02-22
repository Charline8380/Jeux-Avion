package fr.afpa.cda.group4.projet.avion.app.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import fr.afpa.cda.group4.projet.avion.app.controllers.MenuController;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Joueur;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Vaisseau1;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Vaisseau2;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Vaisseau3;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Vaisseau4;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Vaisseau5;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Vaisseau6;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Vaisseau7;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Vaisseau8;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Vaisseau9;
import fr.afpa.cda.group4.projet.avion.app.properties.Constantes;
import fr.afpa.cda.group4.projet.avion.app.serveurAccess.ConnexionServeur;

/**
 * 
 * @author Rachel
 *
 */
public class ChoixVaisseau extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton vaisseau1;
	private JButton vaisseau2;
	private JButton vaisseau3;
	private JButton vaisseau4;
	private JButton vaisseau5;
	private JButton vaisseau6;
	private JButton vaisseau7;
	private JButton vaisseau8;
	private JButton vaisseau9;
	private JButton retour;
	private Joueur joueur;

	/**
	 * Constructeur pour defaut
	 * 
	 * @param image
	 * @param width
	 * @param height
	 */
	public ChoixVaisseau(Joueur joueur) {
		super(new GridBagLayout());
		this.setSize(1500, 800);
		this.setOpaque(false);
		this.joueur = joueur;

		GridBagConstraints grille = new GridBagConstraints();
		Font police2 = new Font("Agency FB", Font.BOLD, 20);

		// bouton Vaisseau 1
		vaisseau1 = new JButton(new ImageIcon(
				"src/main/java/fr/afpa/cda/group4/projet/avion/app/autre/resources/image/Fusee/Vaisseau1.png"));
		vaisseau1.setToolTipText("Vaisseau1");
		vaisseau1.setBackground(Color.BLACK);
		vaisseau1.setPreferredSize(new Dimension(100, 100));
		vaisseau1.addActionListener(this);
		grille.gridx = 0;
		grille.gridy = 0;
		this.add(vaisseau1, grille);

		// bouton Vaisseau 2
		vaisseau2 = new JButton(new ImageIcon(
				"src/main/java/fr/afpa/cda/group4/projet/avion/app/autre/resources/image/Fusee/Vaisseau2.png"));
		vaisseau2.setToolTipText("Vaisseau2");
		vaisseau2.setBackground(Color.BLACK);
		vaisseau2.setPreferredSize(new Dimension(100, 100));
		vaisseau2.addActionListener(this);
		grille.gridx = 1;
		grille.gridy = 0;
		this.add(vaisseau2, grille);

		// bouton Vaisseau 3
		vaisseau3 = new JButton(new ImageIcon(Constantes.getDOSSIER_IMAGES() + "Fusee/Vaisseau3.png"));
		vaisseau3.setToolTipText("Vaisseau3");
		vaisseau3.setBackground(Color.BLACK);
		vaisseau3.setPreferredSize(new Dimension(100, 100));
		vaisseau3.addActionListener(this);
		grille.gridx = 2;
		grille.gridy = 0;
		this.add(vaisseau3, grille);

		// bouton Vaisseau 4
		vaisseau4 = new JButton(new ImageIcon(
				"src/main/java/fr/afpa/cda/group4/projet/avion/app/autre/resources/image/Fusee/Vaisseau4.png"));
		vaisseau4.setToolTipText("Vaisseau4");
		vaisseau4.setBackground(Color.BLACK);
		vaisseau4.setPreferredSize(new Dimension(100, 100));
		vaisseau4.addActionListener(this);
		grille.gridx = 0;
		grille.gridy = 2;
		this.add(vaisseau4, grille);

		// bouton Vaisseau 5
		vaisseau5 = new JButton(new ImageIcon(
				"src/main/java/fr/afpa/cda/group4/projet/avion/app/autre/resources/image/Fusee/Vaisseau5.png"));
		vaisseau5.setToolTipText("Vaisseau5");
		vaisseau5.setBackground(Color.BLACK);
		vaisseau5.setPreferredSize(new Dimension(100, 100));
		vaisseau5.addActionListener(this);
		grille.gridx = 1;
		grille.gridy = 2;
		this.add(vaisseau5, grille);
		this.setVisible(true);

		// bouton Vaisseau 6
		vaisseau6 = new JButton(new ImageIcon(
				"src/main/java/fr/afpa/cda/group4/projet/avion/app/autre/resources/image/Fusee/Vaisseau6.png"));
		vaisseau6.setToolTipText("Vaisseau6");
		vaisseau6.setBackground(Color.BLACK);
		vaisseau6.setPreferredSize(new Dimension(100, 100));
		vaisseau6.addActionListener(this);
		grille.gridx = 2;
		grille.gridy = 2;
		this.add(vaisseau6, grille);
		this.setVisible(true);

		// bouton Vaisseau 7
		vaisseau7 = new JButton(new ImageIcon(
				"src/main/java/fr/afpa/cda/group4/projet/avion/app/autre/resources/image/Fusee/Vaisseau7.png"));
		vaisseau7.setToolTipText("Vaisseau7");
		vaisseau7.setBackground(Color.BLACK);
		vaisseau7.setPreferredSize(new Dimension(100, 100));
		vaisseau7.addActionListener(this);
		grille.gridx = 0;
		grille.gridy = 4;
		this.add(vaisseau7, grille);
		this.setVisible(true);

		// bouton Vaisseau 8
		vaisseau8 = new JButton(new ImageIcon(
				"src/main/java/fr/afpa/cda/group4/projet/avion/app/autre/resources/image/Fusee/Vaisseau8.png"));
		vaisseau8.setToolTipText("Vaisseau8");
		vaisseau8.setBackground(Color.BLACK);
		vaisseau8.setPreferredSize(new Dimension(100, 100));
		vaisseau8.addActionListener(this);
		grille.gridx = 1;
		grille.gridy = 4;
		this.add(vaisseau8, grille);
		this.setVisible(true);

		// bouton Vaisseau 9
		vaisseau9 = new JButton(new ImageIcon(
				"src/main/java/fr/afpa/cda/group4/projet/avion/app/autre/resources/image/Fusee/Vaisseau9.png"));
		vaisseau9.setToolTipText("Vaisseau9");
		vaisseau9.setBackground(Color.BLACK);
		vaisseau9.setPreferredSize(new Dimension(100, 100));
		vaisseau9.addActionListener(this);
		grille.gridx = 2;
		grille.gridy = 4;
		this.add(vaisseau9, grille);
		this.setVisible(true);

		// bouton Retour au menu
		JPanel boutonRetour = new JPanel(new GridBagLayout());
		retour = new JButton("Retour au menu");
		retour.setToolTipText("Retour au menu");
		retour.setFont(police2);
		retour.setBackground(Color.BLACK);
		retour.setForeground(Color.WHITE);
		retour.setPreferredSize(new Dimension(200, 50));
		retour.addActionListener(this);
		grille.gridx = 1;
		grille.gridy = 8;
		boutonRetour.add(retour);
		this.add(boutonRetour, grille);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String code = ((JButton) e.getSource()).getToolTipText();
		System.out.println("ChoixVaisseau - Joueur = " + joueur.getNom());

		if (code.equals("Vaisseau1")) {
			joueur.setVaisseau(new Vaisseau1());
			if (joueur.isMulti()) {
	            MenuController.ecranMultijoueurs(new MultijoueursJPanel(joueur));

			} else {
				MenuController.ecranJeu(new FenetreMeteorite(joueur), joueur);
			}

		} else if (code.equals("Vaisseau2")) {
			joueur.setVaisseau(new Vaisseau2());
			if (joueur.isMulti()) {
	            MenuController.ecranMultijoueurs(new MultijoueursJPanel(joueur));
			} else {
				MenuController.ecranJeu(new FenetreMeteorite(joueur), joueur);
			}
		} else if (code.equals("Vaisseau3")) {
			joueur.setVaisseau(new Vaisseau3());
			if (joueur.isMulti()) {
	            MenuController.ecranMultijoueurs(new MultijoueursJPanel(joueur));
			} else {
				MenuController.ecranJeu(new FenetreMeteorite(joueur), joueur);
			}
		} else if (code.equals("Vaisseau4")) {
			joueur.setVaisseau(new Vaisseau4());
			if (joueur.isMulti()) {
	            MenuController.ecranMultijoueurs(new MultijoueursJPanel(joueur));
			} else {
				MenuController.ecranJeu(new FenetreMeteorite(joueur), joueur);
			}
		} else if (code.equals("Vaisseau5")) {
			joueur.setVaisseau(new Vaisseau5());
			if (joueur.isMulti()) {
	            MenuController.ecranMultijoueurs(new MultijoueursJPanel(joueur));
			} else {
				MenuController.ecranJeu(new FenetreMeteorite(joueur), joueur);
			}
		} else if (code.equals("Vaisseau6")) {
			joueur.setVaisseau(new Vaisseau6());
			if (joueur.isMulti()) {
	            MenuController.ecranMultijoueurs(new MultijoueursJPanel(joueur));
			} else {
				MenuController.ecranJeu(new FenetreMeteorite(joueur), joueur);
			}
		} else if (code.equals("Vaisseau7")) {
			joueur.setVaisseau(new Vaisseau7());
			if (joueur.isMulti()) {
	            MenuController.ecranMultijoueurs(new MultijoueursJPanel(joueur));
			} else {
				MenuController.ecranJeu(new FenetreMeteorite(joueur), joueur);
			}
		} else if (code.equals("Vaisseau8")) {
			joueur.setVaisseau(new Vaisseau8());
			if (joueur.isMulti()) {
	            MenuController.ecranMultijoueurs(new MultijoueursJPanel(joueur));
			} else {
				MenuController.ecranJeu(new FenetreMeteorite(joueur), joueur);
			}
		} else if (code.equals("Vaisseau9")) {
			joueur.setVaisseau(new Vaisseau9());
			if (joueur.isMulti()) {
	            MenuController.ecranMultijoueurs(new MultijoueursJPanel(joueur));
			} else {
				MenuController.ecranJeu(new FenetreMeteorite(joueur), joueur);
			}
		} else if (code.equals("Retour au menu")) {
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
