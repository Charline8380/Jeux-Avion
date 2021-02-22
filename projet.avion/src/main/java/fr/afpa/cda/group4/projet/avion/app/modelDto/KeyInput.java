package fr.afpa.cda.group4.projet.avion.app.modelDto;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import fr.afpa.cda.group4.projet.avion.app.views.FenetreMeteorite;

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
		this.game=game;
	}

	/**
	 * Touche du clavier appuyée
	 */
	public void keyPressed(KeyEvent e) {
		game.keyPressed(e);
	}
	
	/**
	 * Touche du clavier relachée
	 */
	public void keyReleased(KeyEvent e) {
		game.keyReleased(e);
	}
	
}
