package fr.afpa.cda.group.projet.avionServeur.app;

import java.io.IOException;

import fr.afpa.cda.group.projet.avionServeur.serveur.Server;

/**
 * @author Charline
 *
 */
public class App {
    
    public static void main(final String[] args) throws IOException {
        int port = 8888;
        if (args.length == 1)
            port = Integer.parseInt(args[0]);
        Server serveur = new Server(port);
        serveur.lancerTraitement();
    }
    
}
