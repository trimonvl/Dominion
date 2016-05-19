package player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
//import java.util.Random;

import card.*;

public class CardPile {
	Queue<Card> List = new LinkedList<Card>();
	public void insertCards(ArrayList<Card> cardArray){
		List.addAll(shufflePile(cardArray));
	}
	public void addCard(Card card){
		List.add(card);
	}
	public Card drawCard(Player player){
		System.out.println(List.size());
		System.out.println(List.peek());
		Card c = List.poll();
		return c;
	}
	public ArrayList<Card> shufflePile(ArrayList<Card> list){
		//long seed = System.nanoTime();
		Collections.shuffle(list);
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
