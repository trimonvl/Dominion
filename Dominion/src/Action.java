
public class Action extends Card{
	private int bonusGold;
	private int cardDraw;
	private int bonusAction;
	private int bonusBuy;
	
	public Action(int id, String name, String text, int cost, String type, EXPANSION expansion, 
			int cardDraw, int bonusGold, int bonusAction, int bonusBuy){
		super(id, name, text, cost, type, expansion);
		this.cardDraw = cardDraw;
		this.bonusGold = bonusGold;
		this.bonusAction = bonusAction;
		this.bonusBuy = bonusBuy;
	}
	public void run(){
		giveGold();
		drawCard();
		bonusAction();
		bonusBuy();
	}
	private void giveGold() {
		if(bonusBuy!= 0) {
			System.out.println("De speler krijgt " + bonusBuy + " gold");		
		}	
	}
	private void drawCard() {
		if(cardDraw!= 0) {
			System.out.println("De speler trekt " + cardDraw+ " kaart(en)");		
		}
	}
	private void bonusAction() {
		if(bonusAction!= 0) {
			System.out.println("De speler krijgt " + bonusAction + " extra beurten");		
		}
	}
	private void bonusBuy() {
		if(bonusBuy!= 0) {
			System.out.println("De speler krijgt " + bonusBuy + " extra koop acties");		
		}
	}
}
