package fr.afpa.cda.group4.projet.avion.app.serveurAccess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import fr.afpa.cda.group4.projet.avion.app.controllers.MenuController;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Joueur;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Partie;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Vaisseau1;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Vaisseau2;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Vaisseau3;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Vaisseau4;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Vaisseau5;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Vaisseau6;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Vaisseau7;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Vaisseau8;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Vaisseau9;
import fr.afpa.cda.group4.projet.avion.app.views.AttenteJoueurJPanel;
import fr.afpa.cda.group4.projet.avion.app.views.CreerUnePartieJPanel;
import fr.afpa.cda.group4.projet.avion.app.views.FenetreJeuMulti;
import fr.afpa.cda.group4.projet.avion.app.views.RejoindreUnePartieJPanel;

/**
 * 
 * @author 
 *
 */
public class ConnexionServeur implements Runnable {

	private final Integer PORT = 8888;
	private final String HOTE = "127.0.0.1";
	private static final long serialVersionUID = 1L;
	Socket socket = null;
	BufferedReader in;
	PrintWriter out;
	Partie partie;
	Joueur joueur;
	Boolean isHote;

	private static ConnexionServeur connexionServeur = null;

	/**
	 * 
	 * @param partie
	 * @param isHote
	 */
	public ConnexionServeur(Partie partie, Boolean isHote) {
		System.out.println("Client - Essai de connexion");
		try {
			socket = new Socket(HOTE, PORT);
			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e1) {
			System.exit(-1);
		}
		this.partie = partie;
		this.joueur = joueur;
		System.out.println("Connexion Serveur - joueur dans partie = " + partie.getJoueurs().get(0));
		this.isHote = isHote;
		Thread t = new Thread(this);
		t.start();
	}

	/**
	 * Connexion au serveur
	 * @param partie 
	 * @param isHote 
	 * @return ConnexionServeur
	 */
	public static ConnexionServeur getInstance(Partie partie, Boolean isHote) {
		if (connexionServeur == null) {
			connexionServeur = new ConnexionServeur(partie, isHote);
		}
		return connexionServeur;
	}

	/**
	 * 
	 * @return ConnexionServeur
	 */
	public static ConnexionServeur getInstance() {
		return connexionServeur;
	}

	@Override
	public void run() {
		System.out.println("Client - Essai de connexion");
		out.println("CONNEXION_DEMANDEE"); 
		out.flush();
		while (true) { // true en attendant de voir clairement la condition de sortie)
			try {
				String lu = in.readLine();
				if (lu.startsWith("CONNEXION_OK")) {
					System.out.println("Client : connexionOK reçue du serveur");
					if (this.isHote) { // Si le client est le créateur de la partie
						MenuController.ecranCreerUnePartie(new CreerUnePartieJPanel(partie));
					} else {
						System.out.println("Client envoie : LIST_PARTIES");
						out.println("LIST_PARTIES");
					}
					out.flush();
					// --
				} else if (lu.startsWith("PARTIE_CREE_OK")) {
					System.out.println("Client : PARTIE_CREE_OK reçu du serveur");
					out.println("MON_CHOIX_VAISSEAU" + ";" + partie.getJoueurs().get(0).getVaisseau().getNom());
					out.flush();
				} else if (lu.startsWith("CHOIX_VAISSEAU_OK")) {
//					System.out.println("Client : " + lu + " reçu du serveur");
//					String[] tab = lu.split(";");
//					joueur = partie.getJoueurs().get(0);
//					partie.getJoueurs().clear();
//					for (int i = 1; i < tab.length; i = i + 2) {
//						String nomJoueur = tab[i];
//						String nomVaisseau = tab[i+1];
//						System.out.println(nomJoueur+".."+nomVaisseau);
//						partie.getJoueurs().add(new Joueur(nomJoueur, nomVaisseau));
//					}
//					MenuController.ecranAttenteJoueur(new AttenteJoueurJPanel(partie, joueur));
					out.println("MAJ_PARTICIPANTS");
					out.flush();
					// } else if (lu.startsWith("EN_ATTENTE")) {
					// idem au-dessus
				} else if (lu.startsWith("MAJ_PARTICIPANTS")) {
					System.out.println("Client recoit : " + lu);
					String[] tab = lu.split(";");
					joueur = partie.getJoueurs().get(0);
					partie.getJoueurs().clear();
					for (int i = 1; i < tab.length; i = i + 2) {
						String nomJoueur = tab[i];
						String nomVaisseau = tab[i] + 1;
						System.out.println(nomJoueur+".."+nomVaisseau);
						partie.getJoueurs().add(new Joueur(nomJoueur, nomVaisseau));
					}
					MenuController.ecranAttenteJoueur(new AttenteJoueurJPanel(partie, joueur));
				} else if (lu.startsWith("LIST_JOUEURS")) {
					lu = in.readLine();
					while (lu != null) {
						String[] tab = lu.split(";");
						String nomJoueur = tab[0];
						String nomVaisseau = tab[1];
						Joueur joueur = new Joueur(nomJoueur, nomVaisseau);
						partie.getJoueurs().add(joueur);
					}
				} else if (lu.startsWith("PARTIES_DISPONIBLES")) {
					System.out.println("Client recoit :");
					System.out.println(lu);
					String[] tab = lu.split(";");
					List<Partie> partiesDispos = new ArrayList();
					for (int i = 1; i < tab.length; i += 2) {
						System.out.println("Tab" + i + " :" + tab[i]);
						System.out.println("Tab" + i + "+1 :" + tab[i + 1]);

						Partie p = new Partie(new Joueur(tab[i]), Integer.parseInt(tab[i + 1]));
						partiesDispos.add(p);
					}
					MenuController.ecranRejoindreUnePartie(new RejoindreUnePartieJPanel(partie, partiesDispos));
				} else if (lu.startsWith("PARTIE_REJOINTE_OK")) {
					System.out.println("Client : PARTIE_REJOINTE_OK reçu du serveur");
					out.println("MON_CHOIX_VAISSEAU" + ";" + partie.getJoueurs().get(0).getVaisseau().getNom());
					out.flush();
				}

				else if (lu.startsWith("DEMARRER_PARTIE")) {
					System.out.println("Client recoit : " + lu);
					String[] tab = lu.split(";");
					joueur = partie.getJoueurs().get(0);
					partie.getJoueurs().clear();
					System.out.println("Génération des joueurs:"); 
					for (int i = 1; i < tab.length; i = i + 2) {
						String nomJoueur = tab[i];
						String nomVaisseau = tab[i+1];
						Joueur joueurEnCours = new Joueur(nomJoueur);
						if (nomVaisseau.equals("Vaisseau1")) {
							joueurEnCours.setVaisseau(new Vaisseau1());
						}else if (nomVaisseau.equals("Vaisseau2")) {
							joueurEnCours.setVaisseau(new Vaisseau2());
						}else if (nomVaisseau.equals("Vaisseau3")) {
							joueurEnCours.setVaisseau(new Vaisseau3());
						}else if (nomVaisseau.equals("Vaisseau4")) {
							joueurEnCours.setVaisseau(new Vaisseau4());
						}else if (nomVaisseau.equals("Vaisseau5")) {
							joueurEnCours.setVaisseau(new Vaisseau5());
						}else if (nomVaisseau.equals("Vaisseau6")) {
							joueurEnCours.setVaisseau(new Vaisseau6());
						}else if (nomVaisseau.equals("Vaisseau7")) {
							joueurEnCours.setVaisseau(new Vaisseau7());
						}else if (nomVaisseau.equals("Vaisseau8")) {
							joueurEnCours.setVaisseau(new Vaisseau8());
						}else if (nomVaisseau.equals("Vaisseau9")) {
							joueurEnCours.setVaisseau(new Vaisseau9());
						}
						partie.getJoueurs().add(joueurEnCours);
						if (nomJoueur.equals(joueur.getNom())) {
							joueur=joueurEnCours;
						}
						System.out.println(nomJoueur+ " - "+nomVaisseau);
						System.out.println(joueurEnCours.getNom() + " -> "+ joueurEnCours.getVaisseau().getNom());
					}
					System.out.println();
					System.out.println(joueur.getNom()+"--"+joueur.getVaisseau().getNom());
					MenuController.ecranJeuMulti(new FenetreJeuMulti(partie, joueur), partie, joueur);
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

	protected void finalize() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Affiche la partie choisie
	 * @param nomPartie
	 */
	public void envoiChoixPartie(String nomPartie) {
		System.out.println("Client envoie : REJOINDRE_PARTIE;" + nomPartie);
		out.println("REJOINDRE_PARTIE" + ";" + nomPartie + ";" + partie.getJoueurs().get(0).getNom());
		out.flush();
	}

	/**
	 * Affiche la partie créée
	 * @param partie
	 */
	public void envoiCreationPartie(Partie partie) {
		System.out.println("Client envoie : CREATION_PARTIE" + ";" + partie.getCreateur().getNom() + ";"
				+ partie.getCreateur().getNom() + ";" + partie.getNbPlaces());
		out.println("CREATION_PARTIE" + ";" + partie.getCreateur().getNom() + ";" + partie.getCreateur().getNom() + ";"
				+ partie.getNbPlaces());
		out.flush();
	}

	/**
	 * Affiche le démarrage de la partie
	 * @param partie
	 * @param joueur
	 */
	public void envoiDemarrerPartie(Partie partie, Joueur joueur) {
		System.out.println("Client envoie : DEMARRER_PARTIE");
		out.println("DEMARRER_PARTIE");
		out.flush();
	}

	/**
	 * @return the partie
	 */
	public Partie getPartie() {
		return partie;
	}

	/**
	 * @param partie the partie to set
	 */
	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	
	
}