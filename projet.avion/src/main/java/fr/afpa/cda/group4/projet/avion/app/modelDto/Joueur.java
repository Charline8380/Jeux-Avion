package fr.afpa.cda.group4.projet.avion.app.modelDto;

/**
 * 
 * @author 
 *
 */
public class Joueur {
	private Integer id;
	private String nom;
	private String password;
	private Integer score=0;
	private static Integer nbViesInitial =5;
	private Integer nbViesRestantes;
	private Vaisseau vaisseau;
	private Boolean multi=false;
	
	public Joueur() {
		super();
	}
	
	/**
	 * 	
	 * @param id
	 * @param nom
	 * @param password
	 * @param score
	 * @param nbViesRestantes
	 */
	public Joueur(Integer id, String nom, String password, Integer score, Integer nbViesRestantes) {
		super();
		this.id = id;
		this.nom = nom;
		this.password = password;
		this.score = score;
		this.nbViesRestantes = nbViesRestantes;
		this.vaisseau = null;
	}
	
	/**
	 * 
	 * @param nom
	 * @param nomVaisseau
	 */
	public Joueur(String nom, String nomVaisseau) {
		super();
		this.nom = nom;
		this.nbViesRestantes = nbViesInitial;
		this.vaisseau = new Vaisseau(nomVaisseau);
	}
	
	/**
	 * 
	 * @param nom
	 */
	public Joueur(String nom) {
		super();
		this.nom = nom;
		this.nbViesRestantes = nbViesInitial;
	}

	
	/**
	 * 
	 * @return boolean
	 */
	public boolean isGoodFormat() {
		return nom.matches("[^;]{3,6}");
	}


	/**
	 * @return the vaisseau
	 */
	public Vaisseau getVaisseau() {
		return vaisseau;
	}

	/**
	 * @param vaisseau the vaisseau to set
	 */
	public void setVaisseau(Vaisseau vaisseau) {
		this.vaisseau = vaisseau;
	}


	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the score
	 */
	public Integer getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(Integer score) {
		this.score = score;
	}
	/**
	 * @return the nbViesInitial
	 */
	public Integer getNbViesInitial() {
		return nbViesInitial;
	}
	/**
	 * @param nbViesInitial the nbViesInitial to set
	 */
	
	/**
	 * @return the nbViesRestantes
	 */
	public Integer getNbViesRestantes() {
		return nbViesRestantes;
	}
	/**
	 * @param nbViesRestantes the nbViesRestantes to set
	 */
	public void setNbViesRestantes(Integer nbViesRestantes) {
		this.nbViesRestantes = nbViesRestantes;
	}

	public boolean isMulti() {
		return multi;
	}

	public void setMulti(Boolean multi) {
		this.multi = multi;
	}
	 
	
	
}
