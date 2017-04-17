import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;

/**
 * 
 * Class to initialize and utilize DB Connector
 * 
 * @author Darragh
 *
 */
public class DBConnection {

	// DB connection info
	private String DBDriver = "com.mysql.jdbc.Driver";
	private String DBPort = "3306";
	private String DBName = "CinemaBooking";
	private String DBUrl = "jdbc:mysql://localhost:" + DBPort + "/" + DBName + "?useSSL=false";
	private String DBUser = "myuser";
	private String DBPass = "1234";

	private final static Logger logger = Logger.getLogger(DBConnection.class.getName());

	private static Connection conn = null;
	private static Statement stmt = null;

	/**
	 * Constructor to create DB Connection
	 */
	private DBConnection() {

		try {
			Class.forName(DBDriver);

			logger.log(Level.INFO, "Connecting to database...");
			conn = DriverManager.getConnection(DBUrl, DBUser, DBPass);
			logger.log(Level.INFO, "Connection to database made!");

			logger.log(Level.INFO, "Creating Statement...");
			stmt = conn.createStatement();
			logger.log(Level.INFO, "Statement created!");

		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQLException - Connection Error Or Statement Creation Fault: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			logger.log(Level.WARNING, "ClassNotFoundException - Driver Error: " + e.getMessage());
		}
	}

	// All methods might be returning something depending on its purpose

	// All methods will either return something or throw an exception for errors
	// depending how we want to deal with the error
	// For if we want to how an error somewhere we will need to throw an error,
	// but some errors can be dealt within this class

	public static void Login(String userName, char[] password) {
		// Attempts to confirm login details
		// Returns account type, or null for an error
	}

	public static void Register(String userName, char[] pass, String name, String email) {
		// Attempts to register a new user
		// Returns if failed or not
	}

	/**
	 * 
	 * Retrives movie object from DB
	 * 
	 * @return Vector with Movie Objects
	 */
	public Vector<Movie> getMovies() {
		// Returns a vector of currant movies showing with their posters and
		// descriptions

		String sql = "SELECT * FROM movies";
		ResultSet rs = null;
		try {//creates ResultSet
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Vector<Movie> movies = new Vector<Movie>();

		try {
			while (rs.next()) {//converts Byte array back into object
				ByteArrayInputStream bais;
				ObjectInputStream ins;

				bais = new ByteArrayInputStream(rs.getBytes("movieObject"));
				ins = new ObjectInputStream(bais);

				Movie movie = (Movie) ins.readObject();//adds object to vector
				movies.add(movie);
				ins.close();
			}
		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQLException - ResultSet Error: " + e.getMessage());
		} catch (IOException e) {
			logger.log(Level.WARNING, "IOException - ObjectInputStream Error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			logger.log(Level.WARNING, "ClassNotFoundException - ObjectInputStream/Database error: " + e.getMessage());
		}

		return movies; // returns vector

	}
	
	/**
	 * 
	 * Drop currant table to replace with new updated Movie objects
	 * Takes Vector of Movie Objects and stores them into new DB table
	 * Replaces currant one to prevent multiple entries in DB
	 * 
	 * @param movies Vector Of Movie Objects
	 */
	public void setMovies(Vector<Movie> movies) {
		// Attempts to set what movies are currently showing, set the poster for
		// the movie, and the description
		
		PreparedStatement ps = null;
		
		try {//creates new table, and drops if it already exists to prevent multiple entries in table of the same movie
			ps = conn.prepareStatement("DROP TABLE IF EXISTS movies;");
			ps.executeUpdate();
			ps = conn.prepareStatement("CREATE TABLE movies (id int(10) unsigned NOT NULL AUTO_INCREMENT, movieObject longblob, PRIMARY KEY (id));");
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQLException - Table Clearance and Creation: " + e.getMessage());
		}

		for (int i = 0; i < movies.size(); i++) {
			try {//inserts movie object(s) in table
				String sql = "INSERT INTO movies (movieObject) values(?)";
				ps = conn.prepareStatement(sql);
				ps.setObject(1, movies.elementAt(i));
				ps.executeUpdate();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "SQLException - PreparedStatement Error: " + e.getMessage());
			}
		}
	}

	public static void getTimes(String movie) {
		// Attempts to get the times for selected movie
	}

	public static void setTimes(String movie, Date times) {
		// Attempts to set the times for selected movie
	}

	public static void getSeats(String movie, Date time) {
		// Returns to the seating data for a movie at a certain date
	}

	public static void setSeat(int row, int col, int state) {
		// Attempts to set the state of a selected seat
		// i.e. take or not
	}

	// for testing of object storage and retrieval
	public static void main(String[] args) {
		DBConnection conn = new DBConnection();
		Vector<Movie> moviesSet = new Vector<Movie>();
		moviesSet.add(new Movie("SuperMan-Update-Test", new ImageIcon("images/the_hobbit1.jpg"), "Test Movie"));

		conn.setMovies(moviesSet);
		Vector<Movie> moviesGet = conn.getMovies();

		for (int i = 0; i < moviesGet.size(); i++) {
			moviesGet.elementAt(i).printAllDev();
		}
	}
}
