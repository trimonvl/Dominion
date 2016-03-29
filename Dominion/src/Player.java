import java.util.ArrayList;

public class Player {
	private int gold = 0;
	private int actions = 0;
	private int buysLeft = 0;
	public int victoryPoints;
	public CardPile deck = new CardPile();
	public CardPile discardPile = new CardPile();
	public Hand hand = new Hand();
	public String name;
	public Player(String name){
		this.name = name;
	}
	public ArrayList<Card> getHand(){
		return hand.getCards();
	}
	public void playCard(Card card){
		if(actions > 0){
			hand.playCard(card);
			discardPile.addCard(card);	
			actions -= 1;
		}
	}
	public void addCardstoDeck(ArrayList<Card> cards){
		for(Card card : cards){
			deck.addCard(card);
		}
	}
	public void moveDiscardToNewDeck(){
		deck.insertCards(discardPile.getCards());
		discardPile.emptyPile();
	}
	public void moveHandtoDiscard(){
		discardPile.insertCards(hand.getCards());
		hand.emptyPile();
	}
	public void drawCard(){
		if(deck.getCards().size() == 0){
			moveDiscardToNewDeck();
		}
		hand.addCard(deck.drawCard(this));	
	}
	public void buyCard(Card card){
		if(buysLeft > 0){
			if(card.getCost() <= gold){
				gold -= card.getCost();
				discardPile.addCard(card);
				buysLeft -= 1;
			}	
		}
	}
	public void endTurn(){
		this.moveHandtoDiscard();
		  for(int i = 0; i < 5; i++){
			  this.drawCard();
		  }
		  gold = 0;
		  actions = 0;
		  buysLeft = 0;
	}
	public void addAction(int action){
		this.actions += action;
	}
	public void addGold(int gold){
		this.gold += gold;
	}
	public void addBuysLeft(int buys){
		this.buysLeft += buys;
	}
	public String toString(){
		return name + " [" + gold + " Gold, " + actions +
				" actions and  " + buysLeft + " Buys left]";
	}
	public boolean equals(Object o) {
	    Player player = (Player) o;
		if (this.name == player.name){
	    	return true;
	    }
	    else {
	    	return false;
	    }
	}
}
