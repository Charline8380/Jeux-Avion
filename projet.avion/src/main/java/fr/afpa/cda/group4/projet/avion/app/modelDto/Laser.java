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
public class Laser extends Armement implements Runnable{

	public Laser() {
		super(5);
		this.image = new File(Constantes.getDOSSIER_IMAGES() + "Armes/laserRouge.png");
		this.tailleX = 59;
		this.tailleY = 107;
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
		while (posY>0) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.posY=posY-vitesseY;
			this.getLabel().setLocation(this.posX, this.posY);
		}
	}
}
