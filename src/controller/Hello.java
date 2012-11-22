package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import java.rmi.RemoteException;
//import java.rmi.registry.LocateRegistry;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
//import java.util.Properties;

//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.ConnectionPoolDataSource;
//import javax.sql.DataSource;



//import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
//import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * Servlet implementation class for Servlet: Hello
 *
 */
 public class Hello extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Hello() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		out.println("Hello World!");
//		Context envContext = null;
		try
		{
            Connection con =  db.Instance.returnConnection();            
            Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from FEEDBACK.COMMENTS");
			for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
			      out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
			    }
			out.println("Hello World!");
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("exception in hello!");
		}
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}   	  	    
}