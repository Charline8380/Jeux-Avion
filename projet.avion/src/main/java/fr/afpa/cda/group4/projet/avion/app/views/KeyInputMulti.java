package fr.afpa.cda.group4.projet.avion.app.views;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInputMulti  extends KeyAdapter {
	FenetreJeuMulti game;

    public KeyInputMulti(FenetreJeuMulti game) {
        this.game = game;
    }

    public void keyPressed(KeyEvent e) {
        game.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        game.keyReleased(e);
    }

}