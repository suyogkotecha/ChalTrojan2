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
 * Servlet implementation class for Servlet: TripUpdate
 *
 */
 public class TripUpdate extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public TripUpdate() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String handle = request.getParameter("handle");
		String tripName = request.getParameter("tripName");
		String dest = request.getParameter("destName");
		String destLang = request.getParameter("destLang");
		String destLongi = request.getParameter("destLongi");
		String date = request.getParameter("tripDate");
		String pwd = request.getParameter("pwd");
		String active = request.getParameter("active");
		String newName = request.getParameter("newName");
		String query = "update feedback.trip_owner set destName='"+dest+"', destLang='"+destLang+"', destLongi='"+destLongi+"', tripDate='"+date+"', active='"+active+"', tripPwd='"+pwd+"', tripName='"+newName+"' where owner='"+handle+"' and tripName='"+tripName+"'";
		try
		{
			Connection con = db.Instance.returnConnection();
			Statement statement = con.createStatement();			
			int update = statement.executeUpdate(query);
			con.close();
			if(update>0)
				out.println(CreateXML.generateXML(1, "TripUpdated","TripUpdate"));
			else
				out.println(CreateXML.generateXML(0, "TripCouldNotbeUpdated","TripUpdate"));
		}
		catch(SQLException e)
		{
			out.println(CreateXML.generateXML(0, "DbProb","TripUpdate"));
			e.printStackTrace();
		}
		catch(Exception e)
		{
			out.println(CreateXML.generateXML(0, "Problem","TripUpdate"));
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