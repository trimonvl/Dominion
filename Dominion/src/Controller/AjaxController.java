package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import card.*;
import game.*;
import player.*;

/**
 * Servlet implementation class AjaxController
 */
public class AjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
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
				String[] playerArray = new String[4];
				playerArray[0] = request.getParameter("usernameplayer1");
				playerArray[1] = request.getParameter("usernameplayer2");
				playerArray[2] = request.getParameter("usernameplayer3");
				playerArray[3] = request.getParameter("usernameplayer4");
				int numberPlayers = Integer.parseInt(request.getParameter("numberplayers"));
				
				Game currentGame = new Game();
				for(int i=0; i<numberPlayers;i++)
				{
					currentGame.addPlayerToGame(playerArray[i]);
				}
				
				PrintWriter out = response.getWriter();
				out.print(operation);
				out.print(numberPlayers);
//				out.print("Hello " + usernamePlayer1 + " and " + usernamePlayer2 + " and " + usernamePlayer3 + " and " + usernamePlayer4);
				
				//voor iedere player div maken
				ArrayList<Player> players = currentGame.getPlayersArray();
				for(int i=0; i<players.size();i++)
				{
					
				}
				
				//beginwaarden en basiswaarden victorykaarten, treasurekaarten en kingdomkaarten laden
				
			break;
			
			case "draw":
				String cardname = request.getParameter("cardname");
				
				PrintWriter out1 = response.getWriter();
				out1.print(cardname);
				
			break;
		}
		//doGet(request, response);
	}

}
