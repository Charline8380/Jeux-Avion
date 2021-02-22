package fr.afpa.cda.group4.projet.avion.app.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import fr.afpa.cda.group4.projet.avion.app.modelDto.Joueur;
import fr.afpa.cda.group4.projet.avion.app.properties.Constantes;

/**
 * 
 * @author 
 *
 */
public class PannelFinJeuSolo extends JPanel {

    private static final long serialVersionUID = -1979583281400564021L;

    private ImageIcon         icoFond;
    private Image             imgFond;
    private JLabel            titre;
    private Joueur            joueur;

    /**
     * Constructeur par defaut
     */
    public PannelFinJeuSolo(Joueur joueur) {
        super(new GridBagLayout());
        this.joueur = joueur;
        this.setSize(1500, 800);
        //image de fond
        this.icoFond = new ImageIcon(Constantes.getDOSSIER_IMAGES() + "/fond.png");
        this.imgFond = this.icoFond.getImage();
        this.getInsets();

        JPanel principal = new JPanel(new GridBagLayout());
        principal.setOpaque(false);
        GridBagConstraints grille = new GridBagConstraints();

        titre = new JLabel("GAME OVER", SwingConstants.CENTER);
        titre.setForeground(Color.WHITE);
        titre.setBackground(Color.BLACK);
        titre.setBounds(new Rectangle(new Point(200, 100), titre.getPreferredSize()));
        Font police = new Font("MOD20", Font.BOLD, 50);
        titre.setFont(police);
        grille.gridx = 1;
        grille.gridy = 0;
        principal.add(titre, grille);

        JLabel score = new JLabel("Bravo " + joueur.getNom() + ", votre score est de " + joueur.getScore(), SwingConstants.CENTER);
        score.setForeground(Color.WHITE);
        score.setBackground(Color.BLACK);
        score.setBounds(new Rectangle(new Point(200, 100), score.getPreferredSize()));
        Font policeScore = new Font("MOD20", Font.BOLD, 36);
        score.setFont(policeScore);
        grille.gridx = 1;
        grille.gridy = 1;
        principal.add(score, grille);

        JPanel menuPan = new JPanel(new GridBagLayout());
        menuPan.setOpaque(false);
        FinDeJeuSolo finDeJeu = new FinDeJeuSolo(joueur);
        menuPan.add(finDeJeu);
        grille.gridx = 1;
        grille.gridy = 3;
        principal.add(menuPan, grille);

        this.add(principal, grille);
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
        return new Insets(normal.top + 100, normal.left + 50, normal.bottom + 50, normal.right + 50);
    }
}
