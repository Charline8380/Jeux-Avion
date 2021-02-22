package fr.afpa.cda.group4.projet.avion.app.modelDto;

import java.util.Random;

import javax.swing.JLabel;

/**
 * 
 * @author Rachel
 *
 */
public class Vaisseau extends ObjetMouvant {
	private Integer pointsDeVie;
	private JLabel label;
	Random ran = new Random();
	private String nom;

	/////////////////
	private boolean tirerLaser = false;
	private boolean tirerMissile = false;
	private boolean tirerRafale = false;
	private boolean deplacementDroite = true;
	private boolean deplacementGauche = true;
	private boolean deplacementBas = true;
	private boolean deplacementHaut = true;
	////////////////

	/**
	 * 
	 * @param nom
	 */
	public Vaisseau(String nom) {
		super();
		this.nom=nom;
		this.pointsDeVie = 5;
		this.label = null;
		this.vitesseX = 10;
		this.vitesseY = 10;

	}

	/**
	 * 
	 * @param pointsDeVie
	 */
	public Vaisseau(Integer pointsDeVie) {
		super();
		this.pointsDeVie = pointsDeVie;
		this.label = null;
		this.vitesseX = 5;
		this.vitesseY = 5;

	}

	
	
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getPointsDeVie() {
		return pointsDeVie;
	}

	public void setPointsDeVie(Integer pointsDeVie) {
		this.pointsDeVie = pointsDeVie;
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public void run() {

	}

	public boolean isDeplacementDroite() {
		return deplacementDroite;
	}

	public void setDeplacementDroite(boolean deplacementDroite) {
		this.deplacementDroite = deplacementDroite;
	}

	public boolean isDeplacementGauche() {
		return deplacementGauche;
	}

	public void setDeplacementGauche(boolean deplacementGauche) {
		this.deplacementGauche = deplacementGauche;
	}

	public boolean isDeplacementBas() {
		return deplacementBas;
	}

	public void setDeplacementBas(boolean deplacementBas) {
		this.deplacementBas = deplacementBas;
	}

	public boolean isDeplacementHaut() {
		return deplacementHaut;
	}

	public void setDeplacementHaut(boolean deplacementHaut) {
		this.deplacementHaut = deplacementHaut;
	}

	public boolean isTirerLaser() {
		return tirerLaser;
	}

	public void setTirerLaser(boolean tirerLaser) {
		this.tirerLaser = tirerLaser;
	}

	public boolean isTirerMissile() {
		return tirerMissile;
	}

	public void setTirerMissile(boolean tirerMissile) {
		this.tirerMissile = tirerMissile;
	}

	public boolean isTirerRafale() {
		return tirerRafale;
	}

	public void setTirerRafale(boolean tirerRafale) {
		this.tirerRafale = tirerRafale;
	}

	public void verifPos(int PosX, int PosY) {
		if (this.posX <= 8) {
			this.deplacementGauche = false;
		} else {
			this.deplacementGauche = true;
		}

		if (this.posX >= (1500 - (this.tailleX))) {
			this.deplacementDroite = false;
		} else {
			this.deplacementDroite = true;
		}
		if (this.posY >= (800 - (this.tailleY))) {
			this.deplacementBas = false;
		} else {
			this.deplacementBas = true;
		}
		if (this.posY <= 0) {
			this.deplacementHaut = false;
		} else {
			this.deplacementHaut = true;
		}
	}

}
