package fr.afpa.cda.group4.projet.avion.app.views;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import fr.afpa.cda.group4.projet.avion.app.properties.Constantes;

/**
 * 
 * @author 
 *
 */
public class FondEtoiles extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4748252932878326186L;
	private Image image;
	private Integer width;
	private Integer height;

	/** Creates a new instance of MonPanel */
	public FondEtoiles() {
		super();
		this.setLayout(null);
		try {
			image = ImageIO.read(new File(Constantes.getDOSSIER_IMAGES()+"fond.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.width = 1500;
		this.height = 800;
	}

	/**
	 * 
	 * @param width
	 * @param height
	 */
	public FondEtoiles(Integer width, Integer height) {
		super();
		this.setLayout(null);
		try {
			image = ImageIO.read(new File(Constantes.getDOSSIER_IMAGES()+"fond.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.width = width;
		this.height = height;
	}

	/**
	 * dessine le contenu
	 */
	public void paintComponent(Graphics g2) {
		super.paintComponent(g2);
		g2.drawImage(image, 0, 0, width, height, this);
	}
}
