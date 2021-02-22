package fr.afpa.cda.group4.projet.avion.app.autre.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 * @author 
 *
 */
public class ConnexionFactory {

	private static Connection connection;

	/**
	 * Connexion à la base de données
	 * @return Connection
	 */
	public static Connection getConnection() {

		if (ConnexionFactory.connection == null) {
			String url = "jdbc:postgresql://localhost:5432/avion";
			try {
				Class.forName("org.postgresql.Driver");
				System.out.println("Pilote chargé");

				Properties props = new Properties();
				props.setProperty("user", "postgres");
				props.setProperty("password", "root");
				ConnexionFactory.connection = DriverManager.getConnection(url, props);
				return connection;
			} catch (ClassNotFoundException e) {
				System.out.println("Impossible de se charger le driver");
				System.exit(-1);
			} catch (SQLException e) {
				System.out.println("Impossible de se connecter à l’url : " + url);
				System.exit(-1);
			}

		}

		return connection;
	}

}
