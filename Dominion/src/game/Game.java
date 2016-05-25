package game;
import java.sql.SQLException;
import java.util.ArrayList;
import card.*;
import player.*;
import sql.MySQLAccess;
public class Game {
	public String state = "normal";
	private ArrayList<Player> players = new ArrayList<Player>();
	Player currentPlayer = null;
	FieldCards[] cardsInField = new FieldCards[17];
	ArrayList<Card> startingCards = new ArrayList<Card>();
	Command command = new Command();
	MySQLAccess conn = new MySQLAccess();
	Card[] gameCards;
	boolean isGameOver = false;
	//kan ofwel play card ofwel discard ofwel trash card zijn
	public String actionWaitingFor = "play";
	public Card cardWaitingFor = null;
	
	public Game(){
		try {
			gameCards = conn.extractCards();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<7;i++)
		{
			startingCards.add(gameCards[0]);
		}
		for(int i=0;i<3;i++)
		{
			startingCards.add(gameCards[3]);
		}
		startingCards.add(new Action(20, "Witch", "", 5, "Action", 2, 0, 0, 0, 0));
	}
	public void newGame(){
		addPlayerToGame("Player1");
		addPlayerToGame("Player2");
		addCardsToGame();
		setStartingPlayer();
	}
	public void saveGame(){
		
	}
	public void specialAction(Card card)
	{
		String name = card.getName();
		if(name == "Village" || name == "Woodcutter" || name == "Smithy" || name == "Festival" || name == "Laboratory" || name == "Market")
		{
			state = "normal";
		}
		else
		{
			state = name;
		}
		if(name == "Witch")
		{
			state = "normal";
			command.witch(this);
		}
	}
	public void addCardsToGame()
	{	
		for(int i=0;i<gameCards.length;i++)
		{
			//String name = gameCards[i].getName();
			//String type = gameCards[i].getType();
			int id = gameCards[i].getID();
			int amount = 0;
			if(id>=8 && id!=30)
			{
					amount = 10;
			}
			if(id<=3)
			{
				switch(id)
				{
				case 1: amount = 60;
				break;
				case 2: amount = 40;
				break;
				case 3: amount = 30;
				break;
				}
			}
			if((id>3 && id<8) || id == 30)
			{
				if(id == 7)
				{
					switch(players.size())
					{
					case 2: amount = 10;
					break;
					case 3: amount = 20;
					break;
					case 4: amount = 30;
					break;
					}
				}
				else
				{
					switch(players.size())
					{
					case 2: amount = 8;
					break;
					case 3: amount = 12;
					break;
					}	
				}
			}
//			if(type=="Action" || type == "Action - attack" || type == "Action - Reaction")
//			{
//					amount = 10;
//			}
//			if(type=="Treasure")
//			{
//				switch(name)
//				{
//				case "Copper": amount = 60;
//				break;
//				case "Silver": amount = 40;
//				break;
//				case "Gold": amount = 30;
//				break;
//				}
//			}
//			if(type=="Victory")
//			{
//				if(name == "Curse")
//				{
//					switch(players.size())
//					{
//					case 2: amount = 10;
//					break;
//					case 3: amount = 20;
//					break;
//					case 4: amount = 30;
//					break;
//					}
//				}
//				else
//				{
//					switch(players.size())
//					{
//					case 2: amount = 8;
//					break;
//					case 3: amount = 12;
//					break;
//					}	
//				}
//			}
			cardsInField[i] = new FieldCards(gameCards[i], amount);
		}
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
		checkGameOver();
	}
	public FieldCards getFieldCard(int id)
	{
		return cardsInField[id];
	}
	public FieldCards[] getFieldCardsArray()
	{
		return cardsInField;
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
	public void nextPhase()
	{
		if(currentPlayer.getPhase()==3)
		{
			currentPlayer.nextPhase();
			nextPlayer();
		}
		else
		{
			currentPlayer.nextPhase();	
		}
	}
	private void checkGameOver()
	{
		int emptyPiles = 0;
		for(int i=0;i<cardsInField.length;i++)
		{
			if(cardsInField[i].getAmount()==0)
			{
				emptyPiles++;
			}
		}
		if(emptyPiles>=3)
		{
			for(int i=0;i<players.size();i++)
			{
				Player player = players.get(i);
				player.moveHandtoDiscard();
				player.discardDeck();
			}
			isGameOver = true;
		}
	}
	public void witchGiveCurse(){
		for(Player player : players){
			if(player != currentPlayer){
				player.addCardToTopDeck(cardsInField[6].getCard());
				cardsInField[6].remove();	
			}
		}
	}
	ArrayList<Card>toUse = new ArrayList<Card>();
	public boolean chooseChapelCards(Card card){
		if(toUse.size()<4)
		{
			toUse.add(card);
			if(toUse.size()<4){
				return false;
			}
			else{
				return true;
			}
		}
		return false;
	}
	public void ChapelComplete()
	{
		if (toUse.size()!=0)
		{
			for(int i=0; i<toUse.size();i++)
			{
				currentPlayer.trashCard(toUse.get(i));
			}	
		}
		toUse.removeAll(toUse);
	}
	public boolean getIsGameOver()
	{
		return isGameOver;
	}
}