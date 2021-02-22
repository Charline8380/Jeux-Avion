package fr.afpa.cda.group4.projet.avion.app.modelDto;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import fr.afpa.cda.group4.projet.avion.app.properties.Constantes;

/**
 * 
 * @author Rachel
 *
 */
public class Vaisseau7 extends Vaisseau {

	public Vaisseau7() {
		super("Vaisseau7");
		this.image = new File(Constantes.getDOSSIER_IMAGES() + "Fusee/Vaisseau7.png");
		this.imageRotateRight = new File(Constantes.getDOSSIER_IMAGES() + "Fusee/Vaisseau7droite.png");
		this.imageRotateLeft = new File(Constantes.getDOSSIER_IMAGES() + "Fusee/Vaisseau7gauche.png");
		this.tailleX = 88;
		this.tailleY = 90;
		this.posY = 700 - this.tailleY;
		this.posX = ran.nextInt(1500 - this.tailleX);
		ImageIcon icon = new ImageIcon(new ImageIcon(this.image.getAbsolutePath().toString()).getImage()
				.getScaledInstance(this.tailleX, this.tailleY, Image.SCALE_DEFAULT));
		this.setLabel(new JLabel(icon));
		this.getLabel().setBounds(this.posX, this.posY, this.tailleX, this.tailleY);
		this.getLabel().setVisible(true);
	}

	public void run() {
		while (true) {
			this.getLabel().setLocation(this.posX, this.posY);
		}
	}

}
