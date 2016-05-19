package card;

import player.*;

public abstract class Card {
	private int ID;
	private String name;
	private String text;
	private int cost;
	private String type;
	private int bonusGold;
	private int cardDraw;
	private int bonusAction;
	private int bonusBuy;
	private int victoryPoints;
	private Player player;
	public Card(int id, String name, String text, int cost, String type, 
			int cardDraw, int bonusGold, int bonusAction, int bonusBuy, int victoryPoints){
		this.ID = id;
		this.name = name;
		this.text = text;
		this.cost = cost;
		this.type = type;
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
	public String toString(){
		return "ID = " + ID + ", name = " + name + ", cost = " + cost 
				+ ", type = " + type;
	}
	
	public final void play(Player player){
		this.player = player;
		drawCard();
		giveGold();
		bonusAction();
		bonusBuy();
	}
	
	public final void giveGold(){
		if(bonusGold != 0){
			player.addGold(bonusGold);
			System.out.println("The player gets "+bonusGold+" Gold");
		}
	}
	public final void drawCard() {
		for(int i = 0; i < cardDraw; i++){
			player.drawCard();
			System.out.println("De speler trekt een kaart");
		}
	}
	public final void bonusAction() {
		if(bonusAction!= 0) {
			player.addAction(bonusAction);
			System.out.println("De speler krijgt " + bonusAction + " extra beurten");		
		}
	}
	public final void bonusBuy() {
		if(bonusBuy!= 0) {
			player.addBuysLeft(bonusBuy);
			System.out.println("De speler krijgt " + bonusBuy + " extra koop beurten");		
		}
	}
	public final void giveVictoryPoints(){
		if(victoryPoints != 0){
			player.addVictoryPoints(victoryPoints);
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
