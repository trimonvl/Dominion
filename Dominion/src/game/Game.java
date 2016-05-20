package game;
import java.sql.SQLException;
import java.util.ArrayList;

import card.*;
import player.*;
import sql.MySQLAccess;
public class Game {
	private ArrayList<Player> players = new ArrayList<Player>();
	Player currentPlayer = null;
	FieldCards[] cardsInField = new FieldCards[17];
	ArrayList<Card> startingCards = new ArrayList<Card>();
	Command command = new Command();
	public Game(){
		startingCards.add(new Treasure(1, "copper", "", 0, "Treasure", 0, 1, 0, 0, 0));
		startingCards.add(new Treasure(1, "copper", "", 0, "Treasure", 0, 1, 0, 0, 0));
		startingCards.add(new Treasure(1, "copper", "", 0, "Treasure", 0, 1, 0, 0, 0));
		startingCards.add(new Treasure(1, "copper", "", 0, "Treasure", 0, 1, 0, 0, 0));
		startingCards.add(new Treasure(1, "copper", "", 0, "Treasure", 0, 1, 0, 0, 0));
		startingCards.add(new Treasure(1, "copper", "", 0, "Treasure", 0, 1, 0, 0, 0));
		startingCards.add(new Treasure(1, "copper", "", 0, "Treasure", 0, 1, 0, 0, 0));
		startingCards.add(new Victory(4, "estate", "", 2, "Victory", 0, 0, 0, 0, 1));
		startingCards.add(new Victory(4, "estate", "", 2, "Victory", 0, 0, 0, 0, 1));
		startingCards.add(new Victory(4, "estate", "", 2, "Victory", 0, 0, 0, 0, 1));
	}
	public void addCardsToGame()
	{	
		cardsInField[0] = new FieldCards(new Treasure(1, "Copper", "", 0, "Treasure", 0, 1, 0, 0,0), 60);
		cardsInField[1] = new FieldCards(new Treasure(2, "Silver", "", 3, "Treasure", 0, 2, 0, 0,0), 40);
		cardsInField[2] = new FieldCards(new Treasure(3, "Gold", "", 6, "Treasure", 0, 3, 0, 0,0), 30);
		
		switch(players.size())
		{
		case 2:
			cardsInField[3] = new FieldCards(new Victory(4, "Estate", "", 2, "Victory", 0, 0, 0, 0, 1), 8);
			cardsInField[4] = new FieldCards(new Victory(5, "Duchy", "", 5, "Victory", 0, 0, 0, 0, 3), 8);
			cardsInField[5] = new FieldCards(new Victory(6, "Province", "", 8, "Victory", 0, 0, 0, 0, 6), 8);
			cardsInField[6] = new FieldCards(new Action(7, "Curse", "", 0, "Curse", 0, 0, 0, 0, -1), 10);
			break;
		case 3:
			cardsInField[3] = new FieldCards(new Victory(4, "Estate", "", 2, "Victory", 0, 0, 0, 0, 1), 12);
			cardsInField[4] = new FieldCards(new Victory(5, "Duchy", "", 5, "Victory", 0, 0, 0, 0, 3), 12);
			cardsInField[5] = new FieldCards(new Victory(6, "Province", "", 8, "Victory", 0, 0, 0, 0, 6), 12);
			cardsInField[6] = new FieldCards(new Action(7, "Curse", "", 3, "Curse", 0, 0, 0, 0, -1), 20);
			break;
		case 4:
			cardsInField[3] = new FieldCards(new Victory(4, "Estate", "", 2, "Victory", 0, 0, 0, 0, 1), 12);
			cardsInField[4] = new FieldCards(new Victory(5, "Duchy", "", 5, "Victory", 0, 0, 0, 0, 3), 12);
			cardsInField[5] = new FieldCards(new Victory(6, "Province", "", 8, "Victory", 0, 0, 0, 0, 6), 12);
			cardsInField[6] = new FieldCards(new Action(7, "Curse", "", 3, "Curse", 0, 0, 0, 0, -1), 30);
			break;
		}

		cardsInField[7] = new FieldCards(new Action(8, "Cellar", "", 2, "Action", 0, 0, 1, 0, 0), 10);
		cardsInField[8] = new FieldCards(new Action(9, "Chapel", "", 2, "Action", 0, 0, 0, 0, 0), 10);
		cardsInField[9] = new FieldCards(new Action(10, "Chancellor", "", 3, "Action", 0, 2, 0, 0, 0), 10);
		cardsInField[10] = new FieldCards(new Action(11, "Village", "", 3, "Action", 1, 0, 2, 0, 0), 10);
		cardsInField[11] = new FieldCards(new Action(12, "Woodcutter", "", 3, "Action", 0, 2, 0, 1, 0), 10);
		cardsInField[12] = new FieldCards(new Action(13, "Feast", "", 3, "Action", 4, 0, 0, 0, 0), 10);
		cardsInField[13] = new FieldCards(new Action(14, "Militia", "", 4, "Action - Attack", 0, 2, 0, 0, 0), 10);
		cardsInField[14] = new FieldCards(new Action(15, "Moneylender", "", 4, "Action", 0, 0, 0, 0, 0), 10);
		cardsInField[15] = new FieldCards(new Action(16, "Remodel", "", 4, "Action", 0, 0, 0, 0, 0), 10);
		cardsInField[16] = new FieldCards(new Action(17, "Smithy", "", 4, "Action", 3, 0, 0, 0, 0), 10);
	}
	public void addPlayerToGame(String name){
		Player temp = new Player(name);
		temp.addCardstoDeck(startingCards);
		temp.drawCard();
		temp.drawCard();
		temp.drawCard();
		temp.drawCard();
		temp.drawCard();
		players.add(temp);

		/*MySQLAccess conn = new MySQLAccess();
		try {
			conn.insertPlayer(name);
			conn.insertPlayerInGame();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	public void nextPlayer(){
			int i = players.indexOf(currentPlayer);
			if(currentPlayer == null || players.size() <= i + 1){
				currentPlayer = players.get(0);
			}
			else{
				currentPlayer = players.get(i + 1);
			}
	}
	public FieldCards getFieldCard(int id)
	{
		return cardsInField[id];
	}
	public Player getCurrentPlayer()
	{
		System.out.println(currentPlayer.getName());
		return currentPlayer;
	}
	public void setStartingPlayer()
	{
		currentPlayer = players.get(0);
	}
	public Command getCommand()
	{
		return command;
	}
	public ArrayList<Player> getPlayersArray()
	{
		return players;
	}
}
