package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

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
					currentGame.addPlayer(playerArray[i]);
				}
				players = currentGame.getPlayersArray();
			break;
			
			case "getPlayers":
				PrintWriter out2 = response.getWriter();
				out2.print(players);
//				for(int i=0; i<players.size();i++)
//				{
//					PrintWriter out3 = response.getWriter();
//					out3.print("Player!");
//				}
			break;
			
			case "draw":
				String cardname = request.getParameter("cardname");
				
				PrintWriter out4 = response.getWriter();
				out4.print(cardname);
			break;
			
			//doGet(request, response);
		}
	}

}
