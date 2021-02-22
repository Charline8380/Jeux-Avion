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
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import fr.afpa.cda.group4.projet.avion.app.controllers.MenuController;
import fr.afpa.cda.group4.projet.avion.app.modelDto.JouerDto;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Joueur;
import fr.afpa.cda.group4.projet.avion.app.properties.Constantes;
import fr.afpa.cda.group4.projet.avion.app.service.imp.JoueurService;

/**
 * @author Charline
 *
 */
public class ScoreJPanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 491425240407317040L;
    private ImageIcon         icoFond;
    private Image             imgFond;
    private JLabel            titre;
    private JPanel            panelDesScores;
    private JButton           menu             = new JButton("Retour au menu");
    private Joueur            joueur;

    /**
     * 
     * @param joueur
     */
    public ScoreJPanel(final Joueur joueur) {
        super();
        this.joueur = joueur;
        this.setSize(1500, 800);
        //image de fond
        this.icoFond = new ImageIcon(Constantes.getDOSSIER_IMAGES() + "/fond.png");
        this.imgFond = this.icoFond.getImage();
        //this.getInsets();

        JPanel principal = new JPanel(new GridBagLayout());
        principal.setOpaque(false);
        GridBagConstraints grille = new GridBagConstraints();

        //titre panel
        titre = new JLabel("Scores", SwingConstants.CENTER);
        titre.setForeground(Color.WHITE);
        titre.setBackground(Color.BLACK);
        titre.setBounds(new Rectangle(new Point(200, 300), titre.getPreferredSize()));
        Font police = new Font("MOD20", Font.BOLD, 50);
        titre.setFont(police);
        grille.gridx = 1;
        grille.gridy = 0;
        principal.add(titre, grille);

        final JPanel consulterScorePan = new JPanel(new GridBagLayout());
        consulterScorePan.setOpaque(false);

        final ScoresJoueurJPanel scoresJoueurJPanel = new ScoresJoueurJPanel();
        scoresJoueurJPanel.setOpaque(false);
        consulterScorePan.add(scoresJoueurJPanel);
        grille.gridx = 1;
        grille.gridy = 2;
        principal.add(consulterScorePan, grille);

        Font police2 = new Font("Agency FB", Font.PLAIN, 20);
        //JPanel des boutons
        JPanel boutonPan = new JPanel(new GridBagLayout());
        boutonPan.setOpaque(false);
        boutonPan.setFont(police2);
        boutonPan.setBackground(Color.YELLOW);//BLACK
        boutonPan.setPreferredSize(new Dimension(400, 50));
        menu.setFont(police2);
        menu.setPreferredSize(new Dimension(200, 50));
        menu.setBackground(Color.BLACK);
        menu.setForeground(Color.WHITE);
        menu.addActionListener(this);
        boutonPan.add(menu);
        grille.gridx = 1;
        grille.gridy = 4;
        principal.add(boutonPan, grille);

        grille.gridx = 1;
        grille.gridy = 3;
        this.add(principal, grille);

        this.setVisible(true);
    }

    /**
     * Classe
     * 
     * @author Charline
     *
     */
    public class ScoresJoueurJPanel extends JPanel {

        /**
         * 
         */
        private static final long serialVersionUID = 1518055111407840872L;

        /**
         * Constructeur par defaut
         */
        public ScoresJoueurJPanel() {
            super();
            this.setOpaque(true);

            GridBagConstraints grille = new GridBagConstraints();

            //Police de texte label
            Font police2 = new Font("Ariel", Font.PLAIN, 20);

            //Champ de la liste des Scores
            JPanel champScorePan = new JPanel();
            champScorePan.setPreferredSize(new Dimension(1000, 375));
            champScorePan.setBackground(Color.BLACK);
            champScorePan.setForeground(Color.WHITE);

            final JoueurService joueurService = new JoueurService();
            final List<JouerDto> listeScoreJoueurs = joueurService.findAllBestJoueursEtScores();

            for (int i = 0; i < 10; i++) {
                afficherLigneScore(i, police2, champScorePan, listeScoreJoueurs);

            }

            grille.gridx = 1;
            grille.gridy = 1;
            this.add(champScorePan, grille);

        }

        /**
         * Permet d'afficher une ligne de Consulter score
         * 
         * @param police2
         * @param champScorePan
         * @param listeScoreJoueurs
         */
        public void afficherLigneScore(final int num, final Font police2, final JPanel champScorePan, final List<JouerDto> listeScoreJoueurs) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            final JPanel panLigne5 = new JPanel(new GridBagLayout());
            panLigne5.setOpaque(false);
            panLigne5.setPreferredSize(new Dimension(800, 30));
            final JLabel labNom51 = new JLabel(" " + (num + 1) + " -  " + listeScoreJoueurs.get(num).getNomProfil(), SwingConstants.LEFT);
            labNom51.setFont(police2);
            labNom51.setPreferredSize(new Dimension(200, 30));
            labNom51.setForeground(Color.WHITE);
            panLigne5.add(labNom51);
            final JLabel labDate52 = new JLabel(" " + simpleDateFormat.format(listeScoreJoueurs.get(num).getDate_jeu()), SwingConstants.CENTER);
            labDate52.setFont(police2);
            labDate52.setPreferredSize(new Dimension(400, 30));
            labDate52.setForeground(Color.WHITE);
            panLigne5.add(labDate52);
            final JLabel labScore53 = new JLabel(" " + listeScoreJoueurs.get(num).getScore(), SwingConstants.RIGHT);
            labScore53.setFont(police2);
            labScore53.setPreferredSize(new Dimension(100, 30));
            labScore53.setForeground(Color.WHITE);
            panLigne5.add(labScore53);
            champScorePan.add(panLigne5);
        }

        @Override
        public Insets getInsets() {
            Insets normal = super.getInsets();
            return new Insets(normal.top + 50, normal.left + 50, normal.bottom + 50, normal.right + 50);
        }

    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        final Graphics g2 = (Graphics2D) g;
        g2.drawImage(this.imgFond, 0, 0, null);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        MenuController.ecranMenu(new PannelMenu(joueur));
    }

    @Override
    public Insets getInsets() {
        Insets normal = super.getInsets();
        return new Insets(normal.top + 100, normal.left + 100, normal.bottom + 100, normal.right + 100);
    }

    public JPanel getPanelDesScores() {
        return panelDesScores;
    }

    public void setPanelDesScores(final JPanel panelDesScores) {
        this.panelDesScores = panelDesScores;
    }

}
