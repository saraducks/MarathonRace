package demofinal;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * This class reads from derby database and inherits ThreadRunner class to run threads
 * @author saraducks
 */
public class DerbyRaceReader extends ThreadRunner {
	//The PreparedStatement and Connection are initialized to null
	static java.sql.PreparedStatement ps = null;
	static Connection connection = null;
	
	/**
	 * constructor initialize super class
	 */
	
	DerbyRaceReader() {
		super(null, 0, 0);
		}
	
	/**
	 * try block tries to connect to derby database else catch block will handle SQLException
	 * @return connection - to derby database
	 */
	
	public static Connection createConnection(){
		String url = "jdbc:derby:Finaldb";
		String username = "";
		String password = "";
	
		try{
			String dbDirectory = "resources";
	        System.setProperty("derby.system.home", dbDirectory);
		    connection = DriverManager.getConnection(url,username,password);
	        
		    return connection;
	        }
		catch(SQLException e) {
			e.printStackTrace();
			
			return null;
			}
		}
	
	/**
	 * This method calls createConnection method and PreparedStatement contains sql query
	 * ResultSet will execute the query
	 * This method will create the ArrayList and pass it to super class 
	 * @return thread objects
	 */
	
	public ArrayList<ThreadRunner> getrunnerlist(){
		ArrayList<ThreadRunner> tr = new ArrayList<>();
		try{
			createConnection();
			
			java.sql.PreparedStatement ps = connection.prepareStatement("select * from race");
	        ResultSet rs = ps.executeQuery();
	        ResultSetMetaData rsmd = rs.getMetaData();
	        int numberofcols = rsmd.getColumnCount();
	        
	        while(rs.next()){
	        	String name = rs.getString("Name");
			    int speed = rs.getInt("Speed");
			    int restvalue = rs.getInt("Restvalue");
			    ThreadRunner race = new ThreadRunner(name,speed,restvalue);
			    tr.add(race);
			    }
	        shutdown();
	        }
		catch(SQLException e)
		{
			e.printStackTrace();
		    shutdown();
		    return null;
		    }
		return tr;
		}
	
	/**
	 * It will close the connection to database
	 */
	
	private static void shutdown() {
		try{
			if(ps != null){
				ps.close();
				}
			if(connection != null){
				DriverManager.getConnection("jdbc:derby:BineetDB;shutdown=true");
				connection.close();
			}
		}
		catch(SQLException sqlException){}
		}
	}