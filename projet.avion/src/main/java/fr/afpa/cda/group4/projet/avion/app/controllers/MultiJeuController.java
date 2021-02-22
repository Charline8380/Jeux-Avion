package fr.afpa.cda.group4.projet.avion.app.controllers;

import static fr.afpa.cda.group4.projet.avion.app.views.Parametres.isBruitageOn;

import java.awt.Image;
import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import fr.afpa.cda.group4.projet.avion.app.audio.Bruitage;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Armement;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Joueur;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Laser;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Meteorite;
import fr.afpa.cda.group4.projet.avion.app.modelDto.MeteoriteFeu;
import fr.afpa.cda.group4.projet.avion.app.modelDto.MeteoriteGlace;
import fr.afpa.cda.group4.projet.avion.app.modelDto.MeteoriteIceberg;
import fr.afpa.cda.group4.projet.avion.app.modelDto.MeteoriteNormale;
import fr.afpa.cda.group4.projet.avion.app.modelDto.MeteoriteZigZag;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Missile;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Partie;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Vaisseau;
import fr.afpa.cda.group4.projet.avion.app.properties.Constantes;
import fr.afpa.cda.group4.projet.avion.app.views.FenetreJeuMulti;
import fr.afpa.cda.group4.projet.avion.app.views.FenetreMeteorite;
import fr.afpa.cda.group4.projet.avion.app.views.FondEtoiles;

public class MultiJeuController {
	private Vaisseau vaisseau;
	private Joueur joueur;
	private Partie partie;
	private Long lastMeteoriteArrivedTime;
	private Long lastVaisseauTouchedTime;
	private JLabel labExplosion = null;
	FondEtoiles fondEtoiles = new FondEtoiles();
	List<Meteorite> meteoritesEnJeu = new CopyOnWriteArrayList<Meteorite>();


	public MultiJeuController() {
		super();
	}

	public MultiJeuController(Vaisseau vaisseau, Joueur joueur, Partie partie) {
		super();
		this.vaisseau = vaisseau;
		this.joueur = joueur;
		this.partie = partie;
	}

	public static void MoveVaisseauDroite(Joueur joueur) {
		joueur.getVaisseau().verifPos(joueur.getVaisseau().getPosX(), joueur.getVaisseau().getPosY());

		joueur.getVaisseau().setPosX(joueur.getVaisseau().getPosX() + joueur.getVaisseau().getVitesseX());
		ImageIcon icon = new ImageIcon(
				new ImageIcon(joueur.getVaisseau().getImageRotateRight().getAbsolutePath().toString()).getImage());
		joueur.getVaisseau().getLabel().setIcon(icon);
		joueur.getVaisseau().verifPos(joueur.getVaisseau().getPosX(), joueur.getVaisseau().getPosY());

	}

	public static void MoveVaisseauGauche(Joueur joueur) {
		joueur.getVaisseau().verifPos(joueur.getVaisseau().getPosX(), joueur.getVaisseau().getPosY());
		if (joueur.getVaisseau().isDeplacementGauche()) {
			joueur.getVaisseau().setPosX(joueur.getVaisseau().getPosX() - joueur.getVaisseau().getVitesseX());
			ImageIcon icon = new ImageIcon(
					new ImageIcon(joueur.getVaisseau().getImageRotateLeft().getAbsolutePath().toString()).getImage());
			joueur.getVaisseau().getLabel().setIcon(icon);
			joueur.getVaisseau().verifPos(joueur.getVaisseau().getPosX(), joueur.getVaisseau().getPosY());
		}
	}

	public static void MoveVaisseauHaut(Joueur joueur) {
		joueur.getVaisseau().verifPos(joueur.getVaisseau().getPosX(), joueur.getVaisseau().getPosY());
		if (joueur.getVaisseau().isDeplacementHaut()) {
			joueur.getVaisseau().setPosY(joueur.getVaisseau().getPosY() - joueur.getVaisseau().getVitesseY());
			joueur.getVaisseau().verifPos(joueur.getVaisseau().getPosX(), joueur.getVaisseau().getPosY());
		}
	}

	public static void MoveVaisseauBas(Joueur joueur) {
		joueur.getVaisseau().verifPos(joueur.getVaisseau().getPosX(), joueur.getVaisseau().getPosY());
		if (joueur.getVaisseau().isDeplacementBas()) {
			joueur.getVaisseau().setPosY(joueur.getVaisseau().getPosY() + joueur.getVaisseau().getVitesseY());
			joueur.getVaisseau().verifPos(joueur.getVaisseau().getPosX(), joueur.getVaisseau().getPosY());
		}
	}

	public void GenerationMeteorites() {
		Random ran = new Random();
		if (meteoritesEnJeu.size() < 5 && System.currentTimeMillis() - lastMeteoriteArrivedTime > 500) {
			Integer numMeteorite = ran.nextInt(5);
			Meteorite met = null;
			switch (numMeteorite) {
			case 0:
				met = new MeteoriteNormale();
				break;
			case 1:
				met = new MeteoriteFeu();
				break;

			case 2:
				met = new MeteoriteGlace();
				break;

			case 3:
				met = new MeteoriteZigZag();
				break;
			case 4:
				met = new MeteoriteIceberg();
				break;
			}

			if (met != null) {
				meteoritesEnJeu.add(met);
				fondEtoiles.add(met.getLabel());
				(new Thread(met)).start();
			}
			lastMeteoriteArrivedTime = System.currentTimeMillis();

		}
	}

	public void VerificationMeteoritesMortes() {
		for (int i = 0; i < meteoritesEnJeu.size(); i++) {
			if (meteoritesEnJeu.get(i).getMorte() == true) {
				fondEtoiles.remove(meteoritesEnJeu.get(i).getLabel());
				meteoritesEnJeu.remove(i);

			}
		}
	}

	public void VerificationCollisionMeteoriteVaisseau() {
		if (System.currentTimeMillis() - lastVaisseauTouchedTime > 1100 && labExplosion != null) {
			fondEtoiles.remove(labExplosion);
			labExplosion = null;
		}

		for (Meteorite meteorite : meteoritesEnJeu) {
			if (!(this.joueur.getVaisseau().getPosX() > meteorite.getPosX() + meteorite.getTailleX()
					|| this.joueur.getVaisseau().getPosY() > meteorite.getPosY() + meteorite.getTailleY()
					|| this.joueur.getVaisseau().getPosX() + this.joueur.getVaisseau().getTailleX() < meteorite
							.getPosX()
					|| this.joueur.getVaisseau().getPosY() + this.joueur.getVaisseau().getTailleY() < meteorite
							.getPosY())
					&& System.currentTimeMillis() - lastVaisseauTouchedTime > 1200) {
				lastVaisseauTouchedTime = System.currentTimeMillis();
				this.joueur.getVaisseau()
						.setPointsDeVie(this.joueur.getVaisseau().getPointsDeVie() - meteorite.getPointsDegats());
				File img = new File(Constantes.getDOSSIER_IMAGES() + "Explosion/source.gif");
				if (isBruitageOn) {
					Bruitage.playSound("resources/explosionVaisseau.wav");
				}
				ImageIcon icon = new ImageIcon(new ImageIcon(img.getAbsolutePath().toString()).getImage()
						.getScaledInstance(200, 200, Image.SCALE_DEFAULT));
				labExplosion = new JLabel(icon);
				labExplosion.setBounds((this.joueur.getVaisseau().getPosX() + meteorite.getPosX()) / 2 - 30,
						(this.joueur.getVaisseau().getPosY() + meteorite.getPosY()) / 2 - 30, 150, 150);
				labExplosion.setVisible(true);

				fondEtoiles.add(labExplosion);
				Integer num = fondEtoiles.getComponentCount();
				fondEtoiles.setComponentZOrder(this.joueur.getVaisseau().getLabel(), num - 1);
				fondEtoiles.setComponentZOrder(labExplosion, 0);
				meteorite.setPointsDegats(0);

			}
		}
	}

	public static void TireLaser(Joueur joueur, Armement missile) {
		missile.setPosX(joueur.getVaisseau().getPosX());
		missile.setPosY(joueur.getVaisseau().getPosY());
        if (isBruitageOn) {
            Bruitage.playSound("resources/laser.wav");
        }
		(new Thread(missile)).start();



	}

//	public void TireMissile() {
//		if (this.joueur.getVaisseau().isTirerMissile()) {
//			missile = new Missile();
//			laserEnJeu.add(missile);
//			fondEtoiles.add(missile.getLabel());
//			missile.setPosX(this.joueur.getVaisseau().getPosX());
//			missile.setPosY(this.joueur.getVaisseau().getPosY());
//
//			(new Thread(missile)).start();
//			try {
//				Thread.sleep(200);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}

//	public void TireRafale() {
//		if (this.joueur.getVaisseau().isTirerRafale()) {
//			Missile missile1 = new Missile();
//			Missile missile2 = new Missile();
//			Missile missile3 = new Missile();
//			laserEnJeu.add(missile1);
//			laserEnJeu.add(missile2);
//			laserEnJeu.add(missile3);
//			fondEtoiles.add(missile1.getLabel());
//			fondEtoiles.add(missile2.getLabel());
//			fondEtoiles.add(missile3.getLabel());
//			missile1.setPosX(this.joueur.getVaisseau().getPosX());
//			missile1.setPosY(this.joueur.getVaisseau().getPosY());
//			missile2.setPosX(this.joueur.getVaisseau().getPosX() + 50);
//			missile2.setPosY(this.joueur.getVaisseau().getPosY());
//			missile3.setPosX(this.joueur.getVaisseau().getPosX() + 100);
//			missile3.setPosY(this.joueur.getVaisseau().getPosY());
//			missile1.setDegre(-1);
//			missile2.setDegre(0);
//			missile3.setDegre(+1);
//			missile1.setRafale(true);
//			missile2.setRafale(true);
//			missile3.setRafale(true);
//			(new Thread(missile1)).start();
//			(new Thread(missile2)).start();
//			(new Thread(missile3)).start();
//			try {
//				Thread.sleep(200);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}

//	public void VerificationCollisionMeteoriteMissile() {
//		for (Armement armement : laserEnJeu) {
//			for (Meteorite meteorite : meteoritesEnJeu) {
//				if (!(armement.getPosX() > meteorite.getPosX() + meteorite.getTailleX()
//						|| armement.getPosY() > meteorite.getPosY() + meteorite.getTailleY()
//						|| armement.getPosX() + armement.getTailleX() < meteorite.getPosX()
//						|| armement.getPosY() + armement.getTailleY() < meteorite.getPosY())) {
//
//					meteorite.setPointsDeVie(meteorite.getPointsDeVie() - armement.getPointsDegats());
//					System.out.println(
//							"Météorite Touché ! il lui reste : " + meteorite.getPointsDeVie() + " point de vie !");
//					if (isBruitageOn) {
//						Bruitage.playSound("resources/explosionMeteorite.wav");
//					}
//					if (meteorite.getPointsDeVie() <= 0) {
//						File img = new File(Constantes.getDOSSIER_IMAGES() + "Explosion/source.gif");
//						ImageIcon icon = new ImageIcon(new ImageIcon(img.getAbsolutePath().toString()).getImage()
//								.getScaledInstance(200, 200, Image.SCALE_DEFAULT));
//						JLabel lab = new JLabel(icon);
//						lab.setBounds((armement.getPosX() + meteorite.getPosX()) / 2 - 30,
//								(armement.getPosY() + meteorite.getPosY()) / 2 - 30, 150, 150);
//						lab.setVisible(true);
//						fondEtoiles.add(lab);
//						Integer num = fondEtoiles.getComponentCount();
//						// fondEtoiles.setComponentZOrder(armement.getLabel(), num - 1);
//						fondEtoiles.setComponentZOrder(lab, 0);
//						this.joueur.setScore(this.joueur.getScore() + meteorite.getPointsGain());
//						fondEtoiles.remove(meteorite.getLabel());
//						meteoritesEnJeu.remove(meteorite);
//						try {
//							Thread.sleep(100);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//
//						fondEtoiles.remove(lab);
//					}
//					fondEtoiles.remove(armement.getLabel());
//					laserEnJeu.remove(armement);
//
//				}
//				for (Armement reste : laserEnJeu) {
//
//					if (reste.getPosY() <= 0) {
//						laserEnJeu.remove(reste);
//						fondEtoiles.remove(reste.getLabel());
//					}
//				}
//
//			}
//
//		}
//	}

}
