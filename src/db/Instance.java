package db;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

/*import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import java.rmi.RemoteException;
//import java.rmi.registry.LocateRegistry;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
//import java.util.Properties;
*/
import javax.naming.Context;
import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;



public class Instance {

	public static Connection returnConnection()
	{		
			Context envContext = null;
			Connection con = null;
			try
			{
				envContext = new InitialContext();
	            Context initContext  = (Context)envContext.lookup("java:/comp/env");
	            DataSource ds = (DataSource)initContext.lookup("jdbc/testDB");
	            //DataSource ds = (DataSource)envContext.lookup("java:/comp/env/jdbc/testDB");
	            con =  ds.getConnection();
	        
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("In returnConnection @ signin!");
			}
			return con;		
	}
}
