package fr.afpa.cda.group4.projet.avion.app.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import fr.afpa.cda.group4.projet.avion.app.controllers.ConnexionController;
import fr.afpa.cda.group4.projet.avion.app.controllers.MenuController;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Partie;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Vaisseau1;
import fr.afpa.cda.group4.projet.avion.app.serveurAccess.ConnexionServeur;
import fr.afpa.cda.group4.projet.avion.app.views.ConnexionJPanel.Connexion;

/**
 * 
 * @author 
 *
 */
public class RecherchePartie extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton boutonPartie;
	private Partie partie;
	private List<Partie> partiesDispos = new ArrayList<Partie>();

	/**
	 * 
	 * @param partie
	 * @param partiesDispos
	 */
	public RecherchePartie(Partie partie, List<Partie> partiesDispos) {
		super(new GridBagLayout());
		this.partiesDispos = partiesDispos;
		this.partie = partie;
		this.setSize(1500, 800);
		this.setOpaque(false);
		Font police2 = new Font("Agency FB", Font.BOLD, 20);
		GridBagConstraints grille = new GridBagConstraints();

		for (int i = 0; i < partiesDispos.size(); i++) {

			boutonPartie = new JButton("+ " + partiesDispos.get(i).getNom() + " \t \t"
					+ partiesDispos.get(i).getNbPlaces() + " Places Dispo");
			boutonPartie.setToolTipText("Partie n°" + i);
			boutonPartie.setFont(police2);
			boutonPartie.setBackground(Color.BLACK);
			boutonPartie.setForeground(Color.WHITE);
			boutonPartie.setPreferredSize(new Dimension(500, 50));
			boutonPartie.addActionListener(this);
			grille.gridx = 0;
			grille.gridy = i;
			this.add(boutonPartie, grille);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String code = ((JButton) e.getSource()).getToolTipText();
		for (int i = 0; i < partiesDispos.size(); i++) {
			if (code.equals("Partie n°" + i)) {
				ConnexionServeur.getInstance().envoiChoixPartie(partiesDispos.get(i).getNom());;
			}
		}
		

	}
}
