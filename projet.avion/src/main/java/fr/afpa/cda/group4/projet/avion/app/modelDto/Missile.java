package fr.afpa.cda.group4.projet.avion.app.modelDto;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import fr.afpa.cda.group4.projet.avion.app.properties.Constantes;

/**
 * 
 * @author 
 *
 */
public class Missile extends Armement implements Runnable {
	boolean rafale = false;
	int degre = 0;

	public Missile() {
		super(2);
		this.image = new File(Constantes.getDOSSIER_IMAGES() + "Armes/missile.png");
		this.tailleX = 41 / 3;
		this.tailleY = 233 / 4;
		this.posX = 0;
		this.posY = 800;
		this.vitesseX = 0;
		this.vitesseY = 5;
		ImageIcon icon = new ImageIcon(new ImageIcon(this.image.getAbsolutePath().toString()).getImage()
				.getScaledInstance(this.tailleX, this.tailleY, Image.SCALE_DEFAULT));
		this.setLabel(new JLabel(icon));
		this.getLabel().setBounds(this.posX, this.posY - this.tailleY, this.tailleX, this.tailleY);
		this.getLabel().setVisible(true);
	}

	public void run() {
		if (rafale) {
			while (posY > 0) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.posY = posY - vitesseY;
				this.posX = posX + degre;
				this.getLabel().setLocation(this.posX, this.posY);
			}
		} else {
			while (posY > 0) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.posY = posY - vitesseY;
				this.getLabel().setLocation(this.posX, this.posY);
			}
		}

	}

	/**
	 * Si le missile est en rafale 
	 * @return boolean
	 */
	public boolean isRafale() {
		return rafale;
	}

	/**
	 * Passe le missile en rafale
	 * @param rafale
	 */
	public void setRafale(boolean rafale) {
		this.rafale = rafale;
	}
	
	/**
	 * récupère le degré de déplacement
	 * @return int
	 */
	public int getDegre() {
		return degre;
	}

	/**
	 * modifie le degré de déplacement
	 * @param degre
	 */
	public void setDegre(int degre) {
		this.degre = degre;
	}

}
