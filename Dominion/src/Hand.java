import java.util.ArrayList;

public class Hand extends CardPile {
	private ArrayList<Card> hand = new ArrayList<Card>();
	public void playCard(Card card, Player player){
		if(List.contains(card)){
			card.play(player);
			List.remove(card);
			System.out.println("Hand played card");
		}
	}
	public void destroyCard(Card card){
		hand.remove(card);
	}
	public void discard(Card card, Player player)
	{
		if(List.contains(card)){
			List.remove(card);
			System.out.println("Hand discarded card");
		}
	}
}
