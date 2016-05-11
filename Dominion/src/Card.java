
import java.util.ArrayList;
public abstract class Card {
	private int ID;
	private String name;
	private String text;
	private int cost;
	private String type;
	private EXPANSION expansion;
	private int bonusGold;
	private int cardDraw;
	private int bonusAction;
	private int bonusBuy;
	private int victoryPoints;
	private Player player;
	//commands misschien nodig voor speciale kaarten
	private ArrayList<Command> commands = new ArrayList<>();
	public Card(int id, String name, String text, int cost, String type, EXPANSION expansion, 
			int cardDraw, int bonusGold, int bonusAction, int bonusBuy, int victoryPoints){
		this.ID = id;
		this.name = name;
		this.text = text;
		this.cost = cost;
		this.type = type;
		this.expansion = expansion;
		this.cardDraw = cardDraw;
		this.bonusGold = bonusGold;
		this.bonusAction = bonusAction;
		this.bonusBuy = bonusBuy;
		this.victoryPoints = victoryPoints;
	}
	public int getID(){
		return ID;
	};
	public String getName(){
		return name;
	};
	public String getText(){
		return text;
	}
	public int getCost(){
		return cost;
	};
	public String getType(){
		return type;
	};
	public EXPANSION getExpansion(){
		return expansion;
	};
	public String toString(){
		return "ID = " + ID + ", name = " + name + ", cost = " + cost 
				+ ", type = " + type + ", expansion = " + expansion;
	}
	
	final void play(Player player){
		this.player = player;
		specialMove();
		giveGold();
		drawCard();
		bonusBuy();
		bonusAction();
	}
	public abstract void specialMove();
	
	final void giveGold(){
		if(bonusGold != 0){
			player.addGold(bonusGold);
			System.out.println("The player gets "+bonusGold+" Gold");
		}
	}
	final void drawCard() {
		for(int i = 0; i < cardDraw; i++){
			player.drawCard();
			System.out.println("De speler trekt een kaart");
		}
	}
	final void bonusAction() {
		if(bonusAction!= 0) {
			player.addAction(bonusAction);
			System.out.println("De speler krijgt " + bonusAction + " extra beurten");		
		}
	}
	final void bonusBuy() {
		if(bonusBuy!= 0) {
			player.addBuysLeft(bonusBuy);
			System.out.println("De speler krijgt " + bonusBuy + " extra koop beurten");		
		}
	}
	final void giveVictoryPoints(){
		if(victoryPoints != 0){
			player.victoryPoints += victoryPoints;
			System.out.println("The player gets "+victoryPoints+"Victory points");
		}
	}
	
	//override om kaarten te vergelijken
	public boolean equals(Object o) {
	    Card card = (Card) o;
		if (this.ID == card.ID){
	    	return true;
	    }
	    else {
	    	return false;
	    }
	}
}
