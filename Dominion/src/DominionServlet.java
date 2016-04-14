

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DominionServlet
 */
public class DominionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DominionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    response.setContentType("application/json");
	    response.setCharacterEncoding("utf-8");
	    Writer out = response.getWriter();
	    //out.println("<html><body>Hello World.</body></html>");
	    System.out.println("ik heb gewerkt...");
	    		    
	    String operation;
	    		    
	    operation = request.getParameter("operation");
	    
	    if(operation != null) {
	    switch(operation) {
		    case "initialize":
		    	String playerName1 = request.getParameter("name1");
		    	String playerName2 = request.getParameter("name2");
		    	out.append("{ \"status\":\"OK\", \"name1\":\"");
		    	out.append(playerName1);
		    	out.append("\" }");
		    	
		    	//dominion.initialize();
		    	break;
		    case "draw":
		    	int cardNumber = Integer.parseInt(request.getParameter("cardNumber"));
		    	out.append(" { 'status':'OK', 'cardNumber':");
		    	out.append(Integer.toString(cardNumber));
		    	out.append(" } ");
		    	break;
		    default:
		    	out.append(" { 'status':'NOK', 'errorMessage':'Invalid operation' } ");
		    	break;
	    } }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
