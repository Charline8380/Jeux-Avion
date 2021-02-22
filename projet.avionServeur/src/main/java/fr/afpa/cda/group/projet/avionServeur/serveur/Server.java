package fr.afpa.cda.group.projet.avionServeur.serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 
 * @author 
 *
 */
public class Server {
    
    List<Partie>         parties;
    private Integer      compteurPartie = 0;
    private ServerSocket serverSocket;

    /**
     * Constructeur
     * 
     * @param port
     */
    public Server(final int port) {
        parties = new CopyOnWriteArrayList<Partie>();
        try {
            serverSocket = new ServerSocket(port);

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     * Permet de lancer lancer le serveur
     * 
     * @throws IOException
     */
    public void lancerTraitement() throws IOException {
    	System.out.println("Serveur ouvert sur le port "+serverSocket.getLocalPort());
        System.out.println("En attente de connexion...");
        while (true) {
            final Socket socket = getServerSocket().accept();
            System.out.println("Nouvelle connexion de " + socket.getPort()+ "-"+socket.getInetAddress());
            final ThreadJoueur joueur = new ThreadJoueur(socket, this);
            joueur.start();
            //this.enregistrerJoueurDansUnePartie(joueur.getIdJoueur(), joueur);
            //joueur.start();
        } 
    }

    //	synchronized public void EnvoyerA(String pseudo, String message) {
    //
    //		for (ThreadClient client : parties.geclients) {
    //			if (client.getPseudo().equalsIgnoreCase(pseudo)) {
    //				client.envoyer(message);
    //			}
    //		}
    //	}

    /**
     * Permet d'ajouter une nouvelle partie à la liste des parties
     * 
     * @param joueurCreateur
     * @return la liste des parties
     */
    public Partie ajouterUneNouvellePartie(final ThreadJoueur joueurCreateur) {
        final Partie partie = new Partie();
        partie.setIdPartie(this.parties.size());
        partie.setCreateur(joueurCreateur);
        partie.getJoueurs().add(joueurCreateur);
        parties.add(partie);
        return partie;
    }

    /**
     * Permet d'enregistrer un joueur dans une partie
     * 
     * @param idPartie
     * @param joueur
     */
    public void enregistrerJoueurDansUnePartie(final Integer idPartie, final ThreadJoueur joueur) {
        System.out.println(parties);
        System.out.println(idPartie);
        System.out.println(joueur);
        parties.get(idPartie).getJoueurs().add(joueur);
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(final ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public Integer getCompteurPartie() {
        return compteurPartie;
    }

    public void setCompteurPartie(final Integer compteurPartie) {
        this.compteurPartie = compteurPartie;
    }
    
}
