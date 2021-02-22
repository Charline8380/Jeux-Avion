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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import fr.afpa.cda.group4.projet.avion.app.controllers.MenuController;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Joueur;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Partie;
import fr.afpa.cda.group4.projet.avion.app.properties.Constantes;

/**
 * 
 * @author 
 *
 */
public class RejoindreUnePartieJPanel extends JPanel implements ActionListener {
	
    private static final long serialVersionUID = 2190815193086081705L;
    private ImageIcon         icoFond;
    private Image             imgFond;
    private JLabel            titre;
    private Partie            partie;
    private List<Partie>      partiesDispos    = new ArrayList<Partie>();
    private Joueur            joueur;

    /**
     * 
     * @param partie
     * @param partiesDispos
     */
    public RejoindreUnePartieJPanel(Partie partie, List<Partie> partiesDispos) {
        super(new GridBagLayout());
        this.setSize(1500, 800);
        //image de fond
        this.icoFond = new ImageIcon(Constantes.getDOSSIER_IMAGES() + "/fond.png");
        this.imgFond = this.icoFond.getImage();
        this.getInsets();
        this.partie = partie;
        this.partiesDispos = partiesDispos;

        JPanel principal = new JPanel(new GridBagLayout());
        principal.setOpaque(false);
        GridBagConstraints grille = new GridBagConstraints();

        titre = new JLabel("Rejoindre une partie", SwingConstants.CENTER);
        titre.setForeground(Color.WHITE);
        titre.setBackground(Color.BLACK);
        titre.setBounds(new Rectangle(new Point(200, 300), titre.getPreferredSize()));
        Font police = new Font("MOD20", Font.BOLD, 50);
        titre.setFont(police);
        grille.gridx = 1;
        grille.gridy = 0;
        principal.add(titre, grille);

        JPanel rejoindrePan = new JPanel(new GridBagLayout());
        rejoindrePan.setOpaque(false);
        RejoindreUnePartie rejoindreUnePartie = new RejoindreUnePartie(partie, partiesDispos);
        rejoindrePan.add(rejoindreUnePartie);
        grille.gridx = 1;
        grille.gridy = 3;
        principal.add(rejoindrePan, grille);

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
        return new Insets(normal.top + 100, normal.left + 100, normal.bottom + 100, normal.right + 100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            MenuController.ecranAttenteJoueur(new AttenteJoueurJPanel(partie, joueur));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
