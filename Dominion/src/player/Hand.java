package player;
import java.util.ArrayList;

import card.*;

public class Hand extends CardPile {
//	private ArrayList<Card> hand = new ArrayList<Card>();
	public void playCard(Card card, Player player){
		if(List.contains(card)){
			card.play(player);
			List.remove(card);
			System.out.println("Hand played card");
		}
	}
	public void discard(Card card)
	{
		if(List.contains(card)){
			List.remove(card);
			System.out.println("Hand discarded card");
		}
	}
}
