import java.util.ArrayList;

public class Hand extends CardPile {
	private ArrayList<Card> hand = new ArrayList<Card>();
	public void playCard(Card card){
		if(List.contains(card)){
			card.play();
			List.remove(card);		
		}
	}
	public void destroyCard(Card card){
		hand.remove(card);
	}
}
