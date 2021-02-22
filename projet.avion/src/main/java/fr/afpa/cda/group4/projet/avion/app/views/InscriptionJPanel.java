/**
 * 
 */
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.afpa.cda.group4.projet.avion.app.controllers.MenuController;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Joueur;
import fr.afpa.cda.group4.projet.avion.app.properties.Constantes;
import fr.afpa.cda.group4.projet.avion.app.service.imp.JoueurService;

/**
 * @author Charline
 *
 */
public class InscriptionJPanel extends JPanel {

    private static final long serialVersionUID = -8900257746172583702L;

    private ImageIcon         icoFond;
    private Image             imgFond;
    private Joueur            joueur           = new Joueur();
    private JTextField        nomProfil        = new JTextField("Login");
    private JPasswordField    motDePasse       = new JPasswordField("Mot de passe");
    private JButton           valider          = new JButton("S'inscrire");
    private JButton           accueil          = new JButton("Retour à l'accueil");

    /**
     * Constructeur par defaut
     */
    public InscriptionJPanel() {
        super();
        this.setSize(1500, 800);
        //image de fond
        this.icoFond = new ImageIcon(Constantes.getDOSSIER_IMAGES() + "/fond.png");
        this.imgFond = this.icoFond.getImage();

        JPanel principal = new JPanel(new GridBagLayout());
        principal.setOpaque(false);
        GridBagConstraints grille = new GridBagConstraints();

        JPanel titrerPan = new JPanel(new GridBagLayout());
        JLabel titreImg = new JLabel();
        titreImg.setIcon(new ImageIcon("src/main/java/fr/afpa/cda/group4/projet/avion/app/autre/resources/image/titre.jpg"));
        titreImg.setBounds(new Rectangle(new Point(200, 300), titreImg.getPreferredSize()));
        grille.gridx = 1;
        grille.gridy = 0;
        titrerPan.add(titreImg);
        this.add(titrerPan, grille);

        Inscription inscription = new Inscription();

        grille.gridx = 1;
        grille.gridy = 3;
        principal.add(inscription, grille);

        this.add(principal, grille);
        this.setVisible(true);
    }

    /**
     * Classe Inscription
     * 
     * @author Charline
     *
     */
    public class Inscription extends JPanel implements ActionListener {

        /**
         * 
         */
        private static final long serialVersionUID = -4083927278422552012L;

        /**
         * Contructeur par defaut
         */
        public Inscription() {
            super(new GridBagLayout());
            this.setOpaque(false);

            GridBagConstraints grille = new GridBagConstraints();

            //creation du panel des champs de saisie
            JPanel champDeTextePan = new JPanel();
            champDeTextePan.setPreferredSize(new Dimension(1500, 200));
            champDeTextePan.setOpaque(false);

            //les Champs de saisie
            //----police des champs de saisie
            Font police2 = new Font("Agency FB", Font.BOLD, 30);
            Font police3 = new Font("Calibri", Font.PLAIN, 20);
            Font police4 = new Font("Agency FB", Font.BOLD, 20);
            
            //-----Champ Nom de profil
            nomProfil.setPreferredSize(new Dimension(200, 50));
            nomProfil.setFont(police3);
            nomProfil.setBackground(Color.LIGHT_GRAY);
            //contruction de la ligne
            JPanel panLab = new JPanel(new GridBagLayout());
            panLab.setOpaque(false);
            panLab.setPreferredSize(new Dimension(500, 60));
            panLab.add(nomProfil);
            //ajout dans le panel des champs de saisie
            champDeTextePan.add(panLab);

            //-----Champ Mot de passe
            motDePasse.setPreferredSize(new Dimension(200, 50));
            motDePasse.setFont(police3);
            motDePasse.setBackground(Color.LIGHT_GRAY);
            //contruction de la ligne
            JPanel panLab2 = new JPanel(new GridBagLayout());
            panLab2.setOpaque(false);
            panLab2.setPreferredSize(new Dimension(500, 60));
            panLab2.add(motDePasse);
            //ajout dans le panel des champs de saisie
            champDeTextePan.add(panLab2);

            //ajout dans le panel principal
            grille.gridx = 1;
            grille.gridy = 0;
            this.add(champDeTextePan, grille);

            //creation du panel des bouton de validation
            JPanel boutonPan = new JPanel();
            boutonPan.setOpaque(false);
            boutonPan.setFont(police2);
            boutonPan.setBackground(Color.BLACK);
            boutonPan.setPreferredSize(new Dimension(200, 150));

            valider.setFont(police4);
            valider.setPreferredSize(new Dimension(200, 50));
            valider.setBackground(Color.BLACK);
            valider.setForeground(Color.WHITE);
            valider.addActionListener(this);
            boutonPan.add(valider);
            accueil.setFont(police4);
            accueil.setPreferredSize(new Dimension(200, 50));
            accueil.setBackground(Color.BLACK);
            accueil.setForeground(Color.WHITE);
            accueil.addActionListener(this);
            boutonPan.add(accueil);
            
            grille.gridx = 1;
            grille.gridy = 4;
            this.add(boutonPan, grille);
        }

        @Override
        public void actionPerformed(final ActionEvent e) {
            e.getSource();
            JoueurService joueurService = new JoueurService();
            //profil visiteur
            joueur.setNom(nomProfil.getText());
            joueur.setPassword(motDePasse.getText());
            System.out.println("Pseudo : " + joueur.getNom() + "");
            System.out.println("Mot de passe : " + joueur.getPassword() + "");

            final boolean condition = joueur.getNom().equals("Login") && joueur.getPassword().equals("Mot de passe");
            final String code = ((JButton) e.getSource()).getText();
            if (code.equals("S'inscrire")&&!condition) {
        		final Joueur user;
        		if (joueur.isGoodFormat()){
        			user= joueurService.createJoueur(joueur.getNom(), joueur.getPassword());
                }else {
                	user =null;
                }
                if (user != null) {
                    //enregistrement dans la BDD connecté en tant que joueur ==> ecran Menu
                    MenuController.ecranMenu(new PannelMenu(user));
                    System.out.println("User connecté !");
                } else {
                    //joueur ou mot de passe déjà existant ou nom au mauvais format ==> ecran Acceuil
                    MenuController.ecranInscription(new InscriptionJPanel());
                    System.out.println("User non créé");
                }
            } 
            
            if (code.equals("Retour à l'accueil")) {
                MenuController.ecranAccueil(new AccueilJPanel()); 

            }

        }

        @Override
        public Insets getInsets() {
            Insets normal = super.getInsets();
            return new Insets(normal.top + 50, normal.left + 100, normal.bottom + 50, normal.right + 100);
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
