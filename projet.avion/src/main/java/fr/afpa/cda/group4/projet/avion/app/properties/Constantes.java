package fr.afpa.cda.group4.projet.avion.app.properties;

import java.text.SimpleDateFormat;

/**
 * 
 * @author 
 *
 */
public class Constantes {
	private static SimpleDateFormat FORMAT_DATE= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	private static String DOSSIER_IMAGES = "src/main/java/fr/afpa/cda/group4/projet/avion/app/autre/resources/image/";
	private static String DOSSIER_MUSIQUES = "src/main/java/fr/afpa/cda/group4/projet/avion/app/audio/resources/";

	/**
	 * @return the fORMAT_DATE
	 */
	public static SimpleDateFormat getFORMAT_DATE() {
		return FORMAT_DATE;
	}

	/**
	 * @return the dOSSIER_IMAGES
	 */
	public static String getDOSSIER_IMAGES() {
		return DOSSIER_IMAGES;
	}

	/**
	 * @return the dOSSIER_MUSIQUES
	 */
	public static String getDOSSIER_MUSIQUES() {
		return DOSSIER_MUSIQUES;
	}

	public static int tailleX_vaisseau = 61;
	public static int tailleY_vaisseau = 91;
	public static int posX_init_vaisseau = 750;
	public static int posY_init_Vaisseau = 490;
	public static int vitesseX_Vaisseau = 1;
	public static int vitesseY_Vaisseau = 1;
}
