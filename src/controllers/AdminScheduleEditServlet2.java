package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helpers.AdminScheduleUpdateQuery;
import model.Schedule;
import model.TimeConverter;

/**
 * Servlet implementation class AdminScheduleEditServlet2
 */
@WebServlet({ "/AdminScheduleEditServlet2", "/schedule-edit" })
public class AdminScheduleEditServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HttpSession session;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminScheduleEditServlet2() {
        super();
        // TODO Auto-generated constructor stub
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
		this.session = request.getSession();
		System.out.println(request.getRequestURI());
		String url = "";
		String msg = "";
		
		TimeConverter tc = new TimeConverter();
		
		// values passed from schedule.jsp/AdminScheduleSelectQuery
		String scheduleID = request.getParameter("scheduleID");
		System.out.println("ScheduleID: " +  scheduleID);
		String buildingName = request.getParameter("buildingName");
		String buildingID = request.getParameter("buildingID");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String summary = request.getParameter("summary");
		String createdBy = request.getParameter("createdBy");
		
		// edited values from schedule-edit.jsp
		String startDateEdit  = request.getParameter("startDateEdit");
		String startTimeEdit  = request.getParameter("startTimeEdit");
		String endTimeEdit  = request.getParameter("endTimeEdit");
		String summaryEdit  = request.getParameter("summaryEdit");
		
		
		// null check for variables coming in from Admin Schedule Edit Servlet & schedule-edit.jsp
		if (buildingName != null && buildingID != null && startTime != null && 
				endTime != null && startDate != null && endDate != null &&
				summary != null && createdBy != null &&
				scheduleID != null || startDateEdit != null && startTimeEdit != null && 
				endTimeEdit != null && summaryEdit != null){
			
			// forward to edit the schedule 
			url = "admin/schedule-edit.jsp";
			
			// null check for variables coming in from schedule-edit.jsp (admin edited variables)
			if (startDateEdit != null && startTimeEdit != null && endTimeEdit != null && summaryEdit != null){
				// get scheduleID from session
				scheduleID = (String) session.getAttribute("scheduleID");
				
				// place variables from jsp (user altered) into standard variables
				// the start and end date have to be the same
				startDate = startDateEdit;
				endDate = startDateEdit;
				
				// convert from 24-hour time
				startTimeEdit = tc.convertTimeTo24(startTimeEdit);
				startTime = startTimeEdit;
			
				// convert to 24-hour time
				endTimeEdit = tc.convertTimeTo24(endTimeEdit);
				endTime = endTimeEdit;
				
				summary = summaryEdit;
				createdBy = "admin"; // notate that admin made a change in database
				
				msg = "Successfully edited";
				
				// Update Schedule edits into database
				int scheduleInt = Integer.parseInt(scheduleID);
				Schedule schedule = new Schedule(scheduleInt, startDate, endDate,
						startTime, endTime, summary, createdBy);
				AdminScheduleUpdateQuery suq = new AdminScheduleUpdateQuery();
				suq.doScheduleUpdate(schedule);
				
				url = "Schedule";
			} else {
				//msg = "All values must be entered.";
				url = "admin/schedule-edit.jsp";
			}
			System.out.println("Print of: " + startDate + " " + tc.convertTimeTo12(startTime) + " " + tc.convertTimeTo12(endTime) + " " + summary);
			this.session.setAttribute("msg", msg);
			this.session.setAttribute("tc", tc);
			this.session.setAttribute("scheduleID", scheduleID);
			this.session.setAttribute("buildingName", buildingName);
			this.session.setAttribute("buildingID", buildingID);
			this.session.setAttribute("startTime", startTime);
			this.session.setAttribute("endTime", endTime);
			this.session.setAttribute("startDate", startDate);
			this.session.setAttribute("endDate", endDate);
			this.session.setAttribute("summary", summary);
			this.session.setAttribute("createdBy", createdBy);
			
		} else {
			// send back to schedule list (Admin Schedule Edit Servlet)
			url = "Schedule";
		}
		
		// forward the request
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}