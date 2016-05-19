
public class Victory extends Card {
	public Victory(int id, String name, String text, int cost, String type, EXPANSION expansion, 
			int cardDraw, int bonusGold, int bonusAction, int bonusBuy, int victoryPoints){
		super(id, name, text, cost, type, expansion, cardDraw, bonusGold, bonusAction, bonusBuy,victoryPoints);
	}
	public void specialMove(){
		//Do nothing
	}
}
