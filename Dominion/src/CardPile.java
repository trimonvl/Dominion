import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class CardPile {
	private Queue<Card> List = new LinkedList();
	public void insertCards(ArrayList<Card> cardArray){
		List = new LinkedList<Card>(shufflePile(cardArray));
	}
	public void addCard(Card card){
		List.add(card);
	}
	public Card drawCard(){
		return List.poll();
	}
	public ArrayList<Card> shufflePile(ArrayList<Card> list){
		long seed = System.nanoTime();
		Collections.shuffle(list, new Random(seed));
		return list;
	}
	public ArrayList<Card> returnAllCards(){
		return new ArrayList<Card>(List);
	}
	public void emptyPile(){
		List = new LinkedList();
	}
}
