/* MLC View Servlet */

/* @Author Ginger Nix & Victoria Chambers */

package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.apache.tomcat.jni.User;
import model.User;

import helpers.ListUserReservationsQuery;


/**
 * Servlet implementation class ViewServlet
 */
@WebServlet(
		description = "lists the reservations for a student", 
		urlPatterns = { 
				"/ViewServlet", 
				"/View"
		})
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session; 
	private String url;

	//private String MyID;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message = " ";
		String table = "";

		
		//get our current session
		session = request.getSession();
		User user = (User) session.getAttribute("user");
			
		
		System.out.println("view servlet user recd id = " + user.getUserRecordID());
		ListUserReservationsQuery lurq = new ListUserReservationsQuery();
		
		System.out.println("View Servlet: just set up database connection 2");
	
		
		//see how many records the student has, and if none, set error message, and if has at least one, 
		//put reservations found in a table
		System.out.println("Before try/catch");
		
		try {
			System.out.println("Fell into try/catch");
			table = lurq.ListUserReservations(user.getUserRecordID());
		}
		catch (Exception e){
			System.out.println("Inside catch");
			table = "";
		}
		
		System.out.println("After try/catch");
		
		if(table == null || table.isEmpty()){
			message="You have no current reservations.";
			System.out.println("View Servlet: no records found");
			
		}
		else {
			System.out.println("View Servlet: something in table ");
			
		}

		//forward our request along
		request.setAttribute("user", user);
		request.setAttribute("table", table);
		request.setAttribute("message", message);
		
		url = "user/view.jsp";	
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
