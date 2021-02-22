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
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.afpa.cda.group4.projet.avion.app.controllers.MenuController;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Joueur;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Partie;
import fr.afpa.cda.group4.projet.avion.app.serveurAccess.ConnexionServeur;

/**
 * @author Rachel
 *
 */
public class CreerUnePartie extends JPanel implements ActionListener {

    /**
     * 
     */
    private static final long serialVersionUID = 6308545065546042057L;
    private JTextField        nomPartie        = new JTextField("Nom de la partie");
    private JComboBox<String> combo            = new JComboBox<String>();
    private JComboBox<String> combo2           = new JComboBox<String>();
    private JButton           creer;
    private JButton           retour;
    private Partie            partie;


    /**
     * Constructeur pour defaut
     * 
     * @param image
     * @param width
     * @param height
     */
    public CreerUnePartie(Partie partie) {
        super();
        this.partie = partie;
        partie.setCreateur(partie.getJoueurs().get(0));
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(500, 500));
        System.out.println("Panel créer une partie - Joueur dans partie = "+partie.getNom());
        GridBagConstraints grille = new GridBagConstraints();
        Font police2 = new Font("Agency FB", Font.BOLD, 20);

        //creation du panel des champs de saisie
        JPanel champDeTextePan = new JPanel();
        champDeTextePan.setPreferredSize(new Dimension(300, 50));
        champDeTextePan.setOpaque(false);

        //Champ Nom de la partie
        nomPartie.setPreferredSize(new Dimension(300, 50));
        nomPartie.setFont(police2);
        nomPartie.setBackground(Color.LIGHT_GRAY);
        nomPartie.setText(partie.getJoueurs().get(0).getNom());
        JPanel panLab = new JPanel(new GridBagLayout());
        panLab.setOpaque(false);
        panLab.add(nomPartie);
        champDeTextePan.add(panLab);
        grille.gridx = 1;
        grille.gridy = 0;
        this.add(champDeTextePan, grille);

        // Choix nombre de joueurs
        combo.setPreferredSize(new Dimension(300, 50));
        combo.setFont(police2);
        combo.setBackground(Color.LIGHT_GRAY);
        combo.addItem("Nombre de joueurs");
        combo.addItem("2 joueurs");
        combo.addItem("3 joueurs");
        combo.addItem("4 joueurs");
        JPanel listeJoueurs = new JPanel(new GridBagLayout());
        listeJoueurs.add(combo);
        grille.gridx = 1;
        grille.gridy = 2;
        this.add(listeJoueurs, grille);

        // Choix délai
        combo2.setPreferredSize(new Dimension(300, 50));
        combo2.setFont(police2);
        combo2.setBackground(Color.LIGHT_GRAY);
        combo2.addItem("Délai d'attente");
        combo2.addItem("30 secondes");
        combo2.addItem("1 minute");
        combo2.addItem("1 minute et 30 secondes");
        combo2.addItem("2 minutes");
        JPanel listeDelai = new JPanel(new GridBagLayout());
        listeDelai.add(combo2);
        grille.gridx = 1;
        grille.gridy = 4;
        this.add(listeDelai, grille);

        //bouton Créer
        JPanel boutonCreer = new JPanel(new GridBagLayout());
        creer = new JButton("Créer");
        creer.setFont(police2);
        creer.setBackground(Color.WHITE);
        creer.setForeground(Color.BLACK);
        creer.setPreferredSize(new Dimension(200, 30));
        creer.addActionListener(this);
        grille.gridx = 1;
        grille.gridy = 6;
        boutonCreer.add(creer);
        this.add(boutonCreer, grille);

        //bouton Retour au menu
        JPanel boutonRetour = new JPanel(new GridBagLayout());
        retour = new JButton("Retour au menu");
        retour.setFont(police2);
        retour.setBackground(Color.BLACK);
        retour.setForeground(Color.WHITE);
        retour.setPreferredSize(new Dimension(200, 30));
        retour.addActionListener(this);
        grille.gridx = 2;
        grille.gridy = 6;
        boutonRetour.add(retour);
        this.add(boutonRetour, grille);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String code = ((JButton) e.getSource()).getText();

        if (code.equals("Créer")) {
        	partie.setNom(partie.getCreateur().getNom());
//        	System.out.println("Nom Partie :"+nomPartie.getSelectedText());
//            partie.setNom(nomPartie.getSelectedText());//partie.getCreateur().getNom());
//            System.out.println("Combo" +combo.getSelectedItem());
//            if(combo.getSelectedItem().equals("2 joueurs")) { 
//            	partie.setNbPlaces(2);
//            	ConnexionServeur.getInstance().getPartie().setNbPlaces(2);
//            }else if(combo.getSelectedItem().equals("3 joueurs")) { 
//            	partie.setNbPlaces(3);
//            	ConnexionServeur.getInstance().getPartie().setNbPlaces(3);
//            }else { 
//            	partie.setNbPlaces(4);
//            	ConnexionServeur.getInstance().getPartie().setNbPlaces(4);
//            }
        	partie.setNbPlaces(4);

            		
            ConnexionServeur.getInstance().envoiCreationPartie(partie);;
        }
        if (code.equals("Retour au menu")) {
            MenuController.ecranMenu(new PannelMenu(partie.getJoueurs().get(0)));
        }

    }

    @Override
    public Insets getInsets() {
        Insets normal = super.getInsets();
        return new Insets(normal.top + 100, normal.left + 100, normal.bottom + 100, normal.right + 100);
    }

}
