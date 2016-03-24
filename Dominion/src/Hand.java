import java.util.ArrayList;

public class Hand {
	private ArrayList<Card> hand = new ArrayList();
	public void addCard(Card card){
		hand.add(card);
	}
	public ArrayList<Card> showHand(){
		return hand;
	}
	public void playCard(Card card){
		if(hand.contains(card)){
			card.play();
			hand.remove(card);		
		}
	}
	public void destroyCard(Card card){
		hand.remove(card);
	}
}
