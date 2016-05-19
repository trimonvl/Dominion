package card;

public class Treasure extends Card {
	public Treasure(int id, String name, String text, int cost, String type, 
			int cardDraw, int bonusGold, int bonusAction, int bonusBuy, int victoryPoints){
		super(id, name, text, cost, type, cardDraw, bonusGold, bonusAction, bonusBuy,victoryPoints);
	}
	public void specialMove(){
		//Do nothing
	}
}
