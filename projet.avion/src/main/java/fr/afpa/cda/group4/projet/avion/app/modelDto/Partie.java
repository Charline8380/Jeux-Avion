package fr.afpa.cda.group4.projet.avion.app.modelDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 
 * @author 
 *
 */
public class Partie {
    private String          nom;
    private Date            debutPartie;
    private Integer         nbMaxMeteorites;
    private List<Meteorite> meteoritesEnJeu = new CopyOnWriteArrayList<Meteorite>();
    private List<Joueur> joueurs = new ArrayList<Joueur>();
    private Integer			nbPlaces;
    private Joueur createur;

    /**
     * 
     * @param joueur
     */
    public Partie(Joueur joueur) {
        super();
        this.nom = joueur.getNom();
        this.debutPartie = new Date();
        this.nbMaxMeteorites = 5;
        joueurs.add(joueur);
        this.createur=joueur;
        // this.meteoritesEnJeu = meteoritesEnJeu;
    }
    
    /**
     * 
     * @param joueur
     * @param nbPlaces
     */
    public Partie(Joueur joueur, Integer nbPlaces) {
        super();
        this.nom = joueur.getNom();
        this.nbPlaces=nbPlaces;
        this.debutPartie = new Date();
        this.nbMaxMeteorites = 5;
        joueurs.add(joueur);
        this.createur=joueur;
        // this.meteoritesEnJeu = meteoritesEnJeu;
    }

    /**
   	 * @return the joueurs
   	 */
   	public List<Joueur> getJoueurs() {
   		return joueurs;
   	}

   	/**
   	 * @param joueurs the joueurs to set
   	 */
   	public void setJoueurs(List<Joueur> joueurs) {
   		this.joueurs = joueurs;
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

    /**
     * @return the nbJoueurs
     */

    /**
     * @return the debutPartie
     */
    public Date getDebutPartie() {
        return debutPartie;
    }

    /**
     * @param debutPartie the debutPartie to set
     */
    public void setDebutPartie(Date debutPartie) {
        this.debutPartie = debutPartie;
    }

    /**
     * @return the nbMaxMeteorites
     */
    public Integer getNbMaxMeteorites() {
        return nbMaxMeteorites;
    }

    /**
     * @param nbMaxMeteorites the nbMaxMeteorites to set
     */
    public void setNbMaxMeteorites(Integer nbMaxMeteorites) {
        this.nbMaxMeteorites = nbMaxMeteorites;
    }

    /**
     * @return the meteoritesEnJeu
     */
    public List<Meteorite> getMeteoritesEnJeu() {
        return meteoritesEnJeu;
    }

    /**
     * @param meteoritesEnJeu the meteoritesEnJeu to set
     */
    public void setMeteoritesEnJeu(List<Meteorite> meteoritesEnJeu) {
        this.meteoritesEnJeu = meteoritesEnJeu;
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
	public Joueur getCreateur() {
		return createur;
	}
	public void setCreateur(Joueur createur) {
		this.createur = createur;
	}

}
