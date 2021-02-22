package fr.afpa.cda.group.projet.avionServeur.serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.afpa.cda.group.projet.avionServeur.model.Vaisseau;

class ThreadJoueur extends Thread {
	private BufferedReader in;
	private PrintWriter out;
	private Server serveur;
	private Partie partie;
	private Integer idJoueur;
	private String vaisseauName;

	private String pseudo;

	/**
	 * Constructeur
	 * 
	 * @param socket
	 * @param s
	 * @throws IOException
	 */
	public ThreadJoueur(final Socket socket, final Server s) throws IOException {

		final SimpleDateFormat dateFormat = new SimpleDateFormat("hhmmssS");
		pseudo = "ANONYME" + dateFormat.format(new Date());
		out = new PrintWriter(socket.getOutputStream());
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		serveur = s;
		this.vaisseauName = "";
	}

	@Override
	public void run() {
		System.out.println("J'entre dans le run()");
		String message = null;
		String reply = null;
		try {

			System.out.println("Serveur : Connexion OK");
			envoyer("CONNEXION_OK");

			while (true) {
				reply = in.readLine();
				final String[] separationReply = reply.split(";");
				System.out.println("Message reçu :" + reply);
				if (separationReply[0].equalsIgnoreCase("CREATION_PARTIE")) {
					System.out.println("Serveur : reçu " + reply);
					this.partie = serveur.ajouterUneNouvellePartie(this);
					this.partie.setNom(separationReply[1]);
					this.pseudo = separationReply[2];
					this.partie.setNbPlaces(Integer.parseInt(separationReply[3]));
					message = "PARTIE_CREE_OK:" + partie.getIdPartie();
					envoyer(message);

				}
				if (separationReply[0].equalsIgnoreCase("MON_CHOIX_VAISSEAU")) {
					System.out.println("Serveur : reçu " + reply);
					this.vaisseauName = separationReply[1];
					message = "CHOIX_VAISSEAU_OK" + ";";
//					for (ThreadJoueur threadJoueur : this.partie.getJoueurs()) {
//						message += threadJoueur.getPseudo() + ";" + threadJoueur.getVaisseau() + ";";
//					}
					envoyer(message);
					///////////////////
//					message = "MAJ_PARTICIPANTS" + ";";
//					for (ThreadJoueur threadJoueur : this.partie.getJoueurs()) {
//						message += threadJoueur.getPseudo() + ";" + threadJoueur.getVaisseau() + ";";
//					}
//					partie.EnvoyerUneCommandeATous(message);
					//////////////////
				}
				if (reply.startsWith("LIST_PARTIE")) {
					System.out.println("Serveur recoit :" + reply);
//					Partie p0 = new Partie();
//					p0.setNom("Partie fictive Toto0");
//					Partie p1 = new Partie();
//					p1.setNom("Partie fictive Toto1");
//					p1.setNbPlaces(3);
//					serveur.parties.add(p0);
//					serveur.parties.add(p1);
					message = "PARTIES_DISPONIBLES;";
					for (final Partie p : serveur.parties) {
						message = message + p.getNom() + ";" + (p.getNbPlaces() - p.getJoueurs().size() + ";");
					}
					envoyer(message);
				}
				if (reply.startsWith("REJOINDRE_PARTIE")) {
					System.out.println("Serveur recoit :" + reply);
					for (Partie partie : serveur.parties) {
						if (partie.getNom().equals(separationReply[1])) {
							this.pseudo = separationReply[2];
							partie.getJoueurs().add(this);
							System.out.println(
									"Serveur : " + this.pseudo + " enregistré dans partie nommée " + partie.getNom());
							this.partie = partie;
							message = "PARTIE_REJOINTE_OK";
						}
					}
					envoyer(message);
				}
				if (separationReply[0].equalsIgnoreCase("MAJ_PARTICIPANTS")) {
					System.out.println("Serveur : reçu " + reply);
					message = "MAJ_PARTICIPANTS" + ";";
					for (ThreadJoueur threadJoueur : this.partie.getJoueurs()) {
						message += threadJoueur.getPseudo() + ";" + threadJoueur.getVaisseau() + ";";
					}
					partie.EnvoyerUneCommandeATous(message);
				}
				if (reply.startsWith("DEMARRER_PARTIE")) {
					message = "DEMARRER_PARTIE" + ";";
					for (ThreadJoueur threadJoueur : this.partie.getJoueurs()) {
						message += threadJoueur.getPseudo() + ";" + threadJoueur.getVaisseau() + ";";
					}
					partie.EnvoyerUneCommandeATous(message);
				}

				if (separationReply[0].equalsIgnoreCase("CMD_MOVE_UP")) {
					this.partie = serveur.ajouterUneNouvellePartie(this);
					this.pseudo = separationReply[1];
					message = "PARTIE_CREE_OK:" + partie.getIdPartie();
					partie.EnvoyerUneCommandeATous("MAJ_DIPLAY MOVE_UP_TOTO");
				}

			}

		} catch (final IOException e) {

		}

	}

	/**
	 * Permet de renvoyer la main au client
	 * 
	 * @param s
	 */
	public void envoyer(final String s) {
		System.out.println("Serveur envoie :" + s);
		out.println(s);
		out.flush();
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(final String pseudo) {
		this.pseudo = pseudo;
	}

	public Server getServeur() {
		return serveur;
	}

	public void setServeur(final Server serveur) {
		this.serveur = serveur;
	}

	public Partie getPartie() {
		return partie;
	}

	public void setPartie(final Partie partie) {
		this.partie = partie;
	}

	public Integer getIdJoueur() {
		return idJoueur;
	}

	public void setIdJoueur(final Integer idJoueur) {
		this.idJoueur = idJoueur;
	}

	public String getVaisseau() {
		return vaisseauName;
	}

	public void setVaisseau(final String vaisseau) {
		this.vaisseauName = vaisseau;
	}

}
