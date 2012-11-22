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

import javax.naming.Context;
import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;



//import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
//import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


/**
 * Servlet implementation class for Servlet: SignIn
 *
 */
 public class SignIn extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public SignIn() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		String handle = request.getParameter("handle");		
		try
		{
			Connection con = db.Instance.returnConnection();
			Statement statement = con.createStatement();
			String query = "select count(*) from FEEDBACK.username where twitter_handle='"+handle+"' AND email='"+email+"'";
			out.println(query);
			
			ResultSet resultSet = statement.executeQuery(query);
			
			for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
			      
			    }
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("In doGet SignIn");
		}
		
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
}