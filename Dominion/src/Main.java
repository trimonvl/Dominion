// Adapted from http://www.vogella.com/tutorials/MySQLJava/article.html
public class Main {
  public static void main(String[] args) throws Exception {
    //test om database te lezen
	//MySQLAccess dao = new MySQLAccess();
    //dao.readDataBase();
	Card[] mijnHand = new Card[2];
	  
	mijnHand[0] = new Treasure(50, "Gold card die 33 kost en 15 gold geeft","", 33, "Treasure", EXPANSION.basic, 15);
    mijnHand[1] = new Action(23, "Festival","", 5, "Action", EXPANSION.basic, 
			0, 2, 2, 1);
    mijnHand[0].run();
    mijnHand[1].run();
    
  }
  
}
