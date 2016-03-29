import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
public class Game {
	private ArrayList<Player> players = new ArrayList<Player>();
	public Player currentPlayer = null;
	ArrayList<Card> startingCards = new ArrayList<>();
	Timer turnTimer = new Timer();
	public Game(){
		startingCards.add(new Treasure(1, "testgold", "", 0, "Treasure", EXPANSION.basic, 0, 5, 0, 0,0));
		startingCards.add(new Treasure(1, "testgold2", "", 0, "Treasure", EXPANSION.basic, 0, 5, 0, 0,0));
		startingCards.add(new Treasure(1, "testgold3", "", 0, "Treasure", EXPANSION.basic, 0, 5, 0, 0,0));
		startingCards.add(new Treasure(1, "testgold", "", 0, "Treasure", EXPANSION.basic, 0, 5, 0, 0,0));
		startingCards.add(new Treasure(1, "testgold2", "", 0, "Treasure", EXPANSION.basic, 0, 5, 0, 0,0));
		startingCards.add(new Treasure(1, "testgold3", "", 0, "Treasure", EXPANSION.basic, 0, 5, 0, 0,0));
		startingCards.add(new Treasure(1, "testgold", "", 0, "Treasure", EXPANSION.basic, 0, 5, 0, 0,0));
		startingCards.add(new Treasure(1, "testgold2", "", 0, "Treasure", EXPANSION.basic, 0, 5, 0, 0,0));
		startingCards.add(new Treasure(1, "testgold3", "", 0, "Treasure", EXPANSION.basic, 0, 5, 0, 0,0));
		startingCards.add(new Treasure(1, "testgold", "", 0, "Treasure", EXPANSION.basic, 0, 5, 0, 0,0));
		startingCards.add(new Treasure(1, "testgold2", "", 0, "Treasure", EXPANSION.basic, 0, 5, 0, 0,0));
		startingCards.add(new Treasure(1, "testgold3", "", 0, "Treasure", EXPANSION.basic, 0, 5, 0, 0,0));
		startingCards.add(new Treasure(1, "testgold", "", 0, "Treasure", EXPANSION.basic, 0, 5, 0, 0,0));
		startingCards.add(new Treasure(1, "testgold2", "", 0, "Treasure", EXPANSION.basic, 0, 5, 0, 0,0));
		startingCards.add(new Treasure(1, "testgold3", "", 0, "Treasure", EXPANSION.basic, 0, 5, 0, 0,0));
		startingCards.add(new Treasure(1, "testgold", "", 0, "Treasure", EXPANSION.basic, 0, 5, 0, 0,0));
		startingCards.add(new Treasure(1, "testgold2", "", 0, "Treasure", EXPANSION.basic, 0, 5, 0, 0,0));
		startingCards.add(new Treasure(1, "testgold3", "", 0, "Treasure", EXPANSION.basic, 0, 5, 0, 0,0));
		addPlayer("Pablo");
		addPlayer("Aitor");
		addPlayer("Luna");
		addPlayer("Manolo");
		currentPlayer = players.get(0);
		currentPlayer.addAction(1);
		currentPlayer.addBuysLeft(1);
		currentPlayer.drawCard();
		currentPlayer.drawCard();
		currentPlayer.drawCard();
		System.out.println("The current player is : " + currentPlayer.toString());
	}
	public void addPlayer(String name){
		Player temp = new Player(name);
		temp.addCardstoDeck(startingCards);
		temp.drawCard();
		players.add(temp);
	}
	public void start(){
		turnTimer.schedule(new TimerTask() {
			  @Override
			  public void run() {
				  currentPlayer.endTurn();
				  nextPlayer();
				  currentPlayer.addAction(1);
				  currentPlayer.addBuysLeft(1);
				  System.out.println("The current player is : " + currentPlayer.toString());
			  }
			  //verander tijd per beurt hier
			}, 5000, 5000);
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
}
