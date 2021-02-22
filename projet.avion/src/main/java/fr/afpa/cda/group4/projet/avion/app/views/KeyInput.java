package fr.afpa.cda.group4.projet.avion.app.views;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 
 * @author 
 *
 */
public class KeyInput extends KeyAdapter {
    FenetreMeteorite game;

    /**
     * 
     * @param game
     */
    public KeyInput(FenetreMeteorite game) {
        this.game = game;
    }

    /**
     * touche du clavier appuyée
     */
    public void keyPressed(KeyEvent e) {
        game.keyPressed(e);
    }

    /**
     * touche du clavier relachée
     */
    public void keyReleased(KeyEvent e) {
        game.keyReleased(e);
    }

}
