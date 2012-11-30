package db;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.Date;

public class Connect {

	/**
	 * @param args
	 */
	private Connection connect = null;	
	Connect(){
		    try {
		      // This will load the MySQL driver, each DB has its own driver
		      Class.forName("com.mysql.jdbc.Driver");
		      // Setup the connection with the DB
		      connect = DriverManager
		          .getConnection(Constants.DBURL
		              + "user="+Constants.UNAME+"&password="+Constants.PWD);

		      // Statements allow to issue SQL queries to the database
		    }
		    catch(Exception e)
		    {
		    	System.out.println("Problem with db connection");
		    	e.printStackTrace();
		    }		    
	 }
	 public void makeQuery(String query)
	 {
		 try {
			Statement statement = connect.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
			      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
			    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		 		 
	 }
	 public void close()
	 {
		 try {
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 public static void main(String args[])
	 {
		 Connect conn = new Connect();
		 conn.makeQuery("select * from FEEDBACK.COMMENTS");
		 conn.close();
	 }
	 
}
