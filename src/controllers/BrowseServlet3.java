package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helpers.RoomsSelectQuery;

/**
 * Servlet implementation class BrowseServlet3
 */
@WebServlet({ "/BrowseServlet3", "/Browse3" })
public class BrowseServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrowseServlet3() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String floor = (String) session.getAttribute("floor");
		String buildingSelected = (String) session.getAttribute("buildingSelected");
		String floorSelected = (String) request.getParameter("floorList");
		
		RoomsSelectQuery rsq = new RoomsSelectQuery ("tomcatdb", "root", "");
		System.out.println("#3 Building: " + buildingSelected);
		System.out.println("#3 Floor: " + floorSelected);
		rsq.doRoomRead(buildingSelected, floorSelected);
		
		
		String table = rsq.getRoomsTable();
		
		// URL of the view to forward
		String url = "/student/browse.jsp";
		
		// set session attribute
		session.setAttribute("floorSelected", floorSelected);
		session.setAttribute("floor", floor);
		session.setAttribute("table", table);
		
		// forward the request
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
