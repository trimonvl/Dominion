package player;
import java.util.ArrayList;

import card.*;
import game.*;

public class Player {
	int gold = 0;
	int actions = 1;
	int buysLeft = 1;
	int victoryPoints;
	int phase = 1;
	CardPile deck = new CardPile();
	CardPile discardPile = new CardPile();
	CardPile trash = new CardPile();
	Hand hand = new Hand();
	String name;
	public Player(String name){
		this.name = name;
	}
	public ArrayList<Card> getHand(){
		return hand.getCards();
	}
	public void playCard(Card card){
		switch(phase)
		{
		case 1:
			if(actions > 0 && card.getType() == "Action"){
				hand.playCard(card, this);
	//			System.out.println(card.toString());
				discardPile.addCard(card);	
				actions -= 1;
				System.out.println("Player played card");
			}
			break;
		case 2:
			if(card.getType() == "Treasure"){
				hand.playCard(card, this);
		//		System.out.println(card.toString());
				discardPile.addCard(card);	
				System.out.println("Player played card");
			}
			break;
		}
	}
	public void discardCard(Card card)
	{
		hand.discard(card);
		discardPile.addCard(card);
	}
	public void trashCard(Card card)
	{
		hand.discard(card);
		trash.addCard(card);
	}
	public void addCardstoDeck(ArrayList<Card> cards){
		deck.insertCards(cards);
	}
	public void moveDiscardToNewDeck(){
		deck.insertCards(discardPile.getCards());
		discardPile.emptyPile();
	}
	public void moveHandtoDiscard(){
		discardPile.insertCards(hand.getCards());
	//	System.out.println(discardPile.List.size() + " In discard");
		hand.emptyPile();
	}
	public void drawCard(){
		if(deck.getCards().size()!=0 || discardPile.getCards().size()!=0)
		{
			if(deck.getCards().size() <= 0){
				//		System.out.println("Empty");
						moveDiscardToNewDeck();
					}
				//	System.out.println("Not Empty");
					hand.addCard(deck.drawCard(this));	
		}	
	}
	public void buyCard(FieldCards cards){
		if(buysLeft > 0){
			if(cards.getCard().getCost() <= gold){
				gold -= cards.getCard().getCost();
				discardPile.addCard(cards.getCard());
	//			System.out.println(discardPile.List.size());
				cards.remove();
				buysLeft -= 1;
			}	
		}
	}
	public void endTurn(){
		moveHandtoDiscard();
		  for(int i = 0; i < 5; i++){
			  drawCard();
		  }
		  gold = 0;
		  actions = 1;
		  buysLeft = 1;
		  phase = 0;
	}
	public void nextPhase(){
		switch(phase)
		{
		case 3: endTurn();
		phase = 1;
		break;
		case 2: phase = 3;
		break;
		case 1: phase = 2;
		break;
		}
	}
	public int getPhase()
	{
		return phase;
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
	public String getName()
	{
		return name;
	}
	public int getGold()
	{
		return gold;
	}
	public int getBuys()
	{
		return buysLeft;
	}
	public int getActions()
	{
		return actions;
	}
	public void addVictoryPoints(int amount)
	{
		victoryPoints += amount;
	}
}
