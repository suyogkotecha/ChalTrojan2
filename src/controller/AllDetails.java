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

/**
 * Servlet implementation class for Servlet: AllDetails
 *
 */
 public class AllDetails extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public AllDetails() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String handle = request.getParameter("handle");
		try
		{
			String query1 = "select tripName,idtrip_owner,destName, tripDate, tripPwd, active, destLang,destLongi from feedback.trip_owner where owner='"+handle+"' and active = 'Y'"; 
			Connection con = db.Instance.returnConnection();
			Statement statement = con.createStatement(); 
			ResultSet resultSet = statement.executeQuery(query1);
			
			String query2 = "select tripName,idtrip_owner,destName, tripDate, tripPwd, active, destLang,destLongi from feedback.trip_owner where idtrip_owner in (select tripid from trip where handle ='"+handle+"') and active = 'Y'; ";
			ResultSet rs2 = con.createStatement().executeQuery(query2);
			out.println(CreateXML.generateTripAllDetailsXML(resultSet, rs2));
			con.close();
		}
		catch(SQLException e)
		{
			out.println(CreateXML.generateXML(0, "DbProb","AllTrips"));
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