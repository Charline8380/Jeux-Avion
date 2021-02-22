/**
 * 
 */
package fr.afpa.cda.group4.projet.avion.app.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.afpa.cda.group4.projet.avion.app.autre.factory.ConnexionFactory;
import fr.afpa.cda.group4.projet.avion.app.dao.IJoueurDao;
import fr.afpa.cda.group4.projet.avion.app.modelDo.JouerDo;
import fr.afpa.cda.group4.projet.avion.app.modelDo.JoueurDo;
import fr.afpa.cda.group4.projet.avion.app.modelDo.ScoreDo;

/**
 * @author Charline
 *
 */
public class JoueurDao implements IJoueurDao {

    /**
     * Constructeur par defaut
     */
    public JoueurDao() {
        super();
    }

    /**
     * Permet de mapper un joueur avec un beanDO
     * 
     * @param resultSet
     * @return le joueur recupéré
     * @throws SQLException
     */
    private JoueurDo mapToJoueur(final ResultSet resultSet) throws SQLException {
        final JoueurDo joueur = new JoueurDo();
        joueur.setId_joueur(resultSet.getInt(1));
        joueur.setNom_profil(resultSet.getString(2));
        joueur.setMot_de_passe(resultSet.getString(3));

        return joueur;
    }

    /**
     * Permet de mapper un jouer avec un beanDO
     * 
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private JouerDo mapToJouer(final ResultSet resultSet) throws SQLException {

        final JouerDo jouer = new JouerDo();
        final ScoreDao scoreDao = new ScoreDao();
        jouer.setId_jouer(resultSet.getInt(1));
        jouer.getJoueur().setId_joueur(resultSet.getInt(2));
        jouer.getScore().setId_score(resultSet.getInt(3));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            jouer.setDate_jeu(format.parse(resultSet.getString(4)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        final JoueurDo joueur = findJoueurById(jouer.getJoueur().getId_joueur());
        final ScoreDo score = scoreDao.findScoreById(jouer.getScore().getId_score());
        jouer.setJoueur(joueur);
        jouer.setScore(score);

        return jouer;
    }

    @Override
    public JoueurDo createJoueur(final String login, final String motDePasse) {

        final Connection connection = ConnexionFactory.getConnection();
        final String query = "insert into joueur (nom_profil, mot_de_passe) VALUES(?,?); select currval('joueur_id_joueur_seq');";
        final PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, motDePasse);

            System.out.println(preparedStatement.execute());

            int nInserted = preparedStatement.getUpdateCount();
            if (nInserted == 1 && preparedStatement.getMoreResults()) {
                ResultSet rs = preparedStatement.getResultSet();
                final JoueurDo joueur = new JoueurDo();
                if (rs.next()) {
                    joueur.setId_joueur(rs.getInt(1));
                    joueur.setNom_profil(login);
                    joueur.setMot_de_passe(motDePasse);
                }
                return joueur;
            }

        } catch (final SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    @Override
    public JoueurDo findJoueurById(final Integer idJoueur) {
        final Connection connection = ConnexionFactory.getConnection();
        final String requete = "select id_joueur, nom_profil, mot_de_passe  from Joueur where id_joueur = ? ";
        final PreparedStatement preparedStatement;
        final ResultSet resultSet;
        JoueurDo joueur = null;

        try {
            preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setInt(1, idJoueur);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                joueur = mapToJoueur(resultSet);
                return joueur;
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public JoueurDo findJoueurByLoginEtMdp(final String login, final String motDePasse) {
        final Connection connection = ConnexionFactory.getConnection();
        final String requete = "select id_joueur, nom_profil,mot_de_passe  from Joueur where nom_profil = ? and mot_de_passe = ?  ";
        final PreparedStatement preparedStatement;
        final ResultSet resultSet;
        JoueurDo joueur = null;

        try {
            preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, motDePasse);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                joueur = mapToJoueur(resultSet);
                return joueur;
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<JoueurDo> findAllJoueurs() {
        final Connection connection = ConnexionFactory.getConnection();
        final String requete = "select id_joueur, nom_profil,mot_de_passe  from Joueur";
        final List<JoueurDo> listeJoueur = new ArrayList<>();

        try (final PreparedStatement preparedStatement = connection.prepareStatement(requete);
             final ResultSet resultSet = preparedStatement.executeQuery()) {

            // on mappe les resultats
            while (resultSet.next()) {
                final JoueurDo joueur = mapToJoueur(resultSet);
                listeJoueur.add(joueur);
            }
            return listeJoueur;

        } catch (final SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    @Override
    public List<JouerDo> findAllBestJoueursEtScores() {
        final Connection connection = ConnexionFactory.getConnection();
        final String query = "select id_jouer, id_joueur, id_score, date_jeu from jouer;";
        final List<JouerDo> listeJouer = new ArrayList<>();

        final PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(query.toString());
            final ResultSet resultSet = preparedStatement.executeQuery();

            // on mappe les resultats
            while (resultSet.next()) {
                final JouerDo jouer = mapToJouer(resultSet);
                listeJouer.add(jouer);
            }
            //trier la liste par ordre decroissant des scores
            Collections.sort(listeJouer);
            Collections.reverse(listeJouer);
            return listeJouer;
        } catch (final SQLException sqlExcpetion) {
            sqlExcpetion.printStackTrace();
        }

        return null;
    }

    @Override
    public void insererJouer(final JouerDo jouerDo) {

        final String query = "insert into jouer (id_joueur, id_score,date_jeu) VALUES(?,?,?); select currval('jouer_id_jouer_seq');";
        final PreparedStatement preparedStatement;

        try {
            preparedStatement = ConnexionFactory.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, jouerDo.getJoueur().getId_joueur());
            preparedStatement.setInt(2, jouerDo.getScore().getId_score());
            preparedStatement.setTimestamp(3, new Timestamp(jouerDo.getDate_jeu().getTime()));

            System.out.println(preparedStatement.execute());

            int nInserted = preparedStatement.getUpdateCount();
            if (nInserted == 1 && preparedStatement.getMoreResults()) {
                ResultSet rs = preparedStatement.getResultSet();
                if (rs.next()) {
                    jouerDo.setId_jouer(rs.getInt(1));
                }
            }

        } catch (final SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public boolean updateJoueur(final Integer idJoueur, final JoueurDo joueur) {
        final Connection connection = ConnexionFactory.getConnection();
        final String query = "update Joueur set nom_profil=?, mot_de_passe=? where id_joueur = ?";

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, joueur.getNom_profil());
            preparedStatement.setString(2, joueur.getMot_de_passe());
            preparedStatement.setInt(3, idJoueur);
            final int result = preparedStatement.executeUpdate();

            joueur.setId_joueur(idJoueur);

            // on n'attend qu'un seul résultat
            return result == 1;
        } catch (final SQLException sqlExcpetion) {
            sqlExcpetion.printStackTrace();
        }
        return false;
    }

}
