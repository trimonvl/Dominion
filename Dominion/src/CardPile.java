import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class CardPile {
	protected Queue<Card> List = new LinkedList<Card>();
	public void insertCards(ArrayList<Card> cardArray){
		List = new LinkedList<Card>(shufflePile(cardArray));
	}
	public void addCard(Card card){
		List.add(card);
	}
	public Card drawCard(Player player){
		Card c = List.poll();
		c.setPlayer(player);
		return c;
	}
	public ArrayList<Card> shufflePile(ArrayList<Card> list){
		long seed = System.nanoTime();
		Collections.shuffle(list, new Random(seed));
		return list;
	}
	public ArrayList<Card> getCards(){
		return new ArrayList<Card>(List);
	}
	public void emptyPile(){
		List = new LinkedList<Card>();
	}
	public String toString(){
		String temp = "";
		for(Card card : List){
			temp += card.getName() + "\n";
		}
		return temp;
	}
}
