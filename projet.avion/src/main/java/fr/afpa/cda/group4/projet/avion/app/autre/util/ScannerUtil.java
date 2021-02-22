package fr.afpa.cda.group4.projet.avion.app.autre.util;

import java.util.Scanner;

/**
 * Classe ScannerUtil : permet de gerer la lecture des informations saisis par
 * l'utilisateur dans tous le projet
 * @author Charline
 *
 */
public class ScannerUtil {

	public static Scanner scan;

	/**
	 * Ouverture du scanner
	 */
	public static void ouvrir() {
		scan = new Scanner(System.in);
	}

	/**
	 * Lecture du scanner
	 * @return la Chaine de caractère lu
	 */
	public static String lire() {
		return scan.nextLine();
	}

	/**
	 * Fermeture du scanner
	 */
	public static void fermer() {
		scan.close();
	}

}
