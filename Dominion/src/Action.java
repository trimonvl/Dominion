
public class Action extends Card{
	private int bonusGold;
	private int carDraw;
	private int  bonusAction;
	private int bonusBuy;
	
	public Action(int id, String name, int cost, String type, EXPANSION expansion, 
			int cardDraw, int bonusGold, int bonusAction, int bonusBuy){
		super(id, name, cost, type, expansion);
		this.carDraw = cardDraw;
		this.bonusGold = bonusGold;
		this.bonusAction = bonusAction;
		this.bonusBuy = bonusBuy;
	}
}
