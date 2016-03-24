import java.util.ArrayList;

public class Player {
	private int gold;
	private int actions;
	private int buysLeft;
	private CardPile deck = new CardPile();
	private CardPile discardPile = new CardPile();
	private Hand hand = new Hand();
	
	public ArrayList<Card> getHand(){
		return hand.showHand();
	}
	public void playCard(Card card){
		hand.playCard(card);
		discardPile.addCard(card);
	}
	public void moveDiscToNewDeck(){
		deck.insertCards(discardPile.returnAllCards());
		discardPile.emptyPile();
	}
	public void drawCard(){
		if(deck.returnAllCards().size() > 0){
			hand.addCard(deck.drawCard());	
		}
	}
	public void buyCard(Card card){
		if(buysLeft > 0){
			if(card.getCost() <= gold){
				gold -= card.getCost();
				discardPile.addCard(card);
			}	
		}
	}
}
