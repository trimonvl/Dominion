//*elke* kaart heeft een ID, expansion naam, kaart naam, 
//een kost en een type
//deze interface zorgt ervoor dat dit word nageleeft bij alle kaarten
public abstract class Card {
	private int ID;
	private String name;
	private int cost;
	private String type;
	private EXPANSION expansion;
	
	public Card(int id, String name, int cost, String type, EXPANSION expansion){
		this.ID = id;
		this.name = name;
		this.cost = cost;
		this.type = type;
		this.expansion = expansion;
	}
	public int getID(){
		return ID;
	};
	public String getName(){
		return name;
	};
	public int getCost(){
		return cost;
	};
	public String getType(){
		return type;
	};
	public EXPANSION getExpansion(){
		return expansion;
	};
	public String toString(){
		return "ID = " + ID + ", name = " + name + ", cost = " + cost 
				+ ", type = " + type + ", expansion = " + expansion;
	}
}
