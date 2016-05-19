package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
				String usernamePlayer1 = request.getParameter("usernameplayer1");
				String usernamePlayer2 = request.getParameter("usernameplayer2");
				String usernamePlayer3 = request.getParameter("usernameplayer3");
				String usernamePlayer4 = request.getParameter("usernameplayer4");
				int numberplayers = Integer.parseInt(request.getParameter("numberplayers"));
				
				PrintWriter out = response.getWriter();
				out.print(operation);
				out.print(numberplayers);
				out.print("Hello " + usernamePlayer1 + " and " + usernamePlayer2 + " and " + usernamePlayer3 + " and " + usernamePlayer4);
				
				//voor iedere player div maken
				
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
