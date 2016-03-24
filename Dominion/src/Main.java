// Adapted from http://www.vogella.com/tutorials/MySQLJava/article.html
public class Main {
  public static void main(String[] args) throws Exception {
    //test om database te lezen
	//MySQLAccess dao = new MySQLAccess();
    //dao.readDataBase();
	Card[] mijnHand = new Card[2];
	  
	mijnHand[0] = new Treasure(50, "super gold coin","", 5, "Action", EXPANSION.basic, 0, 50, 0, 0,0);
    mijnHand[1] = new Action(23, "Festival","", 5, "Action", EXPANSION.basic, 0, 2, 2, 1,0);
    mijnHand[0].play();
    mijnHand[1].play();
    
  }
  
}
