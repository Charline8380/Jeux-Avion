package fr.afpa.cda.group.projet.avionServeur.serveur;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Classe Partie
 * 
 * @author Charline
 *
 */
public class Partie {

    private List<ThreadJoueur> joueurs;
    private Integer            idPartie;
    private Integer			   nbPlaces;
    private ThreadJoueur       createur;
    private String			   nom;
    private static Integer	   dernierId=0;
    	

    /**
     * Constructeur
     */
    public Partie() {
        super();
        idPartie=dernierId++;
        
    }

    

	/**
     * Permet de renvoyer la commande à tous
     * 
     * @param s
     */
    synchronized public void EnvoyerUneCommandeATous(final String s) {
        System.out.println("Serveur envoie à tous : "+s);
        for (final ThreadJoueur joueur : getJoueurs()) {
            joueur.envoyer(s);
        }

    }

    /**
	 * @return the nbPlaces
	 */
	public Integer getNbPlaces() {
		return nbPlaces;
	}

	/**
	 * @param nbPlaces the nbPlaces to set
	 */
	public void setNbPlaces(Integer nbPlaces) {
		this.nbPlaces = nbPlaces;
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
	
	public List<ThreadJoueur> getJoueurs() {
        if (joueurs == null)
            joueurs = new CopyOnWriteArrayList<ThreadJoueur>();
        return joueurs;
    }

    public void setJoueurs(final List<ThreadJoueur> joueurs) {
        this.joueurs = joueurs;
    }

    public Integer getIdPartie() {
        return idPartie;
    }

    public void setIdPartie(final Integer idPartie) {
        this.idPartie = idPartie;
    }

    public ThreadJoueur getCreateur() {
        return createur;
    }

    public void setCreateur(final ThreadJoueur createur) {
        this.createur = createur;
    }

}
