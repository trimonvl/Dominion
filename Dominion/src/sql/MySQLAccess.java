package sql;
// Adapted from http://www.vogella.com/tutorials/MySQLJava/article.html
//Hier kan je sql statements aanmaken om aan te spreken in andere klassen
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class MySQLAccess {
        
  private Connection connect = null;
  private Statement statement = null;
  private PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;

  final private String host = "localhost";
  final private String user = "root";
  final private String passwd = "root";
  public void readDataBase() throws Exception {
    try {
      // This will load the MySQL driver, each DB has its own driver
      Class.forName("com.mysql.jdbc.Driver");
      
      // Setup the connection with the DB
      connect = DriverManager
          .getConnection("jdbc:mysql://" + host + "/Dominion?"
              + "user=" + user + "&password=" + passwd );

      // Statements allow to issue SQL queries to the database
      statement = connect.createStatement();

      preparedStatement = connect
          .prepareStatement("SELECT * from Dominion.card");
      resultSet = preparedStatement.executeQuery();
      writeResultSet(resultSet);
    } catch (Exception e) {
      throw e;
    } finally {
      close();
    }

  }


  private void writeResultSet(ResultSet resultSet) throws SQLException {
    // ResultSet is initially before the first data set
    while (resultSet.next()) {
      // It is possible to get the columns via name
      // also possible to get the columns via the column number
      // which starts at 1
      // e.g. resultSet.getSTring(2);
      int card_id = resultSet.getInt("card_id");
      String name = resultSet.getString("name");
      String text = resultSet.getString("text");
      String type = resultSet.getString("type");
      System.out.println("card id " + card_id);
      System.out.println("name " + name);
      System.out.println("text : " + text);
      System.out.println("type: " + type);
    }
  }

public void insertPlayer(String playerName) throws SQLException {
    
    // Setup the connection with the DB
    connect = DriverManager
        .getConnection("jdbc:mysql://" + host + "/Dominion?"
            + "user=" + user + "&password=" + passwd );

		String insertTableSQL = "INSERT INTO players(name) VALUES(?)";

		try {
			preparedStatement = connect.prepareStatement(insertTableSQL);
			preparedStatement.setString(1, playerName);
			
			// execute insert SQL statement
			preparedStatement.executeUpdate();

			System.out.println("Record is inserted into player table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (connect != null) {
				connect.close();
			}

		}
}

public void extractRandomCards() throws SQLException {
    
    // Setup the connection with the DB
    connect = DriverManager
        .getConnection("jdbc:mysql://" + host + "/Dominion?"
            + "user=" + user + "&password=" + passwd );

	String extractCardsSQL = "SELECT * FROM cards WHERE card_id >= 8 ORDER BY RAND() DESC LIMIT 10 ";

		try {
			preparedStatement = connect.prepareStatement(extractCardsSQL);
		    resultSet = preparedStatement.executeQuery();
		    while(resultSet.next()){
		    	int cardId = resultSet.getInt("card_id");
		    	String cardName = resultSet.getString("name");
		    	System.out.println(cardName);
		    	String cardText = resultSet.getString("text");
		    	int cardCost = resultSet.getInt("cost");
		    	String cardType = resultSet.getString("type");
		    	int cardDraw = resultSet.getInt("draw_card");
		    	int cardBonusGold = resultSet.getInt("give_gold");
		    	int cardBonusAction = resultSet.getInt("extra_action");
		    	int cardBonusBuy = resultSet.getInt("extra_buy");
		    	int cardVictoryPoints = resultSet.getInt("Victory_points");

		    }
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (connect != null) {
				connect.close();
			}

		}
}


public void insertPlayerInGame() throws SQLException {
    
    // Setup the connection with the DB
    connect = DriverManager
        .getConnection("jdbc:mysql://" + host + "/Dominion?"
            + "user=" + user + "&password=" + passwd );

		String extractPlayerSQL = "SELECT id FROM players ORDER BY ID DESC LIMIT 1";
		String extractGameSQL = "SELECT id FROM games ORDER BY ID DESC LIMIT 1";
		String insertPlayerInGameSQL = "INSERT INTO playersingames(game_id, player_id) VALUES(?, ?)";
		//String extractGameSQL = "SELECT LAST(id) FROM games";
		try {
			preparedStatement = connect.prepareStatement(extractPlayerSQL);
		    resultSet = preparedStatement.executeQuery();
		    resultSet.next();
		    int playerId = resultSet.getInt("id");
			System.out.println("Record is extracted out of player table!");
			System.out.println(playerId);
			
			preparedStatement = connect.prepareStatement(extractGameSQL);
		    resultSet = preparedStatement.executeQuery();
		    resultSet.next();
		    int gameId = resultSet.getInt("id");
			System.out.println("Record is extracted out of games table!");
			System.out.println(gameId);
			
			preparedStatement = connect.prepareStatement(insertPlayerInGameSQL);
			preparedStatement.setInt(1, gameId);
			preparedStatement.setInt(2, playerId);

			// execute insert SQL statement
			preparedStatement.executeUpdate();

			System.out.println("PlayerId and GameId inserted!");
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (connect != null) {
				connect.close();
			}

		}
}

public void insertGame() throws SQLException {
    
    // Setup the connection with the DB
    connect = DriverManager
        .getConnection("jdbc:mysql://" + host + "/Dominion?"
            + "user=" + user + "&password=" + passwd );

		String insertTableSQL = "INSERT INTO games() VALUES()";

		try {
			preparedStatement = connect.prepareStatement(insertTableSQL);

			// execute insert SQL statement
			preparedStatement.executeUpdate();

			System.out.println("Record is inserted into games table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (connect != null) {
				connect.close();
			}

		}
}

  
  // You need to close the resultSet
  private void close() {
    try {
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connect != null) {
        connect.close();
      }
    } catch (Exception e) {

    }
  }

}