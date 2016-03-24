
public class Treasure extends Card {
	private int goldValue;
	public Treasure(int id, String name,String text, int cost, String type, EXPANSION expansion, int goldValue){
		super(id, name, text, cost, type, expansion);
		this.goldValue = goldValue;
	}
	public void run() {
		System.out.println("De speler krijgt " + goldValue + "gold");
		
	}
}
