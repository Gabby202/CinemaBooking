import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.Icon;

class DBConnection {

	//DB conection info
	private String DBUrl = "jdbc:mysql://localhost:port/databasename";
	private String DBUser = "myuser";
	private String DBPass = "1234";

	private DBConnection() {
		try{
			//DB connection here
		}catch(SQLException e){
			
		}
	}
	
	//All methods might be returning something depending on its purpose
	
	//All methods will either return something or throw an exception for errors depending how we want to deal with the error
	//For if we want to how an error somewhere we will need to throw an error, but some errors can be dealt within this class

	
	public static void Login(String userName, char[] password) {
		//Attempts to confirm login details
		//Returns account type, or null for an error
	}

	public static void Register(String userName, char[] pass, String name, String email) {
		//Attempts to register a new user
		//Returns if failed or not
	}

	public static void getMovies() {
		//Returns the list of currant movies showing with their posters and descriptions

	}

	public static void setMvoies(String title[], Icon img[], String desp) {
		//Attempts to set what movies are currently showing, set the poster for the movie, and the description 

	}
	
	public static void getTimes(String movie){
		//Attempts to get the times for selected movie
	}
	
	public static void setTimes(String movie, Date times){
		//Attempts to set the times for selected movie
	}
	
	public static void getSeats(String movie, Date time){
		//Returns to the seating data for a movie at a certain date
	}
	
	public static void setSeat(int row, int col, int state){
		//Attempts to set the state of a selected seat
		//i.e. take or not
	}
}
