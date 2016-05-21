package card;
import java.util.ArrayList;

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
}
