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

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import card.Card;
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
        // TODO Auto-generated constructor stub
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
					
					PrintWriter out3 = response.getWriter();
					out3.print("<div class='player'><div class='halfVertical'><div class='avatar'></div><div class='name'>" + player.getName() + "</div></div><div class='halfVertical'><div class='piles'><div class='discardPile'><div class='amount'>0</div></div><div class='cardPile'></div><div class='amount'>" + deck.size() + "</div></div></div></div>");
				}
			break;
				
			case "getFieldCards":
				for(int i=3; i<6;i++) {
					FieldCards fieldCard = currentGame.getFieldCard(i);
					Card card = fieldCard.getCard();
					
					PrintWriter out4 = response.getWriter();
					out4.print("<div class='card victoryCard " + card.getName() + "Small'><div class='amount'>" + fieldCard.getAmount() + "</div><h4 class='name hidden'>Cardname</h4><div class='image hidden'></div><div class='cost'>" + card.getCost() + "</div><h6 class='type hidden'>Cardtype</h6><button type='button' class='buy'>+</button></div>");
				}
			break;
			
			case "getFieldCards1":
				for(int i=0; i<3;i++) {
					FieldCards fieldCard = currentGame.getFieldCard(i);
					Card card = fieldCard.getCard();
					
					PrintWriter out4 = response.getWriter();
					out4.print("<div class='card treasureCard " + card.getName() + "Small'><div class='amount'>" + fieldCard.getAmount() + "</div><h4 class='name hidden'>Cardname</h4><div class='image hidden'></div><div class='cost'>" + card.getCost() + "</div><h6 class='type hidden'>Cardtype</h6><button type='button' class='buy'>+</button></div>");
				}
			break;
			
			case "getFieldCards2":
				for(int i=6; i<7;i++) {
					FieldCards fieldCard = currentGame.getFieldCard(i);
					Card card = fieldCard.getCard();
					
					PrintWriter out4 = response.getWriter();
					out4.print("<div class='card curseCard " + card.getName() + "Small'><div class='amount'>" + fieldCard.getAmount() + "</div><h4 class='name hidden'>Cardname</h4><div class='image hidden'></div><div class='cost'>" + card.getCost() + "</div><h6 class='type hidden'>Cardtype</h6><button type='button' class='buy'>+</button></div>");
				}
			break;
			
			case "draw":
				String cardname = request.getParameter("cardname");
				
				PrintWriter out5 = response.getWriter();
				out5.print(cardname);
			break;
			
			//doGet(request, response);
		}
	}

}
