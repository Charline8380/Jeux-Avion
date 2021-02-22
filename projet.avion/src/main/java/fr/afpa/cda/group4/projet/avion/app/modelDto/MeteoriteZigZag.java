package fr.afpa.cda.group4.projet.avion.app.modelDto;

import java.awt.Image;
import java.io.File;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import fr.afpa.cda.group4.projet.avion.app.properties.Constantes;

/**
 * 
 * @author 
 *
 */
public class MeteoriteZigZag extends Meteorite {

	public MeteoriteZigZag() {
		super(5, 2, 5);
		this.image = new File(Constantes.getDOSSIER_IMAGES() + "Meteorites/meteoriteZIGZAG.png");
		Random ran = new Random();
		this.tailleX = 50;
		this.tailleY = 50;
		this.posX = this.tailleX + ran.nextInt(1500 - 2*this.tailleX);
		this.posY = 0-this.tailleY;
		this.vitesseX = 0;
		this.vitesseY = 2;
		ImageIcon icon = new ImageIcon(new ImageIcon(this.image.getAbsolutePath().toString()).getImage()
				.getScaledInstance(this.tailleX, this.tailleY, Image.SCALE_DEFAULT));
		this.setLabel(new JLabel(icon));
		this.getLabel().setBounds(this.posX, this.posY - this.tailleY, this.tailleX, this.tailleY);
		this.getLabel().setVisible(true);
	}

	public void run() {
		while (posY < 800) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.posY = posY + vitesseY;
			this.posX = posX + (int) (Math.round(8 * (Math.cos(vitesseX / 5))));
			this.vitesseX++;
			this.getLabel().setLocation(this.posX, this.posY);
		}
		this.setMorte(true);


	}

}
