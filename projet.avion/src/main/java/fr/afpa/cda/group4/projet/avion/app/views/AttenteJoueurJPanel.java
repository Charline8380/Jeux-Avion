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
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import fr.afpa.cda.group4.projet.avion.app.controllers.AttenteJoueurController;
import fr.afpa.cda.group4.projet.avion.app.controllers.MenuController;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Joueur;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Partie;
import fr.afpa.cda.group4.projet.avion.app.properties.Constantes;
import fr.afpa.cda.group4.projet.avion.app.serveurAccess.ConnexionServeur;

/**
 * @author Charline
 *
 */
public class AttenteJoueurJPanel extends JPanel implements ActionListener {

    /**
     * 
     */
    private static final long serialVersionUID  = 5895278264669974167L;
    private ImageIcon         icoFond;
    private Image             imgFond;
    private JLabel            titre;
    private JButton           lancerPartie      = new JButton("Lancer la Partie");
    private Joueur            joueur;
    private Partie            partie;
    private Integer           joueurMaxEnAttent;

    /**
     * 
     * @throws IOException
     */
    public AttenteJoueurJPanel() throws IOException {
        super();
        //image de fond
        this.icoFond = new ImageIcon(Constantes.getDOSSIER_IMAGES() + "/fond.png");
        this.imgFond = this.icoFond.getImage();

        JPanel principal = new JPanel(new GridBagLayout());
        principal.setOpaque(false);
        GridBagConstraints grille = new GridBagConstraints();

        //titre panel
        titre = new JLabel("En attente des autres joueurs ...", SwingConstants.CENTER);
        titre.setForeground(Color.WHITE);
        titre.setBackground(Color.BLACK);
        titre.setBounds(new Rectangle(new Point(200, 300), titre.getPreferredSize()));
        Font police = new Font("MOD20", Font.BOLD, 50);
        titre.setFont(police);
        grille.gridx = 1;
        grille.gridy = 0;
        principal.add(titre, grille);

        final JPanel joueurEnattentePan = new JPanel(new GridBagLayout());
        joueurEnattentePan.setOpaque(false);

        final AttenteJPanel attenteJPanel = new AttenteJPanel();
        attenteJPanel.setOpaque(false);
        joueurEnattentePan.add(attenteJPanel);
        grille.gridx = 1;
        grille.gridy = 2;
        principal.add(joueurEnattentePan, grille);

        Font police2 = new Font("Agency FB", Font.PLAIN, 20);
        //JPanel des boutons
        JPanel boutonPan = new JPanel(new GridBagLayout());
        boutonPan.setOpaque(false);
        boutonPan.setFont(police2);
        boutonPan.setBackground(Color.YELLOW);//BLACK
        boutonPan.setPreferredSize(new Dimension(400, 50));
        lancerPartie.setFont(police2);
        lancerPartie.setPreferredSize(new Dimension(200, 50));
        lancerPartie.setBackground(Color.BLACK);
        lancerPartie.setForeground(Color.WHITE);
        lancerPartie.addActionListener(this);
        boutonPan.add(lancerPartie);
        grille.gridx = 1;
        grille.gridy = 4;
        principal.add(boutonPan, grille);

        grille.gridx = 1;
        grille.gridy = 3;
        this.add(principal, grille);

        this.setVisible(true);
    }

    /**
     * Constructeur avec field
     * 
     * @throws IOException
     */
    public AttenteJoueurJPanel(final Partie partie, final Joueur joueur) throws IOException {
        super();
        System.out.println("Entre dans JPAnel AttenteJoueurJPanel");
        this.joueur = joueur;
        this.partie=partie;
        System.out.println(partie.getNbPlaces());
        this.joueurMaxEnAttent=4;//partie.getNbPlaces();
        //image de fond
        this.icoFond = new ImageIcon(Constantes.getDOSSIER_IMAGES() + "/fond.png");
        this.imgFond = this.icoFond.getImage();

        JPanel principal = new JPanel(new GridBagLayout());
        principal.setOpaque(false);
        GridBagConstraints grille = new GridBagConstraints();

        //titre panel
        titre = new JLabel("En Attente des autres joueurs ...", SwingConstants.CENTER);
        titre.setForeground(Color.WHITE);
        titre.setBackground(Color.BLACK);
        titre.setBounds(new Rectangle(new Point(200, 300), titre.getPreferredSize()));
        Font police = new Font("MOD20", Font.BOLD, 50);
        titre.setFont(police);
        grille.gridx = 1;
        grille.gridy = 0;
        principal.add(titre, grille);

        final JPanel joueurEnattentePan = new JPanel(new GridBagLayout());
        joueurEnattentePan.setOpaque(false);

        final AttenteJPanel attenteJPanel = new AttenteJPanel();
        attenteJPanel.setOpaque(false);
        joueurEnattentePan.add(attenteJPanel);
        grille.gridx = 1;
        grille.gridy = 2;
        principal.add(joueurEnattentePan, grille);

        Font police2 = new Font("Agency FB", Font.PLAIN, 20);
        //JPanel des boutons
        JPanel boutonPan = new JPanel(new GridBagLayout());
        boutonPan.setOpaque(false);
        boutonPan.setFont(police2);
        boutonPan.setBackground(Color.YELLOW);//BLACK
        boutonPan.setPreferredSize(new Dimension(400, 50));
        lancerPartie.setFont(police2);
        lancerPartie.setPreferredSize(new Dimension(200, 50));
        lancerPartie.setBackground(Color.BLACK);
        lancerPartie.setForeground(Color.WHITE);
        lancerPartie.addActionListener(this);
        boutonPan.add(lancerPartie);
        grille.gridx = 1;
        grille.gridy = 4;
        principal.add(boutonPan, grille);

        grille.gridx = 1;
        grille.gridy = 3;
        this.add(principal, grille);

        this.setVisible(true);
    }

    public class AttenteJPanel extends JPanel {

        /**
         * 
         */
        private static final long serialVersionUID = 2998316239929703021L;

        /**
         * 
         * @throws IOException
         */
        public AttenteJPanel() throws IOException {
            super();
            this.setOpaque(true);

            GridBagConstraints grille = new GridBagConstraints();

            //Police de texte label
            Font police2 = new Font("Ariel", Font.PLAIN, 20);

            //Champ de la liste des Joueurs en attente
            JPanel champJoueurPan = new JPanel(new GridBagLayout());
            champJoueurPan.setPreferredSize(new Dimension(1000, 375));
            champJoueurPan.setOpaque(false);
            champJoueurPan.setBackground(Color.BLACK);
            champJoueurPan.setForeground(Color.WHITE);

            int nombreAttente = partie.getJoueurs().size();
            //int nombreAttente = 3;
            if (joueurMaxEnAttent > nombreAttente) {
                //joueur en attente
                for (int i = 0; i < nombreAttente; i++) {
                    AttenteJoueurController.miseEnAttenteJoueur(champJoueurPan, police2, i, partie.getJoueurs().get(i).getNom());
                }
                //place encore disponible
                for (int i = nombreAttente; i < joueurMaxEnAttent; i++) {
                    AttenteJoueurController.miseEnDispoJoueur(champJoueurPan, police2, i);
                }
            } else if (joueurMaxEnAttent == nombreAttente) {
                //tous les joueurs en attente
                for (int i = 0; i < nombreAttente; i++) {
                    AttenteJoueurController.miseEnAttenteJoueur(champJoueurPan, police2, i, partie.getJoueurs().get(i).getNom());
                }
            }

            grille.gridx = 1;
            grille.gridy = 1;
            this.add(champJoueurPan, grille);
        }

        @Override
        public Insets getInsets() {
            Insets normal = super.getInsets();
            return new Insets(normal.top + 50, normal.left + 50, normal.bottom + 50, normal.right + 50);
        }

    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        final Graphics g2 = (Graphics2D) g;
        g2.drawImage(this.imgFond, 0, 0, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ConnexionServeur.getInstance().envoiDemarrerPartie(partie, joueur);

    }

    @Override
    public Insets getInsets() {
        Insets normal = super.getInsets();
        return new Insets(normal.top + 100, normal.left + 100, normal.bottom + 100, normal.right + 100);
    }

}
