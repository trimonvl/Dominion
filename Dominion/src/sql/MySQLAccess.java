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

import card.*;
import game.Game;
import player.Player;

public class MySQLAccess {
        
  private Connection connect = null;
  private Statement statement = null;
  private PreparedStatement preparedStatement = null;
  private PreparedStatement preparedStatement2 = null;
  private PreparedStatement preparedStatement3 = null;
  private ResultSet resultSet = null;
  private ResultSet resultSet2 = null;
  private ResultSet resultSet3 = null;
  
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

public void insertPlayer(String playerName) throws Exception {
    
	Class.forName("com.mysql.jdbc.Driver");

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

public void saveCardsInHand(Game currentGame) throws Exception {
    
	Class.forName("com.mysql.jdbc.Driver");

    // Setup the connection with the DB
    connect = DriverManager
        .getConnection("jdbc:mysql://" + host + "/Dominion?"
            + "user=" + user + "&password=" + passwd );
    	
		String getCurrentPlayerIdSQL = "SELECT id FROM players WHERE name = ?";
		String saveCards = "INSERT INTO cardsinhands(player_id, card_id) VALUES(?,?)";

		for(int i = 0; i < currentGame.getPlayersArray().size(); i++){
			Player player = currentGame.getPlayersArray().get(i);
			String playerName = player.getName();
			
			
			try {
				preparedStatement = connect.prepareStatement(getCurrentPlayerIdSQL);
				preparedStatement.setString(1, playerName);
			    resultSet = preparedStatement.executeQuery();
			   
			    while(resultSet.next()){
			    	int playerId = resultSet.getInt("id");
			    	for(int in = 0; in < player.getHand().size(); in++){
			    		int cardId = player.getHand().get(in).getID();
			    		
						preparedStatement = connect.prepareStatement(saveCards);
						preparedStatement.setInt(1, playerId);
						preparedStatement.setInt(2, cardId);
					    preparedStatement.executeUpdate();
			    	}
			    }
			}
			    catch (SQLException e) {

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
}

public Card[] extractCards() throws Exception {
	int i = 0;
	Card[] cardArray = new Card[17];
	Class.forName("com.mysql.jdbc.Driver");
    // Setup the connection with the DB
    connect = DriverManager
        .getConnection("jdbc:mysql://" + host + "/Dominion?"
            + "user=" + user + "&password=" + passwd );

	String extractTreasureCardsSQL = "SELECT * FROM cards WHERE card_id IN (1,2,3)";
	String extractVictoryCardsSQL = "SELECT * FROM cards WHERE card_id IN (4,5,6,7)";
	String extractCardsSQL = "SELECT * FROM cards WHERE card_id >= 8 ORDER BY RAND() DESC LIMIT 10 ";


		try {
			preparedStatement = connect.prepareStatement(extractTreasureCardsSQL);
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
		    	cardArray[i] = new Treasure(cardId, cardName, cardText, cardCost, cardType, cardDraw, cardBonusGold
		    			, cardBonusAction, cardBonusBuy, cardVictoryPoints);
		    	i += 1;

		    }
		    
			preparedStatement2 = connect.prepareStatement(extractVictoryCardsSQL);
		    resultSet2 = preparedStatement2.executeQuery();

		    while(resultSet2.next()){
		    	int cardId = resultSet2.getInt("card_id");
		    	String cardName = resultSet2.getString("name");
		    	System.out.println(cardName);
		    	String cardText = resultSet2.getString("text");
		    	int cardCost = resultSet2.getInt("cost");
		    	String cardType = resultSet2.getString("type");
		    	int cardDraw = resultSet2.getInt("draw_card");
		    	int cardBonusGold = resultSet2.getInt("give_gold");
		    	int cardBonusAction = resultSet2.getInt("extra_action");
		    	int cardBonusBuy = resultSet2.getInt("extra_buy");
		    	int cardVictoryPoints = resultSet2.getInt("Victory_points");
		    	cardArray[i] = new Victory(cardId, cardName, cardText, cardCost, cardType, cardDraw, cardBonusGold
		    			, cardBonusAction, cardBonusBuy, cardVictoryPoints);
		    	i += 1;

		    }

			preparedStatement3 = connect.prepareStatement(extractCardsSQL);
		    resultSet3 = preparedStatement3.executeQuery();

		    while(resultSet3.next()){
		    	int cardId = resultSet3.getInt("card_id");
		    	String cardName = resultSet3.getString("name");
		    	System.out.println(cardName);
		    	String cardText = resultSet3.getString("text");
		    	int cardCost = resultSet3.getInt("cost");
		    	String cardType = resultSet3.getString("type");
		    	int cardDraw = resultSet3.getInt("draw_card");
		    	int cardBonusGold = resultSet3.getInt("give_gold");
		    	int cardBonusAction = resultSet3.getInt("extra_action");
		    	int cardBonusBuy = resultSet3.getInt("extra_buy");
		    	int cardVictoryPoints = resultSet3.getInt("Victory_points");
		    	cardArray[i] = new Action(cardId, cardName, cardText, cardCost, cardType, cardDraw, cardBonusGold
		    			, cardBonusAction, cardBonusBuy, cardVictoryPoints);
		    	i += 1;

		    }
		    
		}
		
		catch (Exception e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (connect != null) {
				connect.close();
			}

		}
		return cardArray;

}


public void insertPlayerInGame() throws Exception {
    
    // Setup the connection with the DB
	Class.forName("com.mysql.jdbc.Driver");

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

public void insertGame() throws Exception {
    
	Class.forName("com.mysql.jdbc.Driver");

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