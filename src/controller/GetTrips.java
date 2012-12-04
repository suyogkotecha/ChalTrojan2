package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.mysql.jdbc.ResultSetMetaData;

/**
 * Servlet implementation class for Servlet: GetTrips
 *
 */
 public class GetTrips extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public GetTrips() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String handle = request.getParameter("handle");
		String query ="select tripName,idtrip_owner from feedback.trip_owner where owner='"+handle+"' and active = 'Y'";
		//out.println(query);
		try
		{
			Connection con = db.Instance.returnConnection();
			Statement statement = con.createStatement(); 
			ResultSet resultSet = statement.executeQuery(query);				
			out.println(CreateXML.getTripsXML(resultSet,1));
			con.close();
		}
		catch(SQLException e)
		{
			out.println(CreateXML.generateXML(0, "DbProb","GetTrips"));
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}   	  	    
}