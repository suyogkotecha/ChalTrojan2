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
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.Properties;


//import javax.naming.NamingException;
//import javax.sql.ConnectionPoolDataSource;

import com.mysql.jdbc.ResultSetMetaData;




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
		
		int colCount = 0;
		int update = 0;
		try
		{
			out.println("in handle");
			
			Connection con = db.Instance.returnConnection();
			Statement statement = con.createStatement();
			String query = "select count(*) from FEEDBACK.username where twitter_handle='"+handle+"' AND email='"+email+"'";			
			
			ResultSet resultSet = statement.executeQuery(query);
			ResultSetMetaData meta = (ResultSetMetaData) resultSet.getMetaData();
			int cols = meta.getColumnCount();
			resultSet.next();
			
			for  (int i = 1; i<= cols; i++){
				colCount = Integer.parseInt(resultSet.getString(i));				
			}						
			if(colCount != 0)//User present, return success
			{
				out.println(generateXML(1, "success"));
			}
			else //insert user
			{				
				query = "insert into username (email, twitter_handle) values('"+email+"','"+handle+"');";
				try
				{
					update =statement.executeUpdate(query);
					if(update > 0)
					{						
						out.println(generateXML(1, "success"));
					}
				}
				catch(SQLException  e)
				{
					if(e.getErrorCode() == 1062);					
					String xml = generateXML(0,"HandleAlreadyPresent");
					out.println(xml);
				}
				catch(Exception e)
				{
					String xml = generateXML(0,"UnsuccessfulLogin");
					out.println(xml);
				}
			}
			con.close();
		}	
		catch(Exception e)
		{
			//e.printStackTrace();
			System.out.println("In doGet SignIn");
			out.println(generateXML(0, "UnsuccessfulLogin"));
		}
	}  	
	public String generateXML(int success, String message)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("<root><header><success>").append(success).append("</success><message>");
		sb.append(message).append("</message><from>").append("SignIn").append("</from></header><data></data></root>");
		return sb.toString();
	}
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
}