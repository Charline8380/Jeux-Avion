package fr.afpa.cda.group4.projet.avion.app.views;

import javax.swing.JFrame;

/**
 * 
 * @author 
 *
 */
public class FenetreDeJeu extends JFrame {

    private static final long serialVersionUID = -1680145539410283267L;
    private FondEtoiles       fond;

    /**
     * 
     * @param width
     * @param height
     */
    public FenetreDeJeu(Integer width, Integer height) {
        this.setSize(width, height);
        fond = new FondEtoiles(width, height);
        this.add(fond);
    }

    /**
     * @return the fond
     */
    public FondEtoiles getFond() {
        return fond;
    }

    /**
     * @param fond the fond to set
     */
    public void setFond(FondEtoiles fond) {
        this.fond = fond;
    }

    public static void main(String[] args) {
        FenetreDeJeu maFenetre = new FenetreDeJeu(1500, 800);
        maFenetre.setResizable(false);
        //       maFenetre.fond.add(new PanelDeJeu());
        maFenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        maFenetre.setVisible(true);
    }

}
