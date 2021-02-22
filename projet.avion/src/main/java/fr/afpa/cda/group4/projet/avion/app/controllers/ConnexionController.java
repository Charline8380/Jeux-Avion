package fr.afpa.cda.group4.projet.avion.app.controllers;

import fr.afpa.cda.group4.projet.avion.app.main.App;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Partie;
import fr.afpa.cda.group4.projet.avion.app.serveurAccess.ConnexionServeur;

/**
 * 
 * @author 
 *
 */
public class ConnexionController {
	
	public static ConnexionController connexionServeur=null;
	//private ConnexionServeur connexionServeur;
	
	public ConnexionController(Partie partie, Boolean isHote) {
		ConnexionServeur connexionServeur = new ConnexionServeur(partie, isHote);
		Thread thread = new Thread(connexionServeur);
		thread.start();
	}
	
	public static ConnexionController getInstance(Partie partie, Boolean isHote) {
        if (connexionServeur == null) {
            connexionServeur=new ConnexionController(null, isHote);
        }
        return connexionServeur;
    }
	
	
	public static ConnexionController getInstance() {
        return connexionServeur;
    }
	
	/**
	 * @return the connexionServeur
	 */
	public static ConnexionController getConnexionServeur() {
		return connexionServeur;
	}

	/**
	 * @param connexionServeur the connexionServeur to set
	 */
	public static void setConnexionServeur(ConnexionController connexionServeur) {
		ConnexionController.connexionServeur = connexionServeur;
	}
	
	
	public ConnexionController() {
		// TODO Auto-generated constructor stub
	}

	public static void seConnecter(Partie partie, Boolean isHote) {
		ConnexionServeur connexionServeur = new ConnexionServeur(partie, isHote);
		Thread thread = new Thread(connexionServeur);
		thread.start();
	}
}
