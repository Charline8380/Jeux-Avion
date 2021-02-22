package fr.afpa.cda.group4.projet.avion.app.modelDto;

import java.io.File;

/**
 * 
 * @author 
 *
 */
public class ObjetMouvant implements Runnable {
	File image;
	File imageRotateRight;
	File imageRotateLeft;
	int tailleX;
	int tailleY;
	int posX;
	int posY;
	int vitesseX;
	int vitesseY;

	public ObjetMouvant() {
		super();
	}
	
	/**
	 * 
	 * @param image
	 * @param imageRotateRight
	 * @param imageRotateLeft
	 * @param tailleX
	 * @param tailleY
	 * @param posX
	 * @param posY
	 * @param vitesseX
	 * @param vitesseY
	 */
	public ObjetMouvant(File image, File imageRotateRight, File imageRotateLeft, int tailleX, int tailleY, int posX, int posY, int vitesseX, int vitesseY) {
		super();
		this.image = image;
		this.imageRotateRight = imageRotateRight;
		this.imageRotateLeft = imageRotateLeft;
		this.tailleX = tailleX;
		this.tailleY = tailleY;
		this.posX = posX;
		this.posY = posY;
		this.vitesseX = vitesseX;
		this.vitesseY = vitesseY;
	}
	
	public File getImage() {
		return image;
	}
	
	public void setImage(File image) {
		this.image = image;
	}

	public File getImageRotateRight() {
		return imageRotateRight;
	}

	public void setImageRotateRight(File imageRotateRight) {
		this.imageRotateRight = imageRotateRight;
	}

	public File getImageRotateLeft() {
		return imageRotateLeft;
	}

	public void setImageRotateLeft(File imageRotateLeft) {
		this.imageRotateLeft = imageRotateLeft;
	}

	public int getTailleX() {
		return tailleX;
	}
	
	public void setTailleX(int tailleX) {
		this.tailleX = tailleX;
	}
	public int getTailleY() {
		return tailleY;
	}
	public void setTailleY(int tailleY) {
		this.tailleY = tailleY;
	}

	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public int getVitesseX() {
		return vitesseX;
	}
	public void setVitesseX(int vitesseX) {
		this.vitesseX = vitesseX;
	}
	public int getVitesseY() {
		return vitesseY;
	}
	public void setVitesseY(int vitesseY) {
		this.vitesseY = vitesseY;
	}
    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }

	
}
