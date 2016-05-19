package card;
import java.util.ArrayList;

import player.*;

public class Command {
	ArrayList<Card>toDiscard = new ArrayList<Card>();
	public void addDiscard(Card card)
	{
		toDiscard.add(card);
	}
	public void CellarComplete(Player player)
	{
		if (toDiscard.size()!=0)
		{
			for(int i=0; i<toDiscard.size();i++)
			{
				player.discardCard(toDiscard.get(i));
				player.drawCard();
			}	
		}
	}
}
