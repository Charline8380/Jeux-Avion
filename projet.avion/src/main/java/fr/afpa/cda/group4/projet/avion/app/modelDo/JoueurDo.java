/**
 * 
 */
package fr.afpa.cda.group4.projet.avion.app.modelDo;

/**
 * @author Charline
 *
 */
public class JoueurDo {

    private Integer id_joueur;
    private String  nom_profil;
    private String  mot_de_passe;

    /**
     * Consructeur par defaut
     */
    public JoueurDo() {
        super();
    }

    /**
     * Constructeur avec tous les Field
     * 
     * @param id_joueur
     * @param nom_profil
     * @param mot_de_passe
     */
    public JoueurDo(final Integer id_joueur, final String nom_profil, final String mot_de_passe) {
        this.id_joueur = id_joueur;
        this.nom_profil = nom_profil;
        this.mot_de_passe = mot_de_passe;
    }

    /**
     * @param nom_profil
     * @param mot_de_passe
     */
    public JoueurDo(final String nom_profil, final String mot_de_passe) {
        this.nom_profil = nom_profil;
        this.mot_de_passe = mot_de_passe;
    }

    @Override
    public String toString() {
        return "Joueur " + id_joueur + "  -  " + nom_profil + " ;";
    }

    public Integer getId_joueur() {
        return id_joueur;
    }

    public void setId_joueur(final Integer id_joueur) {
        this.id_joueur = id_joueur;
    }

    public String getNom_profil() {
        return nom_profil;
    }

    public void setNom_profil(final String nom_profil) {
        this.nom_profil = nom_profil;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(final String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

}
