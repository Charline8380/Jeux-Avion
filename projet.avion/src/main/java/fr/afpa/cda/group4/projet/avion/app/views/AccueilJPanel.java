package fr.afpa.cda.group4.projet.avion.app.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.afpa.cda.group4.projet.avion.app.controllers.MenuController;
import fr.afpa.cda.group4.projet.avion.app.properties.Constantes;

/**
 * 
 * @author 
 *
 */
public class AccueilJPanel extends JPanel {

    private static final long serialVersionUID = -5659046354361106412L;

    private ImageIcon         icoFond;
    private Image             imgFond;

    private JButton           inscription;
    private JButton           connexion;

    public AccueilJPanel() {
        super();
        this.setSize(1500, 800);

        //image de fond
        this.icoFond = new ImageIcon(Constantes.getDOSSIER_IMAGES() + "/fond.png");
        this.imgFond = this.icoFond.getImage();

        JPanel principal = new JPanel(new GridBagLayout());
        principal.setOpaque(false);
        GridBagConstraints grille = new GridBagConstraints();

        //titre panel
        JPanel titrerPan = new JPanel(new GridBagLayout());
        JLabel titreImg = new JLabel();
        titreImg.setIcon(new ImageIcon("src/main/java/fr/afpa/cda/group4/projet/avion/app/autre/resources/image/titre.jpg"));
        titreImg.setBounds(new Rectangle(new Point(200, 300), titreImg.getPreferredSize()));
        grille.gridx = 1;
        grille.gridy = 0;
        titrerPan.add(titreImg);
        this.add(titrerPan, grille);

        Accueil accueil = new Accueil();
        grille.gridx = 1;
        grille.gridy = 3;
        principal.add(accueil, grille);

        grille.gridx = 1;
        grille.gridy = 3;
        this.add(principal, grille);

        this.setVisible(true);
    }

    /**
     * Classe Accueil
     * 
     * @author Charline
     *
     */
    public class Accueil extends JPanel implements ActionListener {

        /**
         * 
         */
        private static final long serialVersionUID = -4870396715764903476L;

        /**
         * Constructeur par defaut
         */
        public Accueil() {
            super(new GridBagLayout());
            this.setOpaque(false);

            GridBagConstraints grille = new GridBagConstraints();

            //Police des Boutons
            Font police2 = new Font("Agency FB", Font.BOLD, 20);

            //JPanel Champ des Boutton Connexion et Inscription
            JPanel champBoutonPan = new JPanel();
            champBoutonPan.setPreferredSize(new Dimension(1500, 500));
            champBoutonPan.setOpaque(false);

            //Bouton Inscription
            inscription = new JButton("Inscription");
            inscription.setFont(police2);
            inscription.setBackground(Color.BLACK);
            inscription.setForeground(Color.WHITE);
            inscription.setPreferredSize(new Dimension(200, 50));
            inscription.addActionListener(this);
            //Contruction de la ligne
            JPanel panLigne = new JPanel(new GridBagLayout());
            panLigne.setOpaque(false);
            panLigne.setPreferredSize(new Dimension(500, 60));
            panLigne.add(inscription);
            champBoutonPan.add(panLigne);

            //Bouton Connexion
            connexion = new JButton("Connexion");
            connexion.setFont(police2);
            connexion.setBackground(Color.BLACK);
            connexion.setForeground(Color.WHITE);
            connexion.setPreferredSize(new Dimension(200, 50));
            connexion.addActionListener(this);
            //Contruction de la ligne
            JPanel panLigne2 = new JPanel(new GridBagLayout());
            panLigne2.setOpaque(false);
            panLigne2.setPreferredSize(new Dimension(500, 60));
            panLigne2.add(connexion);
            champBoutonPan.add(panLigne2);

            grille.gridx = 1;
            grille.gridy = 4;
            this.add(champBoutonPan, grille);

        }

        @Override
        public void actionPerformed(ActionEvent e) {

            String code = ((JButton) e.getSource()).getText();
            System.out.println(code);
            if (code.equals("Connexion")) {
                e.getSource();

                MenuController.ecranConnexion(new ConnexionJPanel());
            }
            if (code.equals("Inscription")) {
                e.getSource();
                MenuController.ecranInscription(new InscriptionJPanel());
            }
        }

        @Override
        public Insets getInsets() {
            Insets normal = super.getInsets();
            return new Insets(normal.top + 100, normal.left + 100, normal.bottom + 100, normal.right + 100);
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        final Graphics g2 = (Graphics2D) g;
        g2.drawImage(this.imgFond, 0, 0, null);
    }

    @Override
    public Insets getInsets() {
        Insets normal = super.getInsets();
        return new Insets(normal.top + 200, normal.left + 200, normal.bottom + 200, normal.right + 200);
    }
}
