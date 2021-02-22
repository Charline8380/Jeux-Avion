/**
 * 
 */
package fr.afpa.cda.group4.projet.avion.app.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.afpa.cda.group4.projet.avion.app.autre.factory.ConnexionFactory;
import fr.afpa.cda.group4.projet.avion.app.dao.IScoreDao;
import fr.afpa.cda.group4.projet.avion.app.modelDo.ScoreDo;

/**
 * @author Charline
 *
 */
public class ScoreDao implements IScoreDao {

    /**
     * Constructeur par defaut
     */
    public ScoreDao() {
        super();
    }

    /**
     * Permet de mapper un score
     * 
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private ScoreDo mapToScore(final ResultSet resultSet) throws SQLException {
        final ScoreDo score = new ScoreDo();
        score.setId_score(resultSet.getInt(1));
        score.setNb_points_score(resultSet.getInt(2));
        return score;
    }

    @Override
    public ScoreDo createScore(final ScoreDo score) {

        final Connection connection = ConnexionFactory.getConnection();
        final String sqlQuery = "INSERT into score (nb_points_score) VALUES(?);select currval('score_id_score_seq');";
        final PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, score.getNb_points_score());

            System.out.println(preparedStatement.execute());

            int nInserted = preparedStatement.getUpdateCount();
            if (nInserted == 1 && preparedStatement.getMoreResults()) {
                ResultSet rs = preparedStatement.getResultSet();
                if (rs.next()) {
                    score.setId_score(rs.getInt(1));
                }
                return score;
            }

            //            final int result = preparedStatement.executeUpdate();
            //
            //            // on n'attend qu'un seul résultat
            //            if (result == 1) {
            //                // pour récupérer l'id généré
            //                try (final ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
            //                    resultSet.first();
            //                    score.setId_score(resultSet.getInt(1));
            //                    System.out.println(score);
            //                }
            //                return score;
            //            }
        } catch (final SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    @Override
    public ScoreDo findScoreById(final Integer idScore) {

        final Connection connection = ConnexionFactory.getConnection();
        final String requete = "select id_score, nb_points_score   from Score where id_score = ? ";
        final PreparedStatement preparedStatement;
        final ResultSet resultSet;
        ScoreDo score = null;

        try {
            preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setInt(1, idScore);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                score = mapToScore(resultSet);
                return score;
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return score;
    }

    @Override
    public List<ScoreDo> findAllScore() {
        final Connection connection = ConnexionFactory.getConnection();
        final String requete = "select id_score, nb_points_score  from Score";
        final List<ScoreDo> listeScore = new ArrayList<>();

        try (final PreparedStatement preparedStatement = connection.prepareStatement(requete.toString());
             final ResultSet resultSet = preparedStatement.executeQuery()) {

            // on mappe les resultats
            while (resultSet.next()) {
                final ScoreDo score = mapToScore(resultSet);
                listeScore.add(score);
            }
            return listeScore;
        } catch (final SQLException sqlExcpetion) {
            sqlExcpetion.printStackTrace();
        }
        return new ArrayList<>();
    }
    
    public ScoreDo findIdByScore(final Integer score) {

        final Connection connection = ConnexionFactory.getConnection();
        final String requete = "select id_score, nb_points_score   from Score where nb_points_score = ? ";
        final PreparedStatement preparedStatement;
        final ResultSet resultSet;
        ScoreDo scoreDo = null;

        try {
            preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setInt(1, score);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                scoreDo = mapToScore(resultSet);
                return scoreDo;
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return scoreDo;
    }

}
