/**
 * 
 */
package helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.DbConnect;
import model.Reservation;

/**
 * @author Brian Olaogun
 *
 */
public class ReservationInsertQuery {

	private Connection connection;
	private Reservation reservation;
	
	public ReservationInsertQuery(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// hard coded the connection in DbConnect class
			this.connection = DbConnect.devCredentials();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void doReservationInsert(Reservation reservation){
		this.reservation = reservation;
		String query = "INSERT INTO tomcatdb.Reservations (tomcatdb.Reservations.primaryUser, tomcatdb.Reservations.secondaryUser, "
				+ "tomcatdb.Reservations.Rooms_roomID, tomcatdb.Reservations.reserveStartDate, tomcatdb.Reservations.reserveEndDate, "
				+ "tomcatdb.Reservations.reserveStartTime, tomcatdb.Reservations.reserveEndTime, tomcatdb.Reservations.hourIncrement, "
				+ "tomcatdb.Reservations.Building_buildingID, tomcatdb.Reservations.free) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		// securely run query
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			ps.setInt(1, this.reservation.getPrimaryUser());
			ps.setInt(2, this.reservation.getSecondaryUser());
			ps.setInt(3, this.reservation.getRoomsID());
			ps.setString(4, this.reservation.getReserveStartDate());
			ps.setString(5, this.reservation.getReserveEndDate());
			ps.setString(6, this.reservation.getReserveStartTime());
			ps.setString(7, this.reservation.getReserveEndTime());
			ps.setInt(8, this.reservation.getHourIncrement());
			ps.setInt(9, this.reservation.getbuildingID());
			ps.setString(10, this.reservation.getFree());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error in ReservationInsertQuery.java: doReservationInsert method. Please check connection or SQL statement: " + query);
		} 
	}
}