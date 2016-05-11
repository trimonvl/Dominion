
public class FieldCards {
	Card card;
	int amount;
	public FieldCards(Card card, int amount){
		this.card = card;
		this.amount = amount;
	}
	public void add()
	{
		amount ++;
	}
	
	public void remove()
	{
		if(amount>0)
		{
			amount --;
		}
	}
	public Card getCard()
	{
		return card;
	}
	public int getAmount()
	{
		return amount;
	}
}
