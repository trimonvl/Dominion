package javaGui;
import java.util.ArrayList;

import game.*;

// Adapted from http://www.vogella.com/tutorials/MySQLJava/article.html
public class Main {
  public static void main(String[] args) throws Exception {
    //test om database te lezen
	//MySQLAccess dao = new MySQLAccess();
    //dao.readDataBase();
	
	Game newGame = new Game();
	TestFrame newFrame = new TestFrame(newGame);
/*	newGame.start();
	System.out.println("Player " + newGame.currentPlayer.name + " has these cards in his hand: ");
	System.out.println(newGame.currentPlayer.hand.toString());
	
	//kaarten worden vaak in arraylists bijgehouden, zo maak je een arraylist van kaarten aan:
	ArrayList<Card> kaarten = new ArrayList<Card>();
	//hieronder haal ik de kaarten op die de huidige speler in zijn hand heeft en hou ik deze 
	//bij in de arraylist kaarten
	kaarten = newGame.currentPlayer.getHand();
	//om nu de speler een kaart te laten spelen gebruik je playCard(); 
	//je moet de kaart meegeven die gespeeld moeten worden
	
	//hieronder pak ik de kaart nr 0 uit de arraylist kaarten van hierboven
	//en zeg ik dat de huidige speler deze moet spelen
	newGame.currentPlayer.playCard(kaarten.get(0));
*/
  }
}
