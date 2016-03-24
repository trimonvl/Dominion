
public class Treasure extends Card {
	private int goldValue;
	public Treasure(int id, String name, int cost, String type, EXPANSION expansion, int goldValue){
		super(id, name, cost, type, expansion);
		this.goldValue = goldValue;
	}
	public void giveGold(){
		System.out.println("The player gets " + goldValue + " gold!");
	}
}
