package card;
import java.util.ArrayList;

import game.FieldCards;
import player.*;

public class Command {
	ArrayList<Card>toUse = new ArrayList<Card>();
	public void addDiscard(Card card)
	{
		toUse.add(card);
	}
	public void CellarComplete(Player player)
	{
		if (toUse.size()!=0)
		{
			for(int i=0; i<toUse.size();i++)
			{
				player.discardCard(toUse.get(i));
				player.drawCard();
			}	
		}
		toUse.removeAll(toUse);
	}
	public void addChapelTrash(Card card)
	{
		if(toUse.size()<4)
		{
			toUse.add(card);
		}
	}
	public void ChapelComplete(Player player)
	{
		if (toUse.size()!=0)
		{
			for(int i=0; i<toUse.size();i++)
			{
				player.trashCard(toUse.get(i));
			}	
		}
		toUse.removeAll(toUse);
	}
	public int getToUseSize()
	{
		return toUse.size();
	}
	public void chancellor(Player player)
	{
		player.discardDeck();
	}
	public void getCard(Player player,int maxCost,FieldCards cards)
	{
		if(cards.getCard().getCost()<=maxCost)
		{
			player.getPile("discard").addCard(cards.getCard());
			cards.remove();
		}
	}
	public void addSilverToDeck(Player player, FieldCards[] FieldCards) 
	{
		for(int i=0;i<FieldCards.length;i++)
		{
			if(FieldCards[i].getCard().getName()=="Silver")
			{
				player.addCardToTopDeck(FieldCards[i].getCard());
				FieldCards[i].remove();
				break;
			}
		}
	}
	public void moneyLenderTrash(Player player){
		boolean copper = false;
		for(Card card : player.getHand()){
			if(card.getID() == 1){
				copper = true;
			}
		}
		if(copper == true){
			player.addGold(3);
			player.trashCard(new Treasure(1, "", "", 0, "", 0, 0, 0,0, 0));
		}
	}
	public void trash(Player player, int cardID)
	{
		for(int i=0;i<player.getHand().size();i++)
		{
			if(player.getHand().get(i).getID()==cardID)
			{
				player.trashCard(player.getHand().get(i));;
			}
		}
	}
}
