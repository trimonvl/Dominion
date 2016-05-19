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

public void insertPlayer() throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO players"
				+ "(id, name) VALUES"
				+ "(?,?)";

		try {
			preparedStatement = connect.prepareStatement(insertTableSQL);

			preparedStatement.setInt(1, 1);
			preparedStatement.setString(2, "Test");

			// execute insert SQL statement
			preparedStatement.executeUpdate();

			System.out.println("Record is inserted into player table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
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