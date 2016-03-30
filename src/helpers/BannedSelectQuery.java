package helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Banned;
import model.DateTimeConverter;
import model.DbConnect;


/**
 * @author Ronnie Xu
 * Helper for the Admin side of the website.
 *
 */

public class BannedSelectQuery {
	
		private Connection connection;
		private ResultSet results;

		/**
		 * 
		 * Database connection.  Hard coded in DbConnect.java
		 */
		public BannedSelectQuery() {
	
			// set up the driver
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				// hard coded the connection in DbConnect class
				this.connection = DbConnect.devCredentials();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		
		

		public void doRead(){

			String query = "SELECT banned.bannedID, banned.User_userID, banned.Admin_adminID, banned.banStart, banned.banEnd, banned.penaltyCount, banned.description, banned.status FROM banned";
			// securely run query
			try {
				PreparedStatement ps = this.connection.prepareStatement(query);
				results = ps.executeQuery();
				
				
				
			
				
				
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error in BannedSelectQuery.java: getHTMLTable method. Please check connection or SQL statement: " + query);
			} 
		}
		
		
		
		public String getHTMLTable(){ 
			//Return table of banned students
			
			String table = "";
			
		
			try {
				table += "<table id='' class='mdl-data-table' cellspacing='0' width='95%'>";
				
				table += "<thead>";
				table += "<tr>"
						+ "<th>Ban#</th>"
						+ "<th>Student ID</th>"
						+ "<th>Admin ID</th>"
						+ "<th>Ban Start</th>"
						+ "<th>Ban End</th>"
						+ "<th>Penalty Count</th>"
						+ "<th>Description</th>"
						+ "<th>Status</th>"
						+ "<th></th>"
						+ "</tr>";
				table += "</thead>";
				table += "<tbody>";
				while(results.next()){

					DateTimeConverter dtc = new DateTimeConverter();
					
					Banned ban = new Banned();
					ban.setBanID(results.getInt("bannedID"));
					ban.setStudentID(results.getInt("User_userID"));
					ban.setAdminID(results.getInt("Admin_adminID"));
					ban.setBanStart(results.getString("banStart"));
					ban.setBanEnd(this.results.getString("banEnd"));
					ban.setPenaltyCount(results.getInt("penaltyCount"));
					ban.setDescription(results.getString("description"));
					ban.setStatus(results.getInt("status"));
					
					//show only banned
					
					table += "<tr>";
					
					table += "<td>";
					table += ban.getBanID();
					table += "</td>";
					table += "<td>";
					table += ban.getStudentID();
					table += "</td>";
					table += "<td>";
					table += ban.getAdminID();
					table += "</td>";
					table += "<td>";
					table += dtc.dateTimeTo12Long(ban.getBanStart());
					table += "</td>";
					table += "<td>";
					// check to see if end date time string is empty or null
					// if its not, then convert to easier display
					// if empty or null, show blank in table (instead of "null")
					if (ban.getBanEnd() != null && !ban.getBanEnd().isEmpty()){
						// converts sql format to format thats easier to read.
						table += dtc.dateTimeTo12Long(ban.getBanEnd());
					} else {
						// show a blank instead of null in the table
						table += "";
					}
					table += "</td>";
					table += "<td>";
					table += ban.getPenaltyCount();
					table += "</td>";
					table += "<td>";
					table += ban.getDescription();
					table += "</td>";
					table += "<td>";
					if (ban.getStatus() == 1){
						table += "Active";
					} else {
						table += "Not Active";
					}
					//table += ban.getStatus();
					table += "</td>";
					
					table += "<td><a href=unban?banID=" + ban.getBanID() + "> <button type='submit' value='Unban'>Unban</button></a></td>";
	
					
					table += "</tr>";
				}
				table += "</tbody>";
				table += "</table>";
			}
			catch(SQLException e) {
				e.printStackTrace();
				System.out.println("Error in BannedSelectQuery.java: getHTMLTable method. Please check connection or SQL statement.");
				
			}
			
			return table;
		}
		
		
		

		
		

}
