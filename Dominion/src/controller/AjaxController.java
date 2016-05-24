package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import card.*;
import game.*;
import player.*;

/**
 * Servlet implementation class JavaController
 */
public class AjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static Game currentGame = null;
	public static ArrayList<Player> players = new ArrayList<Player>();

    /**
     * Default constructor. 
     */
    public AjaxController() {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		
		String operation = request.getParameter("operation");
		
		switch(operation)
		{
			case "initialize":
				String usernamePlayer1 = request.getParameter("usernameplayer1");
				String usernamePlayer2 = request.getParameter("usernameplayer2");
				String usernamePlayer3 = request.getParameter("usernameplayer3");
				String usernamePlayer4 = request.getParameter("usernameplayer4");
				int numberPlayers = Integer.parseInt(request.getParameter("numberplayers"));
				
				PrintWriter out = response.getWriter();
				//out.print(operation);
				//out.print(numberPlayers);
				//out.print(usernamePlayer1 + " and " + usernamePlayer2 + " and " + usernamePlayer3 + " and " + usernamePlayer4);
				
				//Usernames players in array steken
				String[] playerArray = new String[4];
				playerArray[0] = request.getParameter("usernameplayer1");
				playerArray[1] = request.getParameter("usernameplayer2");
				playerArray[2] = request.getParameter("usernameplayer3");
				playerArray[3] = request.getParameter("usernameplayer4");
				
				//Nieuwe game
				currentGame = new Game();
				
				//Aantal spelers toevoegen a.d.h.v. hun usernames
				for(int i=0; i<numberPlayers; i++) {
					currentGame.addPlayerToGame(playerArray[i]);
				}
				players = currentGame.getPlayersArray();
				
				//Card aan game toevoegen en startende speler toewijzen
				currentGame.addCardsToGame();
				currentGame.setStartingPlayer();
			break;
			
			case "getPlayers":
				//PrintWriter out2 = response.getWriter();
				//out2.print(players);
				for(int i=0; i<players.size();i++) {
					Player player = players.get(i);
					Queue<Card> deck = player.getPile("deck").getList();
					Queue<Card> discard = player.getPile("discard").getList();
					int lengthDiscardPile = player.getPile("discard").getCards().size();
					
					if(lengthDiscardPile > 0) {
						String lastCardDiscardPile = player.getPile("discard").getCards().get(lengthDiscardPile-1).getName();
					
						PrintWriter out3 = response.getWriter();
						out3.print("<div class='player' name='" + player.getName() + "'><div class='halfVertical'><div class='avatar default'></div><div class='name'>" + player.getName() + "</div></div><div class='halfVertical'><div class='piles'><div class='discardPile " + lastCardDiscardPile + "Large'><div class='amount'>" + discard.size() + "</div></div><div class='cardPile BackLarge'><div class='amount'>" + deck.size() + "</div></div></div></div></div>");
					}
					
					else {
						PrintWriter out3 = response.getWriter();
						out3.print("<div class='player' name='" + player.getName() + "'><div class='halfVertical'><div class='avatar default'></div><div class='name'>" + player.getName() + "</div></div><div class='halfVertical'><div class='piles'><div class='discardPile'><div class='amount'>" + discard.size() + "</div></div><div class='cardPile BackLarge'><div class='amount'>" + deck.size() + "</div></div></div></div></div>");
					}
				}
			break;
				
			case "getVictoryCards":
				for(int i=3; i<6;i++) {
					FieldCards fieldCard = currentGame.getFieldCard(i);
					Card card = fieldCard.getCard();
					
					PrintWriter out4 = response.getWriter();
					out4.print("<div class='card victoryCard " + card.getName() + "Small' cost='" + card.getCost() + "' numberindiv='" + i + "'><div class='amount'>" + fieldCard.getAmount() + "</div><h4 class='name hidden'>Cardname</h4><div class='image hidden'></div><div class='cost'>" + card.getCost() + "</div><h6 class='type hidden'>Cardtype</h6><button type='button' class='buy hidden' numberindiv='" + i + "'>+</button></div>");
				}
			break;
			
			case "getTreasureCards":
				for(int i=0; i<3;i++) {
					FieldCards fieldCard = currentGame.getFieldCard(i);
					Card card = fieldCard.getCard();
					
					PrintWriter out4 = response.getWriter();
					out4.print("<div class='card treasureCard " + card.getName() + "Small' cost='" + card.getCost() + "' numberindiv='" + i + "'><div class='amount'>" + fieldCard.getAmount() + "</div><h4 class='name hidden'>Cardname</h4><div class='image hidden'></div><div class='cost'>" + card.getCost() + "</div><h6 class='type hidden'>Cardtype</h6><button type='button' class='buy hidden' numberindiv='" + i + "'>+</button></div>");
				}
			break;
			
			case "getCurseCards":
				for(int i=6; i<7;i++) {
					FieldCards fieldCard = currentGame.getFieldCard(i);
					Card card = fieldCard.getCard();
					
					PrintWriter out4 = response.getWriter();
					out4.print("<div class='card curseCard " + card.getName() + "Small' cost='" + card.getCost() + "' numberindiv='" + i + "'><div class='amount'>" + fieldCard.getAmount() + "</div><h4 class='name hidden'>Cardname</h4><div class='image hidden'></div><div class='cost'>" + card.getCost() + "</div><h6 class='type hidden'>Cardtype</h6><button type='button' class='buy hidden' numberindiv='" + i + "'>+</button></div>");
				}
			break;
			
			case "getKingdomCards":
				for(int i=7; i<17;i++) {
					FieldCards fieldCard = currentGame.getFieldCard(i);
					Card card = fieldCard.getCard();
					
					PrintWriter out4 = response.getWriter();
					out4.print("<a class='detailView' href='http://ngartworks.be/Dominion/" + card.getName() + "-xl.jpg'><div class='card kingdomCard " + card.getName() + "Small' cost='" + card.getCost() + "' numberindiv='" + i + "'><div class='amount'>" + fieldCard.getAmount() + "</div><h4 class='name hidden'>Cardname</h4><div class='image hidden'></div><div class='cost'>" + card.getCost() + "</div><h6 class='type hidden'>Cardtype</h6><button type='button' class='buy hidden' numberindiv='" + i + "'>+</button></div></a>");
				}
			break;
			
			case "getTrash":
				
			break;
			
			case "getPlayerOnTurn":
				String playerOnTurn = currentGame.getCurrentPlayer().getName();
				PrintWriter out4 = response.getWriter();
				out4.print(playerOnTurn);
			break;
			
			case "draw":				
				for(int i=0; i<currentGame.getCurrentPlayer().getHand().size();i++) {
					Card card = currentGame.getCurrentPlayer().getHand().get(i);
					PrintWriter out5 = response.getWriter();
					out5.print("<div class='card inHand kingdomCard " + card.getName() + "Large' cardName='" + card.getName() + "' numberInHand='" + i + "' cardType='" + card.getType() + "'><div class='value hidden'></div><h4 class='name hidden'></h4><div class='image hidden'></div><div class='cost hidden'></div><h6 class='type hidden'></h6></div>");
				}
			break;
			
			case "getPhase":
				PrintWriter outCurrentPhase = response.getWriter();
				outCurrentPhase.print(currentGame.getCurrentPlayer().getPhase());
			break;
			
			case "play":
				String cardname = request.getParameter("cardname");
				String numberinhand = request.getParameter("numberinhand");
				currentGame.getCurrentPlayer().playCard(currentGame.getCurrentPlayer().getHand().get(Integer.parseInt(numberinhand)));
				PrintWriter out6 = response.getWriter();
				out6.print(cardname);
				out6.print(numberinhand);
			break;
			
			case "updateStats":
				int actions = currentGame.getCurrentPlayer().getActions();
				int buys = currentGame.getCurrentPlayer().getBuys();
				int coins = currentGame.getCurrentPlayer().getGold();
				
				PrintWriter out7 = response.getWriter();
				out7.print("<h4>Actions</h4><div class='amountActions'>" + actions + "</div><h4>Buys</h4><div class='amountBuys'>" + buys + "</div><h4>Coins</h4><div class='amountCoins'>" + coins + "</div>");
			break;
			
			/**** GET BUYS ****/
			case "getBuys":
				int buys1 = currentGame.getCurrentPlayer().getBuys();
				
				PrintWriter outBuys = response.getWriter();
				outBuys.print(buys1);
			break;
			
			/**** GET COINS ****/
			case "getCoins":
				int coins1 = currentGame.getCurrentPlayer().getGold();
				
				PrintWriter outCoins = response.getWriter();
				outCoins.print(coins1);
			break;
			
			/**** END PHASE ****/
			case "endPhase":
				currentGame.nextPhase();
				int phase = currentGame.getCurrentPlayer().getPhase();
				PrintWriter out8 = response.getWriter();
				
				if(phase == 1) {
					out8.print("<button type='button' class='' id='endActionPhase'>End action phase</button>");
				}
				
				else if(phase == 2) {
					out8.print("<button type='button' class='' id='endTreasurePhase'>End treasure phase</button>");
					//out8.print("<button type='button' class='' id='playAllTreasureCards'>Play all treasure cards</button>");
				}
				
				else if(phase == 3) {
					out8.print("<button type='button' class='' id='endTurn'>End turn</button>");
					//out8.print("<button type='button' class='' id='playAllTreasureCards'>Play all treasure cards</button>");
				}
			break;
			
			/**** END TURN ****/
			case "endTurn":
				currentGame.nextPhase();
				
				PrintWriter outEndTurn = response.getWriter();
				outEndTurn.print("<button type='button' class='' id='endActionPhase'>End action phase</button>");
			break;
			
			/**** BUY CARD ****/
			case "buyCard":
				String numberindiv = request.getParameter("numberindiv");
				
				FieldCards fieldCard = currentGame.getFieldCard(Integer.parseInt(numberindiv));
				currentGame.getCurrentPlayer().buyCard(fieldCard);
			break;
			
			//doGet(request, response);
		}
	}
}
